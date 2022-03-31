package $Expertise.Language;

import java.util.ArrayList;
import java.util.Iterator;

class Animals{
    String blood_color;
    String name;
    int life;

    public Animals(String blood_color, String name, int life) {
        this.blood_color = blood_color;
        this.name = name;
        this.life = life;
    }
}
class Marines extends Animals{
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
        Iterator<Marines> iterator=marines.iterator();
        while (iterator.hasNext()){
            Marines mri=iterator.next();
            System.out.println(mri);
            if(new Marines("Red","Frog",1,"Ponds",true,0).equals(mri)){
                System.out.println("True");
            }
        }




    }
}

public class Collections {
    public static void main(String[] args) {

        ArrayLists arl=new ArrayLists();
        arl.main();
    }

}
