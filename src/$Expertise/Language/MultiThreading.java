package $Expertise.Language;

// -When we begin Thread.start it creates a separate thread and the execution of Main thread keeps on happening
//-When we put Thread.join the main/master thread is set to wait unless the newly created thread is executed
//Once the t.join thread is executed fully, the further execution of other thread can proceed
// When we do thread.start, a new thread is created and run method is executed,which does not happen if run is called directly
//If we create thread by extending a Thread we cannot extend any other class!!
// JOIN & SLEEP -
//Start method CANNOT BE CALLED TWICE AND WILL THROW
//IlleagealthreadStateException. Run method can be called twice as no new thread is created in this case
//Entending thread class creates multiple instances, whereas implementing runnable interface shared same object among threads
// To achieve multithreding using Runnable interface we create Thread Obj and pass the runnable interface class to initiate
//the run method.
//TODO :: SYNCHRONIZED makes a method/block atomic and provides syncronized visibility . value will depend on the caching mechanism of the hardware
//VOLATILE :: this does not guarentee atomicity the latest values updated will be shown
//EXECUTOR SERVICE -->This is an Interface which is used to execute on threads in a async manner.It helpes in maintaining a pool of thread
// and assign them a task.// TODO it queues up the task when number of current thread is not available
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

class Firstcall {

    static int  a_fixed_number;
      void incrementer(){
        ++a_fixed_number;
    }
}
public class MultiThreading {


    public static void main(String[] args) throws InterruptedException {


        Runnable runnable1 = () -> {

            for (int i = 0; i <150 ; i++) {
              // Firstcall.incrementer();
                try {
                  //  System.out.println(firstcall.a_fixed_number);
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        Runnable runnable=()->{
            for (int i = 0; i < 5; i++) {
                try {
                   Firstcall.a_fixed_number=50;
                    System.out.println("From sencond Thread "+Firstcall.a_fixed_number);
                    Thread.sleep(40);


                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        Thread thread1= new Thread(runnable);
        Thread thread= new Thread(runnable1);
        thread.start();
        thread1.start();
       System.out.println(Firstcall.a_fixed_number);
    }
}
