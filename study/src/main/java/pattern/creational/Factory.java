package pattern.creational;

abstract class Pizza1 {
    public abstract double getPrice();
}
 
class HamAndMushroomPizza extends Pizza1 {
    public double getPrice() {
        return 8.5;
    }
}
 
class DeluxePizza extends Pizza1 {
    public double getPrice() {
        return 10.5;
    }
}
 
class HawaiianPizza extends Pizza1 {
    public double getPrice() {
        return 11.5;
    }
}
 
class PizzaFactory {
    public enum PizzaType {
        HamMushroom,
        Deluxe,
        Hawaiian
    }
 
    public static Pizza1 createPizza(PizzaType pizzaType) {
        switch (pizzaType) {
            case HamMushroom:
                return new HamAndMushroomPizza();
            case Deluxe:
                return new DeluxePizza();
            case Hawaiian:
                return new HawaiianPizza();
//            default:
//				throw new ArgumentError("The pizza type " + type + " is not recognized.");
        }
        throw new IllegalArgumentException("The pizza type " + pizzaType + " is not recognized.");
    }
}
 
public class Factory {
    /*
     * Create all available pizzas and print their prices
     */
    public static void main (String args[]) {
        for (PizzaFactory.PizzaType pizzaType : PizzaFactory.PizzaType.values()) {
            System.out.println("Price of " + pizzaType + " is " + PizzaFactory.createPizza(pizzaType).getPrice());
        }
    }
}
