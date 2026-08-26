package $Expertise.DataStructures.implemented.Problems.BackTracking;

import java.util.ArrayList;
import java.util.List;

class RestoreIpSolution {
     void backTrack(int index, List<String> parts,List<String> result,String s){

         if (parts.size()==4){
             if (index==s.length())
                 result.add(String.join(".",parts));

             return;
         }

         for (int i = 1; i <=3 ; i++) {

             if (index+i>s.length())
                 break;
             String part= s.substring(index,index+i);
             if (Integer.parseInt(part)>255)
                 break;
             if (part.length() > 1 && part.charAt(0) == '0')
                 break;

             parts.add(part);

             backTrack(index+i,parts,result,s);
             parts.removeLast();
         }

     }



}


public class RestoreIpAddress {
    public static void main(String[] args) {
        RestoreIpSolution solution= new RestoreIpSolution();
        ArrayList<String> arrayList = new ArrayList<>();
        solution.backTrack(0,new ArrayList<>(),arrayList,"25525511135");
        System.out.println(arrayList);

    }
}

