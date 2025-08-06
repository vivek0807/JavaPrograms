## Concurrency Control in java

#### Ways to create thread
- implement Runnable
- extend thread
- Callable
  - same as runnable but just that it can return a value
  - we override the call method
  - can throw a checked exception
##### Difference between Execute and Submit
  - Submit takes both callable and runnable as a parameter and can return the value or exception
  - Execute only takes runnable hence no return can be captured/ its a fire and forget mechanism.
  - Execute Method may or mey not create a thread and may execute the call later

##### The Pub Sub problem


##### Sync And Volatile Keywords
  - Sync Keyword -Ensures access of just one thread at a time for a block.
  - Volatile Keyword- Ensures the quick visibility of a variable across shared thread
  - Atoimic class - this is a class that supports Some datatypes Like Integer, Object etc, 
    Since Sync keyword or locks block the code part, this class gives certain methods Like addAndGet etc
    to perform same function as sync and lock keyWord

##### Virtual Thread
  - Virtual threads are just small threads that don't exist in real but they can be used for mini tasks that don't 
    involve much CPu intensive tasks.
  - These are much easier to create and can handle small I/O tasks.
##### Semaphores
  - These are used to acquier a limited number of locks on within a block
  - semaphore = new Semaphore(slotLimit) 
  - The tryAcquire function is used to acquire lock on the block unless the limited number of slots are pending and .release is used to Remove

### Concurrent collections
  - 🤷‍♂️If a collection is modified during iteration, it can throw Concurrent modification exception
  - 🙌Hashmap- Concurrent Hashmap- This enables locking on partial part of map without locking the entire structure
  - 😍The thread is segmented over parts of the hashMap
  - 😍These are the possible parameters that we can use to initiate Concurrent hashMap ConcurrentHashMap(int initialCapacity, float loadFactor, int concurrencyLevel)
  - 🙌CopyOnWriteArrayList- Creates a new copy on every update of the arrayList, rest of teh behaviour is same as normal list
  - 🙌Concurrent LinekdQ- Does not keep external lock using sync method but uses lock free algorithms
    - Methods Offer- adds without throwing exception whereas Add throws and exception if size is reached
    - Peek - Visualizes the top element wherese POLL removes it as well
    - Take method is available in BlockingQ that waits unitll an item becomes available in the Q
#### Fail-Safe and Fail fast techniques
  - Fail fast- directly throws concurrentModificationException if modification is done during iteration so that a false data can be detected
  - Fail-safe -The modification is done on the copy of the Collection hence the iterator keeps giving the older data without failure
