package lab4.model;

/**
 *  Weasley
 *   Model of clock in Weasley Clock.
 *
 *  Author: Ron Cytron, Roger Chamberlain
 *  Course: CS132
 *  Lab:    Studio W
 *  
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Weasley {
	private Set<Person> people;  // set of people part of clock
	private Set<Location> locations;  // set of active locations
	private Map<Person,Location> located;  // which person is at which location
	
	/**
	 * Initializes empty set of people and set of locations with one entry, "limbo".
	 */
	public Weasley() {
		people = new HashSet<Person>();
		locations = new HashSet<Location>();
		located = new HashMap<Person,Location>();
		addLocation(new Location("limbo"));
	}
	
	/**
	 * Adds a person to the set of people being tracked.  The person's
	 * initial location is "limbo".
	 * @param p person to be added to set of people
	 */
	public synchronized void addPerson(Person p) {
		if (people.contains(p)) {
			return;
		}
		people.add(p);
		Location loc = new Location("limbo");
		setLocation(p,loc);
	}
	
	/**
	 * Adds a location to the set of locations that are active.
	 * @param loc location to be added to set of locations
	 */
	public synchronized void addLocation(Location loc) {
		locations.add(loc);
	}
	
	/**
	 * Accessor for set of people being tracked.
	 * @return set of people being tracked
	 */
	public synchronized Set<Person> getPeople() {
		return people;
	}
	
	/**
	 * Accessor for set of locations that are active.
	 * @return set of locations that are active
	 */
	public synchronized Set<Location> getLocations() {
		return locations;
	}
	
	/**
	 * Determine the location for a given person.
	 * @param p person for whom to query location
	 * @return location of person p
	 */
	public synchronized Location getLocation(Person p) {
		if (people.contains(p) == false) {
			addPerson(p);
		}
		return located.get(p);
	}
	
	/**
	 * Establish new location for a given person.
	 * @param p person for who we are to establish location
	 * @param loc new location for person p
	 */
	public synchronized void setLocation(Person p, Location loc) {
		if (loc.equals(getLocation(p))) {
			return;
		}
		if (locations.contains(loc) == false) {
			locations.add(loc);
		}
		located.put(p, loc);
		p.move(loc);
	}
	
	/**
	 * Remove a person from tracking.
	 * @param p person to be removed
	 */
	public synchronized void dropPerson(Person p) {
		located.remove(p);
		people.remove(p);
		return;
	}
	
	/**
	 * Remove an active location. All people at that location get moved to "limbo".
	 * @param loc location to be removed
	 */
	public synchronized void dropLocation(Location loc) {
		for (Person p: people) {
			if (loc.equals(getLocation(p))) {
				setLocation(p, new Location("limbo"));
			}
		}
		locations.remove(loc);
		return;
	}

}
