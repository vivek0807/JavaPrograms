package $Expertise.Language;
// Its not necessary to have catch block for try, we can directly have catch block
//The program stops if exception rises in catch block itself
//Even if a wrong exception is caught, the program shall stop
//Checked Exceptions- SQLe,ClassNotfoundE,IOe
//unchecked Exception- numberFormate,inputE,ArrayIndexE,NullPointer
//The Exception is always tried at the function calling block--KEEP TRACING BACK TILL ORIGINAL METHOD.
//CHECKED EXCEPTIONS ARE NOT PROPAGATED
// THROWS KEYWORD- is just to show the exception that may happen, Actual exception is only caught using TRY & CATCH BLOCK

//AFTER using throw keyword, further lines of code are not executed
// for a method to thorw a CUSTOM EXCEPTION the method must declare the same with THROWS KEYWORD
//**If the superclass method does not declare an exception, SUBCLASS OVERRIDDEN METHOD CANNOT DECLARE THE CHECKED EXCEPTION but can declare unchecked exception also hirarchy has to maintained going on deeper side or No exception.**//
