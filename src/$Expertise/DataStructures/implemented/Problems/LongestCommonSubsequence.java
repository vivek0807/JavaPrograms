
/**
Longest common subsequence
use of dp
Approach.

we form a 2D array that has the number of characters matched bw the two strings till there
if a match is found between two chars at the common index we increment 1 grom last upper diagonal
as that will contain the number of matches till that point
If a match is not found between common indexes we just update the max of last Ith ans last jth cell.
lastly the last bottom cell will contain the max numberber of chars matched

**/


public class LongestCommonSubsequence {
	public static void main(String[] args) {
		String s1 = "abcd";
		String s2 = "abc";
		
		int dp[][]= new int[s1.length()+1][s2.length()+1];
		
		for(int i=1 ; i<=s1.length();i++){
			for (int j=1; j<=s2.length();j++){
				
				if (s1.charAt(i-1)==s2.charAt(j-1))
				{
				dp[i][j]=1+dp[i-1][j-1];
				}
				else{
					dp[i][j]= Math.max(dp[i-1][j],dp[i][j-1]);
					
				}
			
			}
		}
		
		System.out.println(dp[s1.length()][s2.length()]);
		
		
	}
}