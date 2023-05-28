package $Expertise.Language;
/*
* USE OF FLATMAP IS TO FLATTEN THE HIRARCHY, e.G ARRAYLIST OF LIST WILL RESULT IN A SIMPLE LIST
* */
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

class Money{
    int factor;
    int currentvalue;
    String name;

    public Money(int factor, int currentvalue, String name) {
        this.factor = factor;
        this.currentvalue = currentvalue;
        this.name = name;
    }
}
public class FlatMap {
    public static void main(String[] args) {

        List<List<String>> list= new ArrayList<>();
        list.add(Arrays.asList("Aman","Vineeth"));
        list.add(Arrays.asList("Jack","Michael"));

        Stream<String> streamer= list.stream().flatMap(i->i.stream()).sorted();

        streamer.forEach(i->{
            System.out.println(i);
        });

    }
}
