package $Expertise.DataStructures.implemented.Problems;


public class MergeSort {
    void sorter(int arr[], int start, int end){

        int mid=(start+end)/2;
        if(start<end) {
            sorter(arr, start, mid);
            sorter(arr, mid + 1, end);

            merger(arr, start, mid, end);
        }
    }

    void merger(int arr[],int start,int mid, int end){

        int rlen=mid-start+1;
        int llen=end-mid;
        int rr[]=new int[rlen];
        int ll[]=new int[llen];

        for (int i = 0; i <rlen ; i++) {
            rr[i]=arr[start+i];
        }
        for (int i = 0; i <llen ; i++) {
            ll[i]=arr[mid+i+1];
        }

        int i=0;
        int j=0;
        int k=start;

        while (i<rlen && j<llen){
            if(rr[i]<=ll[j])
                arr[k++]=rr[i++];
            else
                arr[k++]=ll[j++];

        }
        while (i<rlen){
            arr[k++]=rr[i++];
        }
        while (j<llen){
            arr[k++]=ll[j++];
        }
    }
    public static void main(String[] args) {

        MergeSort mergeSort = new MergeSort();
        int arr[]={5,45,6,4,78,9,0,3,21};
        mergeSort.sorter(arr,0,arr.length-1);
        for (int i:arr             ) {
            System.out.println(i);
        }
    }
}
