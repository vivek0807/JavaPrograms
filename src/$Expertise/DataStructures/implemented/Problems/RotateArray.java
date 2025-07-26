package $Expertise.DataStructures.implemented.Problems;

import java.util.Arrays;

public class RotateArray {
    /**
     *
     * <h1>Rotate Array</h1>
     * <h4> Brute Force Approach</h4>
     *<li>Keep rotating the array in each for loop with one by one element to get till the final number rotation</li>
     *<h3>Optimal Approach</h3>
     * <li>Reverse array in parts<li/>
     * <li>Reverse from 0 to rotation-1</>
     * <li>Reverse from rotation to n</li>
     * <li>Reverse entire array</li>
     * <h3> For Right Rotation</h3>
     * <li>Reverse entire array</li>
     * <li>Reverse from 0to k-1 and then k to n-1 </li>
     */
    static void reverse(int[] nums, int start, int end){
        while(start<end){
            int temp=nums[end];
            nums[end]=nums[start];
            nums[start]=temp;
            start++;
            end--;
        }
    }
    public static void main(String[] args) {
        int[] nums={1,2,3,4,5,6,7,8,9,0};
        int rotate=3%nums.length;
        reverse(nums,0,rotate-1);
        reverse(nums,rotate,nums.length-1);
        reverse(nums,0,nums.length-1);
        System.out.println(Arrays.toString(nums));
    }
}
