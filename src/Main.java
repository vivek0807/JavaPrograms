
import java.io.IOException;
import java.util.*;
class User_def_Exception extends Exception {


    User_def_Exception(String Message){
        super(Message);
    }
    void ThrowableException() throws IOException {
        throw new IOException();
    }

    class InnterClass{
        int returner(){ ;
        try{
            System.out.println("In block");
           return  1/0;
        }
        catch (Exception e){
            return 5;
        }
        };

    }



}

public class Main {
    public static void main(String[] args) {

        User_def_Exception ud= new User_def_Exception("Hello");

        try{
            ud.ThrowableException();

        }
        catch (IOException e){
          //  return;
            System.out.println(e);
        }
        catch (Exception e){

        }

        User_def_Exception.InnterClass IG= ud. new InnterClass();

        System.out.println(IG.returner());
    }
}
