package $Expertise.DataStructures.implemented.Problems;

import java.util.Arrays;

public class ReverseWordsInArray {

   static String reversedString(String input){
        String arr[]= input.split(" +");

        String reverse_string="";
        for(int i=arr.length-1; i>=0;i--){
            reverse_string=reverse_string + arr[i].trim();
            if (i> 0 && !arr[i - 1].isEmpty())
                reverse_string=reverse_string+" ";
        }

        return  reverse_string;

    }

    static int fisrtIndex(String haystack, String needle){
        int startindex=-1;
        int matchpoint=0;
        boolean found=false;
        for (int i = 0; i <haystack.length() ; i++) {

            if (haystack.charAt(i)==needle.charAt(matchpoint) ){
                if (matchpoint==needle.length()-1)
                     return i-matchpoint;
                else
                    ++matchpoint;
            }
            else {
                matchpoint=0;
            }
        }

        return startindex;
    }
    public static void main(String[] args) {

       // System.out.println(fisrtIndex("mississippi","issip"));
                                               //issip

        System.out.println("mississippi".contains("issip"));

        for (char ch : "hellp".toCharArray()){

        }
    }
}
