package $Expertise.DataStructures.implemented.Problems;
// Leetcode
import java.util.ArrayList;
import java.util.List;

class Solution{

    public List<Integer> spiralOrder(int[][] matrix) {

        int top=0;
        int bottom=matrix.length-1;
        int left=0;
        int right= matrix[0].length-1;
        ArrayList<Integer> result= new ArrayList<>();
        while (top<=bottom && left<=right){
            for (int i = left; i <= right; i++) {

                result.add(matrix[top][i]);
            }
            top+=1;

            for (int i= top;i<=bottom;i++) {
            result.add(matrix[i][right]);
        }
            right--;

            if (top<=bottom){
                for (int i=right;i>=left;i--){
                    result.add(matrix[bottom][i]);
                }
               bottom--;
            }
            if (left<=right){
                for (int i=bottom;i>=top;i--) {
                 result.add(matrix[i][left]);
                }
                left++;
            }

        }
         return result;
        }
    }


public class SpiralMatrix {
    public static void main(String[] args) {

        int[][] arr={{1,2,3},{4,5,6},{7,8,9}};

        System.out.println(new Solution().spiralOrder(arr));


    }
}
