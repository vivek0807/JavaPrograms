### HashTables
- Hashtables are synced and hence are slower
- These use older enumerator
- does not allow null values
- can be constructed with initial capacity and load factor else default capacity is 16 and load factor in 0.75
- each node consists of hashcode, key,value and next node
- Calculation of hash is done by Overriding hashCode method and comparison is doen by overriding equals Method
- The table is array of Buckets. These buckets are the first node of the LL for which the index is directly calculated
- A complex computation can further take place to preciseley calclulate the index position.
- In case of a put operation equals and hash code both are calculated to replace or add a new node.
- The load factor determines when the size of hashMap must be expanded i.e 0.75 means increase when 75% capacity is reached.
- Collision handling is done using open addressing/ chaining(LL creation)

#### LinkedHashMap
- Maintains a D-LL to find the next orer of elements.
#### Tree Maps
- Made from Red Black tree where left node is always the smaller one and right is larger than the parent node.
- Traversal in followed from root.
- All major operations take around logN time
- to provide a custom comparator, create a custom comparator and provide it in the constructor while initiating TreeMap
- Since it uses trees there are no chances of collision however it is slower in comparison of hashmap and linkedhashmap.

###
- Enumerator is a older iterator from java 1.0 that was used for older collections like vector/stack/hastable
- it does not support
  - failfast
  - concurrentmodification
  - does not have methods for removing elements
- Collections framework has a .unmodifiable* method to make a collection immutable.