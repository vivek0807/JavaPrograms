package $Expertise.DataStructures.implemented.Problems;

import java.util.Arrays;
//Find the three index elements that result as the given sum; i!=j!=k
public class ThreeSum {
    public static void main(String[] args) {

        int[] arr={1,2,5,9,8,1,10,4,9};
        int target=19;
        Arrays.sort(arr);

        for (int i = 0; i < arr.length-2 ; i++) {
            int right=arr.length-1;
            int left=i+1;
            int subTarget=target-arr[i];

            while (left<right){
                if (arr[left]+arr[right]>subTarget)
                    right--;
                else if (arr[left]+arr[right]<subTarget)
                    left++;
                else if (arr[left]+arr[right]==subTarget) {
                    System.out.printf("%d %d %d \n",arr[i],arr[left],arr[right]);
                   break;
                }
            }
        }

    }
}
