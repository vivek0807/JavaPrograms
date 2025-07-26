package $Expertise.DataStructures.implemented.LinkedList;


import java.util.Arrays;

public class MergeLists {
    public static void main(String[] args) {

        int[] nums={3,0,1};

        for (int i=0;i<nums.length;i++){
            if (nums[i]!=0){
                nums[nums[i]-1]=-1;
            }
        }
        System.out.println(Arrays.toString(nums));
    }
}
