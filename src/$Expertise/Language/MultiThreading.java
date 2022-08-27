package $Expertise.Language;

// -When we begin Thread.start it creates a separate thread and the execution of Main thread keeps on happening
//-When we put Thread.join the main/master thread is set to wait unless the newly created thread is executed
//Once the t.join thread is executed fully, the further execution of other thread can proceed

//
class ThreadTask extends Thread{
    public void run() {
        System.out.println("Running from Thread class");
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
    }
}
