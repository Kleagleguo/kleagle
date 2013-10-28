/**
 * Represents a vertical hallway. 
 * This specializes a in two ways:
 * It knows to get the South Lock of Room1 and the North Lock of Room2
 * It knows that when its doors open, it connects the South of Room1
 * with Room2, and the North of Room2 with Room1.
 * @author 			Bingkun Guo
 * Lab Section:		C
 * Email:			guobingkun@wustl.edu
 * Lab:				Lab3B:Mazes 
 * File:			VertHall.java
 */
package lab3a.model;
import java.awt.*;
import javax.swing.*;

import lab3a.Lab;
import lab3a.locking.SingleLockInvokable;

public class VertHall extends Hall {
	/**
	 * Constructor of VertHall
	 * @param r1
	 * @param r2
	 */
	public VertHall(Room r1, Room r2) { 
		super(r1, r2);
	}

	/** Get Room1's South Lock */
	public SingleLockInvokable getLock1() { return getRoom1().getSouthLock(); }
	/** Get Room2's North Lock */
	public SingleLockInvokable getLock2() { return getRoom2().getNorthLock(); }

	/** Connect the South of Room1 with Room2, and the North of Room2 with Room1 */
	public void openDoors() {
		Lab.sleep();
		getRoom1().connectSouth(getRoom2());
		getRoom2().connectNorth(getRoom1());
		this.openHall();
	}

}
