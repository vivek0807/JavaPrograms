package $Expertise.Language;

import java.util.Optional;

/*
* OPtional class is a class that is primarily used to avoid NullPointerException
* It has inbuilt Methods such as orelse is present etc that gives addional check and handle ability to
* avoid Such Exceptions
* The Best practice is to make return type of a Method as optional
* */
public class OPtionalClass {
    static Optional<String> returnOPtionaString(){

        return Optional.ofNullable("Hello Nullable");
    }
    public static void main(String[] args) {

        Optional<String> getOptClass=returnOPtionaString();
        System.out.println(getOptClass.orElse("Null tha bhaii"));

    }
}
