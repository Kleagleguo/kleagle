package lab3a.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * Captures the idea of drawing within a portion of a panel.
 * The panel is viewed as a unit rectangle (not necessarily a square),
 *    with coordinates ranging from (0,0) to (1,1).
 * Subclasses provide the color and text we should show.
 * @author cytron
 *
 */
abstract public class Viz implements Scribbler {

	private final JPanel outer;
	private final double dx;
	private final double dy;
	private final double dwidth;
	private final double dheight;
	private boolean showText;

	/** Constructor with parameters as follows
	 * @param dx fraction of outer.getWidth() for x coordinate
	 * @param dy fraction of outer.getHeight() for y coordinate
	 * @param dwidth fraction of outer.getWidth() for width
	 * @param dheight fraction of outer.getHeight() for height
	 * @param outer The outer panel so we can cause it to repaint itself
	 */

	public Viz(
			double dx, double dy, 
			double dwidth, double dheight, 
			JPanel outer) {
		this.dx = dx;
		this.dy = dy;
		this.dwidth = dwidth;
		this.dheight = dheight;
		this.showText = false;
		this.outer = outer;
	}
	
	/**
	 * Can be overridden to specify a Y displacement for
	 *  the text associated with this Viz.  This can be used
	 *  to move it up so it doesn't run into other things.
	 * @return Y displacement, in graphics units
	 */
	protected int getYTextDisplacement() {
		return 0;
	}

	public abstract Color getColor();
	public abstract String getID();

	public void repaint() {
		outer.repaint();
	}

	/**
	 * Set whether the text of the viz is drawn too
	 * @param show Should the text be shown?
	 */
	public Viz setShowText(boolean show) {
		this.showText = show;
		return this;
	}

	/**
	 * Handy method to avoid int casts all over the place
	 * @param frac
	 * @param n
	 * @return product of frac and n, int-style
	 */
	private int mpy(double frac, double n) {
		return (int)(frac * n);
	}

	/**
	 * We end up here by 
	 * outer.repaint() is called for resize window or when we need to refresh
	 *    --> outer.paintComponent(Graphics) is called to repaint the outer JPanel
	 *    --> draw(Graphics)  is called because we override paintComponent in {@link ScribblePanel}
	 *  
	 * Given the supplied {@link Graphics} object
	 * <UL>
	 * <LI> Issue <code>g</code> a setColor command for the appropriate color,
	 * <LI> Issue a fillRect command to fill a rectangle of the appropriate size
	 * </UL>
	 * Do not issue a repaint on the outer panel!  That would cause this
	 * code to run indefinitely.
	 **/
	public void draw(Graphics g) {
		int x = mpy(dx, outer.getWidth());
		int y = mpy(dy, outer.getHeight());
		int width = mpy(dwidth, outer.getWidth());
		int height = mpy(dheight, outer.getHeight());
		//
		// our current color
		//
		g.setColor(getColor());
		g.fillRect(x, y, width, height);
		//
		// and our message if we want to see it
		//
		if (showText) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("Sans Serif", Font.BOLD, 10));
			g.drawString(getID(), x, y+getYTextDisplacement());
		}
	}

}