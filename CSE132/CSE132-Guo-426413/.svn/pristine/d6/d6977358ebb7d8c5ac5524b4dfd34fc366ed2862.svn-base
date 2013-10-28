/** 
 * This class represents part of the server.
 * It will create a server socket and listen
 * to the port. If there is a new connection
 * from the client, it will create a new 
 * thread which executes NetWorkView. In this
 * way, the server will enable multiple clients
 * connections. 
 * @author 			Bingkun Guo
 * Lab Section:		C
 * Email:			guobingkun@wustl.edu
 * Lab:				Lab4b: Remote Weasley Display 
 * File:			Server.java
 */
package lab4;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import lab4.model.Weasley;
import lab4.view.NetworkView;

public class Server implements Runnable{

	private Weasley model;
	ServerSocket ss;
	Socket s;
	
	/**
	 * Constructor of the server, it will instantiate the 
	 * server socket
	 * @param model
	 */
	public Server(Weasley model) {
		this.model = model;
		try {
			ss = new ServerSocket(10400);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * In this method, the server will create a new thread
	 * to handle a newly connected client
	 */
	public void run() {
		while (true) {
			try {
				new Thread(new NetworkView(model, ss.accept())).start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
