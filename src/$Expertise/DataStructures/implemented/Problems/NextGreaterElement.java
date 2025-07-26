package $Expertise.DataStructures.implemented.Problems;

import java.util.Arrays;
import java.util.Stack;

/**
 * Find the Next greater Element in order in Array
 */
public class NextGreaterElement {
    public static void main(String[] args) {

        int[] nums={4,3,5,1,12,2,5,3,1,2,6};
        Stack<Integer> memoryStack = new Stack<>();
        memoryStack.push(nums[nums.length-1]);
        int[] finalArray= new int[nums.length];
        finalArray[nums.length-1]=nums[nums.length-1];
        for (int i=nums.length-2;i>=0;i--){

            if (nums[i]<memoryStack.peek())
            {
                finalArray[i]=memoryStack.peek();
                memoryStack.push(nums[i]);

            }
           else if (memoryStack.peek()<nums[i])
            {
                while (!memoryStack.isEmpty() && memoryStack.peek()<nums[i] )
                    memoryStack.pop();
                if (memoryStack.isEmpty())
                {
                    finalArray[i]=-1;
                memoryStack.push(nums[i]);
                }
                else
                {
                    finalArray[i]=memoryStack.peek();
                    memoryStack.push(nums[i]);
                }
            }

        }
        System.out.println(Arrays.toString(finalArray));
    }
}
