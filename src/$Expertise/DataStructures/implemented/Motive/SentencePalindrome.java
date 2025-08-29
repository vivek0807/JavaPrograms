package $Expertise.DataStructures.implemented.Motive;

public class SentencePalindrome {

    public static boolean isSentencePalindrome(String sentence) {
        // Remove non-alphanumeric characters and convert to lowercase
        String cleaned = sentence.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

        int left = 0, right = cleaned.length() - 1;
        while (left < right) {
            if (cleaned.charAt(left) != cleaned.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {

        String sentence = "A man, a plan, a canal: Panama";
        boolean result = isSentencePalindrome(sentence);
        System.out.println("Is palindrome? " + result);

    }

}
