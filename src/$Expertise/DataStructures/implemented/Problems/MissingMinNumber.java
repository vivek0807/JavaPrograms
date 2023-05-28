package $Expertise.DataStructures.implemented.Problems;
// Report the least non-negative positive number
public class MissingMinNumber {
    public static void main(String[] args) {
        int[] arr={-5,-4,-1,0,4,5,6,7};
        int min=Integer.MIN_VALUE;
        for (int i = 0; i <arr.length -1; i++) {
            if (arr[i]>=0){
                if((arr[i+1]-arr[i])>1){
                    min=arr[i]+1;
                    break;
                }

            }
        }

        System.out.println(min);
    }
}
