/** 
 * This class represents Weasley Clock
 * @author 			Bingkun Guo
 * Lab Section:		C
 * Email:			guobingkun@wustl.edu
 * Lab:				LabW: Weasley Clock 
 * File:			Weasley.java
 */
package weasley.model;

/*
 * LAB W
 * TA:		Matt B
 * GRADE:	100/100
 * 
 * Cover Page:			10/10
 * Separate Model/View	5/5
 * 
 * JUnit Tests  		60/60
 *
 * Printouts			30/30
 *	Logger		10/10
 *	Bouncer		10/10
 *	Stalker		10/10
 */

import java.beans.PropertyChangeSupport;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class Weasley {
	
	private PropertyChangeSupport pcs;
	private Map<Person, Location> weasley;
	private Set<Person> people;
	private Set<Location> places;
	final private Random     rand;
	
	/**
	 * Constructor of class Weasley
	 * In the constructor, we initialize the weasley,
	 * people, places, pcs and rand
	 */
	public Weasley() {
		weasley = new HashMap<Person, Location>();
		people = new HashSet<Person>();
		places = new HashSet<Location>();
		pcs = new PropertyChangeSupport(this);
		rand = new Random();
	}
	
	/**
	 * Add a new person into the People set
	 * @param person
	 */
	public void addPerson(Person person) {
		people.add(person);
	}

	/**
	 * Add a new location into the places set
	 * @param location
	 */
	public void addLocation(Location location) {
		places.add(location);
	}
	
	/**
	 * Get people in the set, this will return a copy
	 * of the people set in this class, since it will 
	 * be used to remove person later
	 * @return
	 */
	public Set<Person> getPeople() {
		Set<Person> personCopy = new HashSet<Person>();
		for (Person p : people) {
			personCopy.add(p);
		}
		return personCopy;
	}
	
	/**
	 * Get the locations in the set, this will return a 
	 * copy of the places set, since it will be used 
	 * to remove location later
	 * @return
	 */
	public Set<Location> getLocations() {
		Set<Location> locationCopy = new HashSet<Location>();
		for (Location l : places) {
			locationCopy.add(l);
		}
		return locationCopy;
	}
	
	/**
	 * Get a person's location, if the person does
	 * not exist in the set, then we add it. 
	 * If this person does not have a location, then
	 * we create an unknown location for it
	 * @param person
	 * @return person's location
	 */
	public Location getLocation(Person person) {
		if (people.contains(person) == false) {
			people.add(person);
		}
		if (weasley.get(person) == null) {
			Location unknown = new Location("unknown");
			places.add(unknown);
			weasley.put(person, unknown);
		}
		return weasley.get(person);
	}
	
	/**
	 * Set the location for the person, then fire property change 
	 * to tell the bouncer, stalker and logger.
	 * If the new location is not contained in the places set, 
	 * then we add it
	 * @param person
	 * @param location
	 */
	public void setLocation(Person person, Location location) {
		pcs.firePropertyChange(person.getName(), weasley.get(person), location);
		pcs.firePropertyChange("leaves", person, getLocation(person) );
		pcs.firePropertyChange("comes", person, location);
		pcs.firePropertyChange("logger", person, location);
		weasley.put(person, location);
		if (places.contains(location) == false) {
			places.add(location);
		}
		
	}
	
	/**
	 * Remove a person from the set and fire a property change
	 * to the logger
	 * @param person
	 */
	public void dropPerson(Person person) {
		people.remove(person);
		pcs.firePropertyChange("loggerRemove", person, null);
	}
	
	/**
	 * Remove a location from the set and fire a property change
	 * to the logger
	 * @param location
	 */
	public void dropLocation(Location location) {
		places.remove(location);
		pcs.firePropertyChange("loggerRemove", location, null);
	}
	
	/**
	 * Get the property change support
	 * @return pcs
	 */
	public PropertyChangeSupport getPcs() {
		return pcs;
	}
	
	
}