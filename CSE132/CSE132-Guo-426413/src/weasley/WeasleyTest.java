package weasley;

import static org.junit.Assert.*;

import org.junit.Test;

import weasley.model.Location;
import weasley.model.Person;
import weasley.model.Weasley;


public class WeasleyTest {
	
	@Test
	public void testEqualsAndHashCode() {
		Person p1 = new Person("Alouicious");
		Person p2 = new Person("Alouicious");
		assertEquals("See note H", p1.hashCode(), p2.hashCode());
		assertEquals("See note E", p1, p2);
		Location l1 = new Location("DUC");
		Location l2 = new Location("DUC");
		assertEquals("See note H", l1.hashCode(), l2.hashCode());
		assertEquals("See note E", l1, l2);
	}
	
	@Test
	public void testDiffObjects() {
		Person p1 = new Person("X");
		Location l1 = new Location("X");
		assertFalse("See note X", p1.equals(l1));
	}
	
	Person alice = new Person("alice");
	Person bob   = new Person("bob");
	Location gym = new Location("gym");
	Location duc = new Location("DUC");
	
	@Test
	public void testAdds() {
		Weasley model = new Weasley();
		
		model.addPerson(alice);
		assertTrue("Missing person?", model.getPeople().contains(alice));
		
		model.addLocation(gym);
		assertTrue("Missing location?", model.getLocations().contains(gym));
		
	}
	
	@Test
	public void testUnMapped() {
		
		Weasley model = new Weasley();
		
		model.addPerson(alice);
		assertNotNull("U", model.getLocation(alice));
		assertNotNull("V", model.getLocation(bob));
	}
	
	@Test
	public void testLookup() {
		Weasley model = new Weasley();
		model.addPerson(alice);
		model.addLocation(gym);
		model.setLocation(alice, gym);
		
		assertEquals("Lookup failed?", gym, model.getLocation(alice));
	}
	
	@Test
	public void testLocationAddedSilently() {
		Weasley model = new Weasley();
		
		model.addPerson(alice);
		model.setLocation(alice, gym);
		assertTrue("See note L", model.getLocations().contains(gym));
	}

}
