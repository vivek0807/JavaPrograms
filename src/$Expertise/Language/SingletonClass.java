package $Expertise.Language;
/*
WAYS OF CREATING A SINGLETON CLASS

-EAGER INITIALIZATION  - MAKING A CALL USING STATIC BLOCKS
-LAZY "- THE USUAL METHOD
-SYNC BLOCK
-DOUBLE CHECK LOCK
-BILL PUGH SOLUTION
-ENUM SINGLETON
 */
class Singleton implements Cloneable{

    public static Singleton current_state=null;

    String s;

    private Singleton(String s) {
        this.s = s;
    }

    public  static Singleton Initilizer(){
        if(Singleton.current_state==null)
            current_state=new Singleton("This is a singleton class");

            return current_state;
    }

    @Override
    public String toString() {
        return this.s;
    }
}

public class SingletonClass {

    public static void main(String[] args) {
        Singleton sc = Singleton.Initilizer();
        System.out.println(sc.hashCode());
//        Singleton copy =
    }
}

/************************************/
/* IMMUTABLE CLASS -- declared as final and all the fields will be final*/