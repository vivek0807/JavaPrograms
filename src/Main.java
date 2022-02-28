import $Expertise.Language.Exceptions;

import java.util.*;
public class Main {
    public static void main(String[] args) {

        Scanner sc=new Scanner(System.in);
        int[] Arr={10,13,12,8};
        //8,
        int count=0;
        Arrays.sort(Arr);

        for (int i=0;i<Arr.length-1;i++){
            if(Arr[i]!=Arr[i+1])
            count=count+((Arr[i+1]-Arr[i])-1);
        }

        System.out.println(count);




    }
}
