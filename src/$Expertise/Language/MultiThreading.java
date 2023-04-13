package $Expertise.Language;

// -When we begin Thread.start it creates a separate thread and the execution of Main thread keeps on happening
//-When we put Thread.join the main/master thread is set to wait unless the newly created thread is executed
//Once the t.join thread is executed fully, the further execution of other thread can proceed
// When we do thread.start, a new thread is created and run method is executed,which does not happen if run is called directly
//If we create thread by extending a Thread we cannot extend any other class!!

//Start method CANNOT BE CALLED TWICE AND WILL THROW
    //IlleagealthreadStateException. Run method can be called twice as no new thread is created in this case
//Entending thread class creates multiple instances, whereas implementing runnable interface shared same object among threads

// To achieve multithreding using Runnable interface we create Thread Obj and pass the runnable interface class to initiate
    //the run method.
class ThreadTask extends Thread{
    public void run() {
        System.out.println("Running from Thread class");
    }

}


class ThreadSafe{
    int increment(int number){

        for (int i = number; i <10 ; i++) {
            number+=i;
        }
        return  number;
    }
}

//MAKING A MTHOD THREAD SAFE
class MakeThreadSafe extends Thread{
    ThreadSafe threadSafe = new ThreadSafe();
    public  void  run(){
        threadSafe.increment(10);
    }

}
class RunnableTask implements Runnable{


    @Override
    public void run() {
        System.out.println("Running from Runnable interface");
    }
}

class JoinExample {

    class  ThreadOne extends Thread{
        @Override
        public void run()  {
            System.out.println("From first method");
            try {
                for (int i = 0; i <5 ; i++) {
                    Thread.sleep(100);
                    System.out.println("Accenture is worst company to work for");
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class Threadtwo extends Thread{
        @Override
        public void run() {
            System.out.println("From second method");
        }
    }

}
public class MultiThreading {

    public static void main(String[] args) throws InterruptedException {

        JoinExample joinExample=new JoinExample();
        JoinExample.ThreadOne threadOne=joinExample.new ThreadOne();
        JoinExample.Threadtwo threadTwo=joinExample.new Threadtwo();

        threadOne.start();
        threadOne.join();
        threadTwo.start();

        Thread thread= new Thread(new RunnableTask());

    }
}
