import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Stream;

class IamGeneric<T>{

    T object;

    public IamGeneric(T object) {
        this.object = object;
    }
    void typePrinter(){
        System.out.println(object.getClass().getName());
    }
}
class Animals{
    int legs;
    String color;
    enum  foody{omnivorous,herbivorous,carnivorous};

    public Animals(int legs, String color) {
        this.legs = legs;
        this.color = color;
    }
}

public class Main {
    public static void main(String[] args) {
        HashSet<Animals> hashSet = new HashSet<>();

        hashSet.add(new Animals(4,"white"));
        hashSet.add(new Animals(4,"white"));
        hashSet.add(new Animals(4,"white"));
        hashSet.add(new Animals(4,"white"));

        Stream<Animals> stream=hashSet.stream();

        stream.forEach(i->{
            System.out.println(i.color);
        });

    }

    }

