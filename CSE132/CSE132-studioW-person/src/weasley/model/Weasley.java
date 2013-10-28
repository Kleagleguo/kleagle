package weasley.model;

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
	
	public Weasley() {
		weasley = new HashMap<Person, Location>();
		people = new HashSet<Person>();
		places = new HashSet<Location>();
		pcs = new PropertyChangeSupport(this);
		rand = new Random();
	}
	
	public void addPerson(Person person) {
		people.add(person);
	}
	
	public void addLocation(Location location) {
		places.add(location);
	}
	
	public Set<Person> getPeople() {
		Set<Person> personCopy = new HashSet<Person>();
		for (Person p : people) {
			personCopy.add(p);
		}
		return personCopy;
	}
	
	public Set<Location> getLocations() {
		Set<Location> locationCopy = new HashSet<Location>();
		for (Location l : places) {
			locationCopy.add(l);
		}
		return locationCopy;
	}
	
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
	
	public void setLocation(Person person, Location location) {
		pcs.firePropertyChange(person.getName(), weasley.get(person), location);
		pcs.firePropertyChange(weasley.get(person).getName(), weasley.get(person), location );
		pcs.firePropertyChange("logger", person, location);
		weasley.put(person, location);
		if (places.contains(location) == false) {
			places.add(location);
		}
		
	}
	
	public void dropPerson(Person person) {
		people.remove(person);
	}
	
	public void dropLocation(Location location) {
		places.remove(location);
	}
	
	public PropertyChangeSupport getPcs() {
		return pcs;
	}
	
	
}
