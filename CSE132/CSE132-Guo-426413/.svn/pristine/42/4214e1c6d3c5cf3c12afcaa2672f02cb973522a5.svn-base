package lab3a.view;

import javax.swing.*;
import java.awt.*;
import java.beans.*;
import java.util.HashMap;
import java.util.Map;

/** This class can draw a rectangle within a specified area of an outer {@link JPanel}.
 * The rectagle changes color based on the signals and colors provided. */
public class RectViz extends Viz {

	private Color color;
	private String id;
	/** Constructor with parameters as follows
	 * @param pcs This class must register as a {@link PropertyChangeListener} for each signal provided 
	 * @param dx fraction of outer.getWidth() for x coordinate
	 * @param dy fraction of outer.getHeight() for y coordinate
	 * @param dwidth fraction of outer.getWidth() for width
	 * @param dheight fraction of outer.getHeight() for height
	 * @param signals array of Strings, names of signals to be received
	 * @param colors array of Colors, one for each signal, the color to set the rectagle
	 * @param initColor the starting Color for the rectangle
	 */
	public RectViz(PropertyChangeSupport pcs, 
			double dx, double dy, 
			double dwidth, double dheight, 
			JPanel outer, 
			String[] signals, Color[] colors, 
			Color initColor) {
		this(pcs, dx, dy, dwidth, dheight, outer, genMap(signals,colors), initColor);
	}

	private static Map<String,Color> genMap(String[] signals, Color[] colors) {
		if (signals.length != colors.length) 
			throw new IllegalArgumentException("signals and colors must have same length");
		Map<String,Color> ans = new HashMap<String,Color>();
		for (int i=0; i < signals.length; ++i) {
			ans.put(signals[i], colors[i]);
		}
		return ans;
	}

	public RectViz(PropertyChangeSupport pcs, 
			double dx, double dy, 
			double dwidth, double dheight, 
			JPanel outer, 
			Map<String,Color> map, 
			Color initColor) {
		super(dx, dy, dwidth, dheight, outer);

		this.id = "";
		this.color = initColor;
		//
		// For each String (event name), register the color we should change
		//   when that event happens
		//
		for (String s : map.keySet()) {
			register(pcs, s, map.get(s));
		}
		repaint();
	}
	
	public Color getColor() { return color; }
	
	public String getID()   { return id; }

	/** A very useful helper method.  
	 * Using the pcs, add a ProeprtyChangeListener that 
	 * simply sets the color and text of the view.
	 */
	protected final void register(PropertyChangeSupport pcs, final String s, final Color c) {
		PropertyChangeListener react = new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent e) {
				color = c;
				id = ""+e.getSource();
				repaint();
			}
		};
		
		pcs.addPropertyChangeListener(s, react);
	}

}
