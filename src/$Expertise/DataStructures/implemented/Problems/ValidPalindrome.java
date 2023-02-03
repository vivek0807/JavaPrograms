package $Expertise.DataStructures.implemented.Problems;

public class ValidPalindrome {

    public static void main(String[] args) {
        String s=" ";
        s=s.replaceAll("\\p{Punct}","");
        s=s.replaceAll("\\s","").toLowerCase();

        StringBuilder sbr= new StringBuilder(s);


        System.out.println(s.equals(sbr.reverse().toString()));
    }
}
