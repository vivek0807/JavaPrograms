package $Expertise.DataStructures.implemented.Problems.SlidingWindow;

import java.util.Arrays;
import java.util.Map;

public class BombDiffusal {
    public static void main(String[] args) {

        int[] nums={2,4,9,3};
        int k=-2;
        int sum=0;
        int pointer=0;
        int left=0;
        int right=1;
        int[] code= new int[nums.length];
        if (k>0){
            left=1;
            for(int i=1;i<=k;i++){
                sum=sum+nums[i];
                right++;
            }
            for (int i = 0; i < nums.length; i++) {
                code[i]=sum;
                sum=sum-nums[left];
                left++;
                left=left%nums.length;
                right=(right)%nums.length;
                sum=sum+nums[right];
                right++;

            }


        }
        if (k==0){
            for (int i = 0; i <nums.length ; i++) {
                code[i]=0;
            }
        }
        if (k<0){
            right=nums.length-2;
            for (int i=nums.length-2;i>(nums.length-Math.abs(k)-2);i--){
                sum=sum+nums[i];
                left=i;
            }

            for (int i=nums.length-1;i>=0;i--){
                code[i]=sum;
                sum=sum-nums[right];
                right=right-1;
                right=right%nums.length;
                if (right<0)
                    right=nums.length-1;
                left=left-1;
                left=left% nums.length;
                if (left<0)
                    left=nums.length-1;
                sum=sum+nums[left];

            }
        }
        System.out.println(Arrays.toString(code));
    }
}
