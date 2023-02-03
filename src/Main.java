import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;


public class Main {
    public static void main(String[] args) {

        //Array rotation

        int arr[]={1,4,5,4,1,5};
        int sum=0;
        for (int i=0;i<arr.length;i++) {
            sum=sum+arr[i];
        }
        int lsum=sum;
        int rsum=0;
        for (int i = arr.length-1; i >-1 ; i--) {

            if(rsum==lsum)
            {
                System.out.println(i);
                break;
            }

            rsum=rsum+arr[i];
            lsum=sum-rsum;

        }
    }
    }



