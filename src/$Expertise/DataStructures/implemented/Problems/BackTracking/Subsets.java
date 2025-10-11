package $Expertise.DataStructures.implemented.Problems.BackTracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class SusbSetsSolution{

    static ArrayList<Integer> list= new ArrayList<>();

    public void generateSubsets(int startIndex, List<Integer> mainArray,List<List<Integer>> ansArray){
        if (startIndex>=mainArray.size()) {
            ansArray.add(new ArrayList<>(list));
            return ;
        }

        list.add(mainArray.get(startIndex));
        generateSubsets(startIndex+1,mainArray,ansArray);
        list.removeLast();
        generateSubsets(startIndex+1,mainArray,ansArray);
    }

}

public class Subsets {

    public static void main(String[] args) {

        List<List<Integer>> answerList= new ArrayList<>();
        List<Integer> mainList= List.of(1,2,3);

        SusbSetsSolution solution= new SusbSetsSolution();
        solution.generateSubsets(0,mainList,answerList);
        System.out.println(answerList);

        int[] arr={1,2,3,4,5};
        List<Integer> twoArr= Arrays.stream(arr).boxed().toList();
        System.out.println(twoArr);


    }
}
