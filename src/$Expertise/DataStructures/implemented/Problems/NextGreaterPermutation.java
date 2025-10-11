package $Expertise.DataStructures.implemented.Problems;

import java.util.Arrays;

/**
 * <h1>31- Next Permutation- Find the next greater permutation in order</h1>
 * <h2>Approach</h2>
 * <li>First find the pivot element from 2nd last from where next elements are smaller</li>
 * <li>Then again find  an element from the end that is smaller than the pivot position</li>
 * <li>Swap the pivot and smaller element found</li>
 * <li> Reverse the remaining Array on the RIGHT</li>
 */

public class NextGreaterPermutation {
    static void swap(int[] arr,int first,int second){

        int temp=arr[second];
        arr[second]=arr[first];
        arr[first]=temp;
    }

    static void reverse(int start,int end,int[] arr){

        while (start<end){
            swap(arr,start++,end--);
        }
    }

    public static void main(String[] args) {

        int[] nums={1,2,3};

        int i=nums.length-2;
        while (i>=0 && nums[i]>=nums[i+1]){
            i--;
        }

        if (i>=0){

            int j=nums.length-1;
            while (nums[i]>=nums[j])
                j--;

           swap(nums,i,j);
        }

        reverse(i+1,nums.length-1,nums);

        System.out.println(Arrays.toString(nums));

    }
}
