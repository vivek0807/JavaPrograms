package $Expertise.Language;


import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

class LeanrnExecutorService{

    /**
     * <h1> Ways to execute Asynchronous programming in java</h1>
     * <li>Using Futures/ Executor Service / Threads </li>
     * <li>Future alone had various disadvantages so came the Completable Future</li>
     *  <li>It cant be chained, it cant be forcefully completed</li>
     *
     * <h1>Executor Service</h1>
     * <p>This is a <b>Thread Manager</b> FrameWork in java that manages the entire life cycle of a thread</p>
     * <p>Executor Service Internally uses a blocking Q as blocking Qs are thread safe to Q the submitted task</p>
     * <p>The ideal size of thread pool= number of core your CPU has[This can be fetched dynamically]<b> In case of CPU intensive task</b></p>
     * <p> In case of a task which a REST call or I/O call we can have a higher number of thread pool as we can make multiple threads wait as the response is not dependent on CPU</p>
     * <h3>Types of Executors</h3>
     * <li>newSingleThreadExecutor for single thread Operation.This uses unbounded Q internally that can lead to a put of memory problem<li/>
     * <li>newFixedThreadPool(int) Creates a set of threads to be used later</li>
     * <li>newFixedThreadPool(int) Creates a set of threads to be used later</li>
     * <li>cachedThreadPoolExecutor-Used to Automatically create and destroy threads for tasks that need short span of time</li>
     * <li>ScheduledThreadPoolExecutor used to execute a task after a set Delay</li>
     *
     *
     */
    void newFixedThreadPool(){
        ExecutorService executorService= Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            executorService.submit(() -> {
                try {
                    Thread.sleep(1000);// ALl statements are executed parallely after 1 sec
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(finalI);
            });
        }

    }
}

class CompletableFutures{
    /**
     * <b>Cf.runAsync(Runnable, ExecutorPool) used when nothing has to be returned</b>
     * <b>cf.SupplyAsync(Supplier<T>)  used when we want to get a value returned</b>
     * <h3>Chaining done by below 3 methods</h3>
     * <li>thenAccept(Consumer) does not return anything</li>
     * <li>thenApply(Function) can return something and takes function as an input</li>
     * <li>thenRun(Runnable) also does not return anything </li>
     * <li> The Async Edition of all these take Executer as an Input and start the input on a separate thread</li>
     * <li> There are thenCombine etc methods to combine futures</li>
     * <h3>Exception handling </h3>
     * <li>exceptions are handled using .exceptionally  methods and can be changed further<li/>
     * <li></li>
     */

    void printThreadName(){
        System.out.println(Thread.currentThread().getName());
    }
    void showExamples(){

        CompletableFuture<String> completableFuture= new CompletableFuture<>();
        completableFuture.complete("Hello World");

        CompletableFuture<List<String>> supplyAsyncMethods= CompletableFuture.supplyAsync(()->{
            //Direct assign,emt of a runnable
            printThreadName();
            return List.of("1","2","3");
        }).thenApplyAsync((list)->list.stream().map(String::toUpperCase).toList());




    }

    /**
     * <h3>Creating multiple features and executing</h3>
     * <li>Use a for loop to create List of Tasks using supply Async, this will contain the call part</li>
     * <li>Combine all the futures to one result by streaming over future and joining them into list</li>
     * <li>use when complete to check if an exception has occured, if occured just call the function again else print results</li>
     */

    void retryAbleCode(ExecutorService executorService,int retryCount){

        if(retryCount>3){
            System.out.println("Max retries reached");
            executorService.shutdown();
            return;
        }
        //Create Multiple instances of rest call

        List<CompletableFuture<String>> futureList= IntStream.range(0,5).
                mapToObj(i->CompletableFuture.supplyAsync(()->makeRestcall(i)).
                        handle((result,ex)->{
                            if (ex!=null)
                                throw new RuntimeException(ex);
                            return result;
                                }
                        )).toList();

        CompletableFuture<Void> allof= CompletableFuture.allOf(futureList.toArray(new CompletableFuture[0]));

        CompletableFuture<List<String>> collectedResults=allof.
                thenApplyAsync((obj->futureList.stream().map(CompletableFuture::join).toList()));

        collectedResults.whenComplete((result,ex)->{

            if (ex!=null)
            {System.out.println("One or more tasks have failed");

                executorService.submit(()->makeRestcall(retryCount+1));
            }
            else
                System.out.println(result);
        });


    }

    String  makeRestcall(int i){
        return String.format("Rest call %d",i);
    }

    /**
     * <b>Its important to shut down executor service so that the tasks are not assigned by any other call</b>
     */
    void superTask(){
        ExecutorService executorService= Executors.newFixedThreadPool(10);
        retryAbleCode(executorService,0);
        executorService.shutdown();
    }

}

public class FuturesAndCallbacks {
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        CompletableFutures completableFutures= new CompletableFutures();
        completableFutures.showExamples();
    }
}
