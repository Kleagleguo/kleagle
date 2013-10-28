package lab3a.model;

import java.beans.PropertyChangeSupport;
import java.util.Random;


/**
 * A class to propagate status changes via firePropertyChange.
 * When the status changes, up to maxTime time is spent simulating a delay
 * due to the status change.
 *
 */

abstract public class StatusChanger {

	public static boolean logging = true;
	public static int     DELAY   = 200;
	
	final protected PropertyChangeSupport pcs;
	final protected Random rand;
	final protected int delay;
	
	/**
	 * 
	 * @param delay maximum time delaying when status changes
	 */
	public StatusChanger(int delay) {
		this.delay = delay;
		this.rand = new Random();
		pcs = new PropertyChangeSupport(this);

	}
	
	abstract public String getName();

	public PropertyChangeSupport getPCS() {
		return pcs;
	}

	/**
	 * announce the message, sleep a little
	 * @param message
	 */
	public void status(String message) {
		if (logging) {
			System.out.println(getName() + " fires PCS message: " + message);
		}
		//
		// The use of null below pushes out the message no matter what
		//   You could use the second and third parameters to
		//   cause a message to be pushed only if something has
		//   changed.
		//
		pcs.firePropertyChange(message, null, null);
		//
		// Take some time to do this thing
		//
		lab3a.Lab.sleep(delay);
	}

}