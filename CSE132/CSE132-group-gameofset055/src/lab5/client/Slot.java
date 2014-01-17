/** 
 * This class represents a slot on the game table.
 * A slot has a card in it, which also contains the 
 * image for this slot.
 * 
 * @author 			Bingkun Guo
 * Lab Section:		C
 * Email:			guobingkun@wustl.edu
 * Lab:				Lab5: Set Multiuser Game  
 * File:			Slot.java
 */
package lab5.client;

import javax.swing.JButton;

import lab5.model.Card;

public class Slot {
	
	private Card card;
	private JButton image;
	
	/**
	 * Default constructor, do nothing
	 */
	public Slot() {}
	
	/**
	 * Constructor of a slot
	 * @param card
	 * @param image
	 */
	public Slot(Card card, JButton image) {
		this.card = card;
		this.image = image;
	}
	
	/**
	 * Get the card in this slot
	 * @return
	 */
	public Card getCard() {
		return card;
	}
	
	/**
	 * Get the image in this slot
	 * @return
	 */
	public JButton getImage() {
		return image;
	}
	
	/**
	 * Set the card for this slot
	 * @param card
	 */
	public void setCard(Card card) {
		this.card = card;
	}
	
}