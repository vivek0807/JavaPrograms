package $Expertise.DesignPatternCode.Creational;

/**
 * A prototype pattern is used in a scenario where creating an object has a complex logic
 * <p>We use techniques like cloning to directly copy the main object with limited modifications</p>
 * <li>Circle and Square are the two class implementing Shape interface for common methodology</li>
 * <li>Shape client is the Container class that is resposible for making copy of all classes using the shape interface</li>
 * <li>We first create one object of circle and square and put them in the container class</li>
 * <li> Then we use container class's create method to copy the Objects</li>
 */

interface  Shape{

    Shape clone();
    void draw();
}

class Circle implements Shape{
    String color;

    Circle(String color){
        this.color = color;
    }

    @Override
    public void draw() {
        System.out.println("-----");
        System.out.println("");
        System.out.println("-----");
        System.out.println("-----");
        System.out.println("-----");
    }

    public Shape clone(){
        return new Circle(this.color);
    }
}

class Square implements Shape{
    String color;
    Square(String color){
        this.color = color;
    }
    @Override
    public void draw()
    {
        System.out.println("Drawing a square");
    }

    public Shape clone(){
        return new Square(this.color);
    }
}

class ShapeClient{

    Shape shapePrototype;

    ShapeClient( Shape shapePrototype){
        this.shapePrototype = shapePrototype;
    }

    public Shape CreateShape(){
        return  shapePrototype.clone();
    }
}

public class PrototypePattern {
    public static void main(String[] args) {
        Shape circle = new Circle("red");
        Shape square = new Square("green");
        ShapeClient shapeClientCircle = new ShapeClient(circle);
        ShapeClient shapeClientSquare = new ShapeClient(square);
        Shape squareCopy= shapeClientSquare.CreateShape();

        Shape circleCopy = shapeClientCircle.CreateShape();

        squareCopy.draw();
        circleCopy.draw();


    }
}
