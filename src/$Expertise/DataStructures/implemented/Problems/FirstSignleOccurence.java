package $Expertise.DataStructures.implemented.Problems;

public class FirstSignleOccurence {
    public static void main(String[] args) {
        String str="haaabbbcccddeffg";

        int arr[]=new int[200];

        for (int i = 0; i <str.length() ; i++) {
            ++arr[str.charAt(i)];
        }
       boolean found=false;
        int index=0;
        for (int i = 0; i <str.length() ; i++) {
            if (arr[str.charAt(i)]==1)
            { found=true;
                index=i;
                break;}
        }

        if (found)
            System.out.println(str.charAt(index));
        else
            System.out.println("Either no single occurence or string is empty");
    }
}
