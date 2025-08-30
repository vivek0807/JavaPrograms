package $Expertise.DesignPatternCode.Behavorial;

/**
 * <h1>Observer Pattern</h1>
 * <h3>This pattern is when want that a change of state affects other registered objects</h3>
 * <h3>The Objects must be able to register and de register them from an observation</h3>
 * <li> To implement this we can simply have a list of Objects registered and on post
 * completetion or a dependent function can iterate though all teh objects to cal their
 * notify methods</li>
 * <li>1. Interface Subject has register and deregister methods</li>
 * <li>2. Interface Observer has methods update and setSubject</li>
 * <li>3. Topic class implements Subject and calls notifyAll after function call</li>
 * <li>4. Subscriber implements Observer and implements the Update method</li>
 */
public class ObserverPattern {
    public static void main(String[] args) {

    }
}
