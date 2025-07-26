package $Expertise.Language;

//ORDER ON CLASS INSTANTIATION STARTS FROM THE BASE CLASS STATIC BLOCK AND THEN THE CONSTRUCTOR STARTING FROM THE ROOT
//This keyword only works in case of Objects
//Constructors can be private but not Static
class Base{
    String variable1;
    Integer number;
    Long lamba;

    public Base(String variable1, Integer number, Long lamba) {
        this.variable1 = variable1;
        this.number = number;
        this.lamba = lamba;
        System.out.println("Calling Base class Custom constructor");
    }

    public Base(){
        System.out.println("Calling Base class Default constructor");
    }

    static {
        System.out.println("Base class static block");
    }
    public void testMethod(){
        System.out.println("Execution from base class");
    }
}

class Derived extends Base{
    public  Derived(){
        super("1",2,1l);    // EXPLICIT CONSTRUCTOR MUST BE CALLED IN THE FIRST LINE
        System.out.println("Derived class constructor");
    }

    public void testMethod(){
        System.out.println("Execution from Derived class");
    }
}

//class Singleton {
//
//    private static Singleton singleton;
//    private  Singleton(){
//        System.out.println("Singleton class constructor");
//    }
//
//    static Singleton getInstance(){
//        System.out.println("Calling the Static Method");
//       singleton= new Singleton();
//       return singleton;
//    }
//
//}
//
public class Classes {
    static {
        System.out.println("Main class static block");
    }
    public static void main(String[] args) {

        Derived derive= new Derived();
    }
}
