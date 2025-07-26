package $Expertise.DesignPatternCode.Creational;

/**
 * <h1>Factory design Pattern</h1>
 * <h2>Usecases</h2>
 * <li>Used for making objects based on conditions</li>
 * <li>Separates the creation logic</li>
 * <h2>Steps for creation</h2>
 * <li>Creator</li>
 * <li>Concrete Creator</li>
 * <li>Product </li>
 * <li>Concrete Product </li>
 */
// Creator
 abstract class Vehicle{
   abstract void createVehicle();
}
// Concreate Creators
class TwoWheeler extends Vehicle{

    @Override
    void createVehicle() {
        System.out.println("Created two Wheeler Vehicle");
    }
}

class FourWheeler extends Vehicle{

     @Override
    void createVehicle(){
         System.out.println("Four Wheeler Created");
     }
}

// Factory

interface VehicleFactory{
     Vehicle createVehicle();
}
//Concrete factory
class TwoWheelerFactory implements VehicleFactory{

    @Override
    public Vehicle createVehicle() {
        return new TwoWheeler();
    }
}

class FourWheelerFactory implements VehicleFactory{

    @Override
    public Vehicle createVehicle() {
        return new FourWheeler();
    }
}

class FactoryClient{
     Vehicle vehicle;

     FactoryClient (VehicleFactory vehicle){
         this.vehicle= vehicle.createVehicle();
     }

     Vehicle getVehicle(){
         return vehicle;
     }

}
public class FactoryDesignPattern {

    public static void main(String[] args) {
        VehicleFactory twoWheelerFactory= new TwoWheelerFactory();
        FactoryClient twowheelerFactoryCleint= new FactoryClient(twoWheelerFactory);
        Vehicle twoWheeler= twowheelerFactoryCleint.getVehicle();
        twoWheeler.createVehicle();
    }
}
