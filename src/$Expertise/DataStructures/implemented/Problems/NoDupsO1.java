package $Expertise.DataStructures.implemented.Problems;
// Eliminate all duplicates in same array and return its length


public class NoDupsO1 {
    public static void main(String[] args) {

        int nums[]={1,1,1,1,2,3,4,5,5};
        int curr=nums[0];
        int see_first=0;

        for(int i=1;i<nums.length;i++){
            if(curr!=nums[i]){
                curr=nums[i];
                nums[++see_first]=nums[i];

            }
        }

        System.out.println(see_first);

    }
}
