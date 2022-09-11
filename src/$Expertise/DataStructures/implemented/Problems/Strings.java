package $Expertise.DataStructures.implemented.Problems;

import java.util.HashSet;


class StringtoNewLine{
    void breakString(String s,int n){
        int start=0;
        int end=n;
        String str="";
        while (end<s.length()){

            if(s.charAt(end)!=' ')
            {
                while (s.charAt(end)!=' ')
                    end--;
            }
            str=str+s.substring(start,end);
            str=str+'\n';
            start=end+1;
            end=end+n;
        }
        str=str+s.substring(start,s.length());
        System.out.println(str);
    }
}

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
    void PrintPermute(String s,int start,int end){                  //GENERATING PERMUTATIONS OF A STRING

        if(start==end)
            System.out.println(s);
        else {
            for (int i = start; i <=end ; i++) {
                s=swap(s,start,i);
                PrintPermute(s,start+1,end);
                s=swap(s,start,i);
            }
        }
    }

    String swap(String s,int i,int j){
        char temp;
        char arr[]=s.toCharArray();
        temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;

        return String.valueOf(arr);
    }

}
public class Strings {

    public static void main(String[] args) {
        StringtoNewLine stringtoNewLine = new StringtoNewLine();
        stringtoNewLine.breakString("This sentence must be broken into 13 character words which I cannot solve in the exam because of lack of problem solving skills",13);
    }
}
