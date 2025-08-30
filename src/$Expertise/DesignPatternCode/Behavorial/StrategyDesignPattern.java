package $Expertise.DesignPatternCode.Behavorial;

import java.util.Arrays;

/**
 * <h1>Strategy Design Pattern</h1>
 * <p>This format implies to have multiple classes with multiple algorithms
 * of execution and assures them to be interchangeable </p>
 * <li>Here we have a bases interface that asks all the inheriting classes to implement the sort method</li>
 * <li>We have a sorting context class that keeps the base interface as variable</li>
 * <li>We create the Sorting context with a SortingStrategy and coll the sort method</li>
 */

interface SortingStrategy{
    int[] sort(int[] array);
}

class SortingContext  {
    private SortingStrategy sortingStrategy;

    SortingContext(SortingStrategy sortingStrategy){
        this.sortingStrategy=sortingStrategy;
    }

    public int[] sortArray(int[] arr){
        sortingStrategy.sort(arr);
        return arr;
    }

}

class BubbleSort implements SortingStrategy{

    @Override
    public int[] sort(int[] array) {
        Arrays.sort(array);
        return array;
    }
}

class MergeSort implements SortingStrategy{

    @Override
    public int[] sort(int[] arr)
    {
        Arrays.sort(arr);
        return arr;
    }
}

public class StrategyDesignPattern {
    public static void main(String[] args) {
        SortingContext sortingContext= new SortingContext(new BubbleSort());
        int[] nums=new int[]{1,5,4,3,6,7};
        sortingContext.sortArray(nums);
        System.out.println(Arrays.toString(nums));

    }
}
