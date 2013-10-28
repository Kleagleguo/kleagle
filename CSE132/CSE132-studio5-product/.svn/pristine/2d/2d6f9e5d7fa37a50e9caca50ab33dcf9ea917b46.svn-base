package lecture;

public class BoundedInteger {

	final private int min, max;
	private int val;
	
	public BoundedInteger(int min, int max) {
		this.min = min; this.max = max;
		this.val = min;

	}

	public synchronized int incr() {
		// At this point, we want 
		//    the lock on "this" object
		//    val < max
		
		while (! (val < max)) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				// FIXME Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		// here, val < max
		
		// The line below is safe because we have the lock
		val = val + 1;
		int ans = val;
		
		this.notifyAll();  // wake up anybody who cares about val
		return check(ans);

	}

	public synchronized int decr() {
		// At this point, we want 
		//    the lock on "this" object
		//    val > min
		
		while (! (val > min)) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				// FIXME Auto-generated catch block
				e.printStackTrace();
			}
		}
		// here, val > min
		
		
		val = val - 1;
		int ans = val;
		this.notifyAll();
		return check(ans);
	}

	private int check(int val) {
		if (min <= val && val <= max)
			return val;
		else throw new Error("Bad value: " + val);
	}

	public static void main(String[] args) {
		final BoundedInteger bi = new BoundedInteger(0,5);
		//
		// Spawn 1000 threads that randomly increment or decrement 
		while (true) {
			new Thread() {
				public void run() {
					if (Math.random() < 0.5) {
						System.out.println("incr " + bi.incr());
					}
					else {
						System.out.println("decr " + bi.decr());
					}
				}
			}.start();
		}
	}

}
