package $Expertise.DesignPatternCode;

public class OverLoadProblem {

    OverLoadProblem(){

    }

    public void print(Object s){
        System.out.println("I am inside 2nd print method");
    }

    public void print(String s){
        System.out.println("I am inside 1st print method");
    }
}
