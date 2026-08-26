import java.io.*;
import java.util.*;


class ElemenetsHolder{
    int first;
    int second;
    int third;

    public ElemenetsHolder(int first, int second, int third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    @Override
    public boolean equals(Object obj) {
        ElemenetsHolder elemenetsHolder=(ElemenetsHolder) obj;

        return elemenetsHolder.first==this.first && elemenetsHolder.second==this.second
                && elemenetsHolder.third==this.third;
    }
    @Override
    public int hashCode() {
        return Objects.hash(first, second, third);
    }

    @Override
    public String toString() {
        return first+" "+second+" "+third;
    }
}

public class Main {


    public static void main(String[] args) throws IOException {

        int[] arr ={1,3,3,4,5,6,1,2,3,4,5,6,7,8,10};

        Arrays.sort(arr);
        int first=arr[0];
        int second=0;
        int third=arr[arr.length-1];
        Set<ElemenetsHolder> set= new HashSet<>();
        int target=9;
        for (int i=0;i<arr.length-2;i++){
            int subTarget=target-arr[i];
            int left=arr[i+1];
            int right=arr[arr.length-1];
            while (left<right){
                if (arr[left]+arr[right]<subTarget)
                left++;
                else if(arr[left]+arr[right]>subTarget)
                    right--;
                else if (arr[left]+arr[right]==subTarget)
                {
                    set.add(new ElemenetsHolder(arr[i],arr[left],arr[right]));
                break;
                }
            }
        }

        set.forEach(e-> System.out.println(e.toString()));
    }
}

