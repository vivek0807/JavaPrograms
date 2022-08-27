package $Expertise.Language;



import java.util.Locale;
/*
String are stored in Heap area
String are immutable as same String are made to point to the same Object in String pool
    If Creted using new keyword then they are stored in Heap area
    else in SCP

--DIFFERENCE BW String Builder and String Buffer
    String buffer class Synchronised methods hence it is thread safe where as opposite case in String builder
    -String Builder is fasted than String Buffer
-- Overriding toString class will call the toString method directly when trying to print the class  LINE 58
* */
class toStringOverride{
    int number1;
    int number2;
    String sentence;

    public toStringOverride(int number1, int number2, String sentence) {
        this.number1 = number1;
        this.number2 = number2;
        this.sentence = sentence;
    }

    @Override                       //Overriding a toString method to customise outputs
    public String toString() {
        return number1+" "+sentence+" "+number2;
    }
}

class showsStringExamples{

    String one="One";
    String two="One";
    String three=new String("Three");
    String sentence="Some day I will get into Product based company";
    char buff[]={'l',',','e','t','s','l','e','a','r'};
    String learn=new String(buff);// CONVERTS ARRAY OF CHARS TO sTRING

    void methodCall(){
        System.out.println(three);
        System.out.println("Comparison");
        System.out.println("One ==two "+one==two);
        System.out.println(one.equals(two));
        System.out.println(one.equalsIgnoreCase(two));
        System.out.println(one.charAt(0));
        char arr[]=one.toCharArray();
        for (int i = 0; i <one.length() ; i++) {
            System.out.print(" "+arr[i]);

        }
        System.out.print('\n');
        System.out.println(sentence.contains("I will"));
        System.out.println(one.toUpperCase());
        System.out.println(one.trim());
        System.out.println(one.replace(" ",""));
        System.out.println(one.indexOf('0'));


    }
}

class Practice{
    String getSentence;

    public Practice(String getSentence) {
        this.getSentence = getSentence;
    }
    public void manipulates(){
        System.out.println(getSentence.charAt(0));
        StringBuilder sb=new StringBuilder(getSentence);
        System.out.println(sb.reverse());
        for (String s:getSentence.split("")) {
            System.out.println(s);
        }
        System.out.println(getSentence.toUpperCase());
        System.out.println(getSentence==new String(getSentence));
        System.out.println(getSentence.getBytes());
        System.out.println(getSentence.equals(new String(getSentence)));
    }


    @Override
    public String toString() {
        return getSentence;
    }
}

public class Strings {
    public static void main(String[] args) {

        //System.out.println(new toStringOverride(5,2,"minus 3 is ").toString());
        System.out.println("Hello".compareTo("Hello0"));
      // new showsStringExamples().methodCall();
        //indexOf method return the position of word/char in String
        //System.out.println(new toStringOverride(5,2,"Difference is 3"));
        Practice p =new Practice("Exemplar Sentence");
        System.out.println(p);
       // p.manipulates();
        showsStringExamples Examples=new showsStringExamples();
      //  Examples.methodCall();

        String s=new String("I will give my 100%");
       s= s.replaceAll("\\s+","");
        System.out.println(s);

    }
}
