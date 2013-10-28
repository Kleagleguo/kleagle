package studio9;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {

	private int port;
	private GameController game;
	private ServerSocket ss;
	
	public Server(int port, GameController g) {
		this.port = port;
		this.game = g;
		try {
			ss = new ServerSocket(3989);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		game.message("Server started at port " + port);
		try {
			// FIXME  replace the line below per instructions
			new PlayerHandler(ss.accept(), game).start();
		} catch(Throwable t) {
			throw new Error("Bad server: " + t);
		}
	}

}
