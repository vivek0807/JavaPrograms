package $Expertise.DataStructures.implemented.Problems;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/*
FIND ALL THE NUMBERS IN AN ARRAY THAT RESULT IN A GIVEN TARGET.
GIVEN- WE CAN USE THE REPEATED NUMBERS
USAGE- RECCURSION
 */
class Solution{

    public void findCombinations(int startIndex, int[] remainingArray,int target ,List<Integer> templist, List<List<Integer>> answerArray){

        if (startIndex==remainingArray.length){
            if (target==0)
                answerArray.add(new ArrayList<>(templist));
            return;
        }

        if (remainingArray[startIndex]<=target){
            templist.add(remainingArray[startIndex]);
            findCombinations(startIndex,remainingArray,target-remainingArray[startIndex],templist,answerArray);
            templist.removeLast();
        }

        findCombinations(startIndex+1,remainingArray,target,templist,answerArray);
    }

    public List<List<Integer>> fundSums(int[] nums, int target){
        List<List<Integer>> answer= new ArrayList<>();
        findCombinations(0,nums,target,new ArrayList<>(),answer);
        return answer;
    }

}
public class CombinationSum {
    public static void main(String[] args) {
        System.out.println(new Solution().fundSums(new int[]{2,3,5},8));
    }
}
