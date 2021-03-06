package percent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
// import javax.swing.event.ChangeEvent;
// import javax.swing.event.ChangeListener;

import lecture.OpinionModel;
import lecture.ViewAsSlider;
import lecture.ViewAsText;
public class Controller extends JPanel implements ActionListener {
	private PercentModel model;
	private ViewAsSlider view1;
	private ViewAsSlider view4;
	private ViewAsText view3;
	private String label;
	private JButton reset;
	private JButton sixtysix;
	public Controller(String label) {
		this.add(new JLabel(label));
		model = new PercentModel();
		view1 = new ViewAsSlider(model, true);
		this.add(view1);
		this.add(new JLabel("Controller"));
		view3 = new ViewAsText(model);
		this.add(view3);
		this.add(new JLabel("Second Controller") );
		view4 = new ViewAsSlider(model, true);
		this.add(view4);
		this.add(new JLabel("Controller"));
		JButton reset = new JButton("reset");
		// ...
		this.add(reset);
		// ...
		reset.addActionListener(this);
		
		//JButton reset = new JButton("reset!");
		//reset.addActionListener(this);
		add(reset);
		JButton sixtysix=new JButton("sixtysix");
		this.add(sixtysix);
		add(sixtysix);
		sixtysix.addActionListener(this);
		// add other things you want to see here:
	}
	private void setValue(int val) {
		model.setValue(val);
		System.out.println("value now " + model.getValue());
	}
	public void actionPerformed(ActionEvent arg0) {
//		System.out.println("something happened: " 
//				+ arg0.getActionCommand());
		
		if (arg0.getActionCommand().equals("reset"))
			setValue(50);
		if (arg0.getActionCommand().equals("sixtysix"))
			setValue(66);
	}

	
	public static void main(String[] args) {
		Controller panel = new Controller("Parameter");
		//
		// What you see below is what YOPS did for you
		//
		JFrame frame = new JFrame();
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

