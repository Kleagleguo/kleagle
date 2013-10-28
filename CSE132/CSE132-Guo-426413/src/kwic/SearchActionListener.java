/**
 * SearchActionListener is implemented for
 * search button
 *  @author 		Bingkun Guo
 * Lab Section:		C
 * Email:			guobingkun@wustl.edu
 * Lab:				Lab2B:KWIC Index 
 * File:			SearchActionListener.java
 */
package kwic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;
import javax.swing.JTextField;

public class SearchActionListener implements ActionListener{
	private JList list;
	private KWIC kwic;
	private JTextField textfield;

	/**
	 * Constructor for SearchActionListener
	 * @param kwic : key word in context class (referenced)
	 * @param textfield : put the entered word
	 * @param list : update the word list
	 */
	public SearchActionListener(KWIC kwic, JTextField textfield, JList list) {
		this.list = list;
		this.kwic = kwic;
		this.textfield = textfield;
	}

	/**
	 * Executed after the search button is clicked
	 * Will look for the word in the kwic.getwords()
	 * and then select that word in the word list
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (!textfield.equals("")) {
			Word word = new Word (textfield.getText());
			if (kwic.getWords().contains(word)) {
				list.setSelectedValue((Object) word, true);
			}
		}
		textfield.setText("");
	}

}
