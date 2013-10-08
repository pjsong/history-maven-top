package pattern.creational;

public class Single_evolve {

}
//Single threaded version,A lock must be obtained in case two threads call getHelper() simultaneously
 class Foo {
    private Foo helper = null;
    public Foo getHelper() {
        if (helper == null) {
            helper = new Foo();
        }
        return helper;
    }
}
//Correct but possibly expensive multithreaded version,Since synchronizing a method can decrease performance by a factor of 100 or higher,[3] the overhead of acquiring and releasing a lock every time this method is called seems unnecessary: once the initialization has been completed, acquiring and releasing the locks would appear unnecessary.
 class Foo1 {
     private Foo1 helper = null;
     public synchronized Foo1 getHelper() {
         if (helper == null) {
             helper = new Foo1();
         }
         return helper;
     }
 }

//Broken multithreaded version,
//"Double-Checked Locking" idiom
class Foo2 {
  private Foo2 helper = null;
  public Foo2 getHelper() {
      if (helper == null) {
          synchronized(this) {
              if (helper == null) {
                  helper = new Foo2();
              }
          }
      }
      return helper;
  }
}

//The volatile keyword now ensures that multiple threads handle the singleton instance correctly
class Foo3 {
    private volatile Foo3 helper = null;
    public Foo3 getHelper() {
    	Foo3 result = helper;
        if (result == null) {
            synchronized(this) {
                result = helper;
                if (result == null) {
                    helper = result = new Foo3();
                }
            }
        }
        return result;
    }
}


