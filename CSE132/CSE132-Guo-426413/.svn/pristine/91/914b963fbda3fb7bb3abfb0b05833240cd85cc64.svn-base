/** 
 * This class represents a logger.
 * The Logger logs all changes to 
 * any person's location by printing 
 * a suitable message. It implements
 * a Property Change Listener interface
 * @author 			Bingkun Guo
 * Lab Section:		C
 * Email:			guobingkun@wustl.edu
 * Lab:				LabW: Weasley Clock 
 * File:			Logger.java
 */
package weasley.view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import weasley.model.Weasley;

public class Logger implements PropertyChangeListener {
	
	private Weasley weasley;
	private PropertyChangeSupport pcs;
	
	/**
	 * Constructor of Logger, it listens to the
	 * weasley pcs
	 * @param weasley
	 */
	public Logger(Weasley weasley) {
		this.weasley = weasley;
		pcs = weasley.getPcs();
		pcs.addPropertyChangeListener("logger", this);
		pcs.addPropertyChangeListener("loggerRemove", this);
	}

	/**
	 * Print out messages when some person moves or something
	 * is removed from the sets.
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals("logger"))
			System.out.println("logger: " + evt.getOldValue() + " moves to " + evt.getNewValue());
		else
			System.out.println("logger: " + evt.getOldValue() + " is removed!");
	}
	
	
}
