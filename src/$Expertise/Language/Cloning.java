package $Expertise.Language;
//Cloning a class Implementation

class Indigo implements Cloneable{
    String name;
    double price;
    int seaths;
    String origin;
    String destination;

    public Indigo(String name, double price, int seaths, String origin, String destination) {
        this.name = name;
        this.price = price;
        this.seaths = seaths;
        this.origin = origin;
        this.destination = destination;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }


    @Override
    public String toString() {
        return
                "name='" + name + '\'' +
                ", price=" + price +
                ", seaths=" + seaths +
                ", origin='" + origin + '\'' +
                ", destination='" + destination;
    }


}

public class Cloning {
   static Indigo function( Indigo i){
        Indigo ir=null;
        try {
            ir= (Indigo) i.clone();
        }
        catch (CloneNotSupportedException c){

        }
        return ir;
    }

    public static void main(String[] args) {
        Indigo indigo=new Indigo("Airbus",4250,150,"New Delhi","Ranchi");

  Indigo i1=null;
     try {
         i1=(Indigo) indigo.clone();
     }
     catch (CloneNotSupportedException c){

     }
        System.out.println(i1);





    }
}
