package studio6;

import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Receiver {

	private int port;

	public Receiver(int port) {
		this.port = port;
	}

	// TODO:  implementation
	public void run() {
	}


	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) {
		Receiver server = new Receiver(3000);
		server.run();

	}

}
