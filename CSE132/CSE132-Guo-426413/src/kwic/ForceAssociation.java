/** 
 * ForceAssociation is implemented for 
 * the Force association button
 *  @author 		Bingkun Guo
 * Lab Section:		C
 * Email:			guobingkun@wustl.edu
 * Lab:				Lab2B:KWIC Index 
 * File:			ForceAssociation.java
 */
package kwic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.JList;
import javax.swing.JTextField;

public class ForceAssociation implements ActionListener{

	private KWIC kwic;
	private JTextField jtf1, jtf2;
	private JList list;
	/**
	 * Constructor for ForceAssociation
	 * @param kwic : key word in context class (referenced)
	 * @param jtf1 : put the entered word
	 * @param jtf2 : put the entered phrase
	 * @param list : the word list
	 */
	public ForceAssociation(KWIC kwic, JTextField jtf1, JTextField jtf2, JList list){
		this.kwic = kwic;
		this.jtf1 = jtf1;
		this.jtf2 = jtf2;
		this.list = list;
	}

	/**
	 * Executed after the force button is clicked
	 * Force the association between the entered word 
	 * and phrase by calling kwic.forceAssoc() function
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if ( (!jtf1.getText().equals("")) && (!jtf2.getText().equals("")) ) {
			Word word = new Word(jtf1.getText());
			Phrase phrase = new Phrase(jtf2.getText());
			if (kwic.wordinset.containsKey(word)) {
				kwic.addPhrase(phrase);
				kwic.forceAssoc(word, phrase);
			}
			else{
				kwic.addWord(word);
				kwic.forceAssoc(word, phrase);
			}
			Set wordset = kwic.getWords();
			Object[] labels   = wordset.toArray();
			list.setListData(labels);
		}
		jtf1.setText("");
		jtf2.setText("");

	}

}
