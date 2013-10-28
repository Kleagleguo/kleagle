/** 
 * This class represents the client side in 
 * the Set Game, since I created a gui for 
 * the client, so I removed the main method 
 * which was in this class at the beginning. 
 * @author 			Bingkun Guo
 * Lab Section:		C
 * Email:			guobingkun@wustl.edu
 * Lab:				Lab5: Set Multiuser Game 
 * File:			Client.java
 */

package lab5.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import lab5.model.Card;
import lab5.model.Player;
import lab5.view.SetMsgInputStream;
import lab5.view.SetMsgOutputStream;

public class Client implements Runnable {

	private Socket client;
	private SetMsgInputStream sis;
	private SetMsgOutputStream sos;
	private String[] shape;
	private String[] number;
	private String[] shading;
	private String[] color;
	private Map<Integer, Slot> selectedSlots;
	private Map<Integer, Slot> images;
	private JTextField console;
	private JFrame contentPane;
	private JList playerScoresList;
	private JTextPane hintConsole;
	private String playerName;
	private byte playerId;
	private ArrayList<Byte> selectedCardsNum;
	private Set<Player> players;
	public static final int DELAY = 1000; // nominal sleep delay (in ms)

	/**
	 * The constructor of the Client. The client object is associated
	 * with Gui, which controls the things displayed in the Gui
	 * @param selectedSlots
	 * @param images
	 * @param console
	 * @param playerScoresList
	 * @param contentPane
	 * @param hintConsole
	 */
	public Client(Map<Integer, Slot> selectedSlots, Map<Integer, Slot> images, JTextField console, 
			JList playerScoresList, JFrame contentPane, JTextPane hintConsole) {
		try {
			client = new Socket("localhost", 1050);
			sis = new SetMsgInputStream(new DataInputStream(client.getInputStream()));
			sos = new SetMsgOutputStream(new DataOutputStream(client.getOutputStream()));

			shape = new String[3];
			shape[0] = "Diamonds";
			shape[1] = "Ovals";
			shape[2] = "Squiggles";

			number = new String[3];
			number[0] = "1";
			number[1] = "2";
			number[2] = "3";

			color = new String[3];
			color[0] = "Blue";
			color[1] = "Green";
			color[2] = "Red";

			shading = new String[3];
			shading[0] = "Filled";
			shading[1] = "Open";
			shading[2] = "Shaded";

			this.selectedSlots = selectedSlots;
			this.images = images;
			this.console = console;
			this.playerScoresList = playerScoresList;
			selectedCardsNum = new ArrayList<Byte>();
			players = new HashSet<Player>();
			this.contentPane = contentPane;
			this.hintConsole = hintConsole;
			
		} catch (UnknownHostException e) {
			System.out.println("Don't know this host");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("can not get io connection");
			e.printStackTrace();
		}
	}

	/**
	 * Tell the game controller that I joined the game
	 * @param images
	 * @param name
	 */
	public void startGame(Map<Integer, Slot> images, String name) {
		playerName = name;
		sos.helloMessage((byte)0, (short)0, name);
	}

	/**
	 * If I select 3 cards, then this method will be called.
	 * In this method, it will send a message to the game
	 * controller(server) that I want to check my set
	 */
	public void checkSet() {
		for (int key : selectedSlots.keySet()) {
			selectedCardsNum.add((byte) key);
		}
		sos.playerCallSetMessage(playerId);
	}

	/**
	 * When the game is over, empty the card table
	 */
	public void emptyTable() {
		for (int key : images.keySet()) {
			images.get(key).getImage().setIcon(new ImageIcon("images\\BlankCard (3).jpg"));
		}
	}


	/**
	 * The sleep that is observed nominally.
	 * Change DELAY to speed things up or slow things down.
	 */
	public static void sleep() {
		sleep(DELAY);
	}

	/**
	 * Delay for an arbitrary amount of time.
	 * @param ms delay amount (in ms)
	 */
	public static void sleep(int ms) {
		try {
			Thread.sleep(ms);
		}
		catch (Exception e) {
			throw new Error("ServerMain.sleep: should not happen " + e);
		}
	}

	/**
	 * Run method of the client(Client is a runnable)
	 * In this run method, it will keep receiving the message sent
	 * from game controller(server)
	 */
	@Override
	public void run() {
		while (true) {
			char c = (char)sis.readByte();
			while (c != '!') {
				c = (char)sis.readByte();
			}
			byte type = sis.readByte();
			short length = sis.readShort();
			switch (type) {
			case 1: {
				byte gameVersion = sis.readByte();
				playerId = sis.readByte();
				if (gameVersion < (byte) 3) {
				console.setText("Welcome to the game " + playerName 
								+ "! Please wait for another " + (3 - gameVersion) + " player to join the game!");
				} 
				hintConsole.setVisible(true);
				hintConsole.setText("  Hint Left: 2");
				break;
			}
			case 5:{
				sis.readByte();
				byte fieldSize = sis.readByte();
				for (int i = 0; i < fieldSize; i++) {
					byte num = sis.readByte();
					byte col = sis.readByte();
					byte sd = sis.readByte();
					byte sp = sis.readByte();
					images.get(i).setCard(new Card(num, col, sd, sp));
					String filename = "images" + "\\\\" + number[num] + " " + color[col] + " " + shading[sd] + " " + shape[sp] + " (3).jpg";
					images.get(i).getImage().setIcon(new ImageIcon(filename));
					if (i > 11) {
						images.get(i).getImage().setVisible(true);
					}
				}
				break;
			}
			case 6: {  
				String newPlayer = "";
				for (int i = 0; i < length; i++) {
					byte id = sis.readByte();
					String name = sis.readUTF();
					players.add(new Player(name, id));
					if (i == (0)) {
						newPlayer = name;
					}
				}
				if (players.size() < 3) {
					console.setText("Player " + newPlayer + " has joined the game!");
				} else {
					console.setText("Game starts!!!");
				}
				playerScoresList.setListData(players.toArray());
				break;
			}
			case 7: {
				byte playerSize = sis.readByte();
				for (byte i = 0; i < playerSize; i++) {
					byte num = sis.readByte();
					short score = sis.readShort();
					for (Player p : players) {
						if ((byte) p.getID() == num) {
							p.setScore(score);
							break;
						}
					}
				}
				playerScoresList.setListData(players.toArray());
				break;
			}
			case 14: {
				byte num = sis.readByte();
				sos.setCall(num, selectedCardsNum);
				break;
			}
			case 17: {
				byte num = sis.readByte();
				console.setText("Player " + num + " succesfully gives a set!!!");
				if (playerId == num) {
					JOptionPane.showMessageDialog(contentPane, "Congratulations! You made a set and got 10 points!");
					selectedCardsNum.clear();
				}
				break;
			}
			case 18: {
				byte num = sis.readByte();
				sis.readByte();
				console.setText("Player " + num + " failed to give a valid set");
				if (playerId == num) {
					selectedCardsNum.clear();
				}
				break;
			}
			case 60: {
				byte num = sis.readByte();
				for (Player p : players) {
					if ((byte) p.getID() == num) {
						JOptionPane.showMessageDialog(contentPane,
								p.getName() + " wins the game with score " + p.getScore() + " !!!");
						emptyTable();
						console.setText(p.getName() + " wins the game!!!");
						break;
					}
				}
				break;
			}
			case 100: {
				byte num = sis.readByte();
				if (num == 1) {
					console.setText("Can't find a set ? First hint has come!");
					hintConsole.setText("  Hint Left: 1");
				} else if (num == 2) {
					console.setText("Second hint comes!!!");
					hintConsole.setText("  Hint Left: 0");
				}
				break;
			}
			}

		}
	}



}
