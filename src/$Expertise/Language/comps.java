package $Expertise.Language;
//COMPARABLE AND COMPARATOR

//Comparable- Class implements Comparable interface to start comparing & required implementation of Compareto Method
//Disadvantage of Comparable is we might need to change the source class code for implementing it
// Comparator method defines its own way of comparing without implemeting the Comparable interface and defining its own comparator method
//Comparator used for custom logic and in case the class does not implement comparable interface
//Comparable -> Compareto(obj1) method
//Comparator ->co  static int i=getsint();            //SINCE THIS IS FIRST IN ORDER, CALLS getsInt Directly even if it is @ last
//    static {
//        System.out.println("DIRECT EXECUTION");     // EXECUTES DIRECTLY
//
//
//    }
//    static {
//        System.out.println("Order 2");
//    }
//    static int getsint(){
//        System.out.println("GetsInt");
//        return 0;
//    }compare(obj1, obj2)


import java.util.*;
import java.util.Collections;


class Laptops implements Comparable<Laptops>{

    double price;
    String brand;
    int ram;

    public Laptops(double price, String brand, int ram) {
        this.price = price;
        this.brand = brand;
        this.ram = ram;
    }

    @Override
    public int compareTo(Laptops o) {           // implements inherited compareTo method from Comparable interface
        if(this.ram>o.ram)
            return 1;
        else if(this.ram<o.ram)
            return -1;
        else
            return 0;
    }

    @Override
    public String toString() {
        return "price=" + price +  ", brand='" + brand + " " +", ram=" + ram;
    }
}


class Bikes{
    int price;
    int EnginePower;
    String name;

    public Bikes(int price, int enginePower, String name) {
        this.price = price;
        EnginePower = enginePower;
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public double getEnginePower() {
        return EnginePower;
    }

    public void setEnginePower(int enginePower) {
        EnginePower = enginePower;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Price ->"+price+" Engine Power ->"+EnginePower+" Name -->"+name;
    }
}



public class comps {
    public static void main(String[] args) {

        ArrayList<Laptops> Al=new ArrayList<Laptops>();         //Initialize arraylist with data type
        Al.add(new Laptops(60000,"Asus",8));
        Al.add(new Laptops(47000,"Dell",4));
        Al.add(new Laptops(30000,"Acer",6));

        ArrayList <Bikes> bk=new ArrayList<Bikes>();
        bk.add(new Bikes(1300,200,"Apache"));
        bk.add(new Bikes(1300,2600,"HayaBhusa"));
        bk.add(new Bikes(1300,1300,"Bullet"));
        bk.add(new Bikes(1300,400,"Dominar"));

        Comparator <Bikes> bkC= new Comparator<Bikes>() {
            @Override
            public int compare(Bikes o1, Bikes o2) {
                if(o1.getEnginePower()>o2.getEnginePower())
                    return 1;
                else if (o1.getEnginePower()<o2.getEnginePower())
                    return -1;
                else return 0;
            }
        };
        Collections.sort(Al);

        for (Laptops l:Al) {
            System.out.println(l);
        }
        Collections.sort(bk,bkC);               //Passing collection Array List object and new Comparator object

        for (Bikes bkeach: bk) {

        }
        for (Bikes b:bk ) {
            System.out.println(b);
        }



    }
}
