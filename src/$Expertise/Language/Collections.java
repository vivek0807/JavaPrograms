package $Expertise.Language;
// Root interface of Collection Framework -Iterable that provides 3 methods to all classes ITERABLE | SPLIT-ITERATOR | FOR EACH
// Collection then again implements Iterator that has some common methods like
// AabstractCollection Class is an Abstract class that facilitates implementation of custom Collection
//Iterator & size class needs to be overriden but rest of the methods are implemented!

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.concurrent.*;

class ListKnowledge{
    /**
     * <h2>List</h2> is an Interface which can be Implemented By
     * <p>ArrayList,Linked List, Vector<p/>
     * <h2>CopyOnWriteArrayList</h2>
     * <p>Allows write with thread safety & creates arrayL on every write operation</p>
     * <p>It does not throw Concurrent Modification Exception</p>
     * <p>Suitable for multithreaded Application</p>
     * <h3>Linked List</h3>
     * <li>Better for random write/Remove operations</li>
     * <li>More memory heavy</li>
     * <li>No resizing and capacity issues</li>
     * <h3>Vector Vs CopyOnWriteArrayList</h3>
     * <li>all methods are synchronized vs a fresh copy is created every time</li>
     * <li>Vectors may throw Concurrent modification Exception if their structure is modified</li>
     * <li>Vectors may throw Concurrent modification Exception if their structure is modified</li>
     */
    public void giveListKnowledge(){
        AbstractCollection<String> abstractCollection= new ArrayList<>();

    }
}

class UnmodifiableCollection{
    /**
     * Wen can make a collection Unmodifiable usiing the following ways
      */
    void change(){
        // We can have multiple collections as part of unmodifiable
        List<String> strings= java.util.Collections.unmodifiableList(List.of("1","2","3"));
        strings.add("4");
    }
}
class Queue{
    /**
     * <p>The Q is  an <b>Interface</b> again that extends <b>Collection</b></p>
     * <p>Q has an Implementation of <b>Priority Q</b></p>
     * <p><b>dQ Interface </b> is again inherited by  <b> Q <b/> and implemented by <b>Array D-Q</b> </Q> </p>
     * <h3>Array DQ</h3>
     * <p>Prominent methods - peek first, peek last, remove first , remove last , insert first , insert last</p>
     * <h3>Priority Q</h3>
     * <p>These are identical to Min Heap Data structures</p>
     * <p>A Custom Comparator can be used to reverse the order of sorting</p>
     * <p>Null Values and Incomparable types cant be stored in Heap</p>
     */
    public void QueueKnowledge(){

    }
}

class Set{
    /**
     * <b> Set </b>is an interface further implemented by <b>HashSet and Linked HashSet</b>
     * <b>by default an hashSet is initialized with size of 16</b>
     * <h3>HashSet<h3/>
     * <li>Sets internally use HashTables </li>
        <li>Performance is generally faster than Linked Hashset as Order is not preserved</li>
     * <h3>LinkedHashSet</h3>
     * <li>Uses a hashTable with Doubly linked List to preserve the insertion Order</li>
     * <b>Both above use equals and HashCode method to check euq</b>
     * <b>As a good practise we always implement hashcode and equals method together  <b/>
     * <p> Different hashcode and same equals can give a very unpredictable behaviour<p/>
     * <h3>Navigable HashSets Interface</h3>
     * <p>Only advantage, can navigate in reverse Order as well</p>
     */
    int knowledge;
    public Set(int knowledge){
       this.knowledge=knowledge;
    }
    public void setKnowledge(){}

    @Override
    public int hashCode(){
        return new Random().nextInt();
    }
    @Override
    public boolean equals(Object obj){
        if(this==obj)
            return true;
        else
            return true;
    }
    @Override
    public String toString(){
        return "Knowledge is "+knowledge;
    }

}

class Maps{

    /**
     * <h3>Maps are not  a part of Iterable Interface</h3>
     * <li>Maps are implemented by HashMaps & Linked HashMaps[Maintains Order of elements]</li>
     * <h3>Sorted Map is an interface inherited by Map and Implemented by Tree Map</h3>
     * <p>Tree Maps internally use Red black Tree for sorting the keys</p>
     * <p>Hash uses Hash Tables whereas any set uses Red Black Trees<p/>
     */
    public void mapsKnowledge(){}
}

class Concurrent{

     /**
     * <h3>Concurrent Version of collection framework</h3>
     * <li>Copy On Write Array list</li>
     * <li>Concurrent HashMap</li>
      * <p>This provides a fine grain Locking on Map rather than locking the Entire map increasing speed</p>
     * <li>Linked Blocking Q  implemented by BlockingArrayQ & dQ</li>
      * <p>Takes initial capacity & works on blocking mechanism.It simply blocks the thread in case of empty or filled</p>
      * <br>
     *  <li>Concurrent linked Q </li>
      *  <p>It's a non blocking q, It does not block but throws out of memory error</p>
     * <p></p>
     * <li>LinkedBlocking Q</li>
     * <li>Concurrent Skip List set</li>
      * <p>It behaves same as sorted Set </p>
     */
    public void concurrentKnowledge(){
        CopyOnWriteArrayList<String> copyOnWriteArrayList= new CopyOnWriteArrayList<>();
        ConcurrentHashMap<String,String> concurrentHashMap= new ConcurrentHashMap<>();
        BlockingQueue<String> blockingQueue= new ArrayBlockingQueue<>(10);
        ConcurrentLinkedDeque<String> concurrentLinkedDeque= new ConcurrentLinkedDeque<>();
        ConcurrentSkipListSet<String> concurrentSkipListSet= new ConcurrentSkipListSet<>();
    }
}

class CustomCollection<T> extends AbstractCollection<T> {


    private final List<T> arrayList;

    public CustomCollection(List<T> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public @NotNull Iterator<T> iterator() {
        return arrayList.iterator();
    }

    @Override
    public int size() {
        return arrayList.size();
    }

    @Override
    public boolean add(T s){
        return true;
    }

}

public class Collections {
    /**
     *
     * @param args
     */
    public static void main(   String[] args) {

        CustomCollection<String> customCollection= new CustomCollection<String>(List.of("1","2"));

        customCollection.add("3");
        System.out.println(customCollection);
    }

}
