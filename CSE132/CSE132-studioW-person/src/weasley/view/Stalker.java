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
	
	public Stalker(Weasley weasley, Person person) {
		this.weasley = weasley;
		this.stalkPerson = person;
		pcs = weasley.getPcs();
		pcs.addPropertyChangeListener(stalkPerson.getName(), this);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		System.out.println("Stalker: " + evt.getPropertyName() + " moved from " + evt.getOldValue() + " to " + evt.getNewValue() );
	}
	
}
