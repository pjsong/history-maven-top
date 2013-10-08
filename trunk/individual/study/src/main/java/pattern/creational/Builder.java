package pattern.creational;

/** "Product" */
class PizzaCookie {
	private String dough = "";
	private String sauce = "";
	private String topping = "";
 
	public void setDough(String dough) {
		this.dough = dough;
	}
 
	public void setSauce(String sauce) {
		this.sauce = sauce;
	}
 
	public void setTopping(String topping) {
		this.topping = topping;
	}
}
 
/** "Abstract Builder" */
abstract class PizzaBuilder {
	protected PizzaCookie pizza;
 
	public PizzaCookie getPizza() {
		return pizza;
	}
 
	public void createNewPizzaProduct() {
		pizza = new PizzaCookie();
	}
 
	public abstract void buildDough();
 
	public abstract void buildSauce();
 
	public abstract void buildTopping();
}

/** "ConcreteBuilder" */
class HawaiianPizzaBuilder extends PizzaBuilder {
	public void buildDough() {
		pizza.setDough("cross");
	}
 
	public void buildSauce() {
		pizza.setSauce("mild");
	}
 
	public void buildTopping() {
		pizza.setTopping("ham+pineapple");
	}
}
 
/** "ConcreteBuilder" */
class SpicyPizzaBuilder extends PizzaBuilder {
	public void buildDough() {
		pizza.setDough("pan baked");
	}
 
	public void buildSauce() {
		pizza.setSauce("hot");
	}
 
	public void buildTopping() {
		pizza.setTopping("pepperoni+salami");
	}
}

/** "Director" */
class Cook {
	private PizzaBuilder pizzaBuilder;
 
	public void setPizzaBuilder(PizzaBuilder pb) {
		pizzaBuilder = pb;
	}
 
	public PizzaCookie getPizza() {
		return pizzaBuilder.getPizza();
	}
 
	public void constructPizza() {
		pizzaBuilder.createNewPizzaProduct();
		pizzaBuilder.buildDough();
		pizzaBuilder.buildSauce();
		pizzaBuilder.buildTopping();
	}
}
 
/** A given type of pizza being constructed. */
public class Builder {
	public static void main(String[] args) {
		Cook cook = new Cook();
		PizzaBuilder hawaiianPizzaBuilder = new HawaiianPizzaBuilder();
		PizzaBuilder spicyPizzaBuilder = new SpicyPizzaBuilder();
 
		cook.setPizzaBuilder(hawaiianPizzaBuilder);
		cook.constructPizza();
 
		PizzaCookie pizza = cook.getPizza();
	}
}
//Ҫ�õ�һ����Ա
//һ������ױʦ��һ����Ա���ж�����󷽷�����Ա��ױ
//�����ͬ���廯ױʦ���廯ױ
//����ȷ�����廯ױʦ
//����ͨ����廯ױʦ��ױ�������õ���Ա
