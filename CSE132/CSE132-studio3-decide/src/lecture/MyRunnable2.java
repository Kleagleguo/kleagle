package lecture;

public class MyRunnable2 implements Runnable {

	@Override
	public void run() {
		try {
			Thread.sleep(5000); // in ms, so 5 seconds
		} catch (InterruptedException e) {
			// FIXME Auto-generated catch block
			e.printStackTrace();
		}

	}

}
