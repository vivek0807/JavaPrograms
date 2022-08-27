package $Expertise.DataStructures.implemented;

import java.util.HashSet;


public class DSPractice {
    void powerset(String s){
        int len=s.length();

        for (int i = 0; i <(1<<len) ; i++) {
                String str="";
            for (int j = 0; j <len ; j++) {
                if((i&(1<<j))!=0)
                    str=str+s.charAt(j);
            }
            System.out.println(str);
        }
    }
    public static void main(String[] args) {

        DSPractice dsPractice = new DSPractice();
        System.out.println((int)Math.pow(2,3));
    }
}
