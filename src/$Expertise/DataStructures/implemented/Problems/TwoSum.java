package $Expertise.DataStructures.implemented.Problems;

import java.util.HashMap;

/*
Given an array of integers nums and an integer target,
return indices of the two numbers such that they add up to target.
*/
public class TwoSum {
    public static void main(String[] args) {
        int[] arr= {1,5,7,5,3,4,5,8};

        int sum =9;
        int diff=0;
        int index=0;
        HashMap<Integer,Integer> hashMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            diff=sum-arr[i];
            if(hashMap.containsKey(diff)){
                index=hashMap.get(diff);
                System.out.println(i+" "+index);
                break;
            }
            else{
                hashMap.put(arr[i],i);
            }
        }


    }
}
