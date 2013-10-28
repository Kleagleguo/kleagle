/** 
 * This class represents the server of this game,
 * inside this class, the server will handle multiclients
 * connections
 * @author 			Bingkun Guo
 * Lab Section:		C
 * Email:			guobingkun@wustl.edu
 * Lab:				Lab5: Set Multiuser Game 
 * File:			Server.java
 */

package lab5;

import java.io.IOException;
import java.net.ServerSocket;

import lab5.view.PlayerHandler;

public class Server implements Runnable {
	GameController game;
	ServerSocket ss;

	/**
	 * Constructor of the server, it will establish
	 * a serversocket on the port 1050
	 * @param game
	 */
	public Server(GameController game) {
		this.game = game;
		try {
			ss = new ServerSocket(1050);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * in side the run method, the server will keep 
	 * listening on the port and accept new connections
	 */
	@Override
	public void run() {
		while (true) {
			try {
				new Thread(new PlayerHandler(game, ss.accept())).start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
