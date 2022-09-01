import java.util.List;
import java.util.stream.Stream;

public class Main {

    void Steamer(){

        List<Integer> list=List.of(1,2,3,4,5,6,7,8,9,0);

        Stream<Integer> stream=list.stream();

        stream.filter(i->i%2==0).forEach(i-> System.out.println(i));
    }

    public static void main(String[] args) {
    Main main= new Main();
    main.Steamer();
    }

    }

