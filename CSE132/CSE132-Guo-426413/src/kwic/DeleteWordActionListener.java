/** 
 * DeleteWordActionListener is implemented for
 * delete word button
 *  @author 		Bingkun Guo
 * Lab Section:		C
 * Email:			guobingkun@wustl.edu
 * Lab:				Lab2B:KWIC Index 
 * File:			DeleteWordActionListener.java
 */
package kwic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.JList;
import javax.swing.JTextField;

public class DeleteWordActionListener implements ActionListener{

	private JList list;
	private KWIC kwic;
	private JTextField textfield;
	/**
	 * Constructor for DeleteWordActionListener 
	 * @param list : The word list
	 * @param kwic : The key word in context
	 * @param textfield : put the entered word
	 */
	public DeleteWordActionListener(JList list, KWIC kwic, JTextField textfield) {
		this.list = list;
		this.kwic = kwic;
		this.textfield = textfield;
	}

	/**
	 * Executed after the delete word button is clicked
	 * Directly delete the entered word in the kwic and
	 * update the word list
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (!textfield.getText().equals("")) {
			Word word = new Word(textfield.getText());
			kwic.deleteWord(word); 
			Set wordset = kwic.getWords();
			Object[] labels   = wordset.toArray();
			list.setListData(labels);
			textfield.setText("");
		}
	}

}
