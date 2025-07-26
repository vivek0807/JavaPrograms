package $Expertise.DataStructures.implemented.Problems;

import java.util.Arrays;

/**
 * <h1>product of array except self</h1>
 * <h2>Approach :-</h2>
 * <li>Maintain two arrays that contain product of the elements except the first and the last elements respectively</li>
 * <li>In the final ans array multiply the same left and reight array with the same position element to get the final answer array </li>
 * <li></li>

 */
public class ProductOfArrayExceptSelf {
    public static void main(String[] args) {
       int[] nums={1,2,3,4};

       int[] right= new int[nums.length];
       int[] left= new int[nums.length];

       left[0]=1;

        for (int i = 1; i <nums.length ; i++) {
            left[i]=left[i-1]*nums[i-1]; //consider i-1 as the product has to started from i-1 element
        }

        right[right.length-1]=1;

        for (int i= nums.length-2;i>=0;i--){
            right[i]=nums[i+1]*right[i+1];
        }
        int[] ans= new int[nums.length];

        for (int i=0;i<nums.length;i++){
            ans[i]=right[i]*left[i];
        }
        System.out.println(Arrays.toString(ans));
    }
}
