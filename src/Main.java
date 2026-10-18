import java.io.*;
import java.util.*;
import java.util.concurrent.*;

class Car{
    int maxSpeed;
}

public class Main {


    public static void main(String[] args) throws IOException {
        try{
              HashMap<String,Integer> map = new HashMap<>();
            map.put("A",1);
            map.put("A",1);
            map.put("A",1);
            map.put("B",1);
            map.put("B",1);
            map.put("B",1);
            map.put("B",1);

            System.out.println(map.merge("A",1,Integer::sum));

        }catch (Exception i){
            System.out.println(i.getCause());
        }


    }
}

