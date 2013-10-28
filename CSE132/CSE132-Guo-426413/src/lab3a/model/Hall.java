/** 
 * Hall will be used to connect two rooms 
 * @author 			Bingkun Guo
 * Lab Section:		C
 * Email:			guobingkun@wustl.edu
 * Lab:				Lab3B:Mazes 
 * File:			Hall.java
 */
package lab3a.model;
import java.beans.PropertyChangeSupport;
import java.util.HashSet;
import java.util.Set;

import lab3a.Lab;
import lab3a.locking.DoubleLock;
import lab3a.locking.LockPub;
import lab3a.locking.SingleLockInvokable;

/** 
 * This is an abstract class, which means you cannot instantiate it. 
 * But it has two useful subclasses. Even though this class cannot be 
 * instantiated, you can still call methods on any Hall, as detailed below. 
 **/
abstract public class Hall {

	private PropertyChangeSupport pcs;
	private Room room1, room2;
	private boolean open;
	private Thread thread;
	private boolean locked = false;

	/**
	 * Constructor of Hall Class
	 * @param r1
	 * @param r2
	 */
	public Hall(Room r1, Room r2) {
		pcs = new PropertyChangeSupport(this);
		this.room1 = r1;
		this.room2 = r2;
	}

	/**
	 * create a hall between two rooms
	 */
	abstract public void openDoors();

	/**
	 *  return the Property Change Support
	 */
	public PropertyChangeSupport getPCS() { return pcs; }

	/** Depends on whether we are horiz or vertical hall */
	abstract public SingleLockInvokable getLock1();
	/** Depends on whether we are horiz or vertical hall */
	abstract public SingleLockInvokable getLock2();

	/** Using an anonymous, inner class, spawn a new Thread that
	 * gets a DoubleLock on this hall's two rooms, and then
	 * calls openDoors (overridden in subclasses)
	 */
	public void attemptToConnectRooms() {
		thread = new Thread() {	
			boolean loopAgain = true;   // if the set changes, we have to release lock and loop again 
			public void run() {
				while (loopAgain) {
					DoubleLock dl = new DoubleLock(getLock1(), getLock2());
					dl.lockInvoke(
							new Runnable() {
								public void run() {
									final Set<Room> s1 = getRoom1().getSet();
									final Set<Room> s2 = getRoom2().getSet(); 
									LockPub set1 = new LockPub(getRoom1().getSet(), "");
									LockPub set2 = new LockPub(getRoom2().getSet(), "");
									DoubleLock d2 = new DoubleLock(set1, set2);
									d2.lockInvoke(new Runnable() {
										public void run() {
											if (s1 != getRoom1().getSet() || s2 != getRoom2().getSet()) { 
												System.out.println("The set has changed! Release lock and loop again");
											}
											else {
												loopAgain = false;
												if (getRoom1().getSet() != getRoom2().getSet()) {
													Set<Room> union = new HashSet<Room>();
													synchronized (union) {
														for (Room r : getRoom1().getSet()) {
															union.add(r);
														}
														for (Room r : getRoom2().getSet()) {
															union.add(r);
														}
														for (Room r : union) {
															r.setSet(union);
														}
														openDoors();
														Lab.sleep();
													}
												}
											}
										}
									});				
								}
							});
				}
			}

		};
		thread.start();
	}

	/**
	 * create a new thread
	 */
	public void newThread() {
		thread = new Thread();
	}

	/**
	 * get the thread member
	 */
	public Thread getThread() { return thread; }

	/**
	 * return toString for this object
	 */
	public String toString() {
		return("Hall between " + getRoom1() + " and " + getRoom2());
	}

	/** Returns the first room specified in the constructor call. **/
	public Room getRoom1() {
		return room1;
	}
	/** Returns the second room specified in the constructor call. **/
	public Room getRoom2() {
		return room2;
	}

	/**
	 * if this hall is open, then return true
	 * open is the flag which indicates whether 
	 * the hall is open or not
	 */
	public void openHall() {
		open = true;
	}
	/**
	 * Check if this hall is open or not by returning open
	 */
	public boolean isOpen() {
		return open;
	}

}
