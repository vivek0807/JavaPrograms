package $Expertise.Language;
// Its not necessary to have catch block for try, we can directly have catch block
//The program stops if exception rises in catch block itself
//Even if a wrong exception is caught, the program shall stop
//Checked Exceptions- SQLe,ClassNotfoundE,IOe
//unchecked Exception- numberFormate,inputE,ArrayIndexE,NullPointer
//The Exception is always tried at the function calling block--KEEP TRACING BACK TILL ORIGINAL METHOD.
//CHECKED EXCEPTIONS ARE NOT PROPAGATED
// THROWS KEYWORD- is just to show the exception that may happen, Actual exception is only caught using TRY & CATCH BLOCK

//AFTER using throw keyword, further lines of code are not executed
// for a method to thorw a CUSTOM EXCEPTION the method must declare the same with THROWS KEYWORD
//**If the superclass method does not declare an exception, SUBCLASS OVERRIDDEN METHOD CANNOT DECLARE THE CHECKED EXCEPTION but can declare unchecked exception also hirarchy has to maintained going on deeper side or No exception.**//
class User_Def_Exception extends Exception{
    public User_Def_Exception(String message) {
        super(message);
    }
}

public class Exceptions  {
    protected String ExceptionClass;
    void generatesExceptions() throws ArithmeticException{

        try{
           // System.out.println(100/0);
            //char[] buff={'a','b','c'};
            //System.out.println(buff[3]);
            //String s=null;
            //System.out.println(s.length());
            throw new User_Def_Exception(" This is my custom exception");
            //throw new ArithmeticException("Arithmatic Excwption raised manually"); // RAISING A CHECKED EXCEPTION MANUALLY
        }
        catch (ArithmeticException |NullPointerException |ArrayIndexOutOfBoundsException|User_Def_Exception  e){   //catches Arithmetic Exception
            if (e.toString().equals("java.lang.NullPointerException"))
                System.out.println("ExceptionRaised ");
            else
                System.out.println(e.toString());

        }

    }


        public static void main (String[]args){
        Exceptions e=new Exceptions();
        e.generatesExceptions();
    }

}
