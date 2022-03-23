package $Expertise.Practice;

//Count number of ones using bit wise operator
class Solution {
    public int[] countBits(int n) {

    int arr[]=new int[n+1];
        for (int i = 0; i <n+1 ; i++) {
            int count=0;
            int bit=i;
            while(bit!=0){
                bit = bit&(bit-1);
                count++;
            }
           // System.out.println(count);
            arr[i]=count;
        }


    return arr;
    }
}
public class BinaryReturn {
    public static void main(String[] args) {

        Solution sol=new Solution();

        int arr[]=sol.countBits(5);

        for (int i:arr  ) {
            System.out.print(i);
        }
    }
}
