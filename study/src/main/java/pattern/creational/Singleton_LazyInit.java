package pattern.creational;

public class Singleton_LazyInit {
	private static volatile Singleton_LazyInit instance = null;

	private Singleton_LazyInit() {
	}

	public static Singleton_LazyInit getInstance() {
            if (instance == null) {
                    synchronized (Singleton.class){
                            if (instance == null) {
                                    instance = new Singleton_LazyInit();
                            }
            }}
            return instance;
    }
}
