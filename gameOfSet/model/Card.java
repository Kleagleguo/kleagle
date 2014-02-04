/** 
 * This class represents a card in this game.
 * This class contains the elements to describe
 * a card, including card number, card shading, 
 * card shape and card color.
 *  
 * @author 			Bingkun Guo
 * Lab Section:		C
 * Email:			guobingkun@wustl.edu
 * Lab:				Lab5: Set Multiuser Game  
 * File:			Card.java
 */
package lab5.model;

public class Card {

	private byte number;
	private byte shading;
	private byte shape;
	private byte color;
	
	/**
	 * Default constructor of Card class
	 */
	public Card() {
		number = 0;
		shading = 0;
		shape = 0;
		color = 0;
	}
	
	/**
	 * Constructor of Card class
	 * @param number: card number
	 * @param shading: card shading
	 * @param shape: card shape
	 * @param color: card color
	 */
	public Card(byte number, byte shading, byte shape, byte color) {
		this.number = number;
		this.shading = shading;
		this.shape = shape;
		this.color = color;
	}
	
	/**
	 * Get the card number
	 * @return
	 */
	public byte getNumber() {
		return number;
	}
	
	/**
	 * Get the card shading
	 * @return
	 */
	public byte getShading() {
		return shading;
	}
	
	/**
	 * Get the card shape
	 * @return
	 */
	public byte getShape() {
		return shape;
	}
	
	/**
	 * Get the card color
	 * @return
	 */
	public byte getColor() {
		return color;
	}
	
	/**
	 * Print out a Card class by outputing its properties
	 */
	public String toString() {
		return "number: " + number + " shading: " + shading + " shape: " + shape + " color: " + color + "\n"; 
	}
}
