package $Expertise.Language;

public class Exceptions {
    protected String ExceptionClass;
    void generatesExceptions(){
        try{
           // System.out.println(100/0);
            char[] buff={'a','b','c'};
            //System.out.println(buff[3]);
            String s=null;
            System.out.println(s.length());
        }
        catch (ArithmeticException |NullPointerException |ArrayIndexOutOfBoundsException e){   //catches Arithmetic Exception
            if (e.toString().equals("java.lang.NullPointerException"))
                System.out.println("EceptionRaised ");
        }

    }

        public static void main (String[]args){
        Exceptions e=new Exceptions();
        e.generatesExceptions();
    }

}