# Java Multithreading & Concurrency Mastery Roadmap

> **Goal:** Progress from knowing how to start a thread to being able to
> reason about shared state, control execution, coordinate threads,
> design thread pools, diagnose concurrency bugs, and use modern Java
> concurrency including virtual threads.

------------------------------------------------------------------------

## Stage 0 --- Prerequisites

Before going deep into concurrency, be comfortable with:

-   Interfaces and abstract methods
-   Lambdas and functional interfaces
-   `Runnable`
-   `Callable<T>`
-   `Consumer<T>`
-   `Function<T, R>`
-   Exceptions and `try/finally`
-   Java collections
-   Basic JVM memory concepts: stack vs heap

You should understand signatures such as:

``` java
Runnable       // () -> void
Callable<T>    // () -> T
Consumer<T>    // T -> void
Function<T,R>  // T -> R
```

------------------------------------------------------------------------

# Phase 1 --- Thread Fundamentals

## Topics

1.  What is a process?
2.  What is a thread?
3.  Platform/OS threads
4.  Creating threads with `Thread`
5.  Creating tasks with `Runnable`
6.  Lambda-based `Runnable`
7.  `start()` vs `run()`
8.  `Thread.currentThread()`
9.  Thread names and IDs
10. Thread priorities --- and why not to rely on them
11. Daemon vs non-daemon threads
12. Thread lifecycle/states:

-   `NEW`
-   `RUNNABLE`
-   `BLOCKED`
-   `WAITING`
-   `TIMED_WAITING`
-   `TERMINATED`

13. `Thread.sleep()`
14. `Thread.join()`
15. `Thread.yield()` and its limitations
16. Interrupts:

-   `interrupt()`
-   `isInterrupted()`
-   `Thread.interrupted()`
-   `InterruptedException`

## Practice

-   Start two threads printing different values.
-   Give them names and print the executing thread.
-   Make one thread wait for another using `join()`.
-   Interrupt a sleeping thread.
-   Observe thread states.

## Mastery Check

You should be able to explain:

-   Why calling `run()` does not create a new thread.
-   What `start()` actually changes.
-   Why `sleep()` does not release a lock.
-   What `join()` makes the calling thread wait for.
-   Why interruption is cooperative rather than forcibly killing a
    thread.

------------------------------------------------------------------------

# Phase 2 --- Shared State and Race Conditions

This is where real concurrency begins.

## Topics

1.  Shared mutable state
2.  Race conditions
3.  Critical sections
4.  Thread safety
5.  Atomic vs non-atomic operations
6.  Visibility
7.  Ordering
8.  Thread interleaving

Study:

``` java
count++;
```

Understand that conceptually it involves:

``` text
READ count
ADD 1
WRITE count
```

Multiple threads can interleave these operations.

## Practice

Create 10 threads where each increments the same counter 10,000 times.

Expected:

``` text
100000
```

Observe that an unsafe implementation can produce a smaller result.

## Mastery Check

Be able to explain exactly how two threads can lose an increment.

------------------------------------------------------------------------

# Phase 3 --- Intrinsic Locks and `synchronized`

Your first major tool for controlling which thread may execute a block.

## Topics

1.  Object monitors / intrinsic locks
2.  `synchronized` blocks
3.  `synchronized` instance methods
4.  `static synchronized` methods
5.  `synchronized(this)`
6.  Locking a dedicated object
7.  Instance lock vs class lock
8.  Mutual exclusion
9.  Reentrancy
10. Lock granularity
11. Lock contention
12. Why locking too much hurts concurrency

Example:

``` java
private final Object lock = new Object();

synchronized (lock) {
    // critical section
}
```

## Critical Question

If Thread A owns a particular monitor, determine exactly which code
Thread B can and cannot execute.

## Practice

-   Fix the shared counter using `synchronized`.
-   Protect only the critical section.
-   Compare two synchronized methods on the same object.
-   Compare synchronization on two different lock objects.
-   Experiment with instance vs static synchronized methods.

------------------------------------------------------------------------

# Phase 4 --- Thread Coordination: `wait`, `notify`, `notifyAll`

Locks prevent simultaneous execution. Coordination controls **when a
thread should proceed**.

## Topics

1.  `wait()`
2.  `notify()`
3.  `notifyAll()`
4.  Why they must be used while owning the monitor
5.  `wait()` releases the monitor
6.  `sleep()` does **not** release the monitor
7.  Waiting in a `while` loop
8.  Spurious wakeups
9.  Missed notifications
10. Guarded blocks

Typical pattern:

``` java
synchronized (lock) {
    while (!condition) {
        lock.wait();
    }

    // perform work
}
```

## Practice

Build two threads that print:

``` text
1 2 3 4 5 6 7 8 9 10
```

where one prints odd numbers and the other prints even numbers.

Then build a basic producer-consumer implementation manually.

------------------------------------------------------------------------

# Phase 5 --- Java Memory Model (JMM) and `volatile`

This explains **why** synchronization works, not merely how to type it.

## Topics

1.  Java Memory Model
2.  Visibility
3.  Ordering
4.  Atomicity
5.  Happens-before relationship
6.  Instruction reordering
7.  Safe publication
8.  `volatile`
9.  What `volatile` guarantees
10. What `volatile` does NOT guarantee
11. `final` field visibility basics

Example:

``` java
volatile boolean running = true;
```

Understand why this can be useful.

Also understand why:

``` java
volatile int count = 0;
count++;
```

is still not sufficient for atomic incrementing.

## Mastery Check

You should clearly distinguish:

``` text
Atomicity
Visibility
Ordering
```

------------------------------------------------------------------------

# Phase 6 --- Explicit Locks: `ReentrantLock`

Now move beyond intrinsic locks and gain finer control.

## Topics

1.  `Lock`
2.  `ReentrantLock`
3.  `lock()`
4.  `unlock()`
5.  Why `unlock()` belongs in `finally`
6.  `tryLock()`
7.  Timed `tryLock()`
8.  `lockInterruptibly()`
9.  Reentrant locking
10. Fair vs non-fair locks
11. Lock contention

Standard pattern:

``` java
lock.lock();

try {
    // protected work
} finally {
    lock.unlock();
}
```

## Practice

Rewrite your synchronized counter using `ReentrantLock`.

Then deliberately forget `unlock()` and understand what happens.

------------------------------------------------------------------------

# Phase 7 --- `Condition`

`Condition` provides richer coordination with `Lock`.

## Topics

1.  `Condition`
2.  `await()`
3.  `signal()`
4.  `signalAll()`
5.  Multiple conditions for one lock
6.  Why `Condition` can be more expressive than `wait/notify`

Relationship:

``` text
synchronized       ReentrantLock

wait()             await()
notify()           signal()
notifyAll()        signalAll()
```

## Practice

Rebuild the odd/even problem using:

``` java
ReentrantLock
Condition
```

Then create separate conditions for different waiting groups.

------------------------------------------------------------------------

# Phase 8 --- Deadlock, Livelock and Starvation

Before using more locks, learn how locking goes wrong.

## Topics

1.  Deadlock
2.  Lock ordering
3.  Nested locks
4.  Livelock
5.  Starvation
6.  Fairness
7.  Lock contention
8.  Deadlock prevention
9.  Deadlock avoidance using `tryLock()`
10. Diagnosing deadlocks with thread dumps

Classic deadlock:

``` text
Thread A:
lock1
  ↓
waiting for lock2

Thread B:
lock2
  ↓
waiting for lock1
```

## Practice

Intentionally create a deadlock.

Then fix it using consistent lock ordering.

------------------------------------------------------------------------

# Phase 9 --- Atomic Variables and CAS

Not every concurrency problem needs a traditional lock.

## Topics

1.  `AtomicInteger`
2.  `AtomicLong`
3.  `AtomicBoolean`
4.  `AtomicReference`
5.  `incrementAndGet()`
6.  `getAndIncrement()`
7.  `compareAndSet()`
8.  Compare-And-Set (CAS)
9.  Optimistic concurrency
10. Lock-free concepts
11. ABA problem --- advanced
12. `LongAdder` / `LongAccumulator`

Conceptually:

``` text
Current value = 10

Change 10 -> 11
ONLY IF value is still 10
```

## Practice

Fix the counter using:

``` java
AtomicInteger
```

Then compare:

``` text
synchronized
ReentrantLock
AtomicInteger
LongAdder
```

------------------------------------------------------------------------

# Phase 10 --- Concurrent Collections

Learn to avoid manually locking ordinary collections when Java already
provides concurrency-aware alternatives.

## Topics

1.  `ConcurrentHashMap`
2.  `CopyOnWriteArrayList`
3.  `ConcurrentLinkedQueue`
4.  `ConcurrentLinkedDeque`
5.  `BlockingQueue`
6.  `ArrayBlockingQueue`
7.  `LinkedBlockingQueue`
8.  `PriorityBlockingQueue`
9.  `DelayQueue`
10. Weakly consistent iterators
11. Compound operations
12. Atomic map operations:
    -   `putIfAbsent`
    -   `computeIfAbsent`
    -   `compute`
    -   `merge`

## Special Focus --- `BlockingQueue`

Understand:

``` text
Producer
    ↓
BlockingQueue
    ↓
Consumer
```

and:

``` java
queue.put(value);
queue.take();
```

## Practice

Implement producer-consumer using `BlockingQueue`.

Compare it with your earlier `wait/notify` implementation.

------------------------------------------------------------------------

# Phase 11 --- Coordination Utilities

Java provides higher-level synchronization tools for common patterns.

## Topics

### `CountDownLatch`

Wait until N operations complete.

### `Semaphore`

Limit how many threads can enter an operation simultaneously.

``` java
Semaphore semaphore = new Semaphore(3);
```

### `CyclicBarrier`

Make several threads wait for each other at a common point.

### `Phaser`

More flexible multi-phase coordination.

### `Exchanger`

Allow two threads to exchange data.

## Practice

Build:

``` text
100 tasks
    ↓
Semaphore(10)
    ↓
Maximum 10 execute protected operation concurrently
```

Then simulate several services starting and use `CountDownLatch` before
continuing.

------------------------------------------------------------------------

# Phase 12 --- Executors and Thread Pools

Now stop manually creating a `Thread` for every ordinary task.

## Topics

1.  `Executor`
2.  `ExecutorService`
3.  `execute()`
4.  `submit()`
5.  `shutdown()`
6.  `shutdownNow()`
7.  `awaitTermination()`
8.  `Future`
9.  `Callable`
10. Executor lifecycle

Learn common factory methods, but understand their trade-offs:

``` java
Executors.newFixedThreadPool(...)
Executors.newSingleThreadExecutor()
Executors.newCachedThreadPool()
Executors.newScheduledThreadPool(...)
```

## `ThreadPoolExecutor`

Master:

``` text
corePoolSize
maximumPoolSize
keepAliveTime
workQueue
ThreadFactory
RejectedExecutionHandler
```

Understand the flow:

``` text
Task
  ↓
core workers
  ↓
work queue
  ↓
additional workers (depending on queue/pool configuration)
  ↓
rejection
```

## Rejection Policies

-   `AbortPolicy`
-   `CallerRunsPolicy`
-   `DiscardPolicy`
-   `DiscardOldestPolicy`

## Practice

Construct a `ThreadPoolExecutor` manually instead of using `Executors`.

Experiment with a small bounded queue and rejection policies.

------------------------------------------------------------------------

# Phase 13 --- `Callable`, `Future`, and `FutureTask`

You have already started this area.

## Topics

1.  `Runnable` vs `Callable`
2.  Returning values
3.  Checked exceptions from `Callable`
4.  `Future<T>`
5.  `Future.get()`
6.  Timed `get()`
7.  Cancellation
8.  `FutureTask`
9.  `ExecutionException`
10. `CancellationException`

Understand:

``` text
Callable<T>
    ↓
submit()
    ↓
Future<T>
    ↓
get()
    ↓
T
```

## Practice

Submit 10 calculations to an executor and collect all results.

------------------------------------------------------------------------

# Phase 14 --- `CompletableFuture`

Learn asynchronous composition rather than manually coordinating every
future.

## Topics

1.  `runAsync()`
2.  `supplyAsync()`
3.  `thenApply()`
4.  `thenAccept()`
5.  `thenRun()`
6.  `thenCompose()`
7.  `thenCombine()`
8.  `allOf()`
9.  `anyOf()`
10. `exceptionally()`
11. `handle()`
12. `whenComplete()`
13. Custom executors
14. Default common pool behavior
15. Async vs non-async continuation methods

Important relationship:

``` text
thenApply   ≈ map
thenCompose ≈ flatMap
```

## Practice

Simulate:

``` text
Fetch User
    ↓
Fetch Orders
    ↓
Fetch Prices
    ↓
Combine Result
```

Include error handling.

------------------------------------------------------------------------

# Phase 15 --- Fork/Join Framework

For recursively splitting CPU-oriented work.

## Topics

1.  `ForkJoinPool`
2.  Work stealing
3.  `RecursiveTask<T>`
4.  `RecursiveAction`
5.  `fork()`
6.  `join()`
7.  `invoke()`
8.  `invokeAll()`
9.  Threshold selection
10. Common pool
11. CPU-bound divide-and-conquer workloads

Understand:

``` text
RecursiveTask<T>   -> returns T
RecursiveAction    -> returns nothing
```

Example structure:

``` text
              [1..1000]
              /      \
         [1..500]   [501..1000]
          /   \       /    \
        ...   ...   ...    ...
```

## Practice

Calculate the sum of a large array using `RecursiveTask<Long>`.

------------------------------------------------------------------------

# Phase 16 --- Virtual Threads

Only after understanding traditional concurrency should you go deep into
virtual threads.

## Topics

1.  Platform threads
2.  Virtual threads
3.  `Thread.startVirtualThread(...)`
4.  `Thread.ofVirtual()`
5.  `Executors.newVirtualThreadPerTaskExecutor()`
6.  One virtual thread per task
7.  Carrier threads
8.  Mounting/unmounting
9.  Parking
10. Blocking I/O
11. Why virtual threads are cheap
12. Why virtual threads are not automatically faster
13. CPU-bound vs I/O-bound workloads
14. Pinning and how to diagnose it
15. Thread-local considerations
16. Limiting access to scarce resources with semaphores rather than
    pooling virtual threads

Example:

``` java
try (var executor =
         Executors.newVirtualThreadPerTaskExecutor()) {

    Future<Integer> result =
            executor.submit(() -> 10 + 20);

    System.out.println(result.get());
}
```

Core mental model:

``` text
Task
 ↓
Virtual Thread
 ↓
may mount on a carrier thread
 ↓
blocks/parks when waiting
 ↓
can unmount when supported
 ↓
carrier can execute other virtual threads
```

## Practice

Simulate thousands of blocking tasks using:

``` java
Thread.sleep(...)
```

Compare the programming model with a bounded platform-thread pool.

------------------------------------------------------------------------

# Phase 17 --- Advanced Java Concurrency

Now study the machinery underneath higher-level APIs.

## Topics

1.  `AbstractQueuedSynchronizer` (AQS)
2.  `LockSupport`
3.  `park()`
4.  `unpark()`
5.  CAS internals
6.  Memory barriers/fences conceptually
7.  `VarHandle`
8.  Optimistic vs pessimistic synchronization
9.  Lock-free algorithms
10. False sharing
11. CPU cache lines
12. Contention
13. ThreadLocal
14. Inheritable thread-local concepts and virtual-thread considerations
15. Immutable objects and safe publication
16. Double-checked locking
17. Concurrent state-machine design

Understand the conceptual stack:

``` text
ReentrantLock
      ↓
AQS
      ↓
state + CAS + waiter queue
      ↓
park / unpark
```

------------------------------------------------------------------------

# Phase 18 --- Debugging and Production Concurrency

Concurrency mastery also means being able to diagnose failures.

## Topics

1.  Thread dumps
2.  `jstack`
3.  `jcmd`
4.  Java Flight Recorder (JFR)
5.  Java Mission Control (JMC)
6.  Deadlock detection
7.  Blocked-thread analysis
8.  Lock contention
9.  Executor queue saturation
10. Thread-pool exhaustion
11. Memory problems caused by unbounded queues/tasks
12. Stuck futures
13. Timeout design
14. Cancellation
15. Graceful shutdown
16. Observability and metrics

## Questions You Should Eventually Be Able to Answer

-   Why is my executor queue continuously growing?
-   Why are all worker threads blocked?
-   Why is throughput low despite having many threads?
-   Is the problem CPU saturation, lock contention, I/O waiting, or
    queueing?
-   Why is `Future.get()` never returning?
-   Why did a deadlock occur?
-   Should this workload use a fixed platform-thread pool or virtual
    threads?
-   Where should concurrency actually be bounded?

------------------------------------------------------------------------

# Recommended Problem Sequence

Do these in order.

## Problem 1 --- Shared Counter

``` text
10 threads
Each increments 10,000 times
```

Learn:

``` text
Race condition
→ synchronized
→ ReentrantLock
→ AtomicInteger
→ LongAdder
```

------------------------------------------------------------------------

## Problem 2 --- Odd/Even Printer

``` text
Thread A: 1
Thread B: 2
Thread A: 3
Thread B: 4
...
```

Solve using:

1.  `synchronized + wait/notifyAll`
2.  `ReentrantLock + Condition`

------------------------------------------------------------------------

## Problem 3 --- Producer/Consumer

First solve manually:

``` text
synchronized
wait
notifyAll
```

Then replace it with:

``` text
BlockingQueue
```

------------------------------------------------------------------------

## Problem 4 --- Limited Concurrent Access

``` text
100 tasks
Only 10 may call a simulated external service simultaneously
```

Solve using:

``` text
Semaphore
```

------------------------------------------------------------------------

## Problem 5 --- Service Startup Coordination

``` text
Application
    ↓
wait for DB
wait for Cache
wait for Config
    ↓
continue
```

Use:

``` text
CountDownLatch
```

------------------------------------------------------------------------

## Problem 6 --- Thread Pool Design

Create your own:

``` java
ThreadPoolExecutor
```

Experiment with:

``` text
pool size
queue capacity
rejection policy
task duration
```

Observe saturation.

------------------------------------------------------------------------

## Problem 7 --- Parallel Return Values

Submit several:

``` java
Callable<Integer>
```

Collect:

``` java
Future<Integer>
```

Handle exceptions and cancellation.

------------------------------------------------------------------------

## Problem 8 --- Async Workflow

Use:

``` java
CompletableFuture
```

Build dependent and independent stages.

------------------------------------------------------------------------

## Problem 9 --- Fork/Join

Split a large array recursively and calculate its sum.

Learn:

``` text
RecursiveTask
fork
join
work stealing
threshold
```

------------------------------------------------------------------------

## Problem 10 --- Virtual Thread Workload

Simulate thousands of blocking I/O-style tasks.

Use:

``` java
Executors.newVirtualThreadPerTaskExecutor()
```

Then explain why virtual threads help this workload but don't magically
speed up CPU-bound calculations.

------------------------------------------------------------------------

# Final Mastery Checklist

You are becoming strong in Java concurrency when you can confidently
explain and use:

-   [ ] Thread lifecycle
-   [ ] `start()` vs `run()`
-   [ ] `sleep()`, `join()`, interruption
-   [ ] Race conditions
-   [ ] Atomicity
-   [ ] Visibility
-   [ ] Ordering
-   [ ] Happens-before
-   [ ] `synchronized`
-   [ ] Object monitors
-   [ ] `wait/notify/notifyAll`
-   [ ] `volatile`
-   [ ] `ReentrantLock`
-   [ ] `Condition`
-   [ ] Deadlock
-   [ ] Livelock
-   [ ] Starvation
-   [ ] CAS
-   [ ] Atomic classes
-   [ ] Concurrent collections
-   [ ] `BlockingQueue`
-   [ ] `Semaphore`
-   [ ] `CountDownLatch`
-   [ ] `CyclicBarrier`
-   [ ] Executors
-   [ ] `ThreadPoolExecutor`
-   [ ] `Runnable`
-   [ ] `Callable`
-   [ ] `Future`
-   [ ] `FutureTask`
-   [ ] `CompletableFuture`
-   [ ] Fork/Join
-   [ ] Work stealing
-   [ ] Platform vs virtual threads
-   [ ] Carrier threads
-   [ ] Virtual-thread pinning
-   [ ] AQS
-   [ ] `LockSupport`
-   [ ] Thread dumps
-   [ ] JFR/JMC
-   [ ] Production concurrency diagnosis

------------------------------------------------------------------------

# Recommended Learning Order --- Condensed

``` text
1. Thread fundamentals
        ↓
2. Shared state + race conditions
        ↓
3. synchronized + monitors
        ↓
4. wait / notify / notifyAll
        ↓
5. Java Memory Model + volatile
        ↓
6. ReentrantLock
        ↓
7. Condition
        ↓
8. Deadlock / livelock / starvation
        ↓
9. Atomics + CAS
        ↓
10. Concurrent collections + BlockingQueue
        ↓
11. Semaphore / Latch / Barrier / Phaser
        ↓
12. ExecutorService + ThreadPoolExecutor
        ↓
13. Callable + Future
        ↓
14. CompletableFuture
        ↓
15. Fork/Join
        ↓
16. Virtual Threads
        ↓
17. AQS / LockSupport / advanced internals
        ↓
18. Production debugging and performance
```

------------------------------------------------------------------------

# Guiding Principle

Do not memorize concurrency APIs independently.

For every mechanism, ask:

1.  **What concurrency problem does this solve?**
2.  **What state is shared?**
3.  **What guarantees do I need --- atomicity, visibility, ordering,
    coordination, or limiting concurrency?**
4.  **Which threads can execute this block at the same time?**
5.  **If a thread waits, what wakes it?**
6.  **Does waiting hold or release a lock?**
7.  **What happens on interruption, exception, timeout, or
    cancellation?**
8.  **Can a higher-level concurrency utility solve this more safely?**

The goal is not merely to know how to create threads. The goal is to be
able to **reason about execution order and shared state, choose the
correct concurrency primitive, and diagnose what happens when the system
behaves incorrectly.**
