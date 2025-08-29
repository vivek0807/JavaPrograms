import java.io.*;
import java.util.HashMap;

/**
 *
 */
public class Main {

    //The approach to this problem is that within a given window i.e right -left the highest frequency
    //character must be lesser than K. in case this happens we shrink the window by incrementing left
    //And reducing the frequency in the map
    //At the end we calculate the maximum window size after this operation.
    public static void main(String[] args) throws IOException {
        String s = s = "AABABBA";
        int k = 1;

        HashMap<Character, Integer> freqMap = new HashMap<>();
        int maxCount = 0, maxLength = 0;
        int left = 0;
        /**
         * Initizalizing the hashMap with frequency of each character
         */
        for (int right = 0; right < s.length(); right++) {
            char ch = s.charAt(right);
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);

            maxCount = Math.max(maxCount, freqMap.get(ch));
            // We target the to remove the character that has maximum number of occurences within a given window
            //
            while ((right - left + 1) - maxCount > k) {
                char leftChar = s.charAt(left);
                freqMap.put(leftChar, freqMap.get(leftChar) - 1);
                left++;
            }

            maxLength = Math.max(maxLength, right - left + 1);
        }
            System.out.println(maxLength);

    }

}


