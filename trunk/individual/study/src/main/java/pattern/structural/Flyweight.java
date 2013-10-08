package pattern.structural;
//http://en.wikipedia.org/wiki/Flyweight_pattern
//A flyweight is an object that minimizes memory use by sharing as much data as possible with other similar objects; it is a way to use objects in large numbers when a simple repeated representation would use an unacceptable amount of memory. Often some parts of the object state can be shared, and it is common practice to hold them in external data structures and pass them to the flyweight objects temporarily when they are used.
//
//A classic example usage of the flyweight pattern is the data structures for graphical representation of characters in a word processor. It might be desirable to have, for each character in a document, a glyph object containing its font outline, font metrics, and other formatting data, but this would amount to hundreds or thousands of bytes for each character. Instead, for every character there might be a reference to a flyweight glyph object shared by every instance of the same character in the document; only the position of each character (in the document and/or the page) would need to be stored internally.
import java.util.HashMap;
import java.util.Map;

//Flyweight object interface
interface CoffeeOrder {
    public void serveCoffee(CoffeeOrderContext context);
}
 
// ConcreteFlyweight object that creates ConcreteFlyweight 
class CoffeeFlavor implements CoffeeOrder {
    private String flavor;
 
    public CoffeeFlavor(String newFlavor) {
        this.flavor = newFlavor;
    }
 
    public String getFlavor() {
        return this.flavor;
    }
 
    public void serveCoffee(CoffeeOrderContext context) {
        System.out.println("Serving Coffee flavor " + flavor + " to table number " + context.getTable());
    }
}
 
class CoffeeOrderContext {
   private int tableNumber;
 
   public CoffeeOrderContext(int tableNumber) {
       this.tableNumber = tableNumber;
   }
 
   public int getTable() {
       return this.tableNumber;
   }
}

 
//FlyweightFactory object
class CoffeeFlavorFactory {
    private Map<String, CoffeeFlavor> flavors = new HashMap<String, CoffeeFlavor>();
 
    public CoffeeFlavor getCoffeeFlavor(String flavorName) {
        CoffeeFlavor flavor = flavors.get(flavorName);
        if (flavor == null) {
            flavor = new CoffeeFlavor(flavorName);
            flavors.put(flavorName, flavor);
        }
        return flavor;
    }
 
    public int getTotalCoffeeFlavorsMade() {
        return flavors.size();
    }
}
 
public class Flyweight {
   /** The flavors ordered. */
   private static CoffeeFlavor[] flavors = new CoffeeFlavor[100];
   /** The tables for the orders. */
   private static CoffeeOrderContext[] tables = new CoffeeOrderContext[100];
   private static int ordersMade = 0;
   private static CoffeeFlavorFactory flavorFactory;
 
   public static void takeOrders(String flavorIn, int table) {
       flavors[ordersMade] = flavorFactory.getCoffeeFlavor(flavorIn);
       tables[ordersMade++] = new CoffeeOrderContext(table);
   }
 
   public static void main(String[] args) {
       flavorFactory = new CoffeeFlavorFactory();
 
       takeOrders("Cappuccino", 2);
       takeOrders("Cappuccino", 2);
       takeOrders("Frappe", 1);
       takeOrders("Frappe", 1);
       takeOrders("Xpresso", 1);
       takeOrders("Frappe", 897);
       takeOrders("Cappuccino", 97);
       takeOrders("Cappuccino", 97);
       takeOrders("Frappe", 3);
       takeOrders("Xpresso", 3);
       takeOrders("Cappuccino", 3);
       takeOrders("Xpresso", 96);
       takeOrders("Frappe", 552);
       takeOrders("Cappuccino", 121);
       takeOrders("Xpresso", 121);
 
       for (int i = 0; i < ordersMade; ++i) {
           flavors[i].serveCoffee(tables[i]);
       }
       System.out.println(" ");
       System.out.println("total CoffeeFlavor objects made: " +  flavorFactory.getTotalCoffeeFlavorsMade());
   }
}
