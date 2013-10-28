/** 
 * This class represents a Set Game being played, 
 * it stores the cards and players
 * 
 * @author 			Bingkun Guo
 * Lab Section:		C
 * Email:			guobingkun@wustl.edu
 * Lab:				Lab5: Set Multiuser Game  
 * File:			SetGame.java
 */

package lab5.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class SetGame {
	
	private Set<Player> players;	// set of players in the game
	private int numPlayers; 		// number of players since game started
	private ArrayList<Card> cards;  
	
	/**
	 * Constructor of the set game, initialize all the cards then shuffle
	 */
	public SetGame() {
		players = new HashSet<Player>();
		cards = new ArrayList<Card>();
		numPlayers = 0;
		for (byte a = 0; a < 3; a++) 
			for (byte b = 0; b < 3; b++)
				for (byte c = 0; c < 3; c++) 
					for (byte d = 0; d < 3; d++)
						cards.add(new Card(a, b, c, d));
		Collections.shuffle(cards);
	}
	
	/**
	 * Adds a player to the set of Players playing set.
	 * @param p player to be added to set of Players
	 */
	public synchronized void addPlayer(String player) {
		numPlayers++;
		Player p = new Player(player,numPlayers);
		if (players.contains(p)) {
			numPlayers--;
			return;
		}
		players.add(p);
	}
	
	/**
	 * Accessor for set of players in game.
	 * @return set of players in game
	 */
	public synchronized Set<Player> getPlayers() {
		return players;
	}
	
	/**
	 * randomly deal a card and return that card
	 * @return
	 */
	public synchronized Card dealCard() {
		Collections.shuffle(cards);
		return cards.remove(0);
	}
	
	/**
	 * return the left cards
	 * @return
	 */
	public synchronized ArrayList<Card> getCards() {
		return cards;
	}
	
	/**
	 * return the number of players
	 * @return
	 */
	public synchronized int getNumPlayer() {
		return numPlayers;
	}
}




