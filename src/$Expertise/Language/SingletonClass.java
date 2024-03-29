package $Expertise.Language;

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
