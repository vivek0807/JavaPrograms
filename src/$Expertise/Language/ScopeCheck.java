package $Expertise.Language;
import $Expertise.Language.Exceptions;

class Test1 extends Exceptions{
    private String s1= super.ExceptionClass;//"This was private in outer class";
    String s2="This was edfualt in outer class";
    public String s3="This was public string in inner class";
    protected String s4="Protected var of inner class";

    class InnerClass{
        private String s1="Private var of Inner class";
        String s2="Default var of inner class";
        public String s3="public var of inner class";
        protected String s4="Protected var of inner class";
    }

    void printer(){

    }
}

class Test2 extends Test1{
    private String s1="This String is Test2 private";
    String s2=super.ExceptionClass;//"Default string of Test2 class";

    void PrinterTest2(){
        System.out.println(" The private string was not accessible");
        System.out.println(super.s2+" "+super.s3+" "+super.s4);

        InnerClass I= new InnerClass();
        System.out.println(I.s2+" "+I.s3+" "+I.s4);
    }

}
public class ScopeCheck {
    public static void main(String[] args) {

        Test2 t2=new Test2();
        t2.PrinterTest2();

    }
}
