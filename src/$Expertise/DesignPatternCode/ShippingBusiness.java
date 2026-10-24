package $Expertise.DesignPatternCode;


enum CustomersType{
    REGULAR(10),SUBSCRIBED(20),LOYAL(40);

    private  int value;

    CustomersType(Integer integer){
        this.value= integer;
    }

    int getRebate(){
        return this.value;
    }
}

enum CategoryType{
    HARDWARE(100),BOOKS(50),CLOTHES(75);

   private int value;

    CategoryType(Integer integer){
        this.value=integer;
    }

    int getCategoryRebate(){
        return value;
    }
}

class Category{
    CategoryType categoryType;

    public Category(String categoryType) {
        this.categoryType = CategoryType.valueOf(categoryType);
    }

    int getCategoryRebate(){
        return categoryType.getCategoryRebate();
    }
}

class Customer{

    CustomersType customersType;

    public Customer(CustomersType customersType) {
        this.customersType = customersType;
    }

    int getCustomerTypeRebate(){
        return customersType.getRebate();
    }
}

class CartValue{
    Customer customer;
    Category category;
    int quantity;

    CartValue (String cutomerType,String categoryType, int quantity){
        this.customer = new Customer(CustomersType.valueOf(cutomerType));
        this.category = new Category(categoryType);
        this.quantity= quantity;
    }

    int finalPrice(){
      return   (category.getCategoryRebate()*quantity)-customer.getCustomerTypeRebate();
    }
}

public class ShippingBusiness {
    static void main() {

        CartValue cartValue = new CartValue("REGULAR","HARDWARE",10);
        System.out.println(cartValue.finalPrice());
    }
}
