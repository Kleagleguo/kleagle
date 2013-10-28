/** 
 * This class represents a room
 * @author 			Bingkun Guo
 * Lab Section:		C
 * Email:			guobingkun@wustl.edu
 * Lab:				Lab3B:Mazes 
 * File:			Room.java
 */
package lab3a.model;

import java.awt.Color;
import javax.swing.*;


import java.awt.*;
import java.util.*;
import java.beans.*;

import lab3a.locking.LockPub;

/** {@link PropertyChangeSupport} messages:
    <DL>
    <DT> set
    <DD> fired whenever the set this room belongs to changes.  The old and new objects
         are the old and new sets, respectively.
    <DT> north, south, east, west
    <DD> Boolean-values PCS messages that indicate the north, south, east, or west
         door has been opened, respectively.
    </DL>
 */
public class Room extends StatusChanger {

	private static int nextID = 0;
	private int id;
	private Set<Room> myset;
	private Room north, east, south, west;
	private LockPub northLock, southLock, westLock, eastLock;

	/** Set this {@link Room}'s {@link Set} to a new {@link HashSet};  initialize PCS and the four locks ({@link LockPub}) */
	public Room() {
		super(1);
		this.id = nextID++;
		Set<Room> s = new HashSet<Room>();
		s.add(this);
		setSet(s);
		northLock = new LockPub(this, "north");
		southLock = new LockPub(this, "south");
		westLock  = new LockPub(this, "west");
		eastLock  = new LockPub(this, "east");
	}

	/**
	 * return the north room
	 */
	public Room getNorth() {
		return north;
	}

	/**
	 * return the east room
	 */
	public Room getEast() {
		return east;
	}

	/**
	 * return the south room
	 */
	public Room getSouth() {
		return south;
	}

	/**
	 * return the west room
	 */
	public Room getWest() {
		return west;
	}

	/** Accessor for the id */
	public int getID() { 
		return id; 
	}

	/** Accessor for this {@link Room}'s PCS */
	public PropertyChangeSupport getPCS() { 
		return pcs; 
	}

	/** Accessor for this Room's west {@link LockInvokable} */
	public LockPub getWestLock() { return westLock; }
	/** Accessor for this Room's east {@link LockInvokable} */
	public LockPub getEastLock() { return eastLock; }
	/** Accessor for this Room's north {@link LockInvokable} */
	public LockPub getNorthLock() { return northLock; }
	/** Accessor for this Room's south {@link LockInvokable} */
	public LockPub getSouthLock() { return southLock; }

	/** Connect now to the supplied {@link Room};  fire the PCS north signal */
	public void connectNorth(Room r)  {
		north = r; open("north");
	}
	/** Connect now to the supplied {@link Room};  fire the PCS east  signal */
	public void connectEast(Room r)   {
		east = r;  open("east");
	}
	/** Connect now to the supplied {@link Room};  fire the PCS south signal */
	public void connectSouth(Room r)  {
		south = r;  open("south");
	}
	/** Connect now to the supplied {@link Room};  fire the PCS west  signal */
	public void connectWest(Room r)   {
		west = r;  open("west");
	}

	/** Mutator for this {@link Room}'s {@link Set} ;  notify via PCS, sending the old and new Set */
	public void setSet(Set<Room> newSet) {
		Set<Room> oldSet = this.myset;
		this.myset = newSet;
		status("set");
	}

	/**
	 * send open signal to pcs
	 */
	private void open(String s) {
		status(s);
	}

	/** Accessor for this {@link Room}'s {@link Set} */
	public Set<Room> getSet() { return myset; }

	/**
	 * return the toString for this Room object
	 */
	public String toString() { return "Room " + getID(); }

	/**
	 * return the name of this room
	 */
	@Override
	public String getName() {
		return "Room " + getID();
	}

}