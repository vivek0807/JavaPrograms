package $Expertise.Language;

// Arrays belong to java.util.Arrays class
public class Arrays {
    public static void main(String[] args) {
        int newArr[];//Declaring an array-No Memory Allocation
        newArr=new int[20];//Memory allocated with size 20 and array initilized with 0s;

        String stringArray[];
        stringArray=new String[20];
        stringArray[0]="NameMe";
        stringArray[1]="Vivek";
        java.util.Arrays.sort(stringArray);

        //System.out.println(java.util.Arrays.binarySearch(stringArray,"Vivek"));//Prints the position

    }
}
