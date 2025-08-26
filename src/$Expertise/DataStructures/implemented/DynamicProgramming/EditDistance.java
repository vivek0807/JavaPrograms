package $Expertise.DataStructures.implemented.DynamicProgramming;

/**
 * <h1>Given a String calculate the minimum number of deletions/updations/insertions required to make a string identical to the other</h1>
 * <h3>Approach</h3>
 * <li>Create a 2D array of size s1 and s2' length +1</li>
 * <li>Fill the 1st columns and rows with iterations of length respectively</li>
 * <li>if the same position character matches then update the diagonally -1 position value as no update has to be done</li>
 * <li>If the characters don't match then fill the dp with min of the surrounding elements</li>
 */
public class EditDistance {
    public static void main(String[] args) {

        String s1="intention";
        String s2="execution";

        int[][] dp=new int[s1.length()+1][s2.length()+1];

        for (int i=1;i<=s1.length();i++)
            dp[i][0]=i;
        for (int i = 1; i <= s2.length(); i++) {
            dp[0][i]=i;
        }


        for (int i = 1; i <=s1.length() ; i++) {

            for (int j=1;j<=s2.length();j++){

                if (s1.charAt(i-1)==s2.charAt(j-1))
                    dp[i][j]=dp[i-1][j-1];
                else{
                    int topLeft=dp[i-1][j-1];
                    int top=dp[i-1][j];
                    int left=dp[i][j-1];

                    dp[i][j]=Math.min(topLeft,Math.min(top,left))+1;
                }
            }
        }

        System.out.println(dp[s1.length()][s2.length()]);

    }
}
