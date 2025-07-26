package $Expertise.DataStructures.implemented.Problems;

import static java.lang.System.out;

class A1{
    /**
     * When the child class in invoked always the MOST PARENT'S default constructor is called
     * ONLY ONE CONSTRUCTOR CALL IS ALLOWED IN A CONSTRUCTOR EITHER THIS OR SUPER
     * WHEN A FUNCTION IS DECLARED STATIC RUN-TIME POLYMORPHISM WILL NOT HAPPEN
     * Arrays in java is always allocated in Heap
     * Any Variable must be initialized before using in java
     * ORDER OF EXECUTION 1.STATIC BLOCK, 2 DEFAULT CONSTRUCTOR , METHOD IF CALLED
     * IN CASE A PARAMETRIZED CONSTRUCTOR IS WRITTEN IN A CLASS IT HAS TO PE EXPLICITLY CALLED USING SUPER KEYWORD
     * */
    A1(){
        System.out.println("Constructor call from A1");
    }

    A1(String s){
        out.println("Coming from A1 parametrized constructor"+s);
    }
}

class A  extends A1{

    A(){
        super("");
        System.out.println("From Default Constructor");
    }
    A(String s){
        out.println("From Parametrized Constructor "+s);
    }
}

class B extends A{
    B(){
       super("Strings");
        out.println("from B Default Constructor");
    }

    B(String s){

        out.println("from B parametrized Constructor"+s);
    }
}
public class Constructor_Super {
    public static void main(String[] args) {

       B b = new B();

    }
}
