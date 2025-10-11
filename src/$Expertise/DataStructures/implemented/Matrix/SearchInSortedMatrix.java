package $Expertise.DataStructures.implemented.Matrix;

import org.w3c.dom.ls.LSOutput;

/**
 * <h1>240. Search in 2D matrix which is sorted row and column wise</h1>
 * <h2>Approach</h2>
 * <li>Start searching from bottom and right</li>
 * <li>Just like binary search increment the column first else the decrement the row</li>
 */
public class SearchInSortedMatrix {
    public static void main(String[] args) {

        int nums[][]= {{},{}};

        int N=nums.length;
        int M=nums[0].length;

        int target=5;

        int row= N-1;
        int column=0;
        while (row>0 && column<M) {
            if (nums[row][column] == target) {
                System.out.println(true);
                System.exit(0);
            } else if (nums[row][column] < target)
                column++;
            else
                row--;
        }
        System.out.println(false);
    }

}
