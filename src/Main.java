import java.security.KeyStore;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

     HashMap<String,String> hashMap= new HashMap<>();
     hashMap.put("a","aloo");
     hashMap.put("b","tamatar");
     hashMap.put("c","khira");
     hashMap.put("d","gajar");


        List<Map.Entry<String,String>> listEntry= new LinkedList<>(hashMap.entrySet());

        Collections.sort(listEntry, new Comparator<Map.Entry<String, String>>() {
            @Override
            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });

        Map<String,String> newHasmap= listEntry.stream().collect(Collectors.toMap(i->i.getKey(),i-> i.getValue()));




    }
    }



