
public class Main {
   static void reversewords(String s){

       String arr[]=s.split(" ");
        String str="";
       for (int i = arr.length-1; i >=0 ; i--) {
           //System.out.print(arr[i]);
           str+=arr[i];
           if(i!=0)
           str+=".";
       }
       //System.out.println(str);
    }

    public static void main(String[] args) {
         reversewords("This is too much");
    }

    }

