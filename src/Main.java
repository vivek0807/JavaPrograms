import java.io.*;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;

class Node{
    int val;
    Node next;
    Node(int val, Node node){
        this.val=val;
        this.next= node;
    }
}
public class Main {

    public static void main(String[] args) throws IOException {
        int[] arr1 = {1, 3, 5, 0, 0, 0};
       int target=8;
        HashMap<Integer,Integer> hashMap= new HashMap<>();
        int diff=0;
        for (int i = 0; i < arr1.length ; i++) {
            diff=target-arr1[i];
            if (hashMap.containsKey(diff)){
                System.out.println(arr1[i]+","+arr1[hashMap.get(diff)]);
            }
            else
                hashMap.put(diff,i);
        }
    }
}

