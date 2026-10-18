package $Expertise.Language;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

class VirtualThreadFunctions{

}

/**
 * <h1>Fork Join Thread Pool</h1>
 * <h2>Used to create a sub task of one single task and run parallely</h2>
 * <li>Way of creating a pool with custom pool size ForkJoinPool = new ForkJoinPool(4)<li/>
 * <li>Direct Submission pool.submit(()->lambdaExpression)</li>
 * <li>RecursiveTask<V></> returns a dataType as give in second example </li>
 * <li> Every thread has its own queue named as work stealing Q.</li>
 * <li>Work Stealing algorithm - Every Worker thread takes a task from the end of D-Queue of other Worker thread</li>
 * <li>Work Stealing algorithm - The Owner thread takes task from the top while others take from the end[LIFO technique is better]</li>
 * <li>CAS is used to minimize contention </li>
 */
class ForkJoinPool extends RecursiveAction {

 int threshHold=4;
 String workLoad ;

  ForkJoinPool(String mainString){
      this.workLoad= mainString;
  }

  @Override
  protected void compute(){
      if (workLoad.length()>threshHold){
          ForkJoinTask.invokeAll(createSubtask());
      }
      else
          makeUpperCase(workLoad);
  }


  List<ForkJoinPool> createSubtask(){
     List<ForkJoinPool> subTask= new ArrayList<>();
     subTask.add(new ForkJoinPool(workLoad.substring(0,workLoad.length()/2)));
     subTask.add( new ForkJoinPool(workLoad.substring((workLoad.length()/2)+1)));
     return subTask;
 }

 void makeUpperCase(String input){
     System.out.println(input.toUpperCase()+" "+Thread.currentThread().getName());
 }


}

class ForkJoinPoolWithReturn extends RecursiveTask<Integer> {

    int[] arr;
    int threshold=5;

    ForkJoinPoolWithReturn(int[] arr){
        this.arr=arr;
    }
    @Override
    protected Integer compute(){
        if (arr.length>threshold)
        {
           return ForkJoinTask.invokeAll(createSubTask()).stream().mapToInt(ForkJoinTask::join).sum();
        }
        else
            return doArraySum(arr);

    }

    List<ForkJoinPoolWithReturn> createSubTask(){

        List<ForkJoinPoolWithReturn> subtask= new ArrayList<>();

        subtask.add(new ForkJoinPoolWithReturn(Arrays.copyOfRange(arr,0,arr.length/2)));
        subtask.add(new ForkJoinPoolWithReturn(Arrays.copyOfRange(arr, (arr.length / 2) + 1, arr.length)));
        return subtask;
    }

    Integer doArraySum(int[] arr){

        return Arrays.stream(arr).map(e->e*10).sum();
    }


}
public class ForkJoinExamples {
    static void main() {
         Runnable run=()-> {
                 System.out.println("From Runnable Object");
             System.out.println(Thread.currentThread().getName());
             };

       new Thread(run).start();
    }

}
