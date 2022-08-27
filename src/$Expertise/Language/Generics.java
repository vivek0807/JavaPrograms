package $Expertise.Language;
//Java generics addan additional parameter to class so that it can recive the dataType of the new object or variable and
//work according to that

class Printer <I,S,D>{

    I integer;S string;D dbl;

    public Printer(I integer, S string, D dbl) {
        this.integer = integer;
        this.string = string;
        this.dbl = dbl;
    }
    void typePrinter(){
        System.out.println(integer.getClass().getName());
        System.out.println(string.getClass().getName());
        System.out.println(dbl.getClass().getName());
    }
}
public class Generics {
    public static void main(String[] args) {
        Printer printer=new Printer<Integer,String,Double>(15,"Generics",123.321);
        printer.typePrinter();
    }
}
