package $Expertise.DataStructures.implemented.Problems;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>Generate All the permutations of a given Array</h1>
 * <h2>Approach</h2>
 * <li>Base case -when temp list size gets equal to the main array size</li>
 * <li>Start with iterating on the elements of the array</li>
 * <li>if the element is present in the temp Array just continue</li>
 * <li>else add the current element to the temp array and call the function again</li>
 * <li>remove the last element from the array</li>
 */
public class Permutations {

    public static void generatePermutations(List<List<Integer>> finalList, int[] nums,List<Integer> tempList){

        if (tempList.size()==nums.length)
        { finalList.add(new ArrayList<>(tempList));
        return;
        }

        for (int number:nums){

            if (tempList.contains(number))
                continue;
            tempList.add(number);
            generatePermutations(finalList,nums,tempList);

            tempList.removeLast();
        }

    }
    public static void main(String[] args) {
        List<List<Integer>> finalList= new ArrayList<>();
        generatePermutations(finalList,new int[]{1,2,3},new ArrayList<>());
        System.out.println(finalList);
    }
}
