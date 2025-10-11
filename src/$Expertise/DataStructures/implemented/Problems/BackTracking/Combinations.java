package $Expertise.DataStructures.implemented.Problems.BackTracking;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>77- Combinations- Generate all possible combinations in [1..n] where the number of elements in array is K</h1>
 * <h2>Approach</h2>
 * <li>Use Backtracking</li>
 * <li>if size of sbList is equal to K, add it to the main Ans list</li>
 * <li>Use a for loop from start to n to append i to the subList</li>
 * <li>call the function again</li>
 * <li>Remove the last element</li>
 */
public class Combinations {
    static List<Integer> subList= new ArrayList<>();

    static void generateCombinations(List<List<Integer>> finalAns,int start,int end, int k){
        if (subList.size()==k)
        {
            finalAns.add(new ArrayList<>(subList));
        return;
        }

        for (int i=start;i<=end;i++)
        {
            subList.add(i);
            generateCombinations(finalAns,i+1,end,k);
            subList.removeLast();
        }
    }
    public static void main(String[] args) {
        List<List<Integer>> ansList= new ArrayList<>();
        generateCombinations(ansList,1,4,2);
        System.out.println(ansList);
    }
}
