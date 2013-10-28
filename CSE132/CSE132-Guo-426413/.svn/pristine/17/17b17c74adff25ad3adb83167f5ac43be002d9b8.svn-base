/**
 * Represents a horizontal hallway.
 * This specializes a in two ways:
 * It knows to get the East Lock of Room1 and the West Lock of Room2
 * It knows that when its doors open, it connects the East of Room1
 * with Room2, and the West of Room2 with Room1.
 * @author 			Bingkun Guo
 * Lab Section:		C
 * Email:			guobingkun@wustl.edu
 * Lab:				Lab3B:Mazes 
 * File:			HorizHall.java
 */
package lab3a.model;
import java.awt.*;
import javax.swing.*;

import lab3a.Lab;
import lab3a.locking.SingleLockInvokable;

public class HorizHall extends Hall {

	/**
	 * Constructor of HorizHall
	 * @param r1
	 * @param r2
	 */
	public HorizHall(Room r1, Room r2) { 
		super(r1, r2); 

	}

	/** Return Room1's East Lock */
	public SingleLockInvokable getLock1() { return getRoom1().getEastLock(); }
	/** Return Room2's West Lock */
	public SingleLockInvokable getLock2() { return getRoom2().getWestLock(); }
	/** Connect the East of Room1 with Room2 and the West of Room2 with Room1 */
	public void openDoors() {
		Lab.sleep();
		getRoom1().connectEast(getRoom2());
		getRoom2().connectWest(getRoom1());
		this.openHall();
	}
}

