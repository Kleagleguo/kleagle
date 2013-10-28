package weasley.view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import weasley.model.Weasley;

public class Logger implements PropertyChangeListener {
	
	private Weasley weasley;
	private PropertyChangeSupport pcs;
	
	public Logger(Weasley weasley) {
		this.weasley = weasley;
		pcs = weasley.getPcs();
		pcs.addPropertyChangeListener("logger", this);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		System.out.println("logger: " + evt.getOldValue() + " moves to " + evt.getNewValue());
	}
	
	
}
