import java.io.*;
import java.util.HashMap;

class OddPrinter implements Runnable{


    @Override
    public void run() {
            int target=10;
            for (int i=0;i<target;i++){
                if (i%2==0)
                    System.out.println(i);
            }

    }
}

class EvenPrinter implements Runnable{

    @Override
    public void run() {
        int target=10;
        for (int i=0;i<target;i++){
            if (i%2!=0)
                System.out.println(i);
        }

    }
}


public class Main {


    public static void main(String[] args)  throws IOException {

        Thread t1= new Thread(new OddPrinter());
        Thread t2= new Thread(new EvenPrinter());
     try{
           t1.start();  //prints evens
           t2.start(); //print odds
         for (int i = 0; i < 10; i++) {
             if (i%2==0)
             {
                // t1.wait();
                // t2.notify();
             }
             else {
                 t2.start();
                 t2.wait();
                 t1.notify();
             }
         }
     }
     catch (Exception e){}
    }

}


