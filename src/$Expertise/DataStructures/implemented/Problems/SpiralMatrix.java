package $Expertise.DataStructures.implemented.Problems;

import java.util.ArrayList;
import java.util.List;

class Solution{

    public List<Integer> spiralOrder(int[][] matrix) {
        int horizontalStart=0;
        int horizontalEnd=matrix.length-1;
        List<Integer> ans= new ArrayList<>();
        while (horizontalStart<=horizontalEnd){
            int verticalStart=horizontalStart+1;
            int verticalEnd=horizontalEnd-1;
            System.out.println("");
            for (int i=horizontalStart;i<=horizontalEnd;i++){
                ans.add(matrix[verticalStart-1][i]);
                System.out.print(matrix[verticalStart-1][i]);
            }

            System.out.println("");
            for (int i=verticalStart;i<=verticalEnd;i++){
                ans.add(matrix[i][horizontalEnd]);
                System.out.print(matrix[i][horizontalEnd]);
            }
            System.out.println("");
            for (int i=horizontalEnd;i>=horizontalStart;i--){
                ans.add(matrix[verticalEnd][i]);
                System.out.print(matrix[verticalEnd+1][i]);
            }

            System.out.println("");
            for (int i=verticalEnd;i>=verticalStart;i--){
                ans.add(matrix[i][horizontalStart]);
                System.out.print(matrix[i][horizontalStart]);
            }
            horizontalStart++;
            horizontalEnd--;

        }

       return ans;
    }
}

public class SpiralMatrix {
    public static void main(String[] args) {

        int[][] arr={{1,2,3},{4,5,6},{7,8,9}};


        Solution solution = new Solution();
        solution.spiralOrder(arr);
    }
}
