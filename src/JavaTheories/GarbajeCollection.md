## Garbaje Collection


#### Types of memories
Stack & Heap
<br>

##### Stack

- Used to store temp variables
- Stores reference of heap objects
- Strong/Weak reference
- Each thread has its own Stack memory

##### Heap Memory





<p>for all the function a memory block is assigned which is limited to a scope and is present in heap
<br>
memory.The stack points to the correct memory location in heap and once it is dereferenced from the
<br>
stack memory, the garbage  collector starts cleaning up the heap.
</p>

### Heap Memory Structure

- Young generation
  - Eden- First place for new Object
  - S0
  - S1
- Old generation - Usually bigger objects are present so it takes more time to run
- MetaSpace

### Algos
##### when any GC algo will run, all the threads will stop hence it is a very expesive task
- Mark & Sweep - runs and marks all the unreferenced objects that has to be deleted
- Mark & Sweep Compact memory
- Concurrent M&S. All dynamic
- Serial and Parallel GC(Multi Core)