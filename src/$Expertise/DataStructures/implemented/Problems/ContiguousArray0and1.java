package $Expertise.DataStructures.implemented.Problems;
//LOGIC TO SOLVE THE PROBLEM
//IF THE TOTAL SUM RESULTS TO 0 THEN THERE ARE EUQAL NUMBER OF 0S TILL THERE
//IF THE SUM IS FOUND BEFORE IN HASHMAP THEN THERE ARE EQUAL NUMBER OF 0S AND 1S TILL INDEX -1
import java.util.HashMap;

public class ContiguousArray0and1 {
    static int maxLength(int nums[]){
        int max_l=Integer.MIN_VALUE;
        int temp_sum=0;
        HashMap<Integer,Integer> hashMap= new HashMap<>();
        for (int i = 0; i <nums.length ; i++) {
            if (nums[i]==0)
                temp_sum-=1;
            else
                temp_sum+=1;
            if (hashMap.containsKey(temp_sum))
            {
                int last_pos=hashMap.get(temp_sum);
                max_l=Math.max(max_l,i-last_pos);
            }
            else if (temp_sum ==0)
                max_l=Math.max(max_l,i+1);
            else hashMap.put(temp_sum,i);
        }

        return max_l;
    }

    public static void main(String[] args) {
        System.out.println(maxLength(new int[]{0,1,0,0,1,1,1,0,0,0}));
    }
}
