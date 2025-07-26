package $Expertise.DataStructures.implemented.Problems;

public class MinimumSizeSumSubArray {
    /**
     * <h1> Minimum Size sub Array </h1>
     * <li>Find the minimum size of a sub-array that results to a target sum</li>
     * <h3>Approach</h3>
     * <li>Use sliding window technique</li>
     * <li>Have pointer at the end and start</li>
     * <li> when sum is greater or same, update the difference and move start to left</li>
     * <li> If sum less than target then again move end one bit right</li>
     * <li> Keep doing this unless end reaches last element and start ==end or sum gets smaller than target</li>
     * @param args from command line
     */
    public static void main(String[] args) {
        int[] arr={2,3,1,2,4,3};

        int target=7;

        int start=0;
        int end=0;
        int sum=0;
        int min=Integer.MAX_VALUE;
        while (end<arr.length){
           if(sum<target){
               sum+=arr[end];
               end++;
           }
           if(arr[end-1]==target)
           { System.out.println(1);
           return;
           }
           while(sum>=target){ // Since we need to move start forward till we find the min length
               min=Math.min(min,end-start);
               sum-=arr[start];
               start++;
           }
        }
        if(min==Integer.MAX_VALUE)
            System.out.println(1);
        else
            System.out.println(min);
    }
}
