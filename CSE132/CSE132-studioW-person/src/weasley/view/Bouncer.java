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
	
	public Bouncer(Weasley weasley, Location location) {
		this.weasley = weasley;
		this.location = location;
		pcs = weasley.getPcs();
		pcs.addPropertyChangeListener(location.getName(), this);
	}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		System.out.println("Bouncer: someone moved from " + arg0.getPropertyName() );
	}
	
}