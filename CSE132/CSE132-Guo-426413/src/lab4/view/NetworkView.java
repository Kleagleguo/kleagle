package lab4.view;

/**
 *  NetworkView
 *   Remote view in Weasley Clock.  Supports socket connections.
 *  
 *  I made some modifications of the protocol, details will be found
 *  in the cover-page
 *  
 *  Author: Roger Chamberlain, Bingkun Guo
 *  Course: CS132
 *  Lab:    4b
 *  
 */

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Set;

import lab4.model.Location;
import lab4.model.Person;
import lab4.model.Weasley;

public class NetworkView implements Runnable {
	Weasley model;

	Socket s = null;
	
	/**
	 * Constructor of NetworkView, it takes the weasley model and the
	 * newly created socket for the specific client.
	 * @param model
	 * @param s
	 * @throws IOException
	 */
	public NetworkView(Weasley model, Socket s) throws IOException {
		this.model = model;
		this.s = s;
	}

	public void run() {
		try {
			DataInputStream dis = new DataInputStream(s.getInputStream());
			DataOutputStream dos = new DataOutputStream(s.getOutputStream());
			while (true) {

				while(dis.readChar() != '$') {
					// The server figures out a new message by checking whether
					// that command starts with '$' to ensure it interpreted
					// messages correctly. Thus this while loop will only exit
					// when it finds the start command sign '$'
				}

				String command = dis.readUTF();

				if (command.equals("locations")) {
					// process "locations" command
					Set<Location> locations = model.getLocations();
					dos.writeInt(locations.size());
					for (Location loc: locations) {
						dos.writeUTF(loc.getName());
					}
				}
				else if (command.equals("people")) {
					// process "people" command
					Set<Person> people = model.getPeople();
					dos.writeInt(people.size());
					for (Person p: people) {
						dos.writeUTF(p.getName());
					}
				}
				else if (command.startsWith("query")) {
					// process "query" command
					String name = command.replaceFirst("query\\s+", "");
					Person p = new Person(name);
					if (model.getPeople().contains(p)) {
						dos.writeInt(1);
						dos.writeUTF(model.getLocation(p).getName());
					}
					else { // name is not a valid Person, send error signal
						dos.writeInt(-1);
					}
				}
				else if (command.startsWith("addPerson")) {
					// process "addPerson" command, the server will only add that person if the
					// user provides the name and there will be no duplicates in the people list.
					// Then tell the client whether that command works or not.
					String name = command.replaceFirst("addPerson\\s+", "");
					Person p = new Person(name);
					if (name.equals("")) {
						dos.writeUTF("You must enter a name to add a ner person");
					} else if (model.getPeople().contains(p)) {
						dos.writeUTF("Failed to add. Person " + name + " is already in the people list");
					} else {
						model.addPerson(p);
						dos.writeUTF("Successfully added Person " + name);
					}
				}
				else if (command.startsWith("addLoc")) {
					// process "addLoc" command, the server will only add that location if the user
					// provides the location name and there will be no duplicates in the location list.
					// Then tell the client whether that command works or not.
					String place = command.replaceFirst("addLoc\\s+", "");
					Location loc = new Location(place);
					if (place.equals("")) {
						dos.writeUTF("You must enter a location name to add a new location");
					} else if (model.getLocations().contains(loc)) {
						dos.writeUTF("Failed to add. Location " + place + " is already in the location list");
					} else {
						model.addLocation(loc);
						dos.writeUTF("Successfully added Location " + place);
					}
				}
				else if (command.startsWith("dropPerson")) {
					// process "dropPerson" command, the server will only delete that person if such person
					// does exist in the current people list and it is not the only one person in the people
					// list. Then tell the client whether that command works or not.
					String name = command.replaceFirst("dropPerson\\s+", "");
					Person p = new Person(name);
					if (model.getPeople().size() == 1) {
						dos.writeUTF("Failed to drop. There must be at least 1 person in the people list");
					} else if (model.getPeople().contains(p)) {
						model.dropPerson(p);
						dos.writeUTF("Successfully dropped Person " + name);
					} else if (!model.getPeople().contains(p)) { // name is not a valid Person, send error signal
						dos.writeUTF("Failed to drop. Person " + name + " doesn't exist in the Weasley Clock");
					}
				}
				else if (command.startsWith("dropLoc")) {
					// process "dropLoc" command, the server will only delete that location if such location
					// does exist in the current locations list and it is not the only one location in the locations
					// list. Then tell the client whether that command works or not.
					String place = command.replaceFirst("dropLoc\\s+", "");
					Location loc = new Location(place);
					if (model.getLocations().size() == 1) {
						dos.writeUTF("Failed to drop. There must be at least 1 location int the location list");
					} else if (model.getLocations().contains(loc)) {
						model.dropLocation(loc);
						dos.writeUTF("Successfully dropped Location " + place);
					} else if (!model.getLocations().contains(loc)){ // name is not a valid Person, send error signal
						dos.writeUTF("Failed to drop. Location " + place + " doesn't exist in the Weasley Clock");
					}
				}
				else {
					// invalid command, send error signal
					dos.writeInt(-1);
				}
			}
		} catch (EOFException e) {
			// graceful termination on EOF
		} catch (IOException e) {
			System.out.println("Remote connection reset");
		}
	}

}
