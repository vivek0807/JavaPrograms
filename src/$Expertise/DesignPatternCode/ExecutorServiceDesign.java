package $Expertise.DesignPatternCode;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * <b>Thread Pool</b>
 * <p>It's a group of pre initialized workers thread that are managed by a thread pool manager</p>
 * <p>Thread pool is responsible to manage the execution of tasks</p>
 * <br>
 * <h3>There are 3 major components of a thread pool</h3>
 * <li>Thread Pool-Enqueue the task</li>
 * <li>Blocking Q- Stores the task</li>
 * <li>Task Executor- task</li>
 * <h3>Thread Pool</h3>
 * Class is initialized with size of thread pool and a blocking Q
 * Then we create given number of threads same as blocking Q size and then provide all these to a runnable interface to start them all
 * <br>
 * Then using we enQ the task to the blocking Q
 */

class ThreadPool{
    int size;
    private BlockingQueue<Runnable> blockingQueue;
    ThreadPool(int size){
        this.blockingQueue = new LinkedBlockingQueue<>(size);

        for(int i=0;i<size;i++){
            new Thread(new TaskExecutor(blockingQueue)).start();
        }
    }
    public void submitTask(Runnable task){
        try{
            blockingQueue.put(task);
        }
        catch(Exception ignored){
            System.out.println("Thread pool interrupted");
        }
    }
}

class TaskExecutor implements Runnable{
    private BlockingQueue<Runnable> blockingQueue;
     TaskExecutor(BlockingQueue<Runnable> blockingQueue){
        this.blockingQueue = blockingQueue;
     }

     @Override
    public void run(){
         while(true){
             try{
                 blockingQueue.take().run();
             }
             catch(Exception ignored){
                 System.out.println( "Task Executor Interrupted");
             }
         }
     }
}
public class ExecutorServiceDesign {
    public static void main(String[] args) {

    }
}
