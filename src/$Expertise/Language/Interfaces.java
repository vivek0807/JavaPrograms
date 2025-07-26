package $Expertise.Language;
// -Helps achieve Loose coupling and multiple Inheritance
//In an interface the methods are public and abstract and Variables are static and final
//static methods in interfaces must have their definition
//Eg of tagged interface Serializable, Clonable,Remote etc
// AS the implementation of methods goes lower we have to reduce the Access restriction
// An Abstract class is just like an Ordinary class with an additional feature of declaring methods without body.
// When implementing an abstract class that has already implemented an interface // it must describe all the methods from class and interface
//Functional Interfaces-- Interface having only one method declaration Runnable, ActionListener, Comparable
//Functional Interface can have only one ABSTRACT METHOD
//MARKER INTERFACE-Empty interfaces used to indicate a special behaviour of the implemented class-Serializable Clonable Remote
//Incase one interface extends other and both have methods with same name, the later will be called.
//Incase Inheritance is not there and method names are same, there will be compile time error


interface Upper{
    default void show(){
        System.out.println("From default method");
    }
    void abstractMethod();
    int a_number=5;

    String a_string="some_value";
    enum Currency{
        RUPEE,DOLLAR,YUANG;
       public int position(){
           return ordinal()+1;
        }

    }
}


interface Lower {
    default void show(){
        System.out.println("Fwon Lower Interface");
    }
}

abstract class halfMethods{

}
public class Interfaces implements  Upper{

    public void abstractMethod(){

    }
//    @Override
//    public void show(){
//        System.out.println("This is from overrriden method");
//      //  Upper.super.show();
//    }
    public static void main(String[] args) {

        //Upper interfaces= new Interfaces();
    }
}
