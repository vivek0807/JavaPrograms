package $Expertise.DataStructures.implemented.Motive;

import java.util.Stack;

/**
 *
 */
public class BasicCalculatorII {
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int num = 0;
        char sign = '+';
        s = s.replaceAll(" ", ""); // remove spaces

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (Character.isDigit(ch)) {
                num = num * 10 + (ch - '0');
            }

            if (!Character.isDigit(ch) || i == s.length() - 1) {
                if (ch == '+' || ch == '-' || ch == '*' || ch == '/' || i == s.length() - 1) {
                    switch (sign) {
                        case '+': stack.push(num); break;
                        case '-': stack.push(-num); break;
                        case '*': stack.push(stack.pop() * num); break;
                        case '/': stack.push(stack.pop() / num); break;
                    }
                    sign = ch;
                    num = 0;
                }
            }
        }

        int result = 0;
        for (int val : stack) result += val;
        return result;
    }

    public static void main(String[] args) {
        BasicCalculatorII calc = new BasicCalculatorII();
        String expression = "3+2*2";
        System.out.println("Result: " + calc.calculate(expression)); // Output: 7
    }
}

