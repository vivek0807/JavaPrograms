package $Expertise.Language;

import java.util.function.Predicate;

/*Predicate Consumer And Supplier are just functions defined within a functional Interface in java.
The functions can be redefined and used later
There return type is also a functional Interface
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
public class PredicateConsumer {
    public static void main(String[] args) {
        Predicates predicates= new Predicates();
        predicates.predicates();
    }
}
