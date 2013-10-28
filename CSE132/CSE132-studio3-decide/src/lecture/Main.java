package lecture;

public class Main {
	
	// running a Runnable the normal way
	private void thing1() {
		Runnable r = new MyRunnable("thing1");
		r.run();
	}
	
	private void thing2() {
		Runnable r = new MyRunnable("thing2");
		Thread t = new Thread(r);
		// above is not enough to run the thread!
		// to run it
		t.run(); // does not create independent work, just runs
		         // t's runnable
		Thread t2 = new Thread(r);
		t2.start(); // two loci of control...this thread and
		   // the one running t2's run method
	}
	
	private void thing3( ){
		Runnable rA = new MyRunnable("A");
		Runnable rB = new MyRunnable2();
		Thread t1 = new Thread(rA);
		Thread t2 = new Thread(rB);
		t1.start();
		t2.start();
		System.out.println("I am waiting");
		//
		// let's wait for t1 and t2 to finish before going on
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// never happens
		} // wait until t1 is finished
		System.out.println("started them");
		
	}
	
	public void thing4() {
		Thread t = new MyThread();
		t.start();
	}
	
	private static void snooze() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// FIXME Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Main m = new Main();
		// examples below, we make a new Runnable
		//   pass that to a new Thread
		//   .start the thread
		snooze();
		m.thing1();
		m.thing2();
		m.thing3();
		m.thing4();

	}

}
