package $Expertise.DataStructures.implemented.BinarySearch;


import java.util.Arrays;

public class BinarySearchImplementation {

    static int findLowerBoundIndex(int[] arr, int number){
        // Time Complexity is O(logN)
        int left=0;
        int right=arr.length-1;
        int mid=0;
        int index=number;
        while (left<=right){
            mid =(left+right)/2;
            if (arr[mid]>=number)   // for upper bound just remove the = symbol to fetch the upper limit
            {                       // In case found element index is required then compare and return arr[mid] with target
                right=mid-1;
                index=mid;
            }
            else
                left=mid+1;


        }
        return index;

    }

    static int InterativeBinarySearch(int[] arr,int search){
        int right=arr.length-1;
        int mid=0;
        int left=0;

        while (right>=left){
            mid=(left+right)/2;
            if (arr[mid]==search)
                return mid;
            else if (arr[mid]>search) {
                right=mid-1;
            } else if (arr[mid]<search) {
                left=mid+1;
            }
        }
        return -1;
    }

    static int[] findFirstAndLastPosition(int arr[], int target){
        int left=0;
        int right= arr.length-1;
        int mid=0;
        int i=0;
        int arr_index[]= {-1,-1};
        if (arr.length==0)
            return arr_index;
        while (left<=right){
            mid=(right+left)/2;

            if (target<arr[mid])
                right=mid-1;
            else if(target>arr[mid])
                left=mid+1;
            else
            {

                right=mid-1;
                arr_index[0]=mid;
            }

        }
        left=0;
        right=arr.length-1;
//        arr_index[0]=i;
        while (left<=right){
            mid=(right+left)/2;

            if (target<arr[mid])
                right=mid-1;
            else if(target>arr[mid])
                left=mid+1;
            else
            {
                left=mid+1;
                arr_index[1]=mid;
            }

        }
       // arr_index[1]=i;
        return arr_index;

    }

    static int searchInRotatedSortedArray(int arr[], int target){
        int left=0;
        int right=arr.length-1;
        int mid=0;
        while (left<=right){
            mid=(left+right)/2;
            if (target==arr[mid])
                return mid;
            //Considering target is present in left side of the array
            if(arr[left]<=arr[mid]) // meaning the arary is linierly sorted till middle
            {
                // Checking on the right side of the array
                if(target>arr[mid] || target <arr[left])
                    left=mid+1;
                else
                    right=mid-1;
            }
            else {  // Considering right side of the array

                if (target<arr[mid]|| target>arr[right])
                    right=mid-1;
                else
                    left=mid+1;
            }

        }


        return -1;
    }

    static int findPeakElement( int arr[]){

        int left=0;
        int right =arr.length-1;
        int mid=0;

        while(left<=right){
            mid=(right+left)/2;
            if (mid>0 && arr[mid]<arr[mid-1])
                right=mid-1;
            else if (mid< arr.length-1 && arr[mid]< arr[mid+1]) {
                left=mid+1;
            }
            else
                return mid;
        }

        return 0;

    }


    public static void main(String[] args) {
        int arr[]=new int[]{1,3,5,6};
        Arrays.sort(arr);
        System.out.println(findPeakElement(new int[]{1,2,1,3,5,6,4}));

    }
}
