/** 
 * DeletePhraseActionListener is implemented for
 * delete selected phrase button
 *  @author 		Bingkun Guo
 * Lab Section:		C
 * Email:			guobingkun@wustl.edu
 * Lab:				Lab2B:KWIC Index 
 * File:			DeletePhraseActionListener.java
 */
package kwic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.JList;

public class DeletePhraseActionListener implements ActionListener{

	private JList list, list2;
	private KWIC kwic;

	/**
	 * Constructor of DeletePhraseActionListener
	 * @param list : The word list (referenced)
	 * @param list2 : The phrase list (referenced)
	 * @param kwic : The key word in context class (referenced)
	 */
	public DeletePhraseActionListener(JList list, JList list2,  KWIC kwic) {
		this.list = list;
		this.list2 = list2;
		this.kwic = kwic;
	}

	/**
	 * Executed after the delete phrase button is clicked
	 * Will delete the selected phrase, if this phrase is 
	 * the only left phrase for that word, then the selected
	 * word will also be deleted
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Word word1 = null;
		if (list.getSelectedValue() != null) {
			word1 = (Word) list.getSelectedValue();
			if (kwic.getPhrases(word1).size() == 1) {
				if (list2.getSelectedValue() != null) {
					kwic.deletePhrase((Phrase) list2.getSelectedValue());
					Set wordset = kwic.getWords();
					Object[] labels   = wordset.toArray();
					list.setListData(labels);
				}
			}
			else {
				if (list2.getSelectedValue() != null) {
					kwic.deletePhrase((Phrase) list2.getSelectedValue());
					Word word = null;
					word = (Word) list.getSelectedValue();
					Set phraseSet = kwic.getPhrases(word);
					Object[] labels   = phraseSet.toArray();
					this.list2.setListData(labels);
					Set wordset = kwic.getWords();
					Object[] labels2   = wordset.toArray();
					list.setListData(labels2);
					list.setSelectedValue((Object) word1, true);
				}
			}
		}
	}
}
