package $Expertise.Language;


import java.time.LocalDateTime;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;
import java.util.function.Function;


class PhaseOne {
    /**
     * This method demonstrates calling Thread.start with Consumer and Function, returning values
     * Thread.join waits to make this execute one by one even if two threads are created.
     *
     */
    protected void executePhaseOne() {
        Consumer<Integer> consumer = (x) -> {
            for (int i = 0; i < x; i++) {
                System.out.println(i);
                try {
                    Thread.sleep(100);
                    if (i == 40)
                        Thread.currentThread().interrupt();
                    if (Thread.currentThread().isInterrupted())
                        break;
                } catch (Exception e) {
                    System.out.println("Thread was interrupted");
                }

            }
        };

        Runnable run = () -> consumer.accept(50);
        Thread consumerthread = new Thread(run);
        consumerthread.start();
        try {
            consumerthread.join();
            Function<Integer, String> function = (i) -> {
                return "The found value is" + i;
            };
            Callable<String> callable = () -> function.apply(30);

            FutureTask<String> future = new FutureTask<>(callable);

            Thread futureThread = new Thread(future);
            futureThread.start();
            System.out.println(future.get());
        } catch (Exception e) {
            System.out.println("Callable thread was interrupted");
        }

        System.out.println("Main Thread Executed");
    }
}

class PhanseTwo {

}

class PhaseThree {
    /**
     * This method demonstrates use of a Shared lock object with Sync keyword
     * <li>VVI- an array is passed as reference and then its value is used because java is pass by value for functions</li>
     * <li>passing just a variable gives both the threads their own copies and increment goes independent</li>
     * <li>Sync is a type of Intrinsic lock where JVM keep s the whole control</li>
     */
    void lockUsingMonitor() {
        Object object = new Object();

        Consumer<int[]> consumer = (x) -> {
            while (x[0] < 50) {
                synchronized (object) {
                    try {
                        System.out.println("The value of x is " + x[0] + " Current Thread " + Thread.currentThread().getName() + " Time " + LocalDateTime.now());
                        x[0]++;
                        Thread.sleep(100);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            }
        };

        int[] x = {5};

        new Thread(() -> consumer.accept(x)).start();
        new Thread(() -> consumer.accept(x)).start();
    }

    /**
     * Other variants of lock
     * declaring sync on method takes lock on class Objec
     * This is equivalent to Synchronized(this)
     * declaring synchronized on a static block takes a lock on The whole class like sync(PhaseThree.class)
     * This prevents any other thread accessing the block be it any other object
     */
    synchronized int increment(int x) {
        return x++;
    }
}

class PhaseFour {
    /**
     * <h1>Notify and Wait examples</h1>
     * <li>Wait is an Object property and make that thread wait which is currently acquiring the monitor object</li>
     * <li>Wait is placed always in while loop to avoid spurious wake up by OS</li>
     */
    void executeWaitAndNotify() {
        Object lockable = new Object();

        Consumer<int[]> consumer = (x) -> {
            synchronized (lockable) {
                try {
                    while (x[0] < 50) {
                        x[0]++;
                        System.out.println("Value of x is " + x[0] + " Thread name -" + Thread.currentThread().getName());
                        if (x[0] == 10) {
                            while (x[0] < 20) { // This is to avoid spurious wake up
                                System.out.println("Locking by thread " + Thread.currentThread().getName());
                                lockable.wait();
                            }
                        }

                        if (x[0] == 20) {
                            System.out.println("Releasing lock by " + Thread.currentThread().getName());
                            lockable.notify();
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Thread interruption");
                }
            }
        };

        int[] x = new int[]{1};
        Thread thread = new Thread(() -> consumer.accept(x));
        Thread thread1 = new Thread(() -> consumer.accept(x));

        thread.start();
        thread1.start();

    }
}

class PhaseSix extends PhaseFour {
    /**
     * <h1>Explicit Locks</h1>
     * <li> Implemented using Lock Interface and Bleow classes</li>
     * <li>ReentrantLock-Only Thread which acquired lock can work with block</li>
     * <li>Allows to configure fairnes policy</li>
     * <li>ReetrantReadWriteLocks- Used when mostly there are more reads and few writes</li>
     * <li>write and read locks are different</li>
     * <li>With locks we use await/signal/signalAll after creating Condition</li>
     * <li>Unlocking Without locking will give Thread Illeagal state exception</li>
     * <li>Fairness policy- Provide lock on first-cum-first serve basis</li>
     */
    void explicitLocking() {
        Lock lock = new ReentrantLock();

        Condition condition = lock.newCondition();
        Consumer<int[]> consumerWithLocks = (x) -> {

                try {
                    while (x[0] < 250) {
                        lock.lock(); // Here lock works like start of sync block
                            System.out.println("Incrementing x from " + x[0] + " Thread name" + Thread.currentThread().getName());

                            x[0]++;
                            if (x[0] == 20) {
                                System.out.println("Put to wait " + Thread.currentThread().getName());
                                condition.await();// The thread which had the lock was put to wait

                            }

                            if (x[0] == 24) {
                                System.out.println(" Removing wait " + Thread.currentThread().getName());
                                condition.signal();// Thread 1 signals Thread 0 to wake up But thread 0 needs to get the lock
                                                    // Currently Thread1 has the lock
                            }
                        lock.unlock(); // release lock from current thread so that other thread can take over
                    }
                } catch (Exception e) {
                    System.out.println("Exception" + e);
                }
        };

        int[] x = {0};
        Thread t1 = new Thread(() -> consumerWithLocks.accept(x));
        Thread t2 = new Thread(() -> consumerWithLocks.accept(x));
        t1.start();
        t2.start();
    }
}

public class MultiThreadingMastery {
    static void main() {
        PhaseSix phaseSix = new PhaseSix();
        phaseSix.explicitLocking();
    }
}
