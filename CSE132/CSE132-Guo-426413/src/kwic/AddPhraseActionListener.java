/** 
 * AddPhraseActionListener is implemented for 
 * add phrase button
 * @author 		Bingkun Guo
 * Lab Section:		C
 * Email:			guobingkun@wustl.edu
 * Lab:				Lab2B:KWIC Index 
 * File:			AddPhraseActionListener.java
 */
package kwic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JTextField;

public class AddPhraseActionListener implements ActionListener {

	private JList list;
	private KWIC kwic;
	private JTextField textfield;
	/**
	 * Constructor of AddPhraseActionListener
	 * @param list : Phrase list (referenced)
	 * @param kwic : Key word in context class (referenced)
	 * @param textfield : put the phrase which the user wants to add
	 */
	public AddPhraseActionListener(JList list, KWIC kwic, JTextField textfield) {
		this.list = list;
		this.kwic = kwic;
		this.textfield = textfield;
	}
	/**
	 * Executed after add phrase button is clicked
	 * Will add the new phrase into the list 
	 * and refresh the list
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		list.clearSelection();
		kwic.addPhrase(textfield.getText());
		Set wordset = kwic.getWords();
		Object[] labels   = wordset.toArray();
		list.setListData(labels);
		textfield.setText("");
	}
}
