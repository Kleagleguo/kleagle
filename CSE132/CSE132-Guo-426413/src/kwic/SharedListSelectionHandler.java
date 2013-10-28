/**
 * SharedListSelectionHandler is used when 
 * the user clicked an item the word list 
 *  @author 		Bingkun Guo
 * Lab Section:		C
 * Email:			guobingkun@wustl.edu
 * Lab:				Lab2B:KWIC Index 
 * File:			SharedListSelectionHandler.java
 */
package kwic;

import java.util.HashSet;
import java.util.Set;

import javax.swing.JList;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;


public class SharedListSelectionHandler implements ListSelectionListener {
	private KWIC kwic;
	private JList wordList = new JList();
	private JList phraseList = new JList();
	private JTextPane textPane;

	/**
	 * Constructor for class SharedListSelectionHandler
	 * @param kwic : key word in context class (referenced)
	 * @param list : the word list
	 * @param list_1 : the phrase list
	 * @param textPane : used to allow scroll 
	 */
	public SharedListSelectionHandler(KWIC kwic, JList list, JList list_1, JTextPane textPane) {
		this.wordList = list;
		this.kwic = kwic;
		this.phraseList = list_1;
		this.textPane = textPane;
	}

	/**
	 * Executed when the user selects an item in the word list
	 * After the user clicks a word, the phrase list will display 
	 * the phrases which are associated with the selected word
	 */
	public void valueChanged(ListSelectionEvent e) {
		if (wordList.getSelectedValue() != null) {
			Word w = null;
			w = (Word) wordList.getSelectedValue();
			Set phraseSet = new HashSet();
			phraseSet = kwic.getPhrases(w);
			Object[] labels   = phraseSet.toArray();
			phraseList.setListData(labels);
		}
	}


}