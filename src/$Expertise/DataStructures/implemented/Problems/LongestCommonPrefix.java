package $Expertise.DataStructures.implemented.Problems;
/*
* Find the longes common Prefix in a given array of Strings
* E,g-> Flower, flow -> OP->flow
* */


public class LongestCommonPrefix {
    public static void main(String[] args) {
        String[] strs={"flower","flow","flight","f"};
        String pre=strs[0];
        int substrlen=pre.length();
        for (int i = 1; i <strs.length ; i++) {
            if(strs[i].length()<substrlen)
                substrlen=strs[i].length();
            for (int j=0;j<substrlen;j++)
            {
                if(strs[i].charAt(j)!=pre.charAt(j))
                { substrlen=j;
                break;
                }
            }
            if(substrlen==0)
                break;
        }

        System.out.println(pre.substring(0,substrlen));
    }

}
