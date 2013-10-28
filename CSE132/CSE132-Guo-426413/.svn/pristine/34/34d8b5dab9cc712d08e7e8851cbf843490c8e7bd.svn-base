/** 
 * DropAssociation is implemented for 
 * the Drop association button
 *  @author 		Bingkun Guo
 * Lab Section:		C
 * Email:			guobingkun@wustl.edu
 * Lab:				Lab2B:KWIC Index 
 * File:			DropAssociation.java
 */
package kwic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.JList;
import javax.swing.JTextField;

public class DropAssociation implements ActionListener{
	private KWIC kwic;
	private JTextField jtf1, jtf2;
	private JList list;

	/**
	 * Constructor of Class DropAssociation
	 * @param kwic : key word in context class (referenced)
	 * @param jtf1 : Put the word
	 * @param jtf2 : Put the phrase
	 * @param list : Word list (referenced)
	 */
	public DropAssociation(KWIC kwic, JTextField jtf1, JTextField jtf2, JList list) {
		this.kwic = kwic;
		this.jtf1 = jtf1;
		this.jtf2 = jtf2;
		this.list = list;
	}
	/**
	 * Executed after the drop button is clicked
	 * drop the association between the entered word
	 * and phrase in the kwic.dropAssoc() function
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		list.clearSelection();
		Word word = new Word(jtf1.getText());
		Phrase phrase = new Phrase(jtf2.getText());
		if (kwic.wordinset.containsKey(word) && kwic.wordinset.get(word).contains(phrase)) {
			kwic.dropAssoc(word, phrase);
			Set wordset = kwic.getWords();
			Object[] labels   = wordset.toArray();
			list.setListData(labels);
		}
		jtf1.setText("");
		jtf2.setText("");

	}


}
