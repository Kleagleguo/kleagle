package lecture;

public class GuardedBlockExample {
	
	// Generic example of a guarded block that needs some
	//   predicate "p" to be true before it can operate
	//   correctly.

	public synchronized void needsP() {
		
		boolean p = false; // this line is here just to make Java happy
		                   // "p" is meant to be some predicate, below
		
		// Here, we have a lock on "this" because
		//   the method is synchronized.  We need the
		//   lock on "this" or "this.wait()" will raise
		//   an IllegalMonitorState Exception
		
		try {
			while (! p) {	  // replace p with the actual condition
					    	  // you need to be true
				
				this.wait();  // temporarily releases lock on "this" and
				              // waits until notifyAll....
				// here..........we woke up because another Thread
				//               did notifyAll on "this" object
				//    and this Thread was selected among those that were
				//    waiting for "this" object
			}		
		}
		catch(Throwable t) {
			// can't happen
		}
		
		// This point in the program is "guarded by" predicate p
		// Here, "p" is true (loop exited) and we have a lock on "this"
		//
		//  act, because we can!
		//  
		//  when we hit end of this method, lock on "this" is released
	}
	
	public synchronized void canEstablishP() {
		//
		// Here we have a lock on "this"
		//   We need that for notifyAll()
		//
		
		//
		//  if the code here could cause a "p" to be true for some Thread
		//     waiting on "this" object, then....
		this.notifyAll();
		//  we still hold the lock on "this", so the notification
		//  cannot happen until this method returns
		
	}

}
