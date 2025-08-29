package $Expertise.DataStructures.implemented.Motive;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AllPalinDromePermutation {


    public List<String> getPalindromicPermutations(String s) {
        Set<String> result = new HashSet<>();
        permute(s.toCharArray(), 0, result);

        List<String> palindromes = new ArrayList<>();
        for (String str : result) {
            if (isPalindrome(str)) {
                palindromes.add(str);
            }
        }

        return palindromes;
    }

    private void permute(char[] arr, int index, Set<String> result) {
        if (index == arr.length) {
            result.add(new String(arr));
            return;
        }

        Set<Character> seen = new HashSet<>();
        for (int i = index; i < arr.length; i++) {
            if (seen.contains(arr[i])) continue; // avoid duplicate permutations
            seen.add(arr[i]);

            swap(arr, i, index);
            permute(arr, index + 1, result);
            swap(arr, i, index); // backtrack
        }
    }

    private boolean isPalindrome(String s) {
        int l = 0, r = s.length() - 1;
        while (l < r) {
            if (s.charAt(l++) != s.charAt(r--)) return false;
        }
        return true;
    }

    private void swap(char[] arr, int i, int j) {
        char temp = arr[i]; arr[i] = arr[j]; arr[j] = temp;
    }



    public static void main(String[] args) {

        AllPalinDromePermutation pp = new AllPalinDromePermutation();
        String input = "aabb";
        List<String> palindromes = pp.getPalindromicPermutations(input);
        System.out.println("Palindromic permutations: " + palindromes);

    }
}
