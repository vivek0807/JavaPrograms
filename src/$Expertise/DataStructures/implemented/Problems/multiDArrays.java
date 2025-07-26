package $Expertise.DataStructures.implemented.Problems;

import java.util.ArrayList;
import java.util.NavigableSet;
import java.util.TreeMap;
import java.util.stream.Stream;

public class multiDArrays {
    public static void main(String[] args) {
        int arr[][]={{1,2,3,4,1},{5,6,7,8,0},{9,10,11,12,0},{13,14,15,16,0}};
        String[] indexes={"Ruby","Diamond","Coal","Gold","Platinum"};
            float sum=0;


        TreeMap<Float,String> treeMap= new TreeMap<>();
        for (int i=0;i<arr[0].length;i++){
            for (int j=0;j<arr.length;j++){
                sum+=arr[j][i];
            }
            treeMap.put(sum/arr.length,indexes[i]);

            sum=0;
        }
        //System.out.println(treeMap.get(treeMap.lastKey()));

        ArrayList<Float> arrayList= new ArrayList<Float>(treeMap.descendingKeySet());
        System.out.println(arrayList);
    }
}
