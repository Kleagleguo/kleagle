/** 
 * This class represents a bouncer, A Bouncer 
 * should be notified when somebody moves in 
 * a particular Weasley to a particular Location.
 * This class implements PropertyChangeListener
 * interface
 * @author 			Bingkun Guo
 * Lab Section:		C
 * Email:			guobingkun@wustl.edu
 * Lab:				LabW: Weasley Clock 
 * File:			Bouncer.java
 */
package weasley.view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import weasley.model.Location;
import weasley.model.Weasley;

public class Bouncer implements PropertyChangeListener {

	private Location location;
	private Weasley weasley;
	private PropertyChangeSupport pcs;
	
	/**
	 * Constructor of the Bouncer, since Bouncer
	 * stays at a specific place, it has a location 
	 * private member. It also listens to the pcs
	 * of weasley
	 * @param weasley
	 * @param location
	 */
	public Bouncer(Weasley weasley, Location location) {
		this.weasley = weasley;
		this.location = location;
		pcs = weasley.getPcs();
		pcs.addPropertyChangeListener("comes", this);
		pcs.addPropertyChangeListener("leaves", this);
	}

	/**
	 * Print out a message when someone moves from this place
	 */
	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
	if (arg0.getPropertyName().equals("comes") && ( (Location) arg0.getNewValue() ).getName().equals(location.getName()))  
		System.out.println("Bouncer: " + arg0.getOldValue() + " comes into " + arg0.getNewValue() );
	if (arg0.getPropertyName().equals("leaves") && ( (Location) arg0.getNewValue() ).getName().equals(location.getName()) ) 
		System.out.println("Bouncer: " + arg0.getOldValue() + " leaves " + arg0.getNewValue());
	
	}
	
}
