/** 
 * This class represents the protocol between 
 * the client and the server. It handles the 
 * messages sent from client to the server 
 * @author 			Bingkun Guo
 * Lab Section:		C
 * Email:			guobingkun@wustl.edu
 * Lab:				Lab5: Set Multiuser Game  
 * File:			PlayerHandler.java
 */

package lab5.view;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import lab5.GameController;

public class PlayerHandler implements Runnable {

	private GameController game;
	private Socket s;
	private String playerName;
	private byte playerId;
	private SetMsgInputStream sis;
	private SetMsgOutputStream sos;
	private int totalNumOfPlayer;
	private boolean gameRun;
	
	/**
	 * Constructor of the PlayerHandler, which takes a game controller and a socket
	 * @param game
	 * @param s
	 * @throws IOException
	 */
	public PlayerHandler(GameController game, Socket s) throws IOException {
		this.game = game;
		this.s = s;
		totalNumOfPlayer = 3;
		gameRun = true;
		DataInputStream dis = new DataInputStream(this.s.getInputStream());
		DataOutputStream dos = new DataOutputStream(this.s.getOutputStream());
		sis = new SetMsgInputStream(dis);
		sos = new SetMsgOutputStream(dos);
	}

	/**
	 * Inside a while(true) loop, the protocol keep receiving messages sent
	 * from the client
	 */
	public void run() {
		while (gameRun) {
			// read commands and return random response
			char c = (char)sis.readByte();
			while (c != '!' && gameRun) {
				c = (char)sis.readByte();
			}
			int type = sis.readByte();
			sis.readShort();
			switch (type) {
			case 0: {						    // hello message from a new player
				String playerName = sis.readUTF();
				this.playerName = playerName;
				game.addPlayer(this, playerName);
				GameController.sleep(50);
				playerId = (byte) game.getGame().getPlayers().size();
				sos.helloResponseMessage((byte)1, (short)2, (byte)game.getGame().getNumPlayer(), playerId);
				if (game.getGame().getPlayers().size() == totalNumOfPlayer) {
					game.startSetGame();
				}
				break;
			}

			case 13: {			 	     		// player calls set
				if (game.getPlayeCallingSet() == -1) {
					byte id = sis.readByte();
					game.handlePlayerCallingSet(id);
				}
				break;
			}

			case 15: {						    // set call
				byte num = sis.readByte();
				int[] cardsNum = new int[3];
				cardsNum[0] = sis.readByte();
				cardsNum[1] = sis.readByte();
				cardsNum[2] = sis.readByte();
				game.identifySet(num, cardsNum);
				break;
			}
			
			default: {  					    // anything else
			}
			}
		}
	}

	/**
	 * get the SetMsgInputStream for this player handler
	 * @return
	 */
	public SetMsgInputStream getSis() {
		return sis;
	}
	
	/**
	 * get the SetMsgOutputStream for this player handler
	 * @return
	 */
	public SetMsgOutputStream getSos() {
		return sos;
	}
	
	/**
	 * get the player id of this playerhandler's player 
	 * @return
	 */
	public byte getPlayerId() {
		return playerId;
	}
	
	/**
	 * set the game status to false
	 */
	public void endGame() {
		gameRun = false;
	}
	
	/**
	 * print this playerHandler
	 */
	public String toString() {
		return "name: " + playerName;
	}
}
