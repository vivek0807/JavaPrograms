package $Expertise.DataStructures.implemented.Problems;

import java.util.HashSet;

class Powersets{        // PRITING ALL THE SUBSETS OF A GIVE STRING

    void  powerset(int index,String str,String curr){
        int n=str.length();
        if(index==n)
            return;
        System.out.println(curr);
        for (int i=index+1;i<n;i++){
            curr=curr+str.charAt(i);

            powerset(i,str,curr);

            curr=curr.substring(0,curr.length()-1);
        }
    }

    }


class Spermutations{
    void permutate(String s1,String s2){

        HashSet <String> primary = new HashSet<>();

        for (int i=0;i<s2.length();i++){
            String s="";
            for (int j = i; j <(s2.length()+i) ; j++) {
                int k=j%(s2.length());
                s=s+s2.charAt(k);


            }
            primary.add(s);
            StringBuilder sbr=new StringBuilder(s);
            primary.add(s);
        }

        for (int i=0;i<s1.length();i++){
            String s="";
            for (int j = i; j <(s1.length()+i) ; j++) {
                int k=j%(s1.length());
                s=s+s1.charAt(k);


            }

            StringBuilder sbr=new StringBuilder(s);
            if(primary.contains(s)||primary.contains(sbr.reverse()))
                System.out.println("Included");
        }

    }
}
public class Strings {

    public static void main(String[] args) {
        Powersets powersets=new Powersets();
        powersets.powerset(-1,"eidboaoo","");
    }
}
