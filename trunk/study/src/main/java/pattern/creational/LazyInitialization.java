package pattern.creational;

import java.util.*;

public class LazyInitialization
{
    private static final Map<String,LazyInitialization> types = new HashMap<String,LazyInitialization>();
    private final String type;
 
    // using a private constructor to force use of the factory method.
    private LazyInitialization(String type) {
      this.type = type;
    }
 
    public static synchronized LazyInitialization getFruit(String type) {
      if(!types.containsKey(type))
      {
        types.put(type, new LazyInitialization(type)); // Lazy initialization
      }
      return types.get(type);
    }
}
