package $Expertise.Language;

import org.w3c.dom.ls.LSOutput;

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
