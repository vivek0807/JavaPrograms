package $Expertise.DataStructures.implemented.Problems;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>Problem - Generate all balanced parantesis with given number of combinations </h1>
 * <h1>Approach</h1>
 * <li>Base case - when no.of open == no of closed and == given n, add the current string to List and return  </li>
 * <li>check if open count is less than n and cal function with increased n+1</li>
 * <li>check if close count is less than open, call function with close count +1</li>
 * <li>At the end of all the calls must remove the last character from the StringBuffer </li>
 */
public class GenerateParentheses {
    static StringBuilder stack= new StringBuilder();
    static List<String> finalList= new ArrayList<>();
    static void generateParentheses(int openCount, int closedCount,int parenthesesCount){

        if (openCount==closedCount && closedCount==parenthesesCount){
            finalList.add(stack.toString());
            return;
        }

        if (openCount<parenthesesCount)
        {
            stack.append("(");
            generateParentheses(openCount+1,closedCount,parenthesesCount);
            stack.deleteCharAt(stack.length()-1);

        }

        if (closedCount<openCount){
            stack.append(")");
            generateParentheses(openCount,closedCount+1,parenthesesCount);
            stack.deleteCharAt(stack.length()-1);
        }

    }
    public static void main(String[] args) {
            generateParentheses(0,0,1);
        ArrayList<String> returnAns= new ArrayList<>(finalList);
        stack.delete(0,stack.length());
        finalList.clear();
        System.out.println(returnAns);

    }
}
