package $Expertise.Language;

// Arrays belong to java.util.Arrays class


// 1 2 3 4
class twoDArray{
    void printRight(int rowpos,int colpos){
        boolean printing=false;
        while (colpos<arr[0].length && arr[rowpos][colpos]<Integer.MAX_VALUE){
            System.out.println(arr[rowpos][colpos]);
            arr[rowpos][colpos]=Integer.MAX_VALUE;
            colpos++;
            printing=true;
        }
        if(printing==true)
            printDown( rowpos, colpos);
    }
    void printDown(int rowpos,int colpos){
       rowpos++;
       colpos--;
        boolean printing=false;
        while (rowpos<arr[0].length && arr[rowpos][colpos]<Integer.MAX_VALUE){
            System.out.println(arr[rowpos][colpos]);
            arr[rowpos][colpos]=Integer.MAX_VALUE;
            rowpos++;
            printing=true;
        }
        if(printing==true)
            printLeft( rowpos, colpos);
    }
    void printLeft(int rowpos,int colpos){
        rowpos--;
        colpos--;
        boolean printing=false;
        while (colpos>-1&&arr[rowpos][colpos]<Integer.MAX_VALUE){
            System.out.println(arr[rowpos][colpos]);
            arr[rowpos][colpos]=Integer.MAX_VALUE;
            colpos--;
            printing=true;
        }
        if(printing==true)
            printUp( rowpos, colpos);
    }
    void printUp(int rowpos,int colpos){
        colpos++;
        rowpos--;
        boolean printing=false;
        while (rowpos>-1&&arr[rowpos][colpos]<Integer.MAX_VALUE){
            System.out.println(arr[rowpos][colpos]);
            arr[rowpos][colpos]=Integer.MAX_VALUE;
            rowpos--;
            printing=true;
        }

        if(printing==true)
        {printRight( ++rowpos, ++colpos);
            System.out.println("Row->"+rowpos+" "+colpos);
        }
    }
    int arr[][]={{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
    void work(){
        printRight(0,0);
    }
}
class singleArray{
    void work(){
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
public class Arrays {
    public static void main(String[] args) {

        twoDArray twoDArray= new twoDArray();
        twoDArray.work();
    }
}
