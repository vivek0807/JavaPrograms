package $Expertise.DataStructures.implemented.Graphs;

/**
 you are designing a multi-threaded task scheduler that executes tasks with dependency constraints . There are N tasks numbered from 0 to N-1 . some tasks depend on other task meaning a task can only start execution after all its dependencies are completed . your scheduler runs on M worker threads and shoould schedule task such that
 No task starts before all it dependencies are completed
 At most M tasks can run concurrently
 Task run instatenously for this problem
 Your goal is gto compute minimum number of time units (ticks) needed to complete all tasks where in each tick any no. of eligible task can run concurretly up to M .
 Write java code for this
 static int minTimeToCompleteTasks(int N , int M , int D , int[][] dependencies){
 }

 D represents the number of dependency relations
 array in the argment is list of pairs(a,b) where each pair represnt the task a depends on task b (i.e b nust complete before a can start)
 */
import java.util.*;

 class TaskSchedulerWithGraph {

    public static int minTimeToCompleteTasks(int N, int M, int D, int[][] dependencies) {
        List<List<Integer>> graph = new ArrayList<>();
        int[] inDegree = new int[N];

        // Initialize graph
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }

        // Build graph and in-degree array
        for (int[] dep : dependencies) {
            int a = dep[0], b = dep[1];
            graph.get(b).add(a); // b -> a
            inDegree[a]++;
        }

        // Queue for tasks with no dependencies
        Queue<Integer> readyQueue = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            if (inDegree[i] == 0) {
                readyQueue.offer(i);
            }
        }

        int ticks = 0;
        int completedTasks = 0;

        while (completedTasks < N) {
            int tasksThisTick = Math.min(M, readyQueue.size());
            List<Integer> toProcess = new ArrayList<>();

            for (int i = 0; i < tasksThisTick; i++) {
                int task = readyQueue.poll();
                toProcess.add(task);
                completedTasks++;
            }

            for (int task : toProcess) {
                for (int neighbor : graph.get(task)) {
                    inDegree[neighbor]--;
                    if (inDegree[neighbor] == 0) {
                        readyQueue.offer(neighbor);
                    }
                }
            }

            ticks++;
        }

        return ticks;
    }

    // Example usage
    public static void main(String[] args) {
        int N = 5, M = 2, D = 4;
        int[][] dependencies = {{1, 0}, {2, 0}, {3, 1}, {4, 2}};
        System.out.println(minTimeToCompleteTasks(N, M, D, dependencies)); // Output: 3
    }
}

public class TaskScheduler {

    public static void main(String[] args) {

    }
}
