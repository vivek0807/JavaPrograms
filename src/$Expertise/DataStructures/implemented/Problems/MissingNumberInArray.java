package $Expertise.DataStructures.implemented.Problems;
// XOR elements with natural number 1.n with elements in array

import java.util.HashMap;

public class MissingNumberInArray {

    static int majorityElement(int arr[]){
        HashMap<Integer ,Integer> max_map= new HashMap<>();
        int max_number=0;
        int occurence=0;
        for (int i = 0; i <arr.length ; i++) {
            if (max_map.containsKey(arr[i])){
                int temp=(max_map.get(arr[i]))+1;
                max_map.put(arr[i],temp);
                if(temp>occurence){
                    max_number=arr[i];
                    occurence=temp;
                }
            }
            else
                max_map.put(arr[i],1);
        }


        return max_number;
    }
    static int missingNumber(int arr[],int n){
        int xor_arr=0;
        int xor_n=0;
        for (int i=1;i<=n;i++){
            if (i<=arr.length)
                xor_arr=arr[i-1]^xor_arr;
            xor_n=i^xor_n;
        }

        return xor_n^xor_arr;
    }
    public static void main(String[] args) {
        System.out.println(majorityElement(new int[]{2,2,1,1,1,2,2}));
    }
}
