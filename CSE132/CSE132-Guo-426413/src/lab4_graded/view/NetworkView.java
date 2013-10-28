package lab4_graded.view;

/**
 *  NetworkView
 *   Remote view in Weasley Clock.  Supports socket connections.
 *
 *  Author: Roger Chamberlain
 *  Course: CS132
 *  Lab:    4
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
	ServerSocket ss;

	public NetworkView(Weasley model) throws IOException {
		this.model = model;
		ss = new ServerSocket(10400);
	}

	public void run() {
		Socket s = null;
		try {
			s = ss.accept();
			DataInputStream dis = new DataInputStream(s.getInputStream());
			DataOutputStream dos = new DataOutputStream(s.getOutputStream());
			while (true) {
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
