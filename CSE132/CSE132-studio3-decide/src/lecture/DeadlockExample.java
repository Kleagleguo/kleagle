package lecture;

// From http://download.oracle.com/javase/tutorial/essential/concurrency/deadlock.html

public class DeadlockExample {
	static class Friend {
		private final String name;
		public Friend(String name) {
			this.name = name;
		}
		public String getName() {
			return this.name;
		}
		public synchronized void bow(Friend bower) {
			System.out.format("%s: %s has bowed to me!%n", 
					this.name, bower.getName());
			//
			// friend bower has bowed to me, so now I must bow back
			//    "synchronized" keeps me from doing so if bower
			//    is currently in either "bow" or "bowBack" methods
			//
			bower.bowBack(this);
		}
		public synchronized void bowBack(Friend bower) {
			System.out.format("%s: %s has bowed back to me!%n",
					this.name, bower.getName());
		}
	}
	
	// FIXME:  Below, change the .run calls to .start calls to see the deadlock

	public static void main(String[] args) {
		final Friend alphonse = new Friend("Alphonse");
		final Friend gaston = new Friend("Gaston");
		
		new Thread(new Runnable() {
			public void run() { alphonse.bow(gaston); }
		}).start();
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// FIXME Auto-generated catch block
			e.printStackTrace();
		}
		new Thread(new Runnable() {
			public void run() { gaston.bow(alphonse); }
		}).start();
		
	}
}


