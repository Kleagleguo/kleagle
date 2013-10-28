/** 
 * Visualization of Hall
 * @author 			Bingkun Guo
 * Lab Section:		C
 * Email:			guobingkun@wustl.edu
 * Lab:				Lab3B:Mazes 
 * File:			HallViz.java
 */
package lab3a.view;

import java.awt.Color;
import java.beans.PropertyChangeSupport;
import java.util.Map;

import javax.swing.JPanel;

import lab3a.locking.LockPub;
import lab3a.model.Room;

public class HallViz extends RectViz{

	/**
	 * Constructor of HallViz class
	 * @param room 
	 * @param dx fraction of outer.getWidth() for x coordinate
	 * @param dy fraction of outer.getHeight() for y coordinate
	 * @param dwidth fraction of outer.getWidth() for width
	 * @param dheight fraction of outer.getHeight() for height
	 * @param outer The outer panel so we can cause it to repaint itself
	 * @param signals Will cause color changes when signal is received
	 */
	public HallViz(Room room, 
			double dx, double dy,
			double dwidth, double dheight, 
			JPanel outer, String signals) {
		super(room.getPCS(), dx, dy, dwidth, dheight, outer, 
				new String[] {signals},
				new Color[] {Color.red},
				Color.DARK_GRAY);
		setShowText(true);
	}
}
