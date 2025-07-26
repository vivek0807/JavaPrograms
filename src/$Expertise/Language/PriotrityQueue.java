package $Expertise.Language;

import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

/*
-- Queue[]--> PriorityQueue[|],
--Queue[]-->Dequeue[|]-->LinkedList[|]-->ArrayDeque[|]
--It can follow a natural ordering on primitive order according to comparator provided in the begining
-- It is based on Prioriy Heap
--Ties are broken arbitarily
--it does not permit null and these are unbound
--These are not Thread safe and the thread safe version is PriorityBlockingQ
--Initial capacity 11
*/
public class PriotrityQueue {
    public static void main(String[] args) {
        Queue<Integer> queue = new PriorityQueue<>();

        queue.add(1);
        queue.add(4);
        queue.add(3);
//        queue.add(2);
        queue.add(10);




 Queue<Integer> dequeue = new ArrayDeque<>();
        dequeue.add(2);
        dequeue.add(7);
        dequeue.add(1);
        dequeue.add(4);
        dequeue.add(0);
        dequeue.forEach(System.out::println);
        dequeue.remove();// removes the first element
        dequeue.forEach(System.out::println);
    }
}
