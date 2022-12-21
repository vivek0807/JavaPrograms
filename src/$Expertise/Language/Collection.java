package $Expertise.Language;

import java.util.*;
import java.util.stream.Collectors;



class Animals{
    String blood_color;
    String name;
    int life;


    public Animals(String blood_color, String name, int life) {
        this.blood_color = blood_color;
        this.name = name;
        this.life = life;
    }

    public String getBlood_color() {
        return blood_color;
    }

    public String getName() {
        return name;
    }

    public int getLife() {
        return life;
    }
}
class Marines extends Animals {
    String water_type;
    boolean amphibian;
    int fins;


    public Marines(String blood_color, String name, int life, String water_type, boolean amphibian, int fins) {
        super(blood_color, name, life);
        this.water_type = water_type;
        this.amphibian = amphibian;
        this.fins = fins;
    }

    public String getWater_type() {
        return water_type;
    }

    public void setWater_type(String water_type) {
        this.water_type = water_type;
    }

    public boolean isAmphibian() {
        return amphibian;
    }

    public void setAmphibian(boolean amphibian) {
        this.amphibian = amphibian;
    }

    public int getFins() {
        return fins;
    }



    public void setFins(int fins) {
        this.fins = fins;
    }

    @Override
    public String toString() {
        return  "blood_color='" + blood_color + '\'' +
                ", name='" + name + '\'' +
                ", life=" + life +
                ", water_type='" + water_type + '\'' +
                ", amphibian=" + amphibian +
                ", fins=" + fins ;
    }


}

class Terestials extends Animals{
    String climate_type;
    int legs;
    boolean Omnivore;

    public Terestials(String blood_color, String name, int life, String climate_type, int legs, boolean omnivore) {
        super(blood_color, name, life);
        this.climate_type = climate_type;
        this.legs = legs;
        Omnivore = omnivore;
    }
}


class ArrayLists{

    void main(){

        ArrayList<Marines> marines =new ArrayList<Marines>();

        marines.add(new Marines("blue","Octupus",5,"Ocean",false,8));
        marines.add(new Marines("Red","Shark",20,"Ocean",false,6));
        marines.add(new Marines("Red","Frog",1,"Ponds",true,0));
        marines.add(new Marines("black","Crab",1,"Rivers",true,4));

        System.out.println("Iteration");

        Comparator<Marines> marinesComparator=new Comparator<Marines>() {
            @Override
            public int compare(Marines o1, Marines o2) {
                if (o1.life>o2.life)
                    return 1;
                else if(o1.life<o2.life)
                    return -1;
                else
                    return 0;
            }
        };

        Collections.sort(marines,marinesComparator);
        Iterator<Marines> marinesIterator= marines.iterator();
        boolean found =false;
       while (marinesIterator.hasNext()){
           Marines mr=marinesIterator.next();
            if(mr.getFins()==6 && mr.getWater_type().equals("Ocean")&& mr.getLife()==20 && mr.getName().equals("Shark"))
            {     found=true;
                    break;
            }
        }
       if(found)
           System.out.println("The Object was found");


    }
}

class Hashmmaps {
    //
    void sortOnvalues(){
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

        Map<String,String> newHasmap= listEntry.stream().collect(Collectors.toMap(i->i.getKey(), i-> i.getValue()));
    }

    void interateHashmap(){
        HashMap<Integer,String> hashMap=new HashMap<Integer,String>();
        hashMap.put(1,"One");
        hashMap.put(2,"two");
        hashMap.put(3,"three");
        hashMap.put(4,"four");
        hashMap.put(5,"five");

        List<Integer> list=hashMap.keySet().stream().collect(Collectors.toList());
        System.out.println(list);
    }
}

class Sets implements Comparator<Marines> {
    //Types of sets-Tree set & Hash Set(ALLOWS NULL)
    // Tree set perform all the operation in O(N) time by using a self-balancing binary Tree. They can be used to store huge amaount of sorted data 🤕NULL
            // An explicit comparator can be provided to sort values on some different parameter.Tree set is also not Synchronized
            //Entry Set Provides additional functionalities for Map.
            //IF A CUSTOM OBJECT IS BEING INSERTED THEN comparator method must be implemented WITH KEEPING THE ORDER OF INT PROPERLY


    @Override
    public int compare(Marines o1, Marines o2) {
        if(o1.getLife()>o2.getLife())
            return 1;
        else if(o2.getLife()<o2.getLife())
            return 0;
        else
            return -1;
    }

    void main(){

       TreeSet<Marines> ts =new TreeSet<Marines>(new Sets());

       ts.add(new Marines("Blue","Octopus",20,"Ocean",false,8));
       ts.add(new Marines("Red","Shark",10,"DeepOcean",false,14));
       ts.add(new Marines("Red","Jelly Fish",5,"Ocean",false,8));
       ts.add(new Marines("Black","EEl",22,"Lake",false,6));
       ts.add(new Marines("Blue","Frog",12,"Pond",false,4));

       Iterator<Marines> itr= ts.iterator();
       while (itr.hasNext())
           System.out.println(itr.next());


    }
}









public class Collection {
    public static void main(String[] args) {

   Hashmmaps hashmmaps= new Hashmmaps();
   hashmmaps.interateHashmap();

    }

}
