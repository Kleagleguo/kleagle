/** 
 * Set Visualization
 * @author 			Bingkun Guo
 * Lab Section:		C
 * Email:			guobingkun@wustl.edu
 * Lab:				Lab3B:Mazes 
 * File:			SetViz.java
 */
package lab3a.view;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/** This class manages the color associated with a Room's set by overriding
 * {@link #getColor()}.
 */
public class SetViz extends Viz {

	private RoomViz r;
	private Color color;
	/** Visualize the set associated with a [@link Room}
	 * @param r The {@link RoomViz} 
	 */
	public SetViz(final RoomViz r, double dx, double dy, double dwidth, double dheight) {
		super(dx, dy, dwidth, dheight, r);
		this.r = r;
		this.color = computeColor();
		//
		// React whenever the set associated with a room changes
		//
		r.getRoom().getPCS().addPropertyChangeListener("set",
				new PropertyChangeListener() {

			public void propertyChange(PropertyChangeEvent evt) {
				color = computeColor();
				repaint();

			}

		}
				);
		setShowText(true);
		repaint();
	}

	protected int getYTextDisplacement() {
		return -10;
	}

	/** Compute a Color based on the Room's current
	 * set.  Hint: use the set's {@link System#identityHashCode(Object)}
	 * to make 3 integers representing the red, green, and blue content
	 * of the color.  For the red and green, don't let it get all the way
	 * red or green, because we use those colors for other things. 
	 */
	private Color computeColor() {
		int hashCode = System.identityHashCode(r.getRoom().getSet());
		Color newColor = new Color(hashCode%220, hashCode%240, hashCode%255);
		return newColor;
	}

	public Color getColor() { 
		return color;
	}

	public String getID(){
		return ""+r.getRoom().getSet();
	}
}
