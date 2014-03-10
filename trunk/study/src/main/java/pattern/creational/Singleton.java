package pattern.creational;

public class Singleton {
	private Singleton(){};
	private static class Singleton1Holder{
		private static final Singleton INSTANCE  = new Singleton();
	}
	public static Singleton getInstance(){
		return Singleton1Holder.INSTANCE ;
	}
}
