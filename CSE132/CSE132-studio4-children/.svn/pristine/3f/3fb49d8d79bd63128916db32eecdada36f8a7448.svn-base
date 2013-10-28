package studio4.puzzler;

import static studio4.misc.Sleeper.sleep;
import studio4.locking.LockInvokable;


public class MultipleLock implements LockInvokable {

	private Object[] things;
	

	public MultipleLock(Object[] thingsToLock) {
		things = thingsToLock;
		
	}

	/**
	 * After holding a lock on each Object in things, run r.
	 * You have to complete this method.
	 */
	public void lockInvoke(Runnable r) {
		for (Object p : things) {
			((LockInvokable) p).lockInvoke(
				      new Runnable() {
					         public void run() {
					         }
					      }
					   );
		}
	}
	
	public void lockInvoker(Runnable r, int length) {
		for (int i = 0; i < length; i++) {
			
		}
	}

}
