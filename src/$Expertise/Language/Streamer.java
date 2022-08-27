package $Expertise.Language;
//STREAM IS AN INTERFACE
//INTERMEDIATE OPERATIONS -MAP-FILTER SORT
//TERMINAL OPERATIONS COLLECT FOR-EACH REDUCE
//Stream can be created with collection.stream() , Stream.of() and Stream builder method
//EACH FUNCTION OF STREAM can take lambda expression as an input expression
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Technique1{
    void main(){

        List<Integer> list =List.of(1,2,3,4,5,6,7,8,9,0,10);

        Stream<Integer> stream =list.stream();

       List<Integer> filteredList= stream.filter(i->i%2==0).collect(Collectors.toList());

       list.stream().map(i->i*i).filter(i->i%2==0).forEach(i-> System.out.println(i));
    }

    void emptyStream() {

        Stream<Object> stream = Stream.empty();
    }

    void fromArray(){

        String arr[]={"Name","array","for","test"};
        Stream<String> stream=Stream.of(arr);

        stream.forEach(e->{
            System.out.println(e);
        });
    }


}



public class Streamer {
    public static void main(String[] args) {

        Technique1 technique1= new Technique1();
        technique1.main();
    }
}
