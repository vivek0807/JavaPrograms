package $Expertise.Language;

import java.util.*;

//Set is an internal implementation of Map. The key is passed as a dummy value everytime
//Addition of any custom class must override hashcode and equals method to it
//CopyOnWriteArray
//HASHSET->Order is not guarenteed and is a implementation of Set interface,Null elements are allowed in Hashset[MIGHT NEED TO OVERRIDE HaShCODE AND EQUALS]
//LINKEDHASHSET--> Created from a doubly linked List and maintians order of insertion
//SortedSetInterface[]-->TREESET[|]--> Provides Sorting ablility if provided with a comparator
//SortedSetInterface[]-->NavigableSet[]-->TreeSet[|] --> Also allows sorting along with additional capability of navigation
//Set<-- Sorted Set<-- Tree Set©[] It uses Tree Data structure for storing elements
public class Sets_ {
    public static void main(String[] args) {
        SortedSet<String> set = new TreeSet<>();
    }
}
