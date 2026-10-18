package $Expertise.Language;

import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * <h1>Virtual Threads</h1>
 * <li>Extremely helpful to manage concurrency as it takes care on own which to run while not blocking other threads</li>
 * <li>Legacy Threads had direct one to one mapping with OS leve threads hence creation and execution takes more resources</li>
 * <li>Virtual threads are managed by JVM and use a carrier threda to sync it with OS thread for the task</li>
 * <li>Virtual Threads are DAEMON thread that only run till the calling thread is running</li>
 * <li> Executors.newVirtualThreadPerTaskExecutor().sumit() method is used to submit a runnable and execute task</li>
 * <h2>If a callable is passed it return a Future and do get to get the value</h2>
 * <li>Number of threads in a thread pool = Cores+1 / cores (1+(waitTime/ComputeTime))</li>
 */
class VTmaking {
    protected void createVirtualThreads() {
        try {
            System.out.println("Starting Virtual thread Code");
            var virtualthread = Thread.startVirtualThread(() -> {
                for (int i = 0; i < 10; i++) {
                    System.out.println("Printing from virtual thread");
                    try {
                        Thread.sleep(100);
                    } catch (Exception e) {

                    }
                }
            });

            virtualthread.join();
            System.out.println("end of program");
        } catch (Exception e) {
            System.out.println("error in Virtual threads " + e.getCause());
        }
    }
}

class VTBasics {
    /**
     * - Because a thread does not directly accept callable
     * - We first create a callable using Function<A,R>
     * - Use the function to create a Callable
     * - pass the callable method to a Future task
     * - Start the thred by passing the future
     * - Then do .get to return the result from thred
     */
    protected void useLambdaWithReturn() {
        try {
            Function<Integer, String> function = (x) -> {
                return Thread.currentThread().getName() + " Accepted" + x;
            };

            Callable<String> callable = () -> function.apply(150);

            FutureTask<String> futureTask = new FutureTask<>(callable);

            new Thread(futureTask).start();
            System.out.println(futureTask.get());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    protected void lambdaRunnable() {
        Consumer<String> consumer = System.out::println;
        Runnable run = () -> consumer.accept("Hey developer");
        Thread t1 = new Thread(run);
        t1.start();
    }
}

public class VirtualThreadsExample {
    static void main() {

        VTBasics vtBasics = new VTBasics();
        VTmaking vTmaking = new VTmaking();
        vTmaking.createVirtualThreads();
    }
}
