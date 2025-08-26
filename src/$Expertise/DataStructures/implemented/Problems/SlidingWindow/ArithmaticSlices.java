package $Expertise.DataStructures.implemented.Problems.SlidingWindow;

public class ArithmaticSlices {
    /**
     * Find the number of subArrays with min L=3 that have the same difference
     * @param args
     * <h3>Approach</h3>
     * <li>Since k is 3 we can directly compare the elements</li>
     *
     */
    public static void main(String[] args) {


        int[] nums={1,2,3,4};
        int count=0;
        int res=0;
        for (int i=2;i<nums.length;i++){
            if (nums[i]-nums[i-1]== nums[i-1]-nums[i-2]){
                count++;
            }
            else
            {
                res =res+(count*(count+1)/2);
                count=0;
            }
        }
        System.out.println(res+(count*(count+1)/2));
    }
}
