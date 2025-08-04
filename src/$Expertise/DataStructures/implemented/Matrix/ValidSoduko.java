package $Expertise.DataStructures.implemented.Matrix;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * <h1>Valid Soduko</h1>
 * <h3>validate if in the board there are no duplicates in the row, column and 3X3 box</h3>
 * <h3>Approach</h3>
 * <li>We will one HashSet making the values unique using string manipulation </li>
 * <li>If any of the values are found again we just return false</li>
 */
public class ValidSoduko {
    public static void main(String[] args) {

        char[][] board={
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}};

        Set<String> repeatSet= new HashSet<>();

        for (int i=0;i<9;i++){

            for (int j = 0; j <9 ; j++) {
                if (board[i][j]!='.'){
                    if (!repeatSet.add(board[i][j]+" found in row "+i)|| // making it unique for each row and likewise
                    !repeatSet.add(board[i][j]+" found in column "+j)||
                    !repeatSet.add(board[i][j]+"found in"+i/3+"-"+j/3))
                    System.exit(-1);

                }

            }
        }

        System.out.println("This is a good board");
    }
}
