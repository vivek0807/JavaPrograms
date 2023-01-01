import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
class Util {
    public static String capitalize(String str) {
        String s;
        if (str == null || str.isBlank()) {
            System.out.println("Before: "+str);
            System.out.println("After: "+str);
            return str;
        }

        if (str.length() == 1) {
            s=str.toUpperCase();
            System.out.println("Before: "+str);
            System.out.println("After: "+str.toUpperCase());
            return str.toUpperCase();
        }
        s=Character.toUpperCase(str.charAt(0)) + str.substring(1);
        System.out.println("Before: "+str);
        System.out.println("After: "+s);
        return s;
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println();
        System.out.println(Util.capitalize(""));
    }
    }



