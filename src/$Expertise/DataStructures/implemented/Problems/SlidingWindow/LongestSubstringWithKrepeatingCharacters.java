package $Expertise.DataStructures.implemented.Problems.SlidingWindow;

import java.util.HashMap;

/**
 *<h1>
 * 395. Longest Substring with At Least K Repeating Characters</h1>
 * <h3>Approach</h3>
 * <li>Start with looping on all 26 unique characters</li>
 * <li>for each iteration check the entire string for number of unique characters, here we maintain with var unique</li>
 * <li>and in countAtLeastK we maintain how many of them have appeared at least k times</li>
 * <li>We then need to reduce the number of uniqueness to nth Charater getting checked</li>
 * <li>Then if either of these frequencies matches K then reduce the countAtLeastK</li>
 * <li>Similarly reduce the number of unique characters and increment start</li>
 * <li>At the end find the max len</li>
 */
public class LongestSubstringWithKrepeatingCharacters {
    public static void main(String[] args) {

        int maxLen = 0;
        String s="aaabb";
        int n = s.length();
        int k=3;
        for (int uniqueTarget = 1; uniqueTarget <= 26; uniqueTarget++) {
            HashMap<Character, Integer> freqMap = new HashMap<>();
            int start = 0, end = 0;
            int unique = 0;
            int countAtLeastK = 0;

            while (end < n) {
                char endChar = s.charAt(end);
                freqMap.put(endChar, freqMap.getOrDefault(endChar, 0) + 1);
                if (freqMap.get(endChar) == 1) unique++;
                if (freqMap.get(endChar) == k) countAtLeastK++;
                end++;

                while (unique > uniqueTarget) {
                    char startChar = s.charAt(start);
                    if (freqMap.get(startChar) == k) countAtLeastK--;
                    freqMap.put(startChar, freqMap.get(startChar) - 1);
                    if (freqMap.get(startChar) == 0) {
                        freqMap.remove(startChar);
                        unique--;
                    }
                    start++;
                }

                if (unique == uniqueTarget && unique == countAtLeastK) {
                    maxLen = Math.max(maxLen, end - start);
                }
            }
        }

        System.out.println( maxLen);
    }
}
