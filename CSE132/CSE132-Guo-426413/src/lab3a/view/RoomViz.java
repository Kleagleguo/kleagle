/** 
 * Visualization of Room
 * @author 			Bingkun Guo
 * Lab Section:		C
 * Email:			guobingkun@wustl.edu
 * Lab:				Lab3B:Mazes 
 * File:			RoomViz.java
 */
package lab3a.view;
import java.awt.Color;
import java.awt.Graphics;

import lab3a.model.Room;

/** Visualization of a {@link Room} for this lab */
public class RoomViz extends ScribblePanel {

	// Useful constants
	private static final double
	h = 0.5000,		// half
	q = h/2,		// quarter
	e = q/2,		// eighth
	s = e/2,		// sixteenth
	t = s/2;		// thirty-second

	private Room room;

	/** This class extends a Panel that has {@link Scribbler}s.
	 *  In the constructor, add any {@link Scribbler}s you wish
	 *  by calling {@link ScribblePanel.addScribbler(Scribbler)}.  
	 */

	/**
	 * Constructor of RoomViz
	 * add Visualizations of Lock, Hall into Scribbler
	 * @param r The room object we want to visualize
	 */
	public RoomViz(Room r) {
		this.room = r;
		//
		// The central part of the viz is the set associated with the room
		// This will show up as the same color everywhere until you fix {@link SetViz}
		//
		addScribbler(new SetViz(this, q, q, h, h));
		//
		// A lock for each hall that might try to get this Room
		//
		addScribbler(new LockViz(r.getNorthLock(), h-s, q, e, q-s, this));
		addScribbler(new LockViz(r.getEastLock(), h+s, h-s, q-s, e, this));
		addScribbler(new LockViz(r.getSouthLock(), h-s, h+s, e, q-s, this));
		addScribbler(new LockViz(r.getWestLock(), q, h-s, q-s, e, this));

		addScribbler(new HallViz(r, h-s, 0, e, q, this, "north"));
		addScribbler(new HallViz(r, h+q, h-s, q, e, this, "east"));
		addScribbler(new HallViz(r, h-s, h+q, e, q, this, "south"));
		addScribbler(new HallViz(r, 0, h-s, q, e, this, "west"));
	}
	/**
	 * Show the outline of the room.
	 * You can get rid of this once things are working
	 */
	public void beforeScribbling(Graphics g) {
		g.setColor(Color.darkGray);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.WHITE);
		g.drawRect(0, 0, this.getWidth(), this.getHeight());
	}

	/**
	 * Label the room.  You can remove this once things are working.
	 */
	public void afterScribbling(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawString(room.toString(), 5, 15);
	}

	/** Accessor for the {@link Room} associated with this RoomViz */
	public Room getRoom() { return room; }

}

