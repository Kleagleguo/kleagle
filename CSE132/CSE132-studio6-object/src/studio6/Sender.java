package studio6;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class Sender {

	private String hostName;
	private int    port;
	
	public Sender(String hostName, int port) {
		this.hostName = hostName;
		this.port = port;
		
	}
	
	// TODO: implementation
	public void run()  {
	}
	
	public static void main(String[] args) throws Exception {
		Sender client = new Sender("localhost", 3000);
		client.run();

	}
}
