package jdkUtil;

import java.io.IOException;

public class Singleton {
    // Private constructor prevents instantiation from other classes
    private Singleton() {
        System.out.println("instance returned");
    }

    /**
    * SingletonHolder is loaded on the first execution of Singleton.getInstance() 
    * or the first access to SingletonHolder.INSTANCE, not before.
    */
    private static class SingletonHolder { 
            public static final Singleton INSTANCE = new Singleton();
            static{
                System.out.println("holder initialization");
            }
            
    }

    static{
        System.out.println("Class initialization");
    }
    public static Singleton getInstance() {
            return SingletonHolder.INSTANCE;
    }
    
    public static void main(String[] args){
        Singleton s = SingletonHolder.INSTANCE;
    }
}
//the inner class SingletonHolder can be also substituted by implementing a Property which provides also access to the static final/read-only class members.


enum SingletonEnum {
    INSTANCE;
    public void execute (String arg) {
            //... perform operation here ...
    }
}
//This approach implements the singleton by taking advantage of Java's guarantee that any enum value is instantiated only once in a Java program. Since Java enum values are globally accessible, so is the singleton. The drawback is that the enum type is somewhat inflexible; for example, it does not allow lazy 


class Singleton1 {
    private static final Singleton1 instance = new Singleton1();
 
    private Singleton1() {}
 
    public static Singleton1 getInstance() {
        return instance;
    }
}
//Eager initialization
//The instance is not constructed until the class is used.
//There is no need to synchronize the getInstance() method, meaning all threads will see the same instance and no (expensive) locking is required.
//The final keyword means that the instance cannot be redefined, ensuring that one (and only one) instance ever exists.


//class Singleton2 {
//    private static final Singleton2 instance;
//   
//    static {
//      try {
//        instance = new Singleton2();
//      } catch (IOException e) {
//        throw new RuntimeException("Darn, an error occurred!", e);
//      }
//    }
//   
//    public static Singleton2 getInstance() {
//      return instance;
//    }
//   
//    private Singleton2() {
//      // ...
//    }
//  }
//Static block initialization