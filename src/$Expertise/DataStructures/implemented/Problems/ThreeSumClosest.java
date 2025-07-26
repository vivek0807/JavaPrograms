package $Expertise.DataStructures.implemented.Problems;

import java.util.Arrays;

class Answer{

    public int threeSumClosest(int[] nums,int target){

        Arrays.sort(nums);
        int mid=0;
        int sum=0;
        int final_target=0;
        int diff=Integer.MAX_VALUE;
        for (int i = 0; i < nums.length-2; i++) {
            mid=i+1;
            int last=nums.length-1;
            while (mid<last){
                sum=nums[i]+nums[mid]+nums[last];
                if (sum==target)
                {
                    System.out.println(target);
                    return target;
                }
                if (sum<target)
                    mid++;
                else
                    last--;
                if (Math.abs(target-sum)<diff)
                {
                    diff=Math.abs(target-sum);
                    final_target=sum;
                }
            }
        }
       return final_target;
    }
}

public class ThreeSumClosest {


    public static void main(String[] args) {

        int[] nums={-1,2,1,-4};
        int tareget=4;
        System.out.println(new Answer().threeSumClosest(nums,tareget));
    }
}
