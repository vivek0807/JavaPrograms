package $Expertise.DesignPatternCode;

import jdk.jshell.spi.ExecutionControl;

import java.util.concurrent.locks.ReentrantLock;

public class DeadLockSimulation {
    public static  final ReentrantLock lock1= new ReentrantLock();
    public static  final ReentrantLock lock2= new ReentrantLock();
    public static void main(String[] args) throws Exception {

        Thread thread1=new Thread(()->{
            lock1.lock();
            try {
                System.out.println(" Acquired lock 1");
                Thread.sleep(100);
                lock2.lock();
            }catch (Exception exception){
                System.out.println("Unable to acquire lock");
            }
           finally {
                lock1.unlock();
            }
        });

        Thread thread2= new Thread(()->{
            lock2.lock();
            System.out.println("Acquired lock 2");

            try {
                Thread.sleep(100);
                System.out.println("trying to get lock 1");
                lock1.lock();
            }
            catch (Exception e){

            }
            finally {
                lock2.unlock();
            }
        });

        thread1.start();
        thread2.start();
    }
}
