package studio9;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Random;

public class SimClient extends Thread {

	private String hostName;
	private int port;
	private String id;
	private Random rand;

	public SimClient(String hostName, int port, String id) {
		this.hostName = hostName;
		this.port = port;
		this.id = id;
		this.rand = new Random();
	}

	private void sleep(int low, int hi) {
		int snooze = rand.nextInt(1000*(hi-low));
		try {
			Thread.sleep(low*1000 + snooze);
		}
		catch (Throwable t) {
			throw new Error("bad sleep " + t);
		}

	}

	public void run() {
		Socket s = null;
		//
		//  Client
		//		connects
		//		sends some messages
		//		disconnects
		//

		try {
			sleep(1,2); 
			s = new Socket(hostName, port);
			DataOutputStream out = new DataOutputStream(s.getOutputStream());
			out.writeUTF("Client " + id + " starting");
			for (int i=0; i < 7; ++i){
				sleep(1,10);
				out.writeUTF(id + " says " + i);
			}
		} catch (Throwable t) {
			System.out.println("Error: " + t);
		} finally {
			try {
				s.close();
			} catch (IOException e) {
				System.out.println("Socket close error");
			}
		}



	}

}
