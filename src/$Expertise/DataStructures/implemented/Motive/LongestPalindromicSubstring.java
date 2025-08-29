package $Expertise.DataStructures.implemented.Motive;
/*
Q- Find the longest palindorminc substring within a given SubsString
 */
public class LongestPalindromicSubstring {

    public static void main(String[] args) {
        String s="bb";
        int max_length=1;
        int start=0;
        /*
        Case to resolve in case l==1
         */
        if (s.length()==1)
        {
            System.out.println(1);
            return;
        }
        /*
        Start from 0 Position index and go till last character
         */
        for (int i=0;i<s.length();i++){
                /*
                Consider Both odd and even cases of palindrome so check j=0 and j<=1
                Hence in once case FORWARAD WILL  start with J
                AND IN OTHER CASE FORWARD WILL START WITH START J=I-1=1
                IN BOTH CASES BACKWARD WILL BE I
                 */
           for (int j=0;j<=1;j++){
               int backward=i;
               int forward=i+j;
                /*
                ITERATE ON BOTH SIDE OF STRING CHECKING THE CHARACTER MATCH
                SINCE MAX_LEN IS CALUCALTED BT COMPARING INDICES, ADD +1
                 */
               while(backward>=0 && forward<s.length() && s.charAt(backward)==s.charAt(forward)){
                   if ((forward-backward+1)>max_length)
                   {
                       start=backward;
                       max_length=forward-backward+1;
                   }
                   forward++;
                   backward--;
               }
           }

        }
        System.out.println(s.substring(start,start+max_length));
    }
}
