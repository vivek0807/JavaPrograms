package $Expertise.DataStructures.implemented.Problems;

import java.util.Arrays;

public class HCitations {
    public static void main(String[] args) {
        int[] citations={3,0,6,1,5};
        int low=0;
        int mid=0;int n=citations.length;
        int high=n-1;
        int candidate=0;
        if(n==0)
            System.out.println(0);
        if(citations[0]>=n)
            System.out.println(n);
        Arrays.sort(citations);
        while(low<=high){

            mid=low+(high-low)/2;
            if(citations[mid]>=n-mid)
            {
                candidate=n-mid;
                high=mid-1;

            }
            else
                low=mid+1;

        }

        System.out.println(candidate);
    }
}
