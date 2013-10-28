/** 
 * This class represents graphical user interface
 * for the client side of Weasley Clock
 * @author 			Bingkun Guo
 * Lab Section:		C
 * Email:			guobingkun@wustl.edu
 * Lab:				Lab4b: Remote Weasley Display 
 * File:			Gui.java
 */
package lab4.client;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import lab4.model.Person;

public class Gui extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextPane personLoc;
	private JScrollPane personLocScrollPane;
	private JList people;
	private JList locations;
	private Client client;
	private JButton btnStart;
	private JButton btnAddNewPerson;
	private JButton btnAddNewLocation;
	private JTextField newPersonName;
	private JTextField newLocationName;
	private JButton btnDeletePerson;
	private JButton btnDeleteLocation;
	private JTextField delPersonName;
	private JTextField delLocationName;
	private JTextField guiConsole;
	private JLabel lblConsole;
	private boolean started;
	private final int contentPaneBoarder = 5;
	private final int guiX = 100;
	private final int guiY = 100;
	private final int guiWidth = 768;
	private final int guiHeight = 482;
	private final int personLocScrollPaneX = 442;
	private final int personLocScrollPaneY = 42;
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
	private final int lblPoeplesLocationsX = 527;
	private final int lblPoeplesLocationsY = 17;
	private final int lblPoeplesLocationsWidth = 111;
	private final int lblPoeplesLocationsHeight = 14;
	private final int newPersonNameX = 10;
	private final int newPersonNameY = 314;
	private final int newPersonNameWidth = 75;
	private final int newPersonNameHeight = 20;
	private final int btnAddNewPersonX = 95;
	private final int btnAddNewPersonY = 313;
	private final int btnAddNewPersonWidth = 106;
	private final int btnAddNewPersonHeight = 23;
	private final int btnAddNewLocationX = 324;
	private final int btnAddNewLocationY = 313;
	private final int btnAddNewLocationWidth = 117;
	private final int btnAddNewLocationHeight = 23;
	private final int newLocationNameX = 228;
	private final int newLocationNameY = 314;
	private final int newLocationNameWidth = 86;
	private final int newLocationNameHeight = 20;
	private final int btnDeletePersonX = 96;
	private final int btnDeletePersonY = 274;
	private final int btnDeletePersonWidth = 105;
	private final int btnDeletePersonHeight = 23;
	private final int btnDeleteLocationX = 324;
	private final int btnDeleteLocationY = 276;
	private final int btnDeleteLocationWidth = 117;
	private final int btnDeleteLocationHeight = 23;
	private final int delPersonNameX = 10;
	private final int delPersonNameY = 276;
	private final int delPersonNameWidth = 75;
	private final int delPersonNameHeight = 20;
	private final int delLocationNameX = 228;
	private final int delLocationNameY = 276;
	private final int delLocationNameWidth = 86;
	private final int delLocationNameHeight = 20;
	private final int guiConsoleX = 10;
	private final int guiConsoleY = 363;
	private final int guiConsoleWidth = 721;
	private final int guiConsoleHeight = 51;
	private final int lblConsoleX = 334;
	private final int lblConsoleY = 347;
	private final int lblConsoleWidth = 52;
	private final int lblConsoleHeight = 14;
	
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
		personLoc.setFont(new Font("Tahoma", Font.BOLD, 12));
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
		
		// Create a textfield for user to enter the name of a new person
		newPersonName = new JTextField();
		newPersonName.setBounds(newPersonNameX, newPersonNameY, newPersonNameWidth, newPersonNameHeight);
		contentPane.add(newPersonName);
		newPersonName.setColumns(10);
		
		// Create an add new person button. Once clicked, a new person with the name user entered will be added to the list 
		btnAddNewPerson = new JButton("Add Person");
		btnAddNewPerson.setBounds(btnAddNewPersonX, btnAddNewPersonY, btnAddNewPersonWidth, btnAddNewPersonHeight);
		contentPane.add(btnAddNewPerson);
		
		// Create an add location button. Once clicked, a new location with the name user entered will be added to the list
		btnAddNewLocation = new JButton("Add Location");
		btnAddNewLocation.setBounds(btnAddNewLocationX, btnAddNewLocationY, btnAddNewLocationWidth, btnAddNewLocationHeight);
		contentPane.add(btnAddNewLocation);
		
		// Create a textfield for user to enter the name of a new location
		newLocationName = new JTextField();
		newLocationName.setBounds(newLocationNameX, newLocationNameY, newLocationNameWidth, newLocationNameHeight);
		contentPane.add(newLocationName);
		newLocationName.setColumns(10);
		
		// Create a drop person button. Once clicked, the specific person will be dropped in the server
		btnDeletePerson = new JButton("Drop Person");
		btnDeletePerson.setBounds(btnDeletePersonX, btnDeletePersonY, btnDeletePersonWidth, btnDeletePersonHeight);
		contentPane.add(btnDeletePerson);
		
		// Create a drop location button. Once clicked, the specific location will be dropped in the server
		btnDeleteLocation = new JButton("Drop Location");
		btnDeleteLocation.setBounds(btnDeleteLocationX, btnDeleteLocationY, btnDeleteLocationWidth, btnDeleteLocationHeight);
		contentPane.add(btnDeleteLocation);
		
		// Create a textfield for user to enter the person's name he/she wants to delete
		delPersonName = new JTextField();
		delPersonName.setBounds(delPersonNameX, delPersonNameY, delPersonNameWidth, delPersonNameHeight);
		contentPane.add(delPersonName);
		delPersonName.setColumns(10);
		
		// Create a textfield for user to enter the location's name he/she wants to delete
		delLocationName = new JTextField();
		delLocationName.setBounds(delLocationNameX, delLocationNameY, delLocationNameWidth, delLocationNameHeight);
		contentPane.add(delLocationName);
		delLocationName.setColumns(10);
		
		// Create a textfield as the console, which displays replies from server 
		guiConsole = new JTextField();
		guiConsole.setFont(new Font("Tahoma", Font.BOLD, 18));
		guiConsole.setBounds(guiConsoleX, guiConsoleY, guiConsoleWidth, guiConsoleHeight);
		contentPane.add(guiConsole);
		guiConsole.setColumns(10);
		
		// Create a label for gui console
		lblConsole = new JLabel("Console");
		lblConsole.setBounds(lblConsoleX, lblConsoleY, lblConsoleWidth, lblConsoleHeight);
		contentPane.add(lblConsole);
		
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
		
		// Implement the action listener for the add new person button by executing 
		// the runAddPerson() method in the client
		btnAddNewPerson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String newPer = newPersonName.getText();
				newPersonName.setText("");
				client.runAddPerson(newPer, guiConsole);
			}
		});
		
		// Implement the action listener for the add new location button by executing
		// the runAddLocation() method in the client
		btnAddNewLocation.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				String newLoc = newLocationName.getText();
				newLocationName.setText("");
				client.runAddLocation(newLoc, guiConsole);
			}
		});
		
		// Implement the action listener for the delete person button by executing 
		// the runDropPerson() method in the client
		btnDeletePerson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				String dropPer = delPersonName.getText();
				delPersonName.setText("");
				client.runDropPerson(dropPer, guiConsole);
			}
		});
		
		// Implement the action listener for the delete location button by executing
		// the runDropLocation() method in the client
		btnDeleteLocation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String dropLoc = delLocationName.getText();
				delLocationName.setText("");
				client.runDropLocation(dropLoc, guiConsole);
			}
		});
		
	}
}
