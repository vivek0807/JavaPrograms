package $Expertise.DataStructures.implemented.sorting;


import java.util.Arrays;

/**
 * <h1>Quick Sort Implementation</h1>
 * <h3>Approach</h3>
 * <li>This is a recursion based algorithm with base case end</li>
 * <li>We assume a pivot element </li>
 * <li>We keep a pointer[i] index from where we swap an element that is getting scanned[j]</li>
 * <li> if the scanning element[j] is lesser than Pivot element we swap j with incremented i</li>
 * <li>This ensures that the elements lesser that pivot are all before the last point where [i] has arrived</li>
 * <li>hence we have arrived at an index where i+1 will be our new pivot</li>
 */

public class QuickSort {
    static int findPartition(int[] arr,int low, int high){
        int i=low-1;

        for (int j=low;j<high;j++){     // j is to be run till the last point given as parameter

            if (arr[j]<arr[high]){  // when ever scanned element is less than pivot element
                i++;
                int temp=arr[i];
                arr[i]=arr[j];
                arr[j]=temp;
            }
        }

        int temp=arr[i+1];  // swapping element at i and making it the new pivot
        arr[i+1]=arr[high];
        arr[high]=temp;
        return i+1; // return the new pivot
    }

   public static void quickSortImplementation(int[] arr,int low,int high){
            if (high<=low)
                return;
            int partition=findPartition(arr,low,high);
            quickSortImplementation(arr,low,partition-1);
            quickSortImplementation(arr,partition+1,high);

          //  low++;
        }

    public static void main(String[] args) {
        int[] arr={4,6,8,1,8,90,14,6};

        quickSortImplementation(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }
}
