package lab3a.view;
import javax.swing.*;


import java.awt.*;

import lab3a.locking.LockPub;

/** Provided for you, this class responds to avail, request, and grant signals by
 * turning gray, red, green, respectively */

public class LockViz extends RectViz {

	public LockViz(LockPub lock,
			double dx, double dy, 
			double dwidth, double dheight, 
			JPanel outer) {
		super(lock.getPCS(), dx, dy, dwidth, dheight, outer,
				new String[] { "avail", "request", "granted" },
				new Color[] { Color.gray, Color.red, Color.green },
				Color.gray);
		setShowText(true);
	}


}

