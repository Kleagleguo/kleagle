/** 
 * A Stalker should react when a particular
 * Person changes location in a particular Weasley.
 * It implements Property Change Listener to listen
 * the pcs of weasley
 * @author 			Bingkun Guo
 * Lab Section:		C
 * Email:			guobingkun@wustl.edu
 * Lab:				LabW: Weasley Clock 
 * File:			Stalker.java
 */
package weasley.view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import weasley.model.Person;
import weasley.model.Weasley;

public class Stalker implements PropertyChangeListener {
	
	private Weasley weasley;
	private Person stalkPerson;
	private PropertyChangeSupport pcs;
	
	/**
	 * Constructor of Stalker, it stalks the person 
	 * which is passed in and listens to the pcs
	 * of weasley
	 * @param weasley
	 * @param person
	 */
	public Stalker(Weasley weasley, Person person) {
		this.weasley = weasley;
		this.stalkPerson = person;
		pcs = weasley.getPcs();
		pcs.addPropertyChangeListener(stalkPerson.getName(), this);
	}

	/**
	 * Print out a message when the person this class stalks moves
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		System.out.println("Stalker: " + evt.getPropertyName() + " moved from " + evt.getOldValue() + " to " + evt.getNewValue() );
	}
	
}
