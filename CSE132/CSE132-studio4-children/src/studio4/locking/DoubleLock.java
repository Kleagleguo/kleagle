package studio4.locking;

import static studio4.misc.Sleeper.sleep;


/**
 * To be implemented in studio * @author cytron
 *
 */
public class DoubleLock implements LockInvokable {
	
	private LockInvokable lock1, lock2;


	public DoubleLock(SingleLockInvokable o1, SingleLockInvokable o2) {
		if (o1.getUniqueID() < o2.getUniqueID()) {
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
		            sleep(50);
		            lock2.lockInvoke(r);
		         }
		      }
		   );
		}

}


