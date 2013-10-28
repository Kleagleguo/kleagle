/** 
 * Create a double lock on two objects
 * @author 			Bingkun Guo
 * Lab Section:		C
 * Email:			guobingkun@wustl.edu
 * Lab:				Lab3B:Mazes 
 * File:			DoubleLock.java
 */
package lab3a.locking;

/**
 * Replace this by your studio implementation.
 * This one doesn't obtain locks properly, so it can lead to deadlock.
 *
 */
public class DoubleLock implements LockInvokable {
	private LockInvokable lock1, lock2;

	/**
	 * Constructor of the DoubleLock
	 * assign the lock based on their uniqueID
	 */
	public DoubleLock(SingleLockInvokable o1, SingleLockInvokable o2) {
		if (o1.getUniqueID() < o2.getUniqueID() ) {
			this.lock1 = o1;
			this.lock2 = o2;
		}
		else {
			this.lock1 = o2;
			this.lock2 = o1;
		}
	}

	/**
	 * While holding both locks, run r.  Sleep a bit before getting the second lock.
	 */
	public void lockInvoke(final Runnable r) {
		lock1.lockInvoke(
				new Runnable() {
					public void run() {
						lab3a.Lab.sleep();
						lock2.lockInvoke(r);
					}
				}
				);
	}

}


