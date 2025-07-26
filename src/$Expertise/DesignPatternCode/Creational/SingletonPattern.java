package $Expertise.DesignPatternCode.Creational;

/**
 * <h1>Singleton Pattern</h1>
 * <h2>Required when only one instance needed per application context</h2>
 * <h2>Things to ensure while creating singleton class</h2>
 * <li>Lazy or eager instantiation</li>
 * <li>Concurrency- to enable this we wil use volatile and Synchronized keyword</li>
 * <li>Global access</li>
 * <li>Private Constructor</li>
 */
class Singleton{
    private static volatile  Singleton instance; // Single copy of vaiable in memory
    private Singleton(){}
    public static Singleton getInstance(){
        if (instance==null)
        synchronized (Singleton.class){  // making only spscific block synced
            {
                instance= new Singleton();
                System.out.println("Singleton class in initialized");
            }
        }
        return instance;

    }

}

public class SingletonPattern {
    public static void main(String[] args) {
        Singleton.getInstance();
    }
}
