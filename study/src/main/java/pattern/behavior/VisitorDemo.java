package pattern.behavior;


interface CarElementVisitor {
    void visit(Wheel wheel);
    void visit(Engine engine);
    void visit(Body body);
    void visit(Car car);
}
interface CarElement {
    void accept(CarElementVisitor visitor); // CarElements have to provide accept().
}
class Wheel implements CarElement {
    private String name;
    Wheel(String name) {
        this.name = name;
    }
    String getName() {
        return this.name;
    }
    public void accept(CarElementVisitor visitor) {
        visitor.visit(this);
    }
}
 
class Engine implements CarElement {
    public void accept(CarElementVisitor visitor) {
        visitor.visit(this);
    }
}
 
class Body implements CarElement {
    public void accept(CarElementVisitor visitor) {
        visitor.visit(this);
    }
}
 
class Car implements CarElement{
    CarElement[] elements;
    public CarElement[] getElements() {
        return elements.clone(); // Return a copy of the array of references.
    }
    public Car() {
        this.elements = new CarElement[]
          { new Wheel("front left"), new Wheel("front right"),
            new Wheel("back left") , new Wheel("back right"),
            new Body(), new Engine() };
    }
    public void accept(CarElementVisitor visitor) {
	visitor.visit(this);			
    }
 
}
 
class CarElementPrintVisitor implements CarElementVisitor {
    public void visit(Wheel wheel) {      
        System.out.println("Visiting "+ wheel.getName()
                            + " wheel");
    }
    public void visit(Engine engine) {
        System.out.println("Visiting engine");
    }
    public void visit(Body body) {
        System.out.println("Visiting body");
    }
 
    public void visit(Car car) {
        System.out.println("\nVisiting car");
        for(CarElement element : car.getElements()) {
            element.accept(this);
        }
        System.out.println("Visited car");
    }
}
 
class CarElementDoVisitor implements CarElementVisitor {
    public void visit(Wheel wheel) {
        System.out.println("Kicking my "+ wheel.getName());
    }
    public void visit(Engine engine) {
        System.out.println("Starting my engine");
    }
    public void visit(Body body) {
        System.out.println("Moving my body");
    }
    public void visit(Car car) {
        System.out.println("\nStarting my car");
        for(CarElement carElement : car.getElements()) {
            carElement.accept(this);
        }
        System.out.println("Started car");
    }
 
}
 
public class VisitorDemo {
    static public void main(String[] args){
        Car car = new Car();
        car.accept(new CarElementPrintVisitor());
        car.accept(new CarElementDoVisitor());
    }
}
