/** 
 * AddWordActionListener is implemented for
 * add word button
 *  @author 		Bingkun Guo
 * Lab Section:		C
 * Email:			guobingkun@wustl.edu
 * Lab:				Lab2B:KWIC Index 
 * File:			AddWordActionListener.java
 */
package kwic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.JList;
import javax.swing.JTextField;

public class AddWordActionListener implements ActionListener{
	private JList list;
	private KWIC kwic;
	private JTextField textfield;
	/**
	 * Constructor of AddWordActionListener
	 * @param list : The word list (referenced)
	 * @param kwic : The key word in contex class (referenced)
	 * @param textfield : Put the new word 
	 */
	public AddWordActionListener(JList list, KWIC kwic, JTextField textfield) {
		this.list = list;
		this.kwic = kwic;
		this.textfield = textfield;
	}

	/**
	 * Executed after the user clicks the add word button
	 * Will add the new word and refresh the word list
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (!textfield.getText().equals("")) {
			Word word = new Word(this.textfield.getText());
			kwic.addWord(word);
			Set wordset = kwic.getWords();
			Object[] labels   = wordset.toArray();
			list.setListData(labels);
			textfield.setText("");
		}

	}

}
