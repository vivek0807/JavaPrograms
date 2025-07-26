package $Expertise.DataStructures.implemented.Problems;

public class MajorityElement {
    /**
     * Brute Force approach - USer Sorted Set to remember the appearance and value
     * <h2>Optimized Technique - Moore's voting Algorithm<h2/>
     * <p>Consider the first element to be the Majority element and occurence to be one<p/>
     * <p>on finding a different element reduce the counter and on same increase</p>
     * <p>in case the counter is 0 reset the current element to next element </p>
     * @param args
     */
    public static void main(String[] args) {

        int[] arr={2,2,1,1,1,2,2};
        int current=arr[0];
        int matched=0;
        for (int i = 0; i < arr.length; i++) {
            if(current!=arr[i]){
                matched--;
            }
            else
                matched++;
            if(matched==0){
                current=arr[i];
                matched=1;
            }
        }
        System.out.println(current);
    }
}
