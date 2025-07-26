package $Expertise.DataStructures.implemented.Problems.SlidingWindow;

import java.util.HashMap;

/**
 * <h1>Find the maximum sum of a sub Array of size K  in a given Array</h1>
 * <h2>Approach:- </h2>
 * <li>Sliding window with hashMap</li>
 * <li>Start with iterating on each of the elements and updating the current sum & increasing the count of the character in hashMap</li>
 * <li>Once window size is greater than = K  , increment the left pointer and decrement it from the sum </li>
 * <li>If hashmap content is 0 for a character the remove the entry</li>
 * <li>If map size == k the update the MaxSum</li>
 *
 *
 */
public class MaximumSumSubArrayOfKSize {
    public static void main(String[] args) {

        int[] nums={2,3,1,2,4,3};
        HashMap<Integer,Integer> hashMap= new HashMap<>();
        int left=0;
        int max_sum=0;
        int currSum=0;
        int k=3;
        for (int i = 0; i < nums.length; i++) {
            currSum=currSum+nums[i];
            hashMap.put(nums[i],hashMap.getOrDefault(nums[i],0)+1 );

            if (i-left+1 >k){
                currSum=currSum-nums[left];
                hashMap.put(nums[left],hashMap.get(nums[left])-1);

                if (hashMap.get(nums[left])==0)
                    hashMap.remove(nums[left]);
                left++;
            }

            if(i-left+1>k && hashMap.size()>=k)
                max_sum=Math.max(currSum,max_sum);
        }
        System.out.println(max_sum);

    }
}
