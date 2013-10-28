package weasley;

import java.util.Random;
import java.util.Set;

import weasley.model.Location;
import weasley.model.Person;
import weasley.model.Weasley;
import weasley.view.Bouncer;
import weasley.view.Logger;
import weasley.view.Stalker;

public class Main {

	final private Weasley    model;
	final private Random     rand;

	public Main() {
		String[]
		       places = { "library", "gym", "dorm", "home", "blueberry hill" },
		       names  = { "studiousmaximus", "alice", "bob", "charlie", "diane" };

		rand = new Random();
		
		model = new Weasley();

		for (String s : names) {
			model.addPerson(new Person(s));
		}
		
		for (String s : places) {
			model.addLocation(new Location(s));
		}

		//
		// logger will produce a printout of all things that happen
		//   to the model.
		//
		Logger logger = new Logger(model);
		//
		// stalker will report any change in the location of the 0th person
		//
		Stalker stalker = new Stalker(model, model.getPeople().iterator().next());
		//
		// bouncer will report anybody trying to get into the 0th place
		//
		Bouncer bouncer = new Bouncer(model, model.getLocations().iterator().next());
	}

	public void run() {
		
		Set<Person>   people    = model.getPeople();
		Set<Location> locations = model.getLocations();
		
		for (Person p : people) {
			Location at = model.getLocation(p);
			System.out.println("Person " + p + " is initially at " + at);
		}
		
		//
		// simulate the people moving around
		//

		for (int i=0; i < 1000; ++i) {
			//
			// Pick a random person and place
			//
			int person = rand.nextInt(people.size());
			int place  = rand.nextInt(locations.size());

			//
			// and tell the model the person's new location (may be the same as its old location)
			//
			model.setLocation(people.toArray(new Person[0])[person], locations.toArray(new Location[0])[place]);
		}
		
		
		//
		// Get rid of the people and locations
		//
		
		for (Person p : people) {
			model.dropPerson(p);
		}
		
		for (Location l : locations) {
			model.dropLocation(l);
		}
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Main m = new Main();
		m.run();

	}

}
