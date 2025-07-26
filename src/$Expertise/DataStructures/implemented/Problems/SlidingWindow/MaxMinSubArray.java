package $Expertise.DataStructures.implemented.Problems.SlidingWindow;

import java.util.TreeSet;

public class MaxMinSubArray {
   static void solution(){
        int[] arr={2, 5, -1, 7, -3, -1, -2};

        int k=4;
        TreeSet<Integer> set= new TreeSet<>();
        for(int i=0; i<k; i++)
            set.add(arr[i]);
        int leftWindow=0;
        int rightWindow=k-1;


        // System.out.println(set.first()+set.last());
        while (rightWindow<arr.length){

            set.add(arr[rightWindow++]);
            if(set.last()!=arr[rightWindow-1])
                set.remove(arr[leftWindow++]);

            System.out.println(set.first()+set.last());
        }
    }
    public static void main(String[] args) {
        // Problem- To find the sum of maximum and minimum within a sub array of size k
        //Solution- Keep a Map with sorted set to store and remove the max and min within that sub Array


    }
}


