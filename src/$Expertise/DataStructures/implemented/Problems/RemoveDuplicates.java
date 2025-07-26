package $Expertise.DataStructures.implemented.Problems;

import java.util.HashSet;

/*
* Remove Duplicate Words Keeping Punctuation marks intact
* */

public class RemoveDuplicates {
    public static int sumAll(int... nums){
        return 0;
    }
    public static void main(String[] args) {

        RemoveDuplicates.sumAll(1,2,4);
        String s="Are you you. MAd. zero zero, zero";
        HashSet<String> hashSet= new HashSet<>();
        String sarr[]= s.split(" ");
        String tempString="";
        String finalString="";
        boolean addspace=false;

        for (int i = 0; i < sarr.length ; i++) {
            tempString=sarr[i].replaceAll("\\p{Punct}","");
            tempString=tempString.replaceAll(" ","");
                if (!tempString.equals("") && !hashSet.contains(tempString)) {
                    hashSet.add(tempString);
                    if (addspace==false) {
                        finalString =finalString + tempString ;
                        addspace=true;
                    }
                    else {
                        finalString =finalString +" "+tempString ;
                    }


                }

                finalString=finalString+ sarr[i].replaceAll("[a-zA-Z]", "");

        }

        System.out.println(finalString);

    }
}
