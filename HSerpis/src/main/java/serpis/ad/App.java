package serpis.ad;


public class App {
	private static App instance=new App();
	
	private App() {}
	
	public static App getInstance() {
		return instance;
	}
	
	
	
}
