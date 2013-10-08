package pattern.behavior.observer;

import java.util.Observable;
import java.util.Observer;

public class DeletegateObserver {
	public static void main(String args[]) {

	  }
	public static void test1(){
	    SpecialSubject s = new SpecialSubject("Corn Pops", 1.29f);
	    NameObserver nameObs = new NameObserver();
	    PriceObserver priceObs = new PriceObserver();
	    // Add those Observers!
	    s.getObservable().addObserver(nameObs);
	    s.getObservable().addObserver(priceObs);
	    // Make changes to the Subject.
	    s.setName("Frosted Flakes");
	    s.setPrice(4.57f);
	    s.setPrice(9.22f);
	    s.setName("Sugar Crispies");
	}
	public static void test2(){
		SpecialSubject2 s = new SpecialSubject2("Corn Pops", 1.29f);
	    NameObserver nameObs = new NameObserver();
	    PriceObserver priceObs = new PriceObserver();
	    // Add those Observers!
	    s.addObserver(nameObs);
	    s.addObserver(priceObs);
	    // Make changes to the Subject.
	    s.setName("Frosted Flakes");
	    s.setPrice(4.57f);
	    s.setPrice(9.22f);
	    s.setName("Sugar Crispies");
	}
}
class SpecialSubject extends DeletegateObserver {
	  private String name;
	  private float price;
	  private DelegatedObservable obs;
	  public SpecialSubject(String name, float price) {
	    this.name = name;
	    this.price = price;
	    obs = new DelegatedObservable();
	  }
	  public String getName() {return name;}
	  public float getPrice() {return price;}
	  public Observable getObservable() {return obs;}
	 public void setName(String name) {
	    this.name = name;
	    obs.setChanged();
	    obs.notifyObservers(name);
	  }
	  public void setPrice(float price) {
	    this.price = price;
	    obs.setChanged();
	    obs.notifyObservers(new Float(price));
	  }
	}

	class DelegatedObservable extends Observable {
	  public void clearChanged() {
	    super.clearChanged();
	  }
	  public void setChanged() {
	    super.setChanged();
	  }
	}
	class SpecialSubject2 extends DeletegateObserver {
		  private String name;
		  private float price;
		  private DelegatedObservable obs;
		public SpecialSubject2(String name, float price) {
		    this.name = name;
		    this.price = price;
		    obs = new DelegatedObservable();
		  }
		  public String getName() {return name;}
		  public float getPrice() {return price;}
		  public void addObserver(Observer o) {
		    obs.addObserver(o);
		  }
		  public void deleteObserver(Observer o) {
		    obs.deleteObserver(o);
		  }
		public void setName(String name) {
		    this.name = name;
		    obs.setChanged();
		    obs.notifyObservers(name);
		  }
		  public void setPrice(float price) {
		    this.price = price;
		    obs.setChanged();
		    obs.notifyObservers(new Float(price));
		  }
		}