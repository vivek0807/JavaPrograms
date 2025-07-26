package $Expertise.DataStructures.implemented.Problems;


import java.util.Arrays;

public class MergeTwoSortedArray {
    /**
     * <h1>Approach</h1>
     * <ol>Get actual number of elements </ol>
     * <ol>Start iterating from  rear with k m+n-1 </ol>
     * <ol>Keep Comparing either arrays with J & I as their indexes and swap with K </ol>
     * <ol> In the end add all the 2nd array elements to 1st unless J is 0 </ol>
     * <ol></ol>
     */
    static void mergeArrays(int[] arr1, int[] arr2) {
        int m = arr1.length - arr2.length; // Length of valid elements in arr1
        int n = arr2.length;               // Length of arr2
        int i = m - 1;                     // Index for the last valid element in arr1
        int j = n - 1;                     // Index for the last element in arr2
        int k = m + n - 1;                 // Index for the last position in arr1

        while (i >= 0 && j >= 0) {
            if (arr1[i] > arr2[j]) {
                arr1[k--] = arr1[i--];
            } else {
                arr1[k--] = arr2[j--];
            }
        }

        while (j >= 0) { // Copy remaining elements of arr2 (if any)
            arr1[k--] = arr2[j--];
        }
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 3, 5, 0, 0, 0};
        int[] arr2 = {2, 4, 6};

        mergeArrays(arr1, arr2);
        System.out.println("Merged array: " + Arrays.toString(arr1));
    }
}
