package $Expertise.DataStructures.implemented.Problems;

import java.util.HashSet;

/* Longest Substring with non - repeating characters*/
//USE OF SLIDING WINDOW TECHNIQUE, STORE THE ARRAY COUNT AND KEEP INCRESING THE RIGHT WINDOW
 // WHEN FOUND COUNT >1 SLIDE THE LEFT WINDOW UNLESS COUNT DROPS TO 0, THEN AGAIN INCREASE THE RIGHT WINDOW
 // KEEP UPDATING THE MAX VALUE
//https://takeuforward.org/data-structure/length-of-longest-substring-without-any-repeating-character/
public class LongestSubWithNRCh {
    public static void main(String[] args) {
        String s="aabbccddefghijklmmnnooqwertyuiop";
        int left=0;
        int max=Integer.MIN_VALUE;

        System.out.println(s.length());
        HashSet<Character> hashSet= new HashSet<>();
        for (int i = 1; i < s.length(); i++) {
            if (hashSet.contains(s.charAt(i))) {
                hashSet.remove(s.charAt(left));
                left++;
            }
            hashSet.add(s.charAt(i));
            max=Math.max(max,i-left+1);
        }
        System.out.println(max);
    }
}
