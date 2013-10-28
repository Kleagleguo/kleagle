package lab3a.view;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;


/**
 * A JPanel that hosts a list of {@link Scribbler}s.
 * Each time the panel repaints itself, the scribblers are called
 * to draw on the panel.
 * @author cytron
 *
 */
public class ScribblePanel extends JPanel {
	
	private List<Scribbler> scribblers;
	
	/**
	 * Start out with an empty list of {@link Scribbler}s.
	 */
	public ScribblePanel() {
		scribblers = new ArrayList<Scribbler>();
	}
	
	/**
	 * Add the specified {@link Scribbler} to the list.
	 * @param s A {@link Scribbler} to add to the list
	 * @return "this" for convenience.
	 */
	public ScribblePanel addScribbler(Scribbler s) {
		scribblers.add(s);
		return this;
	}
	
	/**
	 * Override this to draw on the panel before the scribblers.
	 * @param g
	 */
	protected void beforeScribbling(Graphics g) {
		
	}
	
	/**
	 * Override this to draw on the panel after the scribblers.
	 * @param g
	 */
	protected void afterScribbling(Graphics g) {
		
	}
	
	/**
	 * This is called whenever we resize the panel, or ask it
	 * to repaint itself, or hide or uncover part of the panel
	 * with another window, etc.
	 */
	public void paintComponent(Graphics g) {
		//
		// Clear this component first by having the parent panel paint itself.
		//   NB This was what was missing from Studio 4 that
		//       was causing the strange visual behavior on the CEC PCs
		//
		super.paintComponent(g);
		//
		// Give each scribbler a chance to draw on
		//   this panel
		//
		beforeScribbling(g);
		for (Scribbler s : scribblers) {
			s.draw(g);
		}
		afterScribbling(g);
	}

}
