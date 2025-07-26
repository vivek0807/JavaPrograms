package $Expertise.DataStructures.implemented.Problems;

public class Largest3elements {
    public static void main(String[] args) {

        int arr[]={1,2,3,4,5,6,7,9};

        int third=Integer.MIN_VALUE,second=Integer.MIN_VALUE,first=Integer.MIN_VALUE;

        for (int i = 0; i <arr.length ; i++) {
        if(arr[i]>first){
            third=second;
            second=first;
            first=arr[i];
        } else if (arr[i]>second) {
            third=second;
            second=arr[i];

        } else if (arr[i]>third) {
            third=arr[i];
        }
        }

        System.out.println(third+" "+second+" "+first);
    }
}
