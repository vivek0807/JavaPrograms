package $Expertise.DataStructures.implemented.Problems;

import java.util.HashSet;

public class LongestUniqueSubstring {
    public static int lengthOfLongestSubstring(String s) {
        HashSet<Character> hashSet= new HashSet();
        int lp=0;
        int rp=0;
        int maxL=0;
        int max_out=Integer.MIN_VALUE;
        while(rp!=s.length()){
            if (!hashSet.contains(s.charAt(rp)))
            {
                hashSet.add(s.charAt(rp));
                ++rp;
            }
            else {
                hashSet.remove(s.charAt(lp));
                ++lp;

            }
            maxL=Math.max(maxL,rp-lp);
        }
        return maxL;
    }
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("pwwkew"));
    }
}
