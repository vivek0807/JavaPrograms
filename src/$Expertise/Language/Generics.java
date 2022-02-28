package $Expertise.Language;
//Java generics addan additional parameter to class so that it can recive the dataType of the new object or variable and
//work according to that

class Printer <T>{

    T t;

    public Printer(T t) {
        this.t = t;
    }

    void printer(){
        System.out.println("Coming from generics class "+this.t);
    }
}
public class Generics {
    public static void main(String[] args) {
        Printer<Integer> p=new Printer<Integer>(50);
        p.printer();
        Printer<Double> p1=new Printer<Double>(50.000);
        p1.printer();
    }
}
