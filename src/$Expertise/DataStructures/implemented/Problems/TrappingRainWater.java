package $Expertise.DataStructures.implemented.Problems;

/**
 * <h1>Trapping Rain Water</h1>
 * <h3>Approach-1 Using Aux Space</h3>
 * <li>Maintain two array that maintains max on the right and the max on th right</li>
 * <li>Then we will keep calculating the min from both the arrays and put it in the third array</li>
 * <li>At the end substract the height at that index with the calculated min, it its more than 0 add it to the final sum</li>
 * <h3>Approach -2, without using extra space</h3>
 * <li>Maintain two pointers left and right that will be updated on movement to max till found</li>
 * <li>we move the one when either of the two is lower than the other</li>
 * <li>we will be substracting the current position element from the max of left or right</li>
 */
public class TrappingRainWater {
    public static void main(String[] args) {
        int[] height={0,1,0,2,1,0,1,3,2,1,2,1};
        int left=0;
        int right=height.length-1;
        int maxl=height[0];
        int maxr=height[height.length-1];
        int result=0;

        while (left<right){  // break condition

            if (maxl<maxr){ // update when either of Maxes are lower
                left+=1;
                maxl=Math.max(height[left],maxl);  // Update max
                result+=(maxl-height[left]);  // add to sum

            }
            else{
                right-=1;
                maxr=Math.max(maxr,height[right]);
                result+=(maxr-height[right]);
            }
        }
        System.out.println(result);
    }
}
