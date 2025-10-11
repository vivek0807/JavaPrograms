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
  - 
##### Common methods for thread control
- Thread.sleep(Time in ms)
- Thread.join - once called, the caller thread has to wait till the second thread execution is complete
- Thread.interrupt- cancel the execution cooperatively
- Thread.wait/notify/notifyAll must coordinate on a single shared reference object as wait is Object level method. This must be used in a synced block
- Thread.yield?
- CountDownLatch - wait for N things to complete.
##### The Pub Sub problem
 - The Consumer and the subscriber both keep running in the infinite while loop
  - everytime the buffer must be checked if it is full, if yes then producer.wait() must be called
  - once the message is consumed inside the consume block, current size must be reduced and notify-all must be called

##### Sync And Volatile Keywords
  - Sync Keyword -Ensures access of just one thread at a time for a block.
  - Volatile Keyword- Ensures the quick visibility of a variable across shared thread
  - Atoimic class - this is a class that supports Some datatypes Like Integer, Object etc, 
    Since Sync keyword or locks block the code part, this class gives certain methods Like addAndGet etc
    to perform same function as sync and lock keyWord
##### Exchanger 
- This is class that facilitates exchange of messages between two threads
- Exchanger.send and exchanger.receive are two methods.They act as two was hand off point.
- we have the function barrier.await()

##### Cyclic Barrier
- This concept lets arrive multiple threads at a common point and then releases all the threads for further execution.
##### Virtual Thread
  - Virtual threads are just small threads that don't exist in real but they can be used for mini tasks that don't 
    involve much CPu intensive tasks.
  - These are much easier to create and can handle small I/O tasks.
  - These can only execute HTTP calls or JDBC query
  - can be started as Thread.ofVirtual()->{execution code}
  - The virtual thread is executed by a career thread which is an actual OS level thread. At one time one one virtual thread is executed but is
   distributed among all CPU cores.
##### Daemon threads
- These are background threads that are created as a sub thread of the main user thread.
- There is no guarentee of its execution as termination of the main thread kills the Daemon thread as well.
- These are used in monitoring tasks, GC or auto saving tasks.
- It is created using Thread.setDaemon(true).
- It actually creates a native OS native level thread but cleans once main thread is closed.
#### Fork Join Pool
- Optimized for recursive tasks that can be broken into smaller pieces
- RecursiveAction<T> that don't return any result
- RecursiveTask<T> that return a result @override compute method
- we extend recursiveTask<T> to a class , override compute method tehn we call the .compute method
- in main method we create a ForkJoinPool then we call the .invoke method passing the object of  the class the extended recursiveTask
##### Semaphores
  - These are used to acquier a limited number of locks on within a block
  - semaphore = new Semaphore(slotLimit) 
  - The tryAcquire function is used to acquire lock on the block unless the limited number of slots are pending and .release is used to Remove
##### Conditions in locking
 - can make the block await inside any condition and then signal it after completion to start back
 - These are used with locks where as notify and notify all are used with sync blocks
 - can give multiple condition Qs
 - Eg. Lock = new ReentarantLock(), Condition c = lock.condition, c.awat/ c.signal
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

##### Q- Why is concurrent hashMap faster than normal HashTable
- Hashtables have all of their methods synced where as Concurrent HashMaps use a technique called lock stripping
- The Map is divided internally into parts of 16 
- Only a specific part of the map is locked

##### Ways to prevent Thread Starvation
- Choose fair locking using ReentrantLock()- this ensures locking in FIFO order
- Use Bounded thread pools with TimedOut Locking new ThreadPool(10,20,60, TimeUnit.seconds)
- Avoid long critical sections and if it is so then better have Thread.sleep() in between to allow other threads.
- Also can use thread Dumps to see if threads are starved