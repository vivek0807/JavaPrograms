package $Expertise.DataStructures.implemented.Problems;

/**
 * <h1>Maximum water that can be stored between two pillars </h1>
 * <h3>Solved using two pointer</h3>
 * <h3>Approach</h3>
 * <li>Start with the two pointers at the end</li>
 * <li>Change the pointer with the lower height</li>
 * <li>Keep the maximum of the generated area</li>
 */


public class ContainerWithMostWater {
    public static void main(String[] args) {

        int[] height={1,8,6,2,5,4,8,3,7};
        int left=0;
        int right=height.length-1;
        int area=0;
        while (left<right){

                area= Math.max(area,(right-left)*Math.min(height[left],height[right]));
                if (height[left]<height[right])
                    left++;
                else
                    right-=1;

        }
        System.out.println(area);
    }
}
