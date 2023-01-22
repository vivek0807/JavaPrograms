package $Expertise.Language;
// -Helps achieve Loose coupling and multiple Inheritance
//In an interface the methods are public and abstract and Variables are static and final
//static methods in interfaces must have their definition
//Eg of tagged interface Serializable, Clonable,Remote etc
// AS the implementation of methods goes lower we have to reduce the Access restriction
// An Abstract class is just like an Ordinary class with an additional feature of declaring methods without body.
// When implementing an abstract class that has already implemented an interface // it must describe all the methods from class and interface
//Functional Interfaces-- Interface having only one method declaration Runnable, ActionListener, Comparable
//Functional Interface can have only one function definition i.e DEFAULT method
@FunctionalInterface
interface FuncI{
    float funcbal(int n);

}
interface Banks{
    float Balance=120000;
    float currentBalace();
    float getMoney(float amount);
    float putMoney(float amount);
}

abstract class CreditCard implements Banks{

    public abstract float currentBalace(); // Access privilage weakened
    abstract void addLoan();
    }

class CreditCardBalancer extends  CreditCard{
    @Override
    void addLoan() {
        System.out.println("Loan added"); // When implementing an abstract class that has already implemented an interface
                                            // it must describe all the methods from class and interface
    }

    @Override
    public float getMoney(float amount) {
        return 0;
    }

    @Override
    public float putMoney(float amount) {
        return 0;
    }

    @Override
    public float currentBalace() {
        return 0;
    }
}

class SBI implements Banks {    // A class can only use extends Keyword first and then implements Keyword
    float SBImoney=Banks.Balance;
    @Override
    public float currentBalace() {
        return Banks.Balance;
    }

    @Override
    public float getMoney(float amount) {
        this.SBImoney=Banks.Balance-amount;
        return Banks.Balance;
    }

    @Override
    public float putMoney(float amount) {
        this.SBImoney=Banks.Balance+amount;
        return this.SBImoney;
    }
}
class Icici implements Banks{
    @Override
    public float currentBalace() {
        return Banks.Balance;

    }

    @Override
    public float putMoney(float amount) {
        return 0;
    }

    @Override
    public float getMoney(float amount) {
        return 0;
    }
}

public class Interfaces {
    public static void main(String[] args) {
        SBI s =new SBI();
        System.out.println(s.currentBalace());
        System.out.println(Banks.Balance); // Same output as above

    }
}
