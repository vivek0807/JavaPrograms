package $Expertise.DataStructures.implemented.Problems;

import java.util.Set;
import java.util.Stack;

public class ReverserWordsInAString {
    public static void main(String[] args) {
        String s= "Sentence to reverse words";

        Stack<String> stack = new Stack<>();
        StringBuilder temp= new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            temp.append(s.charAt(i));
            if (s.charAt(i)==' ')
            {stack.push(temp.toString());
            temp=new StringBuilder();
            }
            if (i==s.length()-1)
                stack.push(temp.toString());
        }

        while (!stack.isEmpty()){
            System.out.println(stack.pop());
        }
    }
}
