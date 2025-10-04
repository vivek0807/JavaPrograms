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
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

 class OddEvenReentrantLock {
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition oddTurn = lock.newCondition();
    private final Condition evenTurn = lock.newCondition();
    private boolean isOddTurn = true;  // start with odd thread
    private int number = 1;
    private static final int MAX = 20;

    public void printOdd() {
        while (true) {
            lock.lock();
            try {
                while (!isOddTurn) {   // not odd's turn → wait
                    oddTurn.await();
                }
                if (number > MAX) {    // end condition
                    evenTurn.signal(); // wake even to exit
                    break;
                }
                System.out.println("Odd Thread:  " + number++);
                isOddTurn = false;     // switch turn
                evenTurn.signal();     // wake even thread
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        }
    }

    public void printEven() {
        while (true) {
            lock.lock();
            try {
                while (isOddTurn) {    // not even's turn → wait
                    evenTurn.await();
                }
                if (number > MAX) {
                    oddTurn.signal();  // wake odd to exit
                    break;
                }
                System.out.println("Even Thread: " + number++);
                isOddTurn = true;      // switch turn
                oddTurn.signal();      // wake odd thread
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        OddEvenReentrantLock printer = new OddEvenReentrantLock();

        Thread oddThread = new Thread(printer::printOdd, "OddThread");
        Thread evenThread = new Thread(printer::printEven, "EvenThread");

        oddThread.start();
        evenThread.start();
    }
}

class OddEvenProblem{
    /**
     * <h1> Odd even problem</h1>
     * <h2> Approach</h2>
     * <li>Use a common lock object for reference and keep it in synced block as wait is part of Object class not Thread class</li>
     * <li>create two threads and start them</li>
     * <li>do wait on one and start the other and vice versa</li>
     * <li>Use while loop to acquire Lock as
     * <li> Os level thread scheduling can wake up the thread</li>
     * </li>
     * <li></li>
     */
 static int value=0;
 private static Object commonLockObject= new Object();
 private static  boolean isOddTurn=true;
  public void triggerExecution(){
      Thread t1= new Thread(()->{
    for (;;){
        synchronized (commonLockObject)
        {
            System.out.println(value);
            ++value;
            if (isOddTurn && value<20){
                try {
                    commonLockObject.wait();
                }
                catch (Exception e){
                    System.out.println("Exception in 1st thread");
                }
            }
            if (value>=20)
                System.exit(0);

            isOddTurn=false;
            commonLockObject.notifyAll();
        }
          }
      });

      Thread t2= new Thread(()->{
          for (;;){

              synchronized (commonLockObject)
              {
                  System.out.println(value);
                  ++value;
                  try {
                      while (!isOddTurn && value<20)
                          commonLockObject.wait();
                  }
                  catch (Exception e){
                      System.out.println("Exception in thread 2");
                  }
                  isOddTurn=true;
                  commonLockObject.notifyAll();
                  if (value>20)
                      System.exit(0);

              }



          }

      });

      t1.start();
      t2.start();
  }

}
public class MultiThreading {
     static  int value=0;
    public static void main(String[] args) throws InterruptedException {
     OddEvenProblem oddEvenProblem= new OddEvenProblem();
     oddEvenProblem.triggerExecution();
    }
}
