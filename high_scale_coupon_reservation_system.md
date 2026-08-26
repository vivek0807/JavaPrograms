# High-Scale Coupon Reservation System

## 1. Problem Statement

Design a globally accessible coupon reservation system for a flash-sale scenario where:

- A limited number of coupons are available for an item or campaign.
- Many users may attempt to claim coupons concurrently.
- The system must never issue more discounts than the configured coupon inventory.
- A claimed coupon is only **reserved** initially.
- If the user does not complete checkout within **15 minutes**, the coupon must become available again.
- The system must tolerate retries, crashes, duplicate requests, multiple application instances, multiple expiry workers, and high request volume.

The most important invariant is:

```text
available >= 0
```

And for each inventory bucket:

```text
available + reserved + redeemed = allocated_inventory
```

subject to explicit cancellation/refund rules.

---

## 2. Example Scale Assumptions

Assume the following peak traffic:

```text
Coupon claim requests       : 50,000 requests/sec
Checkout requests           : 10,000 requests/sec
Successful reservations     : 5,000 reservations/sec
Reservation TTL             : 15 minutes
Total campaign inventory    : 10,000,000 coupons
```

Maximum simultaneously active reservations at 5,000 reservations/sec:

```text
5,000 x 60 x 15
= 4,500,000 active reservations
```

Therefore the system should comfortably support millions of active reservations and tens of thousands of concurrent requests per second.

---

## 3. High-Level Architecture

```text
                            +----------------+
                            |   Mobile/Web   |
                            +-------+--------+
                                    |
                                    v
                            +----------------+
                            |  API Gateway   |
                            | Rate Limiting  |
                            +-------+--------+
                                    |
                                    v
                    +-------------------------------+
                    | Coupon Reservation Service    |
                    | Java + Spring Boot             |
                    +----------+-----------+---------+
                               |           |
                     +---------+           +----------+
                     |                                |
                     v                                v
              +-------------+                 +---------------+
              |    Redis    |                 |  PostgreSQL   |
              |             |                 |               |
              | Idempotency |                 | Inventory     |
              | Expiry ZSET |                 | Reservations  |
              | Read cache  |                 | Outbox        |
              +-------------+                 +-------+-------+
                                                      |
                                                      v
                                                   Kafka
                                                      |
                              +-----------------------+-------------------+
                              |                       |                   |
                              v                       v                   v
                         Analytics              Notifications          Audit
```

### Source of Truth

PostgreSQL is the authoritative source of truth for coupon inventory and reservation state.

Redis is used for:

- fast idempotency lookups,
- expiry indexing,
- rate limiting,
- optionally cached campaign metadata or approximate availability indicators.

Redis should **not** be treated as the sole source of truth for financially important coupon availability unless the system is explicitly designed around Redis durability and failover guarantees.

---

## 4. Coupon State Model

```text
                         reserve
        AVAILABLE ----------------------> RESERVED
             ^                               |
             |                               |
             | timeout / cancel              | successful checkout
             |                               v
             +--------------------------- REDEEMED
```

Typical reservation states:

```text
RESERVED
REDEEMED
EXPIRED
CANCELLED
```

Transitions must be implemented as conditional atomic state changes.

---

## 5. Why a Normal Read-Check-Write Is Incorrect

Incorrect implementation:

```java
int available = repository.getAvailableCoupons();

if (available > 0) {
    repository.setAvailableCoupons(available - 1);
}
```

Suppose only one coupon remains:

```text
available = 1
```

Two application instances execute concurrently:

```text
Server A reads 1
Server B reads 1

Server A writes 0
Server B writes 0
```

Both users may receive a coupon even though only one existed.

This is a classic **check-then-act race condition**.

---

## 6. Atomic Conditional Update

Instead, make checking and decrementing one database operation:

```sql
UPDATE coupon_inventory_bucket
SET available = available - 1,
    reserved  = reserved + 1
WHERE campaign_id = :campaignId
  AND bucket_id   = :bucketId
  AND available > 0;
```

Application code checks the affected row count:

```text
updatedRows = 1 -> reservation succeeded
updatedRows = 0 -> bucket had no inventory
```

If 100 concurrent requests hit one remaining coupon:

```text
                       available = 1
                            |
       +--------------------+--------------------+
       |                    |                    |
     Request 1            Request 2          Request 100
       |                    |                    |
       +--------------------+--------------------+
                            |
                     PostgreSQL concurrency
                            |
                         one wins
                            |
                     available = 0
                            |
                 remaining WHERE condition
                     evaluates false

Result:
1 request  -> RESERVED
99 requests -> SOLD_OUT
```

The database provides the serialization needed for conflicting row updates.

---

## 7. Avoiding the Hot-Row Problem

A single campaign row can itself become a contention hotspot:

```text
50,000 requests/sec
        |
        v
campaign_id = 123
available = 1,000,000
```

Every request modifies the same physical row.

### Improvement: Inventory Bucketing

Split inventory across multiple logical buckets:

```text
campaign_id | bucket_id | available | reserved | redeemed
----------------------------------------------------------
123         | 0         | 3907      | 0        | 0
123         | 1         | 3906      | 0        | 0
123         | 2         | 3907      | 0        | 0
...
123         | 255       | 3906      | 0        | 0
```

With 256 buckets:

```text
50,000 / 256 ~= 195 requests/sec per bucket
```

Conceptually:

```text
                         Campaign 123
                              |
             +----------------+----------------+
             |                |                |
             v                v                v
          Bucket 0         Bucket 1         Bucket 2
             |                |                |
         requests         requests         requests
```

### Bucket Selection

Example:

```java
int bucket = Math.floorMod(userId.hashCode(), 256);
```

If the selected bucket is empty, retry a small bounded number of alternate buckets.

Do not scan all buckets synchronously for every request.

---

## 8. Database Model

### coupon_campaign

```text
campaign_id
item_id
start_time
end_time
reservation_duration_seconds
status
created_at
updated_at
```

### coupon_inventory_bucket

```text
campaign_id
bucket_id
available
reserved
redeemed
```

Primary key:

```text
(campaign_id, bucket_id)
```

### coupon_reservation

```text
reservation_id
campaign_id
bucket_id
user_id
status
created_at
expires_at
redeemed_at
cancelled_at
idempotency_key
order_id
```

Recommended constraints:

```sql
UNIQUE (idempotency_key)
```

If business rules allow only one coupon per user per campaign:

```sql
UNIQUE (campaign_id, user_id)
```

### outbox_event

```text
event_id
aggregate_id
aggregate_type
event_type
payload
created_at
published_at
status
```

---

## 9. Reservation Transaction

Inventory decrement and reservation creation must be part of one database transaction.

```text
BEGIN

1. UPDATE inventory bucket
   available--
   reserved++

2. INSERT coupon_reservation

3. INSERT outbox_event

COMMIT
```

Failure before commit:

```text
ROLLBACK
```

This prevents inventory from being permanently lost if the application crashes after decrementing but before creating the reservation record.

### Spring Service Sketch

```java
@Service
@RequiredArgsConstructor
public class CouponReservationService {

    private final InventoryRepository inventoryRepository;
    private final ReservationRepository reservationRepository;
    private final OutboxRepository outboxRepository;
    private final BucketSelector bucketSelector;

    @Transactional
    public ReservationResponse reserve(
            long campaignId,
            long userId,
            String idempotencyKey) {

        var existing = reservationRepository
                .findByIdempotencyKey(idempotencyKey);

        if (existing.isPresent()) {
            return ReservationResponse.from(existing.get());
        }

        int bucket = bucketSelector.select(campaignId, userId);

        int updatedRows = inventoryRepository.tryReserve(
                campaignId,
                bucket
        );

        if (updatedRows == 0) {
            throw new CouponUnavailableException();
        }

        CouponReservation reservation = CouponReservation.reserve(
                campaignId,
                bucket,
                userId,
                Instant.now().plus(15, ChronoUnit.MINUTES),
                idempotencyKey
        );

        reservationRepository.save(reservation);
        outboxRepository.save(CouponReservedEvent.from(reservation));

        return ReservationResponse.from(reservation);
    }
}
```

---

## 10. Checkout vs Expiry Race

A reservation can expire at the same moment the customer attempts checkout.

Example:

```text
expires_at = 10:15:00
```

At 10:15:00:

```text
Checkout thread -> redeem()
Expiry worker    -> expire()
```

Do not perform a Java-side read followed by a write.

### Redeem

```sql
UPDATE coupon_reservation
SET status      = 'REDEEMED',
    redeemed_at = NOW(),
    order_id    = :orderId
WHERE reservation_id = :reservationId
  AND status = 'RESERVED'
  AND expires_at > NOW();
```

### Expire

```sql
UPDATE coupon_reservation
SET status = 'EXPIRED'
WHERE reservation_id = :reservationId
  AND status = 'RESERVED'
  AND expires_at <= NOW();
```

Exactly one transition can win.

```text
                          RESERVED
                          /      \
                         /        \
                    checkout     expiry
                       |            |
               conditional      conditional
                  update            update
                       \            /
                        \          /
                    exactly one wins
```

The losing update affects zero rows.

---

## 11. Expiration Design

Do not create one timer/thread for every reservation.

With millions of reservations this is expensive and operationally fragile.

Use two layers:

1. Redis Sorted Set as a fast expiry index.
2. PostgreSQL reconciliation worker as the correctness fallback.

### Redis Sorted Set

```text
Key: coupon:reservation:expiry

score              member
---------------------------------
1724678100         reservation-A
1724678115         reservation-B
1724678122         reservation-C
```

The score is the reservation expiration timestamp.

Workers periodically fetch members whose score is less than or equal to current time.

### Database Reconciliation

```sql
SELECT reservation_id
FROM coupon_reservation
WHERE status = 'RESERVED'
  AND expires_at <= NOW()
ORDER BY expires_at
LIMIT 1000
FOR UPDATE SKIP LOCKED;
```

Multiple workers can process expiration concurrently:

```text
                 Expired Reservations
                          |
          +---------------+---------------+
          |               |               |
          v               v               v
       Worker 1        Worker 2        Worker 3
       1-1000          1001-2000       2001-3000

       FOR UPDATE SKIP LOCKED
```

This avoids duplicate work and prevents workers from waiting on rows already claimed by another worker.

---

## 12. Idempotency

Clients retry requests due to network errors, timeouts, mobile reconnects, gateway retries, and user double-clicks.

Request:

```http
POST /v1/campaigns/123/reservations
Idempotency-Key: abc-123
```

Store:

```text
abc-123 -> reservation UUID123
```

Repeated calls using the same key return the existing reservation rather than consuming another coupon.

Recommended DB protection:

```sql
UNIQUE (idempotency_key)
```

Redis can be used for a fast first-level idempotency lookup, but the DB unique constraint remains the final correctness guard.

---

# 13. Caching Strategy

Caching must be designed around correctness.

A coupon system should not allow stale cache values to decide whether a financially valuable coupon can be issued.

## 13.1 What Should Be Cached?

Good Redis candidates:

```text
Campaign metadata
Campaign active/inactive status
Item -> campaign mapping
Idempotency result
Reservation expiry index
Rate-limit counters
Approximate availability for UI display
```

Avoid using a normal cache entry such as:

```text
campaign:123:available = 500
```

as the final authority for reservation decisions because the value can become stale under concurrency.

The authoritative reservation decision should still be performed by the transactional inventory operation.

---

## 13.2 Chosen Cache Pattern: Cache-Aside for Read-Mostly Data

For campaign metadata and read-heavy information, use **Cache-Aside / Lazy Loading**.

Flow:

```text
Request
   |
   v
Redis lookup
   |
   +---- HIT ----> return cached data
   |
   +---- MISS
           |
           v
       PostgreSQL
           |
           v
       populate Redis
           |
           v
         return
```

Pseudo-code:

```java
Campaign campaign = redis.get(key);

if (campaign == null) {
    campaign = campaignRepository.findById(id);
    redis.set(key, campaign, ttl);
}

return campaign;
```

### Why Cache-Aside Here?

- Simple operational model.
- Cache failure does not make PostgreSQL unavailable.
- Data is loaded only when required.
- Appropriate for campaign metadata that changes relatively infrequently.

---

## 13.3 Write-Through vs Write-Behind vs Write-Around

### Write-Through

```text
Application
    |
    v
Cache
    |
    v
Database
```

Both cache and DB are updated synchronously.

Pros:

- cache is generally fresh after writes.

Cons:

- write latency increases,
- dual-write consistency must be carefully handled,
- Redis should not become part of the critical inventory transaction path unnecessarily.

### Write-Behind / Write-Back

```text
Application
    |
    v
Cache
    |
    +---- immediate response
    |
    +---- asynchronous DB write later
```

This is **not recommended for authoritative coupon inventory**.

If Redis acknowledges the decrement but the asynchronous DB write is lost, inventory can diverge.

For financially important coupon accounting, this creates unacceptable correctness risk unless a much more sophisticated durability protocol is implemented.

### Write-Around

```text
Application
    |
    +----> Database

Cache populated later on read
```

This combines naturally with cache-aside.

### Chosen Approach

For the design in this document:

```text
Authoritative inventory writes:
    Write directly to PostgreSQL

Campaign metadata reads:
    Cache-Aside

Campaign metadata writes:
    DB-first + invalidate cache

Idempotency:
    Redis fast path + DB unique constraint

Reservation expiry:
    Redis Sorted Set as derived scheduling/index state
    PostgreSQL remains authoritative
```

Therefore the primary caching model is:

> **Cache-Aside + DB-first write/invalidation, not Write-Behind.**

For metadata updates:

```text
BEGIN DB TRANSACTION
      |
      v
Update PostgreSQL
      |
      v
COMMIT
      |
      v
Invalidate Redis key
```

Next read repopulates the cache.

This is often safer than attempting a synchronous DB + cache dual write.

---

## 13.4 Why Not Use Write-Through for Coupon Count?

Suppose:

```text
Redis available = 1
PostgreSQL available = 1
```

A write-through design would need to ensure both systems are updated atomically.

But Redis and PostgreSQL do not naturally share one ACID transaction.

Potential failure:

```text
Redis:      1 -> 0   SUCCESS
PostgreSQL: update   FAIL
```

or:

```text
PostgreSQL: 1 -> 0   SUCCESS
Application crashes before Redis update
```

Now values disagree.

Because the DB can already perform the concurrency-sensitive decrement atomically, the safest design is to avoid making a cached availability count authoritative.

The UI may show an approximate cache-based message such as:

```text
Coupons still available
Low stock
Sold out
```

but the reservation endpoint must always perform the authoritative transaction.

---

# 14. Redis Eviction and TTL Policy

Different Redis data serves different purposes and should not all use the same eviction behavior.

## 14.1 Campaign Metadata

Example key:

```text
campaign:123
```

Recommended TTL:

```text
30 seconds to 5 minutes
```

depending on how quickly campaign changes must propagate.

Recommended eviction behavior:

```text
allkeys-lru
```

or, for workloads where only TTL-managed keys should be evictable:

```text
volatile-lru
```

### Practical Recommendation

For a dedicated metadata cache Redis cluster:

```text
maxmemory-policy allkeys-lru
```

is simple and effective.

Frequently accessed campaign metadata stays hot, while colder entries are evicted under memory pressure.

---

## 14.2 Idempotency Keys

Example:

```text
idempotency:abc-123 -> reservation UUID123
```

TTL should be based on the retry horizon.

Example:

```text
TTL = 30 minutes to 24 hours
```

The DB uniqueness constraint still protects correctness after the Redis key expires.

Idempotency cache entries may be safely evicted because PostgreSQL remains authoritative.

---

## 14.3 Reservation Expiry Sorted Set

Example:

```text
coupon:reservation:expiry
```

This is not an ordinary cache.

It is derived scheduling/index state.

The system should preferably place this on a Redis instance/cluster configured so memory pressure does not silently evict required expiry entries.

Recommended policy for a dedicated expiry Redis workload:

```text
noeviction
```

Why?

If an expiry entry is evicted early, the fast expiry path may miss the reservation.

Correctness is still protected by the PostgreSQL reconciliation worker, but uncontrolled eviction increases delayed coupon returns and operational load.

Therefore:

```text
Expiry ZSET -> noeviction preferred
```

Provision sufficient Redis memory and alert before memory reaches the configured limit.

---

## 14.4 Rate-Limit Counters

Rate-limit keys should always have TTLs.

Example:

```text
rate-limit:user:123
TTL = 1 second / 1 minute depending on algorithm
```

These are naturally short-lived and can be safely recreated.

---

## 14.5 Recommended Redis Separation

For a high-scale production system, avoid mixing every Redis workload into one undifferentiated cluster.

Example:

```text
Redis Cluster A
    Campaign metadata cache
    Idempotency fast-path
    Policy: allkeys-lru

Redis Cluster B
    Reservation expiry sorted sets
    Policy: noeviction

Redis Cluster C
    Rate limiting
    Short TTL keys
```

This prevents a burst of metadata cache entries from evicting reservation expiry information.

---

# 15. Redis Usage Summary

| Use Case | Redis Role | Cache Pattern | TTL | Eviction |
|---|---|---|---|---|
| Campaign metadata | Read cache | Cache-Aside | 30 sec-5 min | allkeys-lru |
| Approx. availability | UI optimization only | Cache-Aside / event refreshed | very short | allkeys-lru |
| Idempotency | Fast lookup | Write on reservation success | 30 min-24 h | evictable |
| Expiry scheduling | Derived timer index | Explicit write/remove | reservation lifetime | noeviction preferred |
| Rate limiting | Counter store | direct Redis operation | seconds/minutes | TTL driven |

---

## 16. Kafka's Role

Kafka should not determine whether inventory exists because the reservation API needs a synchronous answer.

Kafka carries domain events after authoritative state changes:

```text
CouponReserved
CouponRedeemed
CouponExpired
CouponCancelled
```

Example event:

```json
{
  "eventId": "event-92828",
  "reservationId": "UUID123",
  "campaignId": 123,
  "userId": 928282,
  "eventType": "COUPON_RESERVED"
}
```

Consumers:

```text
                          Kafka
                            |
          +-----------------+-----------------+
          |                 |                 |
          v                 v                 v
      Analytics        Notification         Audit
```

---

## 17. Transactional Outbox Pattern

Without an outbox:

```text
DB commit succeeds
      |
Application crashes
      |
Kafka publish never happens
```

Use:

```text
BEGIN

UPDATE inventory
INSERT reservation
INSERT outbox_event

COMMIT
```

A separate publisher sends outbox events to Kafka.

```text
PostgreSQL outbox
       |
       v
Outbox Publisher
       |
       v
     Kafka
```

The publisher itself must be retryable and consumers should be idempotent because duplicate Kafka delivery is still possible.

---

# 18. Global Traffic and Multi-Region Writes

Users may come from:

```text
US
Europe
India
Asia-Pacific
```

If every region independently owns the full inventory count, overselling becomes possible.

Incorrect:

```text
US DB      available = 1
India DB   available = 1
EU DB      available = 1
```

Three simultaneous writes can sell one coupon three times before replication catches up.

## Preferred Approach: Single Writer / Home Region per Campaign

Example:

```text
Campaign 123 -> US-East
Campaign 456 -> Europe
Campaign 789 -> Asia
```

Users enter through nearby gateways, but inventory-changing requests route to the campaign owner.

```text
India --------+
Europe -------+----> Campaign 123 owner ----> PostgreSQL
US -----------+
```

This avoids cross-region split-brain inventory decisions.

Read-only metadata can still be replicated or cached globally.

---

# 19. Why Not One Distributed Lock?

Possible but undesirable:

```text
Request 1 ----+
Request 2 ----+
Request 3 ----+----> LOCK campaign-123 ----> inventory
...
Request 50K --+
```

One global campaign lock serializes all traffic and becomes a bottleneck.

Prefer:

```text
Atomic conditional updates
+
Inventory buckets
```

which preserves correctness while permitting much more parallelism.

---

# 20. Optimistic vs Pessimistic Locking Here

## Optimistic Locking

Example:

```java
@Version
private long version;
```

Works well when collisions are uncommon.

Flash-sale counters are the opposite: conflicts are common.

Thousands of requests can repeatedly fail and retry, causing a retry storm.

Therefore optimistic locking is not the preferred primary inventory strategy.

## Pessimistic Locking

Example:

```sql
SELECT *
FROM coupon_inventory_bucket
WHERE campaign_id = :id
FOR UPDATE;
```

Correct but concurrent callers wait for the lock.

For moderate traffic this can work.

For a hot flash-sale path, conditional atomic updates plus bucketing generally scale better.

---

# 21. API Design

## Reserve Coupon

```http
POST /v1/campaigns/{campaignId}/reservations
Idempotency-Key: 8293-ABC
```

Response:

```json
{
  "reservationId": "f891...",
  "status": "RESERVED",
  "expiresAt": "2026-08-26T14:15:00Z"
}
```

## Redeem

```http
POST /v1/reservations/{reservationId}/redeem
```

Body:

```json
{
  "orderId": "ORD-81927"
}
```

## Cancel

```http
DELETE /v1/reservations/{reservationId}
```

Cancellation transition:

```text
RESERVED -> CANCELLED

reserved--
available++
```

---

# 22. Storage Estimation

Assume roughly 1 KB effective storage per reservation after row and index overhead.

At:

```text
10,000,000 reservations/day
```

Approximate storage:

```text
10M x 1 KB ~= 10 GB/day
```

30 days:

```text
~300 GB
```

before replicas, backups, WAL, and exact index overhead.

Recommended approach:

- partition `coupon_reservation` by date,
- keep hot recent data in PostgreSQL,
- archive historical data to object storage/data lake when operationally appropriate.

### PostgreSQL Partitioning Example

```text
coupon_reservation
    |
    +-- coupon_reservation_2026_08
    +-- coupon_reservation_2026_09
    +-- coupon_reservation_2026_10
```

Daily partitions may be justified only at very high volume; monthly partitions are easier operationally for moderate-to-high workloads.

---

# 23. Redis Memory Estimate

Assume:

```text
4.5 million active reservations
```

If a Redis expiry/index entry averages approximately 500 bytes after key, member, data-structure, allocator, and metadata overhead:

```text
4.5M x 500 bytes ~= 2.25 GB
```

This is only a sizing approximation.

Actual Redis sizing must include:

- fragmentation,
- replication,
- persistence buffers,
- failover headroom,
- sorted-set encoding overhead,
- key/member sizes,
- peak rather than average active reservations.

Load testing and `MEMORY USAGE` measurements should be used before production sizing.

---

# 24. Failure Handling Matrix

| Failure Scenario | Protection |
|---|---|
| Two users claim the last coupon | Atomic conditional DB update |
| One inventory row becomes hot | Inventory bucketing |
| Same request retried | Idempotency key |
| Same user claims concurrently | Unique DB constraint |
| App crashes after decrement | Same DB transaction |
| DB commits but Kafka publish fails | Transactional outbox |
| Customer abandons checkout | 15-minute expiration |
| Redis expiry entry lost | PostgreSQL reconciliation |
| Multiple expiry workers | `FOR UPDATE SKIP LOCKED` |
| Checkout races expiry | Conditional state update |
| Kafka delivers twice | Idempotent consumer/event ID |
| Global regions issue same coupon | Single writer/home region |
| Cache is stale | DB remains source of truth |
| Redis unavailable | Fallback to DB where correctness requires it |

---

# 25. Recommended Technology Stack

```text
Backend
    Java
    Spring Boot

Primary transactional storage
    PostgreSQL

Distributed cache / temporary indexes
    Redis Cluster

Messaging
    Kafka

Deployment
    Kubernetes

Observability
    Prometheus
    Grafana
    OpenTelemetry

Load Testing
    k6 or Gatling

Schema Migration
    Flyway
```

---

# 26. Important Metrics

At minimum monitor:

```text
coupon_reservation_requests_total
coupon_reservation_success_total
coupon_reservation_sold_out_total
coupon_reservation_latency
coupon_inventory_update_latency
coupon_inventory_conflict_or_retry_total
coupon_expiration_backlog
coupon_expiration_delay_seconds
coupon_reconciliation_repairs_total
redis_memory_usage
redis_evictions_total
redis_hit_ratio
postgres_active_connections
postgres_lock_wait_time
outbox_pending_events
kafka_publish_failures
```

Critical alerts:

```text
available < 0
```

should theoretically be impossible and must page immediately if detected.

Also alert if:

```text
expired RESERVED rows continue growing
Redis expiry backlog grows continuously
outbox backlog grows continuously
Redis evictions occur on the noeviction expiry cluster
DB lock wait latency spikes
```

---

# 27. End-to-End Reservation Flow

```text
Client
  |
  | POST /campaigns/123/reservations
  | Idempotency-Key: ABC
  v
API Gateway
  |
  v
Coupon Reservation Service
  |
  +--> check Redis idempotency cache
  |        |
  |        +--> hit -> return existing reservation
  |
  +--> BEGIN PostgreSQL transaction
  |        |
  |        +--> atomic inventory decrement
  |        |
  |        +--> insert reservation
  |        |
  |        +--> insert outbox event
  |        |
  |        +--> COMMIT
  |
  +--> write idempotency fast-path entry to Redis
  |
  +--> add reservation to expiry ZSET
  |
  v
Return RESERVED
```

If the Redis post-commit writes fail, the reservation is still correct in PostgreSQL.

The expiry reconciliation worker can recover missing expiry-index entries indirectly by scanning authoritative expired reservations.

---

# 28. End-to-End Expiry Flow

```text
Redis ZSET
   |
   | expired reservation IDs
   v
Expiry Worker
   |
   +--> conditional DB transition
   |        RESERVED -> EXPIRED
   |
   +--> inventory bucket
   |        reserved--
   |        available++
   |
   +--> outbox event
   |
   v
Kafka -> analytics/notifications/audit
```

Fallback:

```text
PostgreSQL reconciliation worker
        |
        +--> find expired RESERVED rows
        +--> FOR UPDATE SKIP LOCKED
        +--> perform same transition
```

---

# 29. Key Architectural Decisions

1. PostgreSQL is authoritative for inventory and reservation state.
2. Inventory reservation uses an atomic conditional `UPDATE`.
3. Inventory is bucketed to avoid a single hot row.
4. Reservation creation and inventory decrement occur in one DB transaction.
5. Every externally retryable operation is idempotent.
6. Reservation expiry is represented as a state transition, not deletion.
7. Redis accelerates expiry detection and read-heavy paths but is not the final correctness authority.
8. PostgreSQL reconciliation repairs missed expiry processing.
9. Kafka handles asynchronous side effects, not synchronous inventory decisions.
10. Transactional Outbox bridges the DB/Kafka consistency gap.
11. Campaign ownership provides a single authoritative writer in multi-region deployment.
12. Cache-Aside is used for read-heavy metadata.
13. Authoritative writes go DB-first; caches are invalidated/refreshed afterward.
14. Write-Behind is avoided for coupon inventory because lost asynchronous persistence could create incorrect balances.
15. Redis expiry structures should preferably run with `noeviction`; normal metadata cache can use LRU-style eviction.

---

# 30. Interview Summary

A concise explanation of the design:

> I would not protect the entire campaign using one distributed lock. Coupon reservation is modeled as an atomic state transition. PostgreSQL performs a conditional inventory decrement using `UPDATE ... WHERE available > 0`, which guarantees the count cannot go below zero. To avoid turning one campaign row into a hotspot, inventory is divided into buckets. Reservation creation and inventory modification occur in the same transaction, and idempotency keys protect against retries. Reservations have a 15-minute expiry tracked efficiently through Redis sorted sets, with PostgreSQL reconciliation as the correctness fallback. Checkout and expiry compete using conditional state transitions so only one can win. Kafka is used for downstream asynchronous events through a transactional outbox. Read-heavy campaign metadata uses cache-aside caching, while authoritative inventory is never based on stale cached counts. In a multi-region deployment, each campaign has one authoritative write region to prevent cross-region overselling.

---

# 31. Final Mental Model

```text
                         HIGH-SCALE COUPON SYSTEM

                    +----------------------------+
                    |     GLOBAL API LAYER       |
                    +-------------+--------------+
                                  |
                                  v
                    +----------------------------+
                    | RESERVATION SERVICE        |
                    |                            |
                    | Idempotency                |
                    | Validation                 |
                    | Bucket Selection           |
                    +------+------+--------------+
                           |      |
                 +---------+      +----------------+
                 |                                 |
                 v                                 v
          +--------------+                  +---------------+
          |    Redis     |                  |  PostgreSQL   |
          |--------------|                  |---------------|
          | Metadata     |                  | Inventory     |
          | Idempotency  |                  | Reservations  |
          | Expiry ZSET  |                  | Outbox        |
          | Rate limits  |                  | Constraints   |
          +------+-------+                  +-------+-------+
                 |                                  |
                 | expiry hints                     | durable events
                 v                                  v
          +--------------+                       Kafka
          | Expiry Worker|                         |
          +------+-------+            +------------+------------+
                 |                    |            |            |
                 +----------------> Analytics  Notify        Audit

Correctness:
    PostgreSQL transactions + atomic conditional state changes

Concurrency:
    row-level serialization + bucketed inventory

Speed:
    Redis + horizontal application scaling

Reliability:
    idempotency + reconciliation + transactional outbox

Global consistency:
    single authoritative writer per campaign
```

---

# 32. Design Patterns Used in This Architecture

The architecture uses a mix of classic object-oriented patterns and distributed-system patterns. The important point is not to add patterns for their own sake; each pattern addresses a specific scalability, correctness, or maintainability problem in the coupon system.

The most relevant patterns are:

1. Repository Pattern
2. Strategy Pattern
3. State Pattern
4. Idempotency Pattern
5. Transactional Outbox Pattern
6. Saga Pattern
7. Cache-Aside Pattern
8. Competing Consumers Pattern
9. Sharding / Partitioning Pattern
10. Single Writer / Ownership Pattern

The following sections show where each pattern fits, which problem it solves, and a representative Java/Spring implementation.

---

# 33. Repository Pattern

## Scenario

The reservation service needs to work with inventory, reservations, and outbox records, but business logic should not contain SQL or persistence-specific code everywhere.

Without a repository abstraction, the service can quickly become tightly coupled to PostgreSQL/JPA details.

## Where it fits

```text
CouponReservationService
        |
        +--> InventoryRepository
        +--> ReservationRepository
        +--> OutboxRepository
```

The service expresses business intent while repositories encapsulate persistence operations.

## Java example

```java
public interface InventoryRepository {

    boolean tryReserve(long campaignId, int bucketId);

    void release(long campaignId, int bucketId);

    void markRedeemed(long campaignId, int bucketId);
}
```

A Spring Data implementation can expose the atomic operation directly:

```java
@Repository
public interface JpaInventoryRepository
        extends JpaRepository<CouponInventoryBucket, CouponInventoryBucketId> {

    @Modifying
    @Query(value = """
        UPDATE coupon_inventory_bucket
        SET available = available - 1,
            reserved = reserved + 1
        WHERE campaign_id = :campaignId
          AND bucket_id = :bucketId
          AND available > 0
        """, nativeQuery = true)
    int reserve(
            @Param("campaignId") long campaignId,
            @Param("bucketId") int bucketId
    );
}
```

Adapter:

```java
@Component
@RequiredArgsConstructor
public class PostgresInventoryRepository implements InventoryRepository {

    private final JpaInventoryRepository repository;

    @Override
    public boolean tryReserve(long campaignId, int bucketId) {
        return repository.reserve(campaignId, bucketId) == 1;
    }

    // release(...) and markRedeemed(...) omitted for brevity
}
```

## Benefit

The business layer depends on a domain-facing interface rather than database details. It also becomes much easier to test `CouponReservationService` using a mocked repository.

---

# 34. Strategy Pattern — Bucket Selection

## Scenario

Inventory is split into multiple buckets to avoid one hot database row. The system needs a way to decide which bucket should receive a reservation attempt.

Different campaigns may eventually need different policies:

- deterministic hash by user
- random bucket
- round-robin
- least-loaded bucket
- region-aware bucket selection

Hard-coding this decision in the service makes the class difficult to evolve.

## Where it fits

```text
CouponReservationService
        |
        v
BucketSelectionStrategy
        |
        +--> HashBucketSelectionStrategy
        +--> RandomBucketSelectionStrategy
        +--> RegionAwareBucketSelectionStrategy
```

## Java example

```java
public interface BucketSelectionStrategy {
    int select(long campaignId, long userId, int totalBuckets);
}
```

Hash-based strategy:

```java
@Component
public class HashBucketSelectionStrategy
        implements BucketSelectionStrategy {

    @Override
    public int select(long campaignId, long userId, int totalBuckets) {
        long hash = 31L * campaignId + userId;
        return Math.floorMod(Long.hashCode(hash), totalBuckets);
    }
}
```

Service usage:

```java
@Service
@RequiredArgsConstructor
public class CouponReservationService {

    private final BucketSelectionStrategy bucketSelectionStrategy;
    private final InventoryRepository inventoryRepository;

    public boolean reserve(long campaignId, long userId, int bucketCount) {
        int bucketId = bucketSelectionStrategy.select(
                campaignId,
                userId,
                bucketCount
        );

        return inventoryRepository.tryReserve(campaignId, bucketId);
    }
}
```

## Benefit

The reservation workflow stays unchanged even if the bucket-allocation algorithm changes.

---

# 35. State Pattern — Reservation Lifecycle

## Scenario

A reservation has legal state transitions:

```text
RESERVED --> REDEEMED
    |
    +------> EXPIRED
    |
    +------> CANCELLED
```

Transitions such as these must never occur:

```text
REDEEMED --> EXPIRED
EXPIRED  --> REDEEMED
CANCELLED --> REDEEMED
```

If status checks are scattered throughout controllers, workers, and services, business rules become easy to violate.

## Simple domain-state implementation

```java
public enum ReservationStatus {
    RESERVED,
    REDEEMED,
    EXPIRED,
    CANCELLED
}
```

```java
@Entity
public class CouponReservation {

    @Id
    private UUID id;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    private Instant expiresAt;
    private Instant redeemedAt;

    public void redeem(Instant now) {
        if (status != ReservationStatus.RESERVED) {
            throw new IllegalStateException(
                    "Only RESERVED coupons can be redeemed"
            );
        }

        if (!now.isBefore(expiresAt)) {
            throw new IllegalStateException("Reservation has expired");
        }

        status = ReservationStatus.REDEEMED;
        redeemedAt = now;
    }

    public void expire(Instant now) {
        if (status != ReservationStatus.RESERVED) {
            throw new IllegalStateException(
                    "Only RESERVED coupons can expire"
            );
        }

        if (now.isBefore(expiresAt)) {
            throw new IllegalStateException(
                    "Reservation has not expired yet"
            );
        }

        status = ReservationStatus.EXPIRED;
    }
}
```

## Important concurrency note

The domain state validation above improves code structure, but it is **not sufficient by itself** for distributed concurrency.

The database should still enforce the transition atomically:

```sql
UPDATE coupon_reservation
SET status = 'REDEEMED',
    redeemed_at = NOW()
WHERE reservation_id = :id
  AND status = 'RESERVED'
  AND expires_at > NOW();
```

If the affected-row count is `0`, another transaction already changed the state or the reservation expired.

## Benefit

The State Pattern expresses legal lifecycle transitions clearly, while DB conditional updates provide the actual cross-instance concurrency guarantee.

---

# 36. Idempotency Pattern — Duplicate Claim Protection

## Scenario

A client sends:

```text
POST /campaigns/123/reservations
```

The server successfully creates the reservation, but the network response is lost.

The client retries.

Without idempotency:

```text
request #1 --> reservation A
request #2 --> reservation B
```

One logical action has consumed two coupons.

## Request

```http
POST /v1/campaigns/123/reservations
Idempotency-Key: 84f52f46-2f08-4e26-bce0-239552eab491
```

## Table constraint

```sql
ALTER TABLE coupon_reservation
ADD CONSTRAINT uq_reservation_idempotency_key
UNIQUE (idempotency_key);
```

## Java example

```java
@Transactional
public ReservationResponse reserve(
        long campaignId,
        long userId,
        String idempotencyKey) {

    Optional<CouponReservation> existing =
            reservationRepository.findByIdempotencyKey(idempotencyKey);

    if (existing.isPresent()) {
        return ReservationResponse.from(existing.get());
    }

    CouponReservation reservation = createReservation(
            campaignId,
            userId,
            idempotencyKey
    );

    try {
        reservationRepository.saveAndFlush(reservation);
        return ReservationResponse.from(reservation);
    } catch (DataIntegrityViolationException duplicate) {
        // Another instance may have processed the same key concurrently.
        return reservationRepository
                .findByIdempotencyKey(idempotencyKey)
                .map(ReservationResponse::from)
                .orElseThrow(() -> duplicate);
    }
}
```

## Why both application check and DB constraint?

Two servers can perform the initial lookup simultaneously and both see no record. The unique database constraint is the final concurrency guard.

## Benefit

Safe retries from browsers, mobile clients, API gateways, load balancers, and internal services do not consume multiple coupons.

---

# 37. Transactional Outbox Pattern — Reliable Kafka Publishing

## Scenario

Suppose the service does this:

```text
1. Commit reservation in PostgreSQL
2. Publish CouponReserved to Kafka
```

What if the process crashes after step 1 and before step 2?

```text
DB = RESERVED
Kafka = no event
```

The database and event stream are now inconsistent.

## Solution

Write the reservation and an outbox record in the same transaction:

```text
BEGIN

UPDATE inventory
INSERT reservation
INSERT outbox_event

COMMIT
```

A separate publisher reads the outbox and sends records to Kafka.

## Outbox entity

```java
@Entity
@Table(name = "outbox_event")
public class OutboxEvent {

    @Id
    private UUID eventId;

    private String aggregateType;
    private String aggregateId;
    private String eventType;

    @Column(columnDefinition = "jsonb")
    private String payload;

    private Instant createdAt;

    @Enumerated(EnumType.STRING)
    private OutboxStatus status;
}
```

## Reservation transaction

```java
@Transactional
public ReservationResponse reserve(...) {

    reserveInventory(...);

    CouponReservation reservation =
            reservationRepository.save(createReservation(...));

    OutboxEvent event = OutboxEventFactory.couponReserved(reservation);
    outboxRepository.save(event);

    return ReservationResponse.from(reservation);
}
```

## Publisher

```java
@Component
@RequiredArgsConstructor
public class OutboxPublisher {

    private final OutboxRepository outboxRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Scheduled(fixedDelay = 200)
    public void publish() {
        List<OutboxEvent> events =
                outboxRepository.findNextBatch(500);

        for (OutboxEvent event : events) {
            kafkaTemplate.send(
                    "coupon-events",
                    event.getAggregateId(),
                    event.getPayload()
            );
        }
    }
}
```

In production, marking the outbox event as published also needs careful delivery semantics. Consumers should still be idempotent because Kafka delivery can be at-least-once.

## Benefit

The durable business transaction and event-generation intent are committed together.

---

# 38. Saga Pattern — Multi-Service Checkout

## Scenario

A checkout may involve several independently deployed services:

```text
Order Service
    |
    v
Payment Service
    |
    v
Coupon Service
    |
    v
Inventory / Fulfillment
```

A single ACID transaction cannot realistically span all of them.

Suppose:

```text
1. Create order          SUCCESS
2. Charge payment        SUCCESS
3. Redeem coupon         SUCCESS
4. Reserve product stock FAILURE
```

The system needs compensating actions.

## Orchestrated saga

```text
CheckoutSagaOrchestrator
        |
        +--> create order
        +--> authorize payment
        +--> redeem coupon
        +--> reserve product inventory

If product reservation fails:
        |
        +--> release coupon / undo redemption if business rules allow
        +--> refund or void payment
        +--> cancel order
```

## Java sketch

```java
@Service
@RequiredArgsConstructor
public class CheckoutSagaOrchestrator {

    private final OrderClient orderClient;
    private final PaymentClient paymentClient;
    private final CouponClient couponClient;
    private final ProductInventoryClient inventoryClient;

    public CheckoutResult checkout(CheckoutCommand command) {

        Order order = orderClient.create(command);
        PaymentAuthorization payment = null;
        CouponRedemption redemption = null;

        try {
            payment = paymentClient.authorize(order);
            redemption = couponClient.redeem(
                    command.reservationId(),
                    order.id()
            );

            inventoryClient.reserve(order);

            return CheckoutResult.success(order.id());

        } catch (Exception failure) {

            if (redemption != null) {
                couponClient.compensateRedemption(redemption.id());
            }

            if (payment != null) {
                paymentClient.voidAuthorization(payment.id());
            }

            orderClient.cancel(order.id());
            throw failure;
        }
    }
}
```

In a large production system, the saga is usually persisted so that orchestration can resume after process restarts.

## Benefit

Provides a controlled way to maintain business consistency across multiple services without pretending they share one database transaction.

---

# 39. Cache-Aside Pattern — Campaign Metadata

## Scenario

Campaign metadata is read extremely often:

```text
campaign name
start/end time
item information
discount amount
campaign status
```

It changes relatively infrequently and does not need to participate in the critical inventory decrement transaction.

That makes it a good Redis cache candidate.

## Why Cache-Aside

```text
Application
     |
     v
Redis lookup
   /   \
 hit   miss
 |      |
return  v
     PostgreSQL
         |
         v
      Redis SET
         |
         v
       return
```

## Java example

```java
@Service
@RequiredArgsConstructor
public class CampaignQueryService {

    private final CampaignRepository campaignRepository;
    private final RedisTemplate<String, CampaignDto> redisTemplate;

    public CampaignDto getCampaign(long campaignId) {

        String key = "campaign:" + campaignId;

        CampaignDto cached = redisTemplate.opsForValue().get(key);

        if (cached != null) {
            return cached;
        }

        CampaignDto campaign = campaignRepository.findById(campaignId)
                .map(CampaignDto::from)
                .orElseThrow(CampaignNotFoundException::new);

        redisTemplate.opsForValue().set(
                key,
                campaign,
                Duration.ofMinutes(10)
        );

        return campaign;
    }
}
```

When campaign metadata changes:

```java
@Transactional
public void updateCampaign(long campaignId, UpdateCampaignRequest request) {
    campaignRepository.update(campaignId, request);
    redisTemplate.delete("campaign:" + campaignId);
}
```

The next read repopulates the cache.

## Why not use Cache-Aside for authoritative coupon decrement?

A cached count may be stale:

```text
Redis says available = 1
PostgreSQL says available = 0
```

So cached availability should be treated only as a read optimization or approximate signal. The final reservation decision must come from the authoritative atomic write path.

---

# 40. Write-Through, Write-Behind, and Write-Around — What We Choose

The coupon system uses different caching approaches depending on the data.

## 40.1 Write-Through

Write-through means:

```text
Application
    |
    v
Cache
    |
    v
Database
```

The write is considered successful only after both cache and DB are updated.

This can simplify cache consistency, but placing Redis directly in the authoritative coupon-inventory write path adds another dependency to every reservation.

For the coupon counter, this is not the preferred choice in this design.

## 40.2 Write-Behind / Write-Back

Write-behind means:

```text
Application --> Cache --> ACK
                  |
                  | later
                  v
              Database
```

This offers very low write latency but creates a dangerous window where Redis contains inventory changes that PostgreSQL does not yet contain.

For a limited coupon inventory where overselling must never occur, generic write-behind caching is **not recommended** for the authoritative count.

## 40.3 Write-Around / DB-First + Invalidate

For campaign metadata updates, the recommended behavior is effectively:

```text
Application
    |
    v
PostgreSQL
    |
    +--> invalidate Redis entry
```

Reads then use Cache-Aside.

This combination is simple and robust:

```text
READS  --> Cache-Aside
WRITES --> DB first, then cache invalidation
```

## 40.4 Recommended policy by data type

| Data | Pattern | Reason |
|---|---|---|
| Coupon inventory count | Direct authoritative DB atomic write | Correctness-critical; stale values cannot allocate coupons |
| Campaign metadata | Cache-Aside + DB-first invalidation | Read-heavy, relatively stable |
| Reservation lookup | Cache-Aside where useful | DB remains source of truth |
| Idempotency fast lookup | Redis + durable DB unique key | Fast rejection plus durable correctness |
| Expiry scheduling index | Redis sorted set + DB reconciliation | Redis accelerates timing; DB preserves correctness |

---

# 41. Redis Eviction and TTL Policy

Eviction policy should depend on what a Redis instance is storing. Mixing correctness-sensitive scheduling data with disposable metadata in one Redis deployment can make eviction behavior difficult to reason about.

## 41.1 Campaign metadata cache

Good candidate for an LRU-style policy such as:

```text
allkeys-lru
```

or a modern LFU policy if repeated hot-key access patterns make it more appropriate:

```text
allkeys-lfu
```

Each metadata key should also have a TTL, for example:

```text
campaign:123 --> TTL 10 minutes
```

This protects against stale entries surviving indefinitely even if explicit invalidation fails.

## 41.2 Expiry index

The reservation expiry sorted set is different. Random eviction of expiry records is undesirable because an evicted entry could delay release of a coupon.

For a dedicated Redis instance used for expiry coordination, a conservative choice is:

```text
maxmemory-policy noeviction
```

The Redis instance should be capacity-managed so the application sees an explicit write failure rather than silently losing a correctness-relevant timer entry.

Even then, PostgreSQL expiry reconciliation remains mandatory:

```sql
SELECT reservation_id
FROM coupon_reservation
WHERE status = 'RESERVED'
  AND expires_at <= NOW()
FOR UPDATE SKIP LOCKED
LIMIT 1000;
```

Therefore Redis improves latency, but loss of Redis expiry hints does not permanently leak coupons.

## 41.3 Idempotency keys

Redis idempotency entries naturally expire after the maximum retry/replay window, for example:

```text
idempotency:{key} --> TTL 24 hours
```

The database unique constraint remains the durable final defense.

---

# 42. Competing Consumers Pattern — Expiry Workers

## Scenario

Millions of reservations may be active. When many become expired, one worker may not process them fast enough.

We want:

```text
Worker 1
Worker 2
Worker 3
Worker 4
```

all processing expired reservations simultaneously without working on the same rows.

## PostgreSQL query

```sql
SELECT reservation_id
FROM coupon_reservation
WHERE status = 'RESERVED'
  AND expires_at <= NOW()
ORDER BY expires_at
FOR UPDATE SKIP LOCKED
LIMIT 1000;
```

Worker A locks one set.

Worker B skips those locked rows and obtains a different set.

## Java repository

```java
@Repository
public interface ReservationRepository
        extends JpaRepository<CouponReservation, UUID> {

    @Query(value = """
        SELECT *
        FROM coupon_reservation
        WHERE status = 'RESERVED'
          AND expires_at <= NOW()
        ORDER BY expires_at
        FOR UPDATE SKIP LOCKED
        LIMIT :batchSize
        """, nativeQuery = true)
    List<CouponReservation> lockExpiredBatch(
            @Param("batchSize") int batchSize
    );
}
```

Worker:

```java
@Service
@RequiredArgsConstructor
public class ReservationExpiryWorker {

    private final ReservationRepository reservationRepository;
    private final InventoryRepository inventoryRepository;

    @Transactional
    public int processBatch(int batchSize) {

        List<CouponReservation> reservations =
                reservationRepository.lockExpiredBatch(batchSize);

        for (CouponReservation reservation : reservations) {
            reservation.markExpired();

            inventoryRepository.release(
                    reservation.getCampaignId(),
                    reservation.getBucketId()
            );
        }

        return reservations.size();
    }
}
```

## Benefit

Horizontal worker scaling without a central distributed lock.

---

# 43. Sharding / Partitioning Pattern — Avoiding the Hot Counter

## Scenario

A campaign has 1,000,000 coupons and receives 50,000 reservation attempts per second.

One inventory row would become a hot row:

```text
Campaign 123 --> one DB row <-- 50K requests/sec
```

Instead, split inventory into buckets:

```text
Campaign 123
   |
   +--> Bucket 0
   +--> Bucket 1
   +--> Bucket 2
   ...
   +--> Bucket 255
```

At 256 buckets:

```text
50,000 / 256 ~= 195 requests/sec/bucket
```

The contention is distributed.

## Entity key

```java
@Embeddable
public class CouponInventoryBucketId implements Serializable {
    private Long campaignId;
    private Integer bucketId;
}
```

```java
@Entity
public class CouponInventoryBucket {

    @EmbeddedId
    private CouponInventoryBucketId id;

    private long available;
    private long reserved;
    private long redeemed;
}
```

## Bucket fallback

```java
public boolean tryReserveAcrossBuckets(
        long campaignId,
        long userId,
        int totalBuckets) {

    int start = bucketSelectionStrategy.select(
            campaignId,
            userId,
            totalBuckets
    );

    int maxAttempts = Math.min(4, totalBuckets);

    for (int attempt = 0; attempt < maxAttempts; attempt++) {
        int bucket = (start + attempt) % totalBuckets;

        if (inventoryRepository.tryReserve(campaignId, bucket)) {
            return true;
        }
    }

    return false;
}
```

This prevents a temporarily empty bucket from automatically meaning the entire campaign is sold out.

## Benefit

Turns one contention hotspot into many independently mutable inventory partitions.

---

# 44. Single Writer / Ownership Pattern — Global Deployment

## Scenario

The same campaign is available globally:

```text
US
India
Europe
```

If each region independently modifies a local replicated count, two regions can consume the final coupon before replication converges.

## Ownership model

```text
Campaign 1001 --> US-East
Campaign 1002 --> India
Campaign 1003 --> EU-West
```

All authoritative mutations for one campaign are routed to its owner region.

```text
India request -----\
Europe request -----+--> Campaign 1001 owner: US-East --> PostgreSQL
US request --------/
```

Read APIs can still be served locally from replicas/caches if eventual consistency is acceptable.

## Java routing abstraction

```java
public interface CampaignRegionResolver {
    Region ownerOf(long campaignId);
}
```

```java
@Service
@RequiredArgsConstructor
public class GlobalCouponRouter {

    private final CampaignRegionResolver regionResolver;
    private final RegionalCouponClientFactory clientFactory;

    public ReservationResponse reserve(ReservationCommand command) {

        Region owner = regionResolver.ownerOf(command.campaignId());

        return clientFactory
                .clientFor(owner)
                .reserve(command);
    }
}
```

A deterministic placement function or campaign metadata can provide the owner mapping.

## Benefit

Strongly simplifies global inventory correctness because concurrent mutations for a campaign converge on one authoritative write domain.

---

# 45. Supporting Resilience Patterns

The following patterns are also useful around the main architecture.

## 45.1 Circuit Breaker

Scenario: notification or a non-critical downstream service becomes unhealthy.

Reservation success should not depend on it.

```java
@CircuitBreaker(name = "notificationService", fallbackMethod = "ignoreNotificationFailure")
public void notifyReservation(CouponReservedEvent event) {
    notificationClient.send(event);
}
```

Prefer asynchronous Kafka consumers for such side effects where possible.

---

## 45.2 Retry

Retry only transient failures and only when the operation is safe to retry.

```java
@Retryable(
        retryFor = TransientDataAccessException.class,
        maxAttempts = 3,
        backoff = @Backoff(delay = 100, multiplier = 2)
)
public void publishOutboxEvent(OutboxEvent event) {
    publisher.publish(event);
}
```

Do not blindly retry a non-idempotent coupon reservation command unless idempotency protection is present.

---

## 45.3 Bulkhead

Separate critical resources so an expiry backlog cannot consume all capacity needed by live reservation traffic.

```text
Reservation API executor / DB pool

Expiry worker executor / controlled concurrency

Kafka publisher executor
```

Example executor configuration:

```java
@Bean("expiryExecutor")
public Executor expiryExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(8);
    executor.setMaxPoolSize(16);
    executor.setQueueCapacity(1000);
    executor.setThreadNamePrefix("coupon-expiry-");
    executor.initialize();
    return executor;
}
```

The exact sizing must come from load tests and DB capacity rather than arbitrary thread counts.

---

# 46. Pattern Mapping to the Complete Request Flow

```text
Client
  |
  |  Idempotency Pattern
  v
API Gateway
  |
  v
CouponReservationService
  |
  |-- Strategy Pattern ------------------> choose inventory bucket
  |
  |-- Repository Pattern ----------------> persistence abstraction
  |
  |-- Sharding Pattern ------------------> mutate one inventory bucket
  |
  |-- atomic DB conditional UPDATE ------> concurrency correctness
  |
  |-- State Pattern ---------------------> RESERVED lifecycle
  |
  |-- Transactional Outbox -------------> durable event creation
  |
  +--------------------------------------> COMMIT
                                                |
                                                v
                                              Kafka
                                                |
                                      Producer/Consumer Pattern
                                                |
                           +--------------------+--------------------+
                           |                    |                    |
                     Notifications         Analytics             Audit

15 minutes later if unused:

Redis expiry index
       |
       v
Competing Consumers
       |
       v
Expiry workers
       |
       |-- conditional state transition
       |-- release inventory bucket
       v
PostgreSQL

Global traffic:

Regional API --> Single Writer / Campaign Ownership --> authoritative region

Read path:

API --> Cache-Aside Redis --> PostgreSQL on miss
```

---

# 47. Pattern Selection Summary

| Pattern | Applied To | Main Problem Solved |
|---|---|---|
| Repository | DB access | Separates persistence from business logic |
| Strategy | Bucket selection | Allows allocation algorithm to change independently |
| State | Reservation lifecycle | Controls legal status transitions |
| Idempotency | Claim/redeem APIs | Prevents duplicate logical operations |
| Transactional Outbox | PostgreSQL -> Kafka | Prevents committed DB changes from losing events |
| Saga | Multi-service checkout | Handles distributed transaction compensation |
| Cache-Aside | Campaign metadata/read models | Reduces repeated DB reads |
| Competing Consumers | Expiry workers | Parallel processing without duplicate work |
| Sharding / Partitioning | Coupon inventory | Prevents a single hot DB row |
| Single Writer / Ownership | Multi-region writes | Prevents cross-region overselling |
| Circuit Breaker | Non-critical dependencies | Prevents cascading failure |
| Retry | Transient failures | Recovers from temporary dependency failures |
| Bulkhead | Executors/connections/workloads | Isolates failures and resource exhaustion |

---

# 48. Interview-Level Explanation of the Pattern Choices

A concise way to explain the pattern choices is:

> The core write path uses Repository abstractions around atomic PostgreSQL operations. Inventory is partitioned into buckets, and a Strategy selects the bucket so one campaign does not become a single-row hotspot. Reservation lifecycle rules are modeled as explicit state transitions, while the database still performs conditional updates to enforce those transitions across concurrent application instances. API retries are handled using idempotency keys backed by database uniqueness. Reservation creation and event generation use the Transactional Outbox Pattern so a committed reservation cannot lose its Kafka event. Expiry processing uses the Competing Consumers Pattern with `FOR UPDATE SKIP LOCKED`, allowing multiple workers to release timed-out reservations safely. Campaign metadata uses Cache-Aside caching, while correctness-critical inventory stays in the transactional database. For global deployments, each campaign follows a Single Writer/Ownership model. If checkout spans payment, order, coupon, and inventory services, an orchestrated Saga coordinates compensating actions.

The most important design principle remains:

```text
Patterns structure the solution.
Atomic database state transitions guarantee correctness.
```
