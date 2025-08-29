package $Expertise.DataStructures.implemented.Matrix;

import java.util.Arrays;

/**
 * <h1>Rotate a 2D array in clock wise direction</h1>
 * <h3>Approach</h3>
 * <li>Start with left at 0 and right to n-1 as you need to replace till the last number</li>
 * <li>Moving inward into the matrix you will run a second loop till r-l</li>
 * <li>Swap all 4 elements at a time</li>
 */

public class Rotate2DArray {

    public static void main(String[] args) {
        int [][] matrix = {{1,2,3},{4,5,6},{7,8,9}};

        int left=0;
        int right=matrix.length-1;

        while (left<right){

            for (int i=0;i<(right-left);i++){

                int top=left;
                int bottom=right;

                int topLeft=matrix[top][left+i];
                matrix[top][left+i]=matrix[bottom-i][left];
                matrix[bottom-i][left]=matrix[bottom][right-i];
                matrix[bottom][right-i]=matrix[top+i][right];
                matrix[top+i][right]=topLeft;

            }
            right-=1;
            left+=1;
        }


        for (int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
    }


}
