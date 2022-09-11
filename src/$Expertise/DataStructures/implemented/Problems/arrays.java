package $Expertise.DataStructures.implemented.Problems;

import java.util.ArrayList;
import java.util.List;

class Kadane{       //Tofind maxsum with subarray
    void kadane(){
        int arr[]={1,-4,20,5,-9,-6};
        int max_sum=0;
        int cur_sum=0;
        int start=0;
        int end=0;
        for (int i = 0; i <arr.length ; i++) {
            cur_sum=cur_sum+arr[i];
            if(cur_sum<0)
            {cur_sum=0;
            start=i;

            }

            if(max_sum<cur_sum)
            {max_sum=cur_sum;
            end=i;
            }
        }
        System.out.println(max_sum+" "+(start+1)+" "+end);
    }
}
//Array Rotation
class ArrayRotate{

    void rotate(int d,int arr[]){
        int dup[]=arr;
        int j=0;
        for (int i = (d%arr.length); i <(arr.length +d%arr.length) ; i++) {

            System.out.println(arr[i%arr.length]);
        }


    }
}

class PivotIndex{
    void pivot(){
        int arr[]={1,9,1,4,5,6};
        int sum=0;
        int len=arr.length;
        for (int i = 0; i <len ; i++) {
            sum=sum+arr[i];
        }

        int rsum=sum;
        int lsum=0;
        for (int i =0 ; i <len ; i++) {
            if (lsum==rsum){
                System.out.println("The piovt is -> "+(i-1));
                break;
            }
            rsum=sum-lsum;
            lsum=lsum+arr[i];
        }

    }

}
public class arrays {
    public static void main(String[] args) {
     PivotIndex pivotIndex = new PivotIndex();

     pivotIndex.pivot();

    }
}
