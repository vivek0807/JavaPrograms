package $Expertise.Language;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

//(argument-list)-> body{}
//Direct utilizations must be from interface
interface LamdaBaba{
    void TellMe();
  //  void TelHim();
}
public class LambdaA {
    public static void main(String[] args) {
        LamdaBaba la=()->{
            System.out.println(" Hello Lamda baba");
        };
       la.TellMe();


        List<String> li =new ArrayList<>();
        li.add("Vennice");
        li.add("Antartica");
        li.add("Fermany");
        li.add("Lopps");

        li.forEach((name)-> System.out.println(name));


    }
}
