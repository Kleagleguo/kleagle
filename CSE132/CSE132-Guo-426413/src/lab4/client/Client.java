/** 
 * This class represents the client side in 
 * the Weasley Clock, since I created a gui for 
 * the client, so I removed the main method 
 * which was in this class at the beginning. 
 * @author 			Bingkun Guo
 * Lab Section:		C
 * Email:			guobingkun@wustl.edu
 * Lab:				Lab4b: Remote Weasley Display 
 * File:			client.java
 */
package lab4.client;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class Client {
	Socket client;
	DataInputStream dis;
	DataOutputStream dos;

	/**
	 * Constructor of Client
	 * In the constructor, we initialize the client socket,
	 * DataInputStream dis and DataOutputStream dos
	 */
	public Client() {
		try {
			client = new Socket("localhost", 10400);
			dis = new DataInputStream(client.getInputStream());
			dos = new DataOutputStream(client.getOutputStream());
		} catch (UnknownHostException e) {
			System.out.println("Don't know this host");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("can not get io connection");
			e.printStackTrace();
		}
	}

	/**
	 * The run method of client. In the run method, the client will
	 * send queries to the server. It will first send "people" to the 
	 * server, then server sends back the information of people. Then 
	 * client sends "locations" and the server sends back the information
	 * of places people could enter into. At last, in a while(true) loop, 
	 * the client will keeps sending queries to server to get the location 
	 * of a specific person. While receiving the reply from the server, the 
	 * run method also update the information displayed in the gui.
	 * @param people: a JList represents a list of all the people
	 * @param locations: a JList represents a list of all the places
	 * @param personLoc: a JTextPane displays people's locations in real time
	 */
	public void run(JList people, JList locations, JTextPane personLoc) {
		try {
			ArrayList<String> names = new ArrayList<String>();
			ArrayList<String> locNames = new ArrayList<String>();
			Map<String, String> info = new HashMap<String, String>();
			int size;
			String reply;

			while (true) {
				names.clear();
				dos.writeChar('$');
				dos.writeUTF("people");
				size = dis.readInt();
				for (int i = 0; i < size; i++) {
					reply = dis.readUTF();
					names.add(reply);
				}
				people.setListData(names.toArray());

				locNames.clear();
				dos.writeChar('$');
				dos.writeUTF("locations");
				size = dis.readInt();
				for (int i = 0; i < size; i++) {
					reply = dis.readUTF();
					locNames.add(reply);
				}
				locations.setListData(locNames.toArray());

				String peopleLoc = "";

				info.clear();
				personLoc.setText("");
				peopleLoc = "";
				for (String name : names) {
					dos.writeChar('$');
					String query = "query " + name;
					dos.writeUTF(query);
					size = dis.readInt();
					reply = dis.readUTF();
					info.put(name, reply);
					peopleLoc += name + " is at " + info.get(name) + "\n";
				}
				personLoc.setText(peopleLoc);
				Thread.sleep(1000);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * In this method, the client will send a query to the server to tell
	 * the server that we want to add a new person in the people list.
	 * @param name: the name of the person we want to add
	 * @param guiConsole: display the reply from server
	 */
	public void runAddPerson(String name, JTextField guiConsole) {
		try {
			dos.writeChar('$');
			dos.writeUTF("addPerson " + name);
			String reply = dis.readUTF();
			guiConsole.setText(reply);
			System.out.println(reply);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * In this method, the client will send a query to the server to tell
	 * the server that we want to add a new location in the locations list.
	 * @param place: the name of the location we want to add
	 * @param guiConsole: display the reply from the server
	 */
	public void runAddLocation(String place, JTextField guiConsole) {
		try {
			dos.writeChar('$');
			dos.writeUTF("addLoc " + place);
			String reply = dis.readUTF();
			guiConsole.setText(reply);
			System.out.println(reply);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * In this method, the client will send a query to the server to tell
	 * the server that we want to drop a specific person in the people list.
	 * @param name: the name of the person that we want to delete
	 * @param guiConsole: display the reply from the server
	 */
	public void runDropPerson(String name, JTextField guiConsole) {
		try {
			dos.writeChar('$');
			dos.writeUTF("dropPerson " + name);
			String reply = dis.readUTF();
			guiConsole.setText(reply);
			System.out.println(reply);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * In this method, the client will send a query to the server to tell
	 * the server that we want to drop a specific location in the locations
	 * list. 
	 * @param place: the name of the location we want to delete
	 * @param guiConsole: display the reply from the server
	 */
	public void runDropLocation(String place, JTextField guiConsole) {
		try {
			dos.writeChar('$');
			dos.writeUTF("dropLoc " + place);
			String reply = dis.readUTF();
			guiConsole.setText(reply);
			System.out.println(reply);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
