package $Expertise.DataStructures.implemented.Problems.BackTracking;

/**
 * <h1>Min Deletion required to make a string Palindrome</h1>
 * <h2>Approach</h2>
 * <li>Recurrsion with two pointers starting from start and end/li>
 * <li>base case-start>end</li>
 * <li>if i==j just skip</li>
 * <li> if diff then call the function back with getting min of start+1 and end +1</li>
 */
public class MinDeletions {
    static  int minDel(int start, int end,String s){

        if (start>=end)
            return 0;
        if (s.charAt(start)==s.charAt(end))
            return minDel(start+1,end-1,s);

        return 1+Math.min(minDel(start+1,end,s),minDel(start,end-1,s));
    }
    public static void main(String[] args) {
        String s="aebcbda";
        int end=s.length()-1;

        System.out.println(minDel(0,end,s));
    }
}
