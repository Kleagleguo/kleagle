package studio9;

import java.io.DataInputStream;
import java.net.Socket;

/**
 * An instance of this class sits between each Client
 * and the server.
 * @author cytron
 *
 */
public class PlayerHandler extends Thread {

	private Socket socket;
	private GameController game;
	private int playerNum;
	private static int num = 0;
	private boolean shouldDie;

	public PlayerHandler(Socket s, GameController g) {
		this.socket = s;
		this.game = g;
		this.playerNum = ++num;
		this.shouldDie = false;
	}
	
	/**
	 * Tell the game a player has been added
	 * Then repeatedly get a String from the player and
	 * pass it to the game using sendMessage(string);
	 * 
	 * The protocol here is that the Client sends a String
	 * and this class sends it to the game.  You could just as easily
	 * accept integers, by doing in.readInt() instead of in.readUTF()
	 * 
	 * The protocol for your game is up to you, but it has to be
	 * consistent from the Server and Client points of view.
	 */
	public void run() {
		game.addPlayer(this);
		try {
			//
			// get the socket's input stream and make a DataInputStream out of it
			//
			DataInputStream in = new DataInputStream(socket.getInputStream()); // FIXME
			while (true) {
				if (shouldDie) {
					//FIXME
				}
				//
				//  call game.message on the next string I read
				//
				// FIXME
				game.message(in.readUTF());
			}

		}
		catch (Throwable t) {
			System.out.println("Noting exception for " + this);
		}
		finally {
			game.removePlayer(this);
		}

	}
	
	public void tellClient(String s) {
		System.out.println("Server would now send message to client: " + s);
	}
	
	/**
	 * Generate uniform messages
	 * @param s
	 */
	private void sendMessage(String s) {
		game.message("From Client " + this + " " + s);
		
	}
	
	public void die() {
		shouldDie = true;
	}
	
	public int getPlayerNum() {
		return playerNum;
	}

	public String toString() {
		return "Player #" + playerNum;
	}

}
