package $Expertise.DataStructures.implemented.Problems;

import java.util.Stack;

public class ValidParentheses {
    public static void main(String[] args) {
        String brackets="(){}{{}}{}}";
        Stack<Character> stack= new Stack<>();

        for (int i = 0; i < brackets.length(); i++) {
            char character=brackets.charAt(i);
                if(character=='{'|| character=='['|| character=='(')
                    stack.push(character);

                if(character=='}'|| character==']'|| character==')')
                {
                    if(stack.isEmpty())
                        System.out.println("Invalid");
                    else {
                        char pop = stack.pop();
                        if (character == '}' && pop != '{')
                            System.out.println("Invalid");
                        else if (character == ']' && pop != '[')
                            System.out.println("Invalid");
                        else if (character == ')' && pop != '(')
                            System.out.println("Invalid");
                    }
                }
        }
        if (stack.isEmpty())
            System.out.println("Valid Set");
        else
            System.out.println("Invalid");
    }
}
