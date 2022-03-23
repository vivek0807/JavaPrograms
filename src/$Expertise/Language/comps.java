package $Expertise.Language;

import java.util.ArrayList;
//COMPARABLE AND COMPARATOR
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
    public int compareTo(Laptops o) {
        if(this.ram>o.ram)
            return 1;
        else if(this.ram==o.ram)
            return 0;
        else
            return -1;
    }
}

public class comps {
    public static void main(String[] args) {

        ArrayList<Laptops> Al=new ArrayList<Laptops>();
        Al.add(new Laptops(60000,"Asus",8));
        Al.add(new Laptops(47000,"Dell",4));
        Al.add(new Laptops(30000,"Acer",6));

    }
}
