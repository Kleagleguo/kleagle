package lecture;

public class MyRunnable implements Runnable {

	private String message;
	public MyRunnable(String message) {
		this.message = message;
	}
	public void run() {
		System.out.println("hello from " + message);

	}

}
