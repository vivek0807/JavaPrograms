package $Expertise.Language;
/**
 * sealed class in java is a concept where we can limit the classes that want to inherit them
 * we use SEALED CLASS CLASS_NAME PERMIT CLASS_A, CLASS_B,CLASS_C {}
 * A class inheriting them must declare weather its sealed or not
 * A sealed class can be inherited by only class that has sealed/non-sealed/final keyword attached with it
 */

sealed class Bike permits Honda
{}

sealed class Honda extends Bike {

}

final class Yamaha extends Honda{}

class Suzuki{}

class Hero{}
public class SealedClass  {
}
