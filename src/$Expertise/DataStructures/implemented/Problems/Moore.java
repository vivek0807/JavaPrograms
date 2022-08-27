package $Expertise.DataStructures.implemented.Problems;

import java.util.Arrays;

class Sieve{
    void primerange(int start,int end){

        boolean arr[]= new boolean[end];
        Arrays.fill(arr,true);

        for (int i=2;i<Math.sqrt(end);i++){
            int mult=2;
            while (i*mult<end) {
                arr[i*mult]=false;
                mult++;
            }
        }

        for (int i = start; i <end ; i++) {
            if(arr[i]==true)
                System.out.println(i);
        }

    }
}
public class Moore {
    public static void main(String[] args) {
        Sieve sieve = new Sieve();
        sieve.primerange(10,29);

    }
}
