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

public class arrays {
    public static void main(String[] args) {
        Kadane kadane=new Kadane();
        kadane.kadane();

    }
}
