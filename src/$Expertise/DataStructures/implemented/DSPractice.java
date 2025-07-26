package $Expertise.DataStructures.implemented;

import java.util.Arrays;
import java.util.HashSet;


public class DSPractice {
 void primeRange(int start,int end){
     boolean arr[]=new boolean[end];
     Arrays.fill(arr,true);
     for (int i = 2; i <Math.sqrt(end) ; i++) {
         int mult=2;
         while (i*mult<end){
            arr[i*mult]=false;
            ++mult;
         }

     }

     for (int i = start; i <end ; i++) {
         if(arr[i]==true)
             System.out.print(i+" ");
     }
 }
    public static void main(String[] args) {

        DSPractice dsPractice = new DSPractice();
        dsPractice.primeRange(2,9);
    }
}
