package $Expertise.DataStructures.implemented.Problems;

import java.util.ArrayList;
import java.util.List;
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
        ArrayRotate arrayRotate = new ArrayRotate();
        int arr[]={1,2,3,4,5,6};
        arrayRotate.rotate(2,arr);
    }
}
