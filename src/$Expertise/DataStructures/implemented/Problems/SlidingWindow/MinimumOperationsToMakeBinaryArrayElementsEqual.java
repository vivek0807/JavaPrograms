package $Expertise.DataStructures.implemented.Problems.SlidingWindow;

/**
 * <h1>Leetcode 3191- Minimum Number of flips to make all the elements of a binary array =1</h1>
 * <h2>Approach</h2>
 * <li>If an element is 0 at flip 3 next elements from there</li>
 * <li>if last 2 elements are 0 just return -1</li>
 */
public class MinimumOperationsToMakeBinaryArrayElementsEqual {
    public static void main(String[] args) {
        int[] nums={0,1,1,1};
        int count=0;
        for (int i=0;i<nums.length-2;i++){
            if (nums[i]==0){
                nums[i]=Math.abs(nums[i]-1);
                nums[i+1]=Math.abs(nums[i+1]-1);
                nums[i+2]=Math.abs(nums[i+2]-1);
                count++;
            }
        }
        if (nums[nums.length-1]==0 || nums[nums.length-2]==0)
            System.out.println("-1");
        else
            System.out.println(count);

    }
}
