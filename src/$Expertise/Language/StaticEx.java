package $Expertise.Language;


/**
 * THE STATIC KEYWORD
 * -Initializes without any object
 * --every class will have only one copy of static variables that can be changed from any of the objects
 * --Static methods can be called without initializing the class object
 * --STATIC block will get executed directly AND IS THE FIRST THING( and are then executed in order) TO GET EXECUTED IF A CLASS'S METHOD IS CALLED OR OBJECTIFIED
 * --static keywords are not allowed inside any method
 * --String variables are initialised only after they are declared EVEN FROM OTHER CLASS
 * STATIC FUNCTIONS CANNOT be called using object name, they can only be called using the CLASS NAME
 */

class Example{
    static String var1="Hello";
    String var2;
    static void fromOtherclass(){

        System.out.println("from other class");
    }
   static {
       System.out.println("Initialization happened");
   }
}

class Example2{
    static {
        System.out.println("coming from Example 2");
    }
    void NoStatic(){

        System.out.println("Not a static method");
    }
}
public class StaticEx {
     static int i=getsint();            //SINCE THIS IS FIRST IN ORDER, CALLS getsInt Directly even if it is @ last
    static {
        System.out.println("DIRECT EXECUTION");     // EXECUTES DIRECTLY

    }
    static {
        System.out.println("Order 2");
    }
    static int getsint(){
        System.out.println("GetsInt");
        return 0;
    }
    public static void main(String[] args) {

        Example.fromOtherclass();
        Example2 e2=new Example2();
        e2.NoStatic();
    }
}
/*  OUTPUT
GetsInt
DIRECT EXECUTION
Order 2
Initialization happened
from other class
coming from Example 2
Not a static method
 */