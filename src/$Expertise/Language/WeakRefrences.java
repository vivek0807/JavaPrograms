package $Expertise.Language;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public class WeakRefrences {
    /**
     * <b>Weak Reference</b>- It is cleared by the JVM when it is weakely reachable which is <br>
     * object has irrelevant references pointing to it
     * <h3>Weak Vs Soft Reference</h3>
     * <b>Soft reference</b> This is basically a big LRU cache which means having a big chance of being used in future<br>
     * Has a high chance of getting deleted when not used recently.
     * <b>Weak reference</b> is what when the source referent is not there the target also gets deleted
     * <h3>A good implementation of eak reference is WeakHashMap and is mostly used for canonical mappings</h3>
     */
    public static void main(String[] args) {
        // This class is used by java to place reference objects and the same can be used to find out
        //when an object becomes phantom soft or weak referenced
        ReferenceQueue<String> referenceQueue = new ReferenceQueue<>();
        WeakReference<String> weakReference = new WeakReference<>("Weak Reference1",referenceQueue);
    }
}
