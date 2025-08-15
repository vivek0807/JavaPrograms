package $Expertise.DataStructures.implemented.sorting;

import java.util.Arrays;

public class MergeSort {
    public static void mergeSort(int[] arr){
        int length=arr.length;
        int[] arrLeft=new int[length/2];
        int[] arrRight= new int[length-(length/2)];

        int i=0;
        int j=0;

        for (;i<length;i++){
            if (i<length/2){
                arrLeft[i]=arr[i];
            }
            else {
                arrRight[j]=arr[i];
                j++;
            }

        }

        mergeSort(arrLeft);
        mergeSort(arrRight);
        merge(arrLeft,arrRight,arr);
    }

    public static void merge(int[] left,int[] right,int[] destArray){

        int leftSize=destArray.length/2;
        int rightSize=destArray.length-(destArray.length/2);

        int i=0;int r=0;int l=0;

        while (i<destArray.length){
             if (left[l]<right[r]){
                 destArray[i]=left[l];
                 l++;
                 i++;
             }
             else {
                 destArray[i]=right[r];
                 i++;
                 r++;
             }
        }
        if (l<leftSize)
            destArray[i]=left[l];
        else if (r<rightSize)
            destArray[i]=right[r];



    }


    public static void main(String[] args) {
        int[] arr=new int[]{5,1,4,6,7,8,9,0};
        mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
