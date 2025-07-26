package $Expertise.DataStructures.implemented.Recurssion;

import java.sql.SQLOutput;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

class ProblemsEasy{

    void printWord(int i, int j){

        if (i==j)
            return;
        System.out.println(MessageFormat.format("The method is being called in {0} time",i));
        printWord(i+1,j);
    }
    void printNumbers(int i, int j){
        if (i==j)
            return;
       printNumbers(i+1,j);
       System.out.println(i);
    }
    int sumOfNmbers(int n){
        if (n==0)
            return 0;
      return n+sumOfNmbers(--n);
    }
    int factorialOfNumber(int n){
        if (n==0)
            return 1;
       return n*factorialOfNumber(n-1);
    }

    int[] reverseArr(int arr[], int pos) {
        if (pos > arr.length / 2)
            return arr;
        int temp = arr[arr.length - pos - 1];
        arr[arr.length - pos - 1] = arr[pos];
        arr[pos] = temp;
        return reverseArr(arr, pos + 1);

    }

    Boolean reverse(String original, int curPos){
        if (curPos>=(original.length()/2))
            return true;
        return original.charAt(curPos) == original.charAt(original.length()-curPos-1) & reverse(original,curPos+1);
    }

    void printSubsequence(int index, ArrayList<Integer> mainArray,ArrayList<Integer> tempArray){
        if (index==mainArray.size())
        {
            if (!tempArray.isEmpty())
                System.out.println(tempArray);
        }
        else {
            printSubsequence(index + 1, mainArray, tempArray);
            tempArray.add(mainArray.get(index));
            printSubsequence(index + 1, mainArray, tempArray);
            tempArray.remove(tempArray.size() - 1);

        }
        return;
    }
}


public class Recurssion {

    public static void main(String[] args) {
        ProblemsEasy problemsEasy= new ProblemsEasy();
        int arr[]={1,2,3,4,5,6,7,8};
        ArrayList<Integer> integerList=  new ArrayList<>();
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);
        problemsEasy.printSubsequence(0,integerList,new ArrayList<>());
       // System.out.println(problemsEasy.reverse("MNM",0));
    }
}
