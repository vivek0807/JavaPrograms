package $Expertise.Language;

import java.util.*;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/*Predicate Consumer And Supplier are just functions defined within a functional Interface in java.
The functions can be redefined and used later
PREDICATES HAVE some inbuilt functions like Test, Negate isEqual or and
CHAINING METHODS using AND,OR methods
There return type is also a functional Interface

Consumers- This are functional interfaces with default method as ACCEPT. They can be assigned any
function and data passed to that function will be interpreted such as throwing out put or doing filter.
CHAINING METHODS-> andThen

Suppliers- These are functional interfaces with GET as default method. We define functions
like Math.random to then and fetch it when required
* */

class Predicates{
    void predicates(){
        Predicate<Integer> predicate=i->(i<10);

        System.out.println("Test Method of Predicate "+ predicate.test(9));
        System.out.println("Negate Method of Predicate "+predicate.negate().test(9));
        System.out.println(" From custom predicate "+checkNumber.test(5));

        List<Integer> intlist= Arrays.asList(1,2,4,5,6,67,32123,4,53,6,4,23,1,4);
        Predicate<Integer> superfilter=i->(i>10 &&i<500);

        Map<Integer,Long> counterMap=intlist.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
        List<Map.Entry<Integer,Long>> LiistOfMap=new LinkedList<>(counterMap.entrySet());
        Collections.sort(LiistOfMap, new Comparator<Map.Entry<Integer, Long>>() {
            @Override
            public int compare(Map.Entry<Integer, Long> o1, Map.Entry<Integer, Long> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });

        Map<Integer,Long> newMap= LiistOfMap.stream().collect(Collectors.toMap(i->i.getKey(), j->j.getValue()));

        System.out.println(newMap);


    }

    static Predicate< Integer> checkNumber= new Predicate<Integer>() {
        @Override
        public boolean test(Integer integer) {
            if(integer<Integer.MAX_VALUE)
                return true;
            else
                return false;
        }
    };
}

class ConsumerTest{
    void makeitwork(){

       Consumer<List<Integer>> listConsumer= filter_base->{
           for (int i = 0; i < filter_base.size() ;i++) {
                filter_base.set(i,i*2);
           }
       };

       Consumer<Integer> display_function=i-> System.out.println(i);

       display_function.accept(5);
    }
}
public class PredicateConsumer {
    public static void main(String[] args) {
//        Predicates predicates= new Predicates();
//        predicates.predicates();

//        ConsumerTest consumerTest= new ConsumerTest();
//        consumerTest.makeitwork();

        Map<Integer,Integer> map= new TreeMap<>();
        map.put(1,1);
        map.put(2,1);
        map.put(4,1);
        map.put(5,1);
        map.put(123123123,1);
        map.put(11,1);
        map.put(123,1);
        map.put(109,1);
        map.put(23,1);
        System.out.println(map);
    }
}
