package $Expertise.DataStructures.implemented.Problems;

import java.util.Arrays;

/**
 * <h1>80- Remove Duplicates from a Sorted Array where only two elements remains same</h1>
 * <h2>Approach</h2>
 * <li>Keep a right pointer and increment it any ways</li>
 * <li>Keep a left pointer and increment it only in case of twice repeats</li>
 */
public class RemoveDoubleDuplicatesFromSortedArray {
    public static void main(String[] args) {

        int [] nums={1,1,1,2,2,3};

        int left=0;
        int right=0;

        while (right<nums.length){
            int count=1;
            while (right+1<nums.length && nums[right]==nums[right+1])
            {  right++;
            count++;
            }
            for (int i = 0; i <Math.min(count,2) ; i++) {
                nums[left]=nums[right];
                left++;
            }
        right++;

        }

        System.out.println(Arrays.toString(nums));
    }
}
