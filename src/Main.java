import java.security.KeyStore;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

       List<String> list= Arrays.asList("Helo","Mello","Pello","Khelo");

       String str="";
       list.stream().map(i->i+"  sdfsdf").forEach((String strs)->{
           strs=str+strs;
       });

    }
    }



