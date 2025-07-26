package $Expertise.Language;
// FIRST WE WILL HAVE TO CREATE THE OBJECT OF THE OUTER CLASS USING WHICH WE CREATE THE OBJECT OF THE INNER CLASS
//
class Outer{

    class Inner{

        void innerClass(){
            System.out.println("From inner class");
        }
    }

    void outerMethod(){
        System.out.println("From Outer Method");
    }
}


public class InnerOuterClass {
    public static void main(String[] args) {

        Outer outer = new Outer();
        Outer.Inner inner= outer.new Inner();

        inner.innerClass();

    }
}
