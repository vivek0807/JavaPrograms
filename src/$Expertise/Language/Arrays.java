package $Expertise.Language;

// Arrays belong to java.util.Arrays class
public class Arrays {
    public static void main(String[] args) {
        int newArr[];//Declaring an array-No Memory Allocation
        newArr=new int[20];//Memory allocated with size 20 and array initilized with 0s;

        String stringArray[]=new String[20];
        stringArray[0]="NameMe";
        stringArray[1]="Vivek";
        String strArray[]=stringArray;
        strArray[0]="Cyrus";
        strArray[2]="Name1";
        strArray[3]="Name2";
        strArray[4]="Name3";
        strArray[5]="Name4";
        System.out.println("before Using copyOf method");
        System.out.println(stringArray[0]);
        System.out.println(stringArray[4]);
        System.out.println(strArray[0]);
        System.out.println(java.util.Arrays.compare(strArray,stringArray));
        System.out.println("After using Copy of Array");

        strArray=java.util.Arrays.copyOf(stringArray,20); //Copy of Method must pass length as well
        String[] strArray1=java.util.Arrays.copyOfRange(stringArray,0,3); //Copy of Method must pass length as well
        for (String a: stringArray ) {
            System.out.print(a+" ");
        }
        strArray[3]="NayaNaam3";
        strArray[4]="NayaNaam4";
        strArray[5]="NayaNaam5";
        strArray[6]="NayaNaam6";

        System.out.println('\n');
        for (String a:strArray1  ) {
            System.out.print( a+" ");
        }
        java.util.Arrays.fill(strArray1,"Filled value");
        System.out.println(java.util.Arrays.binarySearch(strArray1,"Filled value"));
        //System.out.println(java.util.Arrays.binarySearch(stringArray,"Vivek"));//Prints the position

    }
}
