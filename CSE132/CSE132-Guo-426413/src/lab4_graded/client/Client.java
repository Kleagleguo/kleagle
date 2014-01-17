/** 
 * This class represents the client side in 
 * the Weasley Clock, since I created a gui for 
 * the client, so I removed the main method 
 * which was in this class at the beginning. 
 * @author 			Bingkun Guo
 * Lab Section:		C
 * Email:			guobingkun@wustl.edu
 * Lab:				Lab4a: Remote Weasley Display 
 * File:			client.java
 */
package lab4_graded.client;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JList;
import javax.swing.JTextPane;

/*TA: DR
 * Grade: 100/100
 * 
 */

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
			
			dos.writeUTF("people");
			size = dis.readInt();
			for (int i = 0; i < size; i++) {
				reply = dis.readUTF();
				names.add(reply);
			}
			people.setListData(names.toArray());

			dos.writeUTF("locations");
			size = dis.readInt();
			for (int i = 0; i < size; i++) {
				reply = dis.readUTF();
				locNames.add(reply);
			}
			locations.setListData(locNames.toArray());

			String peopleLoc = "";
			while (true) {
				info.clear();
				personLoc.setText("");
				peopleLoc = "";
				for (String name : names) {
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

}