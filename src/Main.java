
import java.util.*;
class Bikes implements Comparable<Bikes> {
     String name;
     double price;
     double EnginePower;

    public Bikes(String name, double price, double enginePower) {
        this.name = name;
        this.price = price;
        EnginePower = enginePower;
    }

    @Override
    public String toString() {
        return  "name='" + name + '\'' +
                ", price=" + price +
                ", EnginePower=" + EnginePower;
    }

    @Override
    public int compareTo(Bikes o) {
        if(this.EnginePower<o.EnginePower)
            return -1;
        else if(this.EnginePower> o.EnginePower)
            return 1;
        else
            return 0;
    }
}

class Cars{
    String name;
    double price;
    double EnmginePower;

    public Cars(String name, double price, double enmginePower) {
        this.name = name;
        this.price = price;
        EnmginePower = enmginePower;
    }

    @Override
    public String toString() {
        return  "name='" + name + '\'' +
                ", price=" + price +
                ", EnmginePower=" + EnmginePower ;
    }
}

public class Main {
    public static void main(String[] args) {
        ArrayList<Bikes> bikes = new ArrayList<>();
        bikes.add(new Bikes("Apache",20000,200));
        bikes.add(new Bikes("Splendor",1000,100));
        bikes.add(new Bikes("Dominor",40000,400));
        bikes.add(new Bikes("Ninja",100000,1000));

       // Collections.sort(bikes);

        Comparator<Bikes> bkC= new Comparator<Bikes>() {
            @Override
            public int compare(Bikes o1, Bikes o2) {
                if (o1.price>o2.price)
                    return 1;
                else if(o1.price< o2.price)
                    return -1;
                else
                    return 0;
            }
        };

        Collections.sort(bikes,bkC);
        for (Bikes b: bikes) {
            System.out.println(b);
        }


    }
}
