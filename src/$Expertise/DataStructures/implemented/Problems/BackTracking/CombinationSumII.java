//package $Expertise.DataStructures.implemented.Problems.BackTracking;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.LinkedList;
//import java.util.List;
//
//class Solution{
//
//    List<List<Integer>> finalAns= new ArrayList<>(new ArrayList<>());
//    int target;
//    public void findCombination(int currentIndex, List<Integer> list,int total,int[] candidates){
//        if (total==target)
//        { finalAns.add(new ArrayList<>(list));
//                return;
//        }
//        if (total>target || currentIndex==candidates.length)
//            return;
//
//        list.add(candidates[currentIndex]);
//        findCombination(currentIndex+1,list,total+candidates[currentIndex],candidates);
//        list.removeLast();
//        while (currentIndex+1< candidates.length && candidates[currentIndex]==candidates[currentIndex+1])
//            currentIndex++;
//        findCombination(currentIndex+1,list,total,candidates);
//    }
//    public List<List<Integer>> findCombinations(int[] candidates, int target){
//        Arrays.sort(candidates);
//        this.target=target;
//        findCombination(0,new ArrayList<>(),0,candidates);
//        return finalAns;
//    }
//}
//
//public class CombinationSumII {
//    public static void main(String[] args) {
//       Solution solution = new Solution();
//
//        System.out.println(solution.findCombinations(new int[]{10,1,2,7,6,1,5},8));
//    }
//}
