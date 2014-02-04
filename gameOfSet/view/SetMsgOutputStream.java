/** 
 * This class is designed for both server
 * and client to write messages to each other 
 * @author 			Bingkun Guo
 * Lab Section:		C
 * Email:			guobingkun@wustl.edu
 * Lab:				Lab5: Set Multiuser Game 
 * File:			SetMsgOutputStream.java
 */

package lab5.view;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import lab5.model.Card;
import lab5.model.Player;

public class SetMsgOutputStream {

	private DataOutputStream dos;

	/**
	 * Constructor of SetMsgOutputStream, which takes a DataOutputStream
	 * @param dos
	 */
	public SetMsgOutputStream(DataOutputStream dos) {
		this.dos = dos; 
	}

	/**
	 * helloMessage from the client, which tells the server the name 
	 * of the client
	 * @param messageType
	 * @param length
	 * @param name
	 */
	public void helloMessage(byte messageType, short length, String name) {
		try {
			dos.writeByte('!');
			dos.writeByte(messageType);
			dos.writeShort(length);
			dos.writeUTF(name);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * helloResponse Message is sent from server to the client,
	 * which tells the client the game version and his/her player ID
	 * @param messageType
	 * @param length
	 * @param gameVersion
	 * @param playerNum
	 */
	public void helloResponseMessage(byte messageType, short length, byte gameVersion, byte playerNum) {
		try {
			dos.writeByte('!');
			dos.writeByte(messageType);
			dos.writeShort(length);
			dos.writeByte(gameVersion);
			dos.writeByte(playerNum);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Update the players' information in the gameSent from server to client, 
	 * tell the clients the current players' names and ids
	 * @param numPlayers
	 * @param players
	 */
	public void gamePlayerUpdateMessage(int numPlayers, Set<Player> players) {
		try {
			dos.writeByte('!');
			dos.writeByte(6);
			dos.writeShort(numPlayers);
			for (Player p : players) {
				dos.writeByte(p.getID());
				dos.writeUTF(p.getName());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Update the game table, tell the client's what cards should be on the table
	 * @param cardsLeft
	 * @param fieldSize
	 * @param currentCards
	 */
	public void gameFieldUpdateMessage(byte cardsLeft, byte fieldSize, Map<Integer, Card> currentCards) {
		try {
			dos.writeByte('!');
			dos.writeByte(5);
			dos.writeShort(50);
			dos.writeByte(cardsLeft);
			dos.writeByte(fieldSize);
			for (int i = 0; i < fieldSize; i++) {
				dos.writeByte(currentCards.get(i).getNumber());
				dos.writeByte(currentCards.get(i).getColor());
				dos.writeByte(currentCards.get(i).getShading());
				dos.writeByte(currentCards.get(i).getShape());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * If a player selects 3 cards, it will send this message to 
	 * the server, then the server will send a response to the 
	 * client
	 * @param num
	 */
	public void playerCallSetMessage(byte num) {
		try {
			dos.writeByte('!');
			dos.writeByte(13);
			dos.writeShort(1);
			dos.writeByte(num);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * The server asks the client to select the cards
	 * @param num
	 */
	public void selectCardsMessage(byte num) {
		try {
			dos.writeByte('!');
			dos.writeByte(14);
			dos.writeShort(1);
			dos.writeByte(num);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * The client tells the server his/her player ID, and 
	 * tells the server which cards he/she selects
	 * @param num
	 * @param selectedCardsNum
	 */
	public void setCall(byte num, ArrayList<Byte> selectedCardsNum) {
		try {
			dos.writeByte('!');
			dos.writeByte(15);
			dos.writeShort(4);
			dos.write(num);
			for (int i = 0; i < selectedCardsNum.size(); i++) {
				dos.writeByte((byte) selectedCardsNum.get(i));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * tell the specific player that he/she successfully gives a set
	 * @param num
	 */
	public void validSet(byte num) {
		try {
			dos.writeByte('!');
			dos.writeByte(17);
			dos.writeShort(1);
			dos.writeByte(num);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * tell the specific player that he/she fails to give a set 
	 * @param num
	 */
	public void invalidSet(byte num) {
		try {
			dos.writeByte('!');
			dos.writeByte(18);
			dos.writeShort(2);
			dos.writeByte(num);
			dos.writeByte(2);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * update the scores of players after some player gave a set.
	 * @param players
	 */
	public void gameScoreUpdate(Set<Player> players) {
		try {
			dos.writeByte('!');
			dos.writeByte(7);
			dos.writeShort((short) players.size() + 1);
			dos.writeByte((byte) players.size());
			for (Player p : players) {
				dos.writeByte((byte) p.getID());
				dos.writeShort((short) p.getScore());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * If the time is up or all the cards have been played,
	 * the server will tell all the clients that the game 
	 * is over
	 * @param num
	 */
	public void gameOverMessage(byte num) {
		try {
			dos.writeByte('!');
			dos.writeByte(60);
			dos.writeShort(1);
			dos.writeByte(num);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * tell all the clients that a hint has been given
	 * @param num
	 */
	public void hintMessage(byte num) {
		try {
			dos.writeByte('!');
			dos.writeByte(100);
			dos.writeShort(1);
			dos.writeByte(num);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
