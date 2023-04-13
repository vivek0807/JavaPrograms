package $Expertise.DataStructures.implemented;
//Searching in a sorted array by divide and conquer
//Time Complexity - OlogN Space OlogN
//Recursive and Iterative approaches

import java.util.Arrays;

class Recurssive{

    int binarySearch(int arr[],int low, int high,int x){
        if(low>high)
            return -1;
        else
            return 1;
    }

}

class Iterative{
    void binarySearch(int[] arr,int element){
        int l=0;
        int r=arr.length-1;


        while (r-l>1){
            int mid= (l+r)/2;
            if(arr[mid]< element)
                l=mid+1;
            else
                r=mid;

        }

            if(arr[r]==element)
                System.out.println("Fount at "+r);
            else if (arr[l]==element) {
                System.out.println("Found at "+l);
            }
            else
                System.out.println("Nai mila kuch");
    }
}

public class BinarySearch {
    public static void main(String[] args) {
        int arr[]={5};
        Arrays.sort(arr);
        Iterative iterative= new Iterative();
        iterative.binarySearch(arr,-5);
    }
}
