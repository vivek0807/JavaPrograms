package $Expertise.DataStructures.implemented.Problems;

import java.util.HashSet;

/* Longest Substring with non - repeating characters*/
public class LongestSubWithNRCh {
    public static void main(String[] args) {
        String s="abccg";
        HashSet<Character> hashChar= new HashSet<>();
        int end=0;
        int last_end=0;
        int max=Integer.MIN_VALUE;
        int pointA=0;
        int pointB=0;

        for (int i = 0; i < s.length(); i++) {
            if(hashChar.contains(s.charAt(i))){
                hashChar.clear();
                hashChar.add(s.charAt(i));
                last_end=i;

            }
            else {
                hashChar.add(s.charAt(i));
                if(end-last_end>max){
                    pointA=last_end;
                    pointB=end;
                    max=end-last_end;
                }
                end+=1;
            }
        }
        System.out.println(pointA+" "+(pointB+1));
    }
}
