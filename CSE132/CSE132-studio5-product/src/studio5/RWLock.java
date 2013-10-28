package studio5;

public class RWLock {
	private int reader;
	private int writer;
	public RWLock() {
		// FIXME  add what you need here
		reader = 0;
		writer = 0;
	}

	/**
	 * First half of a read-only lock.  Should this be synchronized?
	 */
	public synchronized void acquireReadOnly() {
		// FIXME  need to implement this method
		while (writer != 0) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
		}
		reader++;
		this.notifyAll();
	}

	/**
	 * First half of a read-or-write lock.  Should this be synchronized?
	 */
	public synchronized void acquireReadWrite() {
		// FIXME need to implement this method
		while (writer != 0 || reader != 0) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		writer++;
		reader++;
		this.notifyAll();
	}

	/**
	 * Second half of either lock.  Should this be synchronized?
	 * Release whichever of the two locks the current thread holds.
	 * Make sure a Thread doesn't invoke this method without having one of the two locks!
	 */
	public synchronized void release() {
		// FIXME  need to implement this method
		if (writer != 0) {
			writer = 0;
		}
		if (reader != 0) {
			reader--;
		}
		this.notifyAll();
	}

	/**
	 * For convenience, acquire the read-only lock, run, release
	 * Should this be synchronized?
	 * @param r Run r after acquiring a read-only lock
	 */
	public void acquireReadOnly(Runnable r) {
		// TA Do not modify this method!
		try {
			acquireReadOnly();
			r.run();
		}
		finally {
			release();
		}

	}
	
	/**
	 * For convenience, acquire the read-write lock, run, release
	 * Should this be synchronized?
	 * @param r Run r after acquiring a read-write lock
	 */
	public void acquireReadWrite(Runnable r) {
		// TA Do not modify this method!
		try {
			acquireReadWrite();
			r.run();
		}
		finally {
			release();
		}

	}

}
