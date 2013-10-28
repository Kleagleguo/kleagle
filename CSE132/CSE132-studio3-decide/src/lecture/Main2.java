package lecture;

public class Main2 {

	private int c;

	public Main2() {
		c = 0;
	}

	private void dosomething() {
		Thread t1 = new Thread() {  // anonymous inner class
			public void run() {
				c = 1;
			}

		};

		Thread t2 = new Thread() {
			public void run() {
				c = 2;
			}
		};

		t1.start();
		
		t2.start();

		// wait 
		try{
			t1.join();
			t2.join();
		} catch (Throwable t) { }
		
		System.out.println("C is " + c);

	}
	
	private static void snooze() {
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// FIXME Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private synchronized void incr() {
		// c = c + 1
		int val = c+1;
		snooze();
		c = val;
	}
	
	private synchronized void decr() {
		// c = c + 1
		int val = c-1;
		snooze();
		c = val;
	}
	private void dosomethingelse() {
		for (int i=0; i < 50; ++i) {
			final int inneri = i;
			Thread t = new Thread() {
				public void run() {
					if (inneri % 2 == 0) { //i is even
						incr();
					}
					else {
						decr();
					}
				}
			};
			t.start();
		}
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Main2 m = new Main2();
		// m.dosomething();
		m.dosomethingelse();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// FIXME Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("c is "  + m.c);

	}

}
