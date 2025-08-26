## Jave Stack & Heap Memory

##### STACK MEMORY
- Used for storing function calls local variables and reference variables
- Memory is allocated in LIFO order
- The memory is freed when a function execution is complete.
- Any time a function is called it is pushed into a frame and post execution it is completed.
- The stack frame data of a function contains the return address and the exception handled
- Every thread has it own share of stack memory
- Ways to visualize Stack -- Thread.DumpStack / Thread.printStacktree()

#### HEAP MEMORY
- Used to store Object and class instances
- Shared across all resources
- This is not cleared unless GC clears it 
- Divided into the following parts
    - Young Generation, newly created objects are stored here
      - Eden Space
      - Survivor space
    - Old Generation, that stores old objects usually Objects that survive multiple GC are promoted here
    - Meta gen introduced in jdk 8 + where class meta data is stored
      
  ##### Other spaces
  - Method Area-Unlike the heap, which holds individual object instances, the method area is used to store class-level information that is shared across all instances of a class and all threads within the application and shared across all threads.
  - Program counter-the PC register is concerned with instruction-level control: keeping track of which bytecode instruction a thread should execute next.
  - Native method stack- used for instructions that are beyond JVM's capabilities and handles the control to OS like executing a C code or hardware acceleration code.
#### MEMORY LEAK CAUSES AND REMEDY
- A memory leak can happen if Objects not in use but still they are referenced somehow and GC is not able to collect it
- Some other common causes are :
  - Static references 
  - Unclosed resources
  - Listeners or callbacks
  - Unreferenced Collections

- We can Dump the Heap to capture and analyze the heap memory and do code profiling

###### Prevention
- Use Weak referencing 
- Always close resources once used
- Avoid static collection holding Objects

##### Referencing in java
- Strong reference (Default)
  - The default memory creation type with new object creation.
  - Can lead to memory leaks if not de-referenced properly
- Weak Reference
  - This a class<T>, and object is created with T as parameter for Type
  - This marks in Heap Memory to quickly get collected after GC even if it is referenced
  - These are mostly used in memory sensetive applications Like cache & avoid memory leaks.
  - These are also not synchronized.
- Phantom Reference
  - It belongs to a lifecycle

##### Memory Tuning in java
- Used to optimize application memory usage and latency and resolve out of memory related issues and reduce GC calls
  - Heap Memory Tuned Using - Xms and -Xmx
  - Metaspace stores class MetaData, tuned using -XX: MaxMetaSpaceSize, -XXMetaSpaceSize
  - Stack Memory, Tuned using -Xss stack per thread
##### String Pool
The string pool is a separate space in heap memory that is used only to store String literals

- Creation of String using new Keyword
    - On every string creation using new keyword a new memory is allocated in heap
    - Even if the string content is same a new memory is allocated in the heap but it points to the same String literal in string pool
    - In case you want to use the same Object even if new KeyWord is used, we use new String("abcd"").intern method.
    - The same happens in case StringBuilder is used to create a new String
  
- Creation of String using ""
    - Creating a String this way makes a String Object have shared reference acrross variables.
    - No new Objects are created every time.
    - GC never cleans the String pool
    - Since Most Strings are normal literals and are created normally during log creation or variable assignment, String pool prevents duplicacy
- https://www.baeldung.com/java-reference-types#:~:text=In%20this%20article%2C%20we%20explored,references%20for%20fine%2Dgrained%20finalization.
