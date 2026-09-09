package $Expertise.DataStructures.implemented.Problems;

import java.util.HashMap;

class LongestRepeatingCharacterReplacementSolution{

   public int repeatedCharacterString(String s, int k){
        int maxFreq=0;
        HashMap<Character,Integer> map = new HashMap<>();
        int left=0;
        int replacements=0;
        int maxLength=0;
        for (int i=0;i< s.length();i++){

            map.put(s.charAt(i),map.getOrDefault(s.charAt(i),0)+1);

            maxFreq= Math.max(maxFreq,map.get(s.charAt(i)));

            replacements=(i-left+1)-maxFreq;

            while (replacements>k){

                map.put(s.charAt(left),map.get(s.charAt(left))-1);
                left++;
                replacements=(i-left+1)-maxFreq;
            }
            maxLength=Math.max(maxLength,i-left+1);

        }


        return maxLength;

    }

}
public class LongestRepeatingCharacterReplacement {
    static void main() {

        System.out.println(new LongestRepeatingCharacterReplacementSolution().repeatedCharacterString("AABABBA",1));
    }
}
