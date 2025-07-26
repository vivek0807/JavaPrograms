package $Expertise.DataStructures.implemented.Problems;

import java.util.HashMap;

//Find the length of continuous numbers in an array
//Approach Make a HashMap and keep checking for forward and backward Sequences with marking and False forUnvisited and true
//for Visited.
//Check for the maximum of length found.
public class LongestSubsequenceOfNumbers {
    public static void main(String[] args) {
        int arr[]={0,1,2,49,50,58,47,3,4,5,6};
        HashMap<Integer,Boolean> map= new HashMap<>();
        int max_len=Integer.MIN_VALUE;
        for(int i=0;i<arr.length;i++){

            map.put(arr[i],Boolean.FALSE);
        }

        for(int i=0;i<arr.length;i++){
                int curr_len=1;

                int prev_num=arr[i]-1;
                //Check for reverse
                while (map.containsKey(prev_num) && !map.get(prev_num)){
                    curr_len++;
                    map.put(prev_num,Boolean.TRUE);
                    prev_num--;

                }
                int next_num=arr[i]+1;
                while(map.containsKey(next_num) && !map.get(next_num)){
                    curr_len++;
                    map.put(next_num,Boolean.TRUE);
                    next_num++;
                }

            max_len= Math.max(curr_len,max_len);
        }
        System.out.println( max_len);
    }
}
