package lab1;
import nip.*;
import percent.Controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
/*TA
 * 110/100
 * Make sure to comment next time or you will lose points
 * JG
 */
public class RGB extends JPanel implements ActionListener{

	final private Controller red, blue, green;
	private int identifier;
	//private JButton lock;
	/**
	 * I'll talk about "final" below in lecture next week -- RKC
	 * 
	 * @param one An image from which pixels will be copied
	 * @param two An image, same size as one, to which pixels will be copied
	 */
	public RGB(final Image one, final Image two) {
		this.identifier = 0;
		add(red = new Controller("red"));
		//
		//  Add a green and blue Controller to this JPanel, too
		//  Until you do that, you'll get null pointer exceptions if you hit "go"
		//
		add(blue = new Controller("blue"));
		add(green = new Controller("green"));
		red.getModel().addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				if (identifier == 1) {
					blue.getModel().setValue(red.getModel().getValue());
					green.getModel().setValue(red.getModel().getValue());
				}
				copyImage(one, two);
			}
		}
		);
		blue.getModel().addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				if (identifier == 1) {
					red.getModel().setValue(blue.getModel().getValue());
					green.getModel().setValue(blue.getModel().getValue());
				}
				copyImage(one, two);
			}
		}
		);
		green.getModel().addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				if (identifier == 1) {
					blue.getModel().setValue(green.getModel().getValue());
					red.getModel().setValue(green.getModel().getValue());
				}
				copyImage(one, two);
			}
		}
		);
		JButton go = new JButton("go");
		this.add(go);
		
		JButton lock = new JButton("lock");
		lock.addActionListener(this);
		this.add(lock);
		
		//
		// When "go" button is pressed, call copyImage() to do the work
		//  This will fail until you initialize red, green, and blue
		//  The code below uses an anonymous inner class, which I will
		//  explain in upcoming lectures.
		//

		go.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.out.println("go is clicked");
						copyImage(one, two);
					}
				}
		);
				
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// FIXME Auto-generated method stub
		if (arg0.getActionCommand().equals("lock")) {
			System.out.println("lock");
			this.identifier = 1;
			((JButton) arg0.getSource()).setText("off");
		}
		if (arg0.getActionCommand().equals("off")) {
			System.out.println("unlock");
			this.identifier = 0;
			((JButton) arg0.getSource()).setText("lock");
		}
	}
	
	/**
	 * Copy the in image to the out image.
	 * Each pixel is broken into its red, green, blue components.
	 * The appropriate percentage is taken of each color and combined into
	 *    a new color, written to the out image.
	 */
	public void copyImage(Image in, Image out) {
		for (int i=0; i < in.getWidth(); ++i) {
			for (int j=0; j < in.getHeight(); ++j) {

				Color c = in.getPixelColor(i, j);

				int r = red.getModel().computePercentOf(c.getRed());
				int g = green.getModel().computePercentOf(c.getGreen());
				int b = blue.getModel().computePercentOf(c.getBlue());

				out.setPixel(i, j, new Color(r, g, b));
			}
		}
		out.repaint();  // force the changes to get shown

	}

	public static void main(String[] args) {
		/*
		 * picture by Melanie Cytron http://macytron.deviantart.com/
		 */
		NIP nip = new NIP(600, 400, 2, "Summit_Lake_II_by_macytron.jpg");
		RGB panel = new RGB(
				nip.getGraphicsPanel(0).getMainImage(),
				nip.getGraphicsPanel(1).getMainImage()
		);

		//
		// What you see below is what NIP did for you
		//
		JFrame frame = new JFrame();
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
