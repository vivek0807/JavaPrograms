package $Expertise.Language;
//Clone implementation
// The Clone interface has a Method clone that has to be implemented in the class that has to be cloned
import org.w3c.dom.ls.LSOutput;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
class FoodGarage{
    float totalBill;
    String custName;
    int persons;
    String customizations;

    public float getTotalBill() {
        return totalBill;
    }

    public void setTotalBill(float totalBill) {
        this.totalBill = totalBill;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public int getPersons() {
        return persons;
    }

    public void setPersons(int persons) {
        this.persons = persons;
    }

    public String getCustomizations() {
        return customizations;
    }

    public void setCustomizations(String customizations) {
        this.customizations = customizations;
    }
}
 class MenuBuilder{
    int ketchup=20;
    int myo=50;
    int butter=30;
    int nonveg=100;

     int getPrice(int basePrice,boolean k,boolean m,boolean b,boolean n){
        return basePrice+this.ketchup+this.myo+this.butter+this.nonveg;
    }
     int getPrice(int basePrice,boolean k,boolean m,boolean b){
        return basePrice+this.ketchup+this.myo+this.butter;
    }
     int getPrice(int basePrice,boolean k,boolean m){
        return basePrice+this.ketchup+this.myo;
    }
     int getPrice(int basePrice,boolean k){
        return basePrice+this.ketchup;
    }
     int getPrice(int basePrice){
        return basePrice;
    }

    void Menubuilder(){
        System.out.println("Coming from Menubuilder class");
    }

}
class BillGenerator extends MenuBuilder implements Cloneable{
    FoodGarage fg;
    int oneprice;
    public FoodGarage getFg() {
        return fg;
    }

    public void setFg(FoodGarage fg) {
        this.fg = fg;

        if(fg.getCustomizations().equalsIgnoreCase("yes")){
            MenuBuilder mb=new MenuBuilder( );
            this.oneprice=mb.getPrice(500,true,true,true);
        }
    }

    double totalWithGST(){
        return  (this.oneprice*fg.getPersons())+0.18*(this.oneprice*fg.getPersons());
    }

    @Override
    void Menubuilder() {
        System.out.println("Coming from bill generator class");
    }

    @Override
    public String toString() {
        return "Your Total bill is "+ this.totalWithGST();
    }

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
}


public class OOP {
    public static void main(String[] args) {
        try{
        Scanner sc=new Scanner(System.in);
        FoodGarage fg=new FoodGarage();
        System.out.println("Provide Customer Name");
        fg.setCustName(sc.nextLine());
        System.out.println("Please give number of customers");
        fg.setPersons(sc.nextInt());
        sc.nextLine();
        System.out.println("Please Tell if you are going for modifications");
        fg.setCustomizations(sc.nextLine());
        BillGenerator bg=new BillGenerator();
        bg.setFg(fg);
        System.out.println(bg);

        BillGenerator bgCopy=(BillGenerator)bg.clone();
            System.out.println("Coming from clone object"+bgCopy);
        }

        catch (InputMismatchException|NumberFormatException|CloneNotSupportedException e){
            SimpleDateFormat sd= new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date dt=new Date();
            System.out.println(e +" at time "+sd.format(dt));
        }




    }
}
