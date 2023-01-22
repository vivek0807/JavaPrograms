import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;


public class Main {
    public static void main(String[] args) {

        String[] list1={"Shogun","Tapioca Express","Burger King","KFC"};
        String[] list2={"KFC","Shogun","Burger King"};

        HashMap<String,Integer> hashMap= new HashMap<>();

        if(list1.length>=list2.length){
            for (int i = 0; i < list1.length; i++) {
                hashMap.put(list1[i],i);
            }
        }
        else {
            for (int i = 0; i < list2.length ; i++) {
                hashMap.put(list2[i],i);
            }
        }

        int min=Integer.MAX_VALUE;
        String retuner[] = new String[155];
        int index=0;
        if(list1.length>=list2.length){
            for (int i = 0; i < list2.length ; i++) {
                if(hashMap.containsKey(list2[i])) {
                    int get = hashMap.get(list2[i]);
                    if (get + i < min) {
                        min = get + i;
                        retuner[index++] = list2[i];
                    }
                }
            }
        }
        else if (list2.length>list1.length){
            for (int  i= 0; i < list1.length; i++) {
                if (hashMap.containsKey(list1[i])){
                    int get=hashMap.get(list1[i]);
                    if(get+i<min){
                        min=get+i;
                        retuner[index++]=list1[i];
                    }
                }
            }
        }
        for (String s:retuner ) {
            System.out.println(s);
        }
    }
    }



