package $Expertise.Language;
//Java generics add an additional parameter to class so that it can receive the dataType of the new object or variable and
//work according to that
//generics don't support primitive data types
//The first parameter in a List is associated with different data types associated that the function will be taking
import java.util.List;
import java.util.function.Function;

class Printer <I,S,D>{

    I integer;S string;D dbl;

    public <V,T> void returnmanipulatedlist(List<V> list){

    }
}
public class Generics {
    public static void main(String[] args) {
        Function<Integer,Double> halfer= e-> (double) (e/2);
        System.out.println(halfer.apply(20));
        Printer<Integer,Integer,Integer> printer= new Printer<>();
        printer.returnmanipulatedlist(List.of(1,2,3,4));
    }
}
