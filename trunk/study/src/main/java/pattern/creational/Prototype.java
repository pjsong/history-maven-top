package pattern.creational;

 class Cookie implements Cloneable {
    @Override
    public Cookie clone() throws CloneNotSupportedException {
        // call Object.clone()
        Cookie copy = (Cookie) super.clone();
 
        //In an actual implementation of this pattern you might now change references to
        //the expensive to produce parts from the copies that are held inside the prototype.
 
        return copy;
    }
}
 /**
  * Concrete Prototypes to clone
  */
 class CoconutCookie extends Cookie { }

/**
 * Client Class
 */
public class Prototype {
    private Cookie cookie; // Could have been a private Cloneable cookie.
    public Prototype(Cookie cookie) {
        this.cookie = cookie;
    }
    public Cookie makeCookie() throws CloneNotSupportedException {
        return (Cookie) this.cookie.clone();
    }
    public static void main(String args[]) throws CloneNotSupportedException {
        Cookie tempCookie = null;
        Cookie prot = new CoconutCookie();
        Prototype cm = new Prototype(prot);
        for (int i = 0; i < 100; i++)
            tempCookie = cm.makeCookie();
    }
}

