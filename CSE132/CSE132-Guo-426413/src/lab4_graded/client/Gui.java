/** 
 * This class represents graphical user interface
 * for the client side of Weasley Clock
 * @author 			Bingkun Guo
 * Lab Section:		C
 * Email:			guobingkun@wustl.edu
 * Lab:				Lab4a: Remote Weasley Display 
 * File:			Gui.java
 */
package lab4_graded.client;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import javax.swing.JList;
import java.awt.Font;
import javax.swing.JLabel;

public class Gui extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextPane personLoc;
	private JScrollPane personLocScrollPane;
	private JList people;
	private JList locations;
	private Client client;
	private JButton btnStart;
	private boolean started;
	private final int contentPaneBoarder = 5;
	private final int guiX = 100;
	private final int guiY = 100;
	private final int guiWidth = 716;
	private final int guiHeight = 346;
	private final int personLocScrollPaneX = 401;
	private final int personLocScrollPaneY = 39;
	private final int personLocScrollPaneWidth = 289;
	private final int personLocScrollPaneHeight = 258;
	private final int btnStartX = 27;
	private final int btnStartY = 11;
	private final int btnStartWidth = 252;
	private final int btnStartHeight = 23;
	private final int peopleScrollPaneX = 27;
	private final int peopleScrollPaneY = 66;
	private final int peopleScrollPaneWidth = 132;
	private final int peopleScrollPaneHeight = 199;
	private final int locationsScrollPaneX = 222;
	private final int locationsScrollPaneY = 66;
	private final int loactionsScrollPaneWidth = 132;
	private final int locationsScrollPaneHeight = 199;
	private final int lblPeopleX = 62;
	private final int lblPeopleY = 45;
	private final int lblPeopleWidth = 46;
	private final int lblPeopleHeight = 14;
	private final int lblLocationsX = 253;
	private final int lblLocationsY = 45;
	private final int lblLocationsWidth = 67;
	private final int lblLocationsHeight = 14;
	private final int lblPoeplesLocationsX = 491;
	private final int lblPoeplesLocationsY = 14;
	private final int lblPoeplesLocationsWidth = 111;
	private final int lblPoeplesLocationsHeight = 14;
	/**
	 * Launch the application.
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui frame = new Gui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame. See comments for details
	 */
	public Gui() {
		started = false;
		client = new Client();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Set the bounds of the whole Gui
		setBounds(guiX, guiY, guiWidth, guiHeight);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(contentPaneBoarder, contentPaneBoarder, contentPaneBoarder, contentPaneBoarder));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Create a scrollable JTextPane to display people's locations and set its bounds and font
		personLocScrollPane = new JScrollPane();
		personLocScrollPane.setBounds(personLocScrollPaneX, personLocScrollPaneY, personLocScrollPaneWidth, personLocScrollPaneHeight);
		contentPane.add(personLocScrollPane);
		personLoc = new JTextPane();
		personLoc.setFont(new Font("Tahoma", Font.BOLD, 11));
		personLoc.setEditable(false);
		personLocScrollPane.setViewportView(personLoc);

		// Create a start button and set its bounds. This button will start the client.
		btnStart = new JButton("Start Remote Weasley Display");
		btnStart.setBounds(btnStartX, btnStartY, btnStartWidth, btnStartHeight);
		contentPane.add(btnStart);

		// Create a JList which displays all the people and make it scrollable to make the number of people flexible. 
		JScrollPane peopleScrollPane = new JScrollPane();
		peopleScrollPane.setBounds(peopleScrollPaneX, peopleScrollPaneY, peopleScrollPaneWidth, peopleScrollPaneHeight);
		contentPane.add(peopleScrollPane);
		people = new JList();
		peopleScrollPane.setViewportView(people);

		// Create a JList which displays all the locations and make it scrollable to make the number of locations flexible.
		JScrollPane locationsScrollPane = new JScrollPane();
		locationsScrollPane.setBounds(locationsScrollPaneX, locationsScrollPaneY, loactionsScrollPaneWidth, locationsScrollPaneHeight);
		contentPane.add(locationsScrollPane);
		locations = new JList();
		locationsScrollPane.setViewportView(locations);

		// Create a label for people.
		JLabel lblPeople = new JLabel("People");
		lblPeople.setBounds(lblPeopleX, lblPeopleY, lblPeopleWidth, lblPeopleHeight);
		contentPane.add(lblPeople);

		// Create a label for locations.
		JLabel lblLocations = new JLabel("Locations");
		lblLocations.setBounds(lblLocationsX, lblLocationsY, lblLocationsWidth, lblLocationsHeight);
		contentPane.add(lblLocations);

		// Create a label for people's locations
		JLabel lblPoeplesLocations = new JLabel("Poeple's locations");
		lblPoeplesLocations.setBounds(lblPoeplesLocationsX, lblPoeplesLocationsY, lblPoeplesLocationsWidth, lblPoeplesLocationsHeight);
		contentPane.add(lblPoeplesLocations);

		// Implement the action listener for the start button by creating a separated thread which
		// executes the run method in the client.
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (started == false) {
					started = true;
					new Thread(new Runnable() {
						public void run() {
							personLoc.setText("");
							client.run(people, locations, personLoc);
						}
					}).start();
				}
			}
		});
	}
}
