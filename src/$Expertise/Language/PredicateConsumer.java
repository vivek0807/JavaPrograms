package $Expertise.Language;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/*Predicate Consumer And Supplier are just functions defined within a functional Interface in java.
The functions can be redefined and used later
There return type is also a functional Interface

Consumers- This are functional interfaces with default method as ACCEPT. They can be assigned any
function and data passed to that function will be interpreted

Suppliers- These are functional interfaces with GET as default method. We define functions
like Math.random to then and fetch it when required
* */


class Predicates{
    void predicates(){
        Predicate<Integer> predicate=i->(i<10);
        System.out.println("Test Method of Predicate "+ predicate.test(9));
        System.out.println("Negate Method of Predicate "+predicate.negate().test(9));
        System.out.println(" From custom predicate "+checkNumber.test(5));
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

        ConsumerTest consumerTest= new ConsumerTest();
        consumerTest.makeitwork();
    }
}
