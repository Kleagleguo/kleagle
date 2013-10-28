package kwic;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.beans.PropertyChangeSupport;
import java.io.*;
/*
 * TA: MM
 * GRADE:	100/100
 * 
 * Word:		35/35
 * Phrase:		20/20
 * KWIC:		35/35
 * Cover Page: 10/10
 */
/** 
 * Key Word in Context
 *  @author 		Bingkun Guo
 * Lab Section:		C
 * Email:			guobingkun@wustl.edu
 * Lab:				Lab2A:KWIC Index 
 * File:			KWIC.java
 */

public class KWIC {

	protected Map<Word, Set<Phrase>> wordinset;
	
	protected PropertyChangeSupport pcs;

	/**
	 *  contructor of class KWIC
	 */
	public KWIC() { 
		pcs = (new PropertyChangeSupport(this)); 
		this.wordinset = new HashMap<Word, Set<Phrase>>();
	}

	/** 
	 * Required for part (b) of this lab.
	 * Accessor for the {@link PropertyChangeSuppport} 
	 */

	public PropertyChangeSupport getPCS() { return pcs; }

	/** 
	 * Convenient interface, accepts a standrd Java {@link String}
	 * @param s String to be added
	 */
	public void addPhrase(String s) {
		addPhrase(new Phrase(s));
	}
	
	/**
	 * Add each line in the file as a phrase.
	 * For each line in the file, call {@link addPhrase(String)} to
	 *   add the line as a phrase.
	 * @param file the file whose lines should be loaded as phrases
	 */
	public void addPhrases(File file) {
		FileInputStream fis;
		try {
			fis = new FileInputStream(file);
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			String currentline;
			while ((currentline = br.readLine()) != null) {
				addPhrase(currentline);
			}
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	/** 
	 * For each {@link Word} in the {@link Phrase}, 
	 * add the {@link Word}
	 * to the association.
	 * Use reduction to {@link #forceAssoc(Word, Phrase)}.
	 * @param p Phrase to be added
	 */
	public void addPhrase(Phrase p) {
		for (Word w : p.getWords()) {
			forceAssoc(w, p);			
		}
		pcs.firePropertyChange("Phrase Added",false, true);
	}


	/** For each word in the {@link Phrase}, delete the association between
      the word and the phrase.
      Use reduction to {@link #dropAssoc(Word, Phrase)}.
	 */
	public void deletePhrase(Phrase p) {
		for (Word w : p.getWords()) {
			dropAssoc(w,p);
		}
		pcs.firePropertyChange("Phrase Deleted",false,true);
	}

	/** Force a mapping between the speicified {@link Word} and {@link Phrase} */
	public void forceAssoc(Word w, Phrase p) {
		if (this.wordinset.containsKey(w)) {
			this.wordinset.get(w).add(p);
		}
		else {
			Set<Phrase> s = new HashSet<Phrase>();
			s.add(p);
			this.wordinset.put(w, s);
		}
		pcs.firePropertyChange("Phrase Added",false,true);
	}


	/** 
	 * Drop the association between the 
	 * specified {@link Word} and {@link Phrase}, if any
	 */
	public void dropAssoc(Word w, Phrase p) {
		this.wordinset.get(w).remove(p);
		if (this.wordinset.get(w).size() == 0) {
			this.wordinset.remove(w);
		}
		pcs.firePropertyChange("Phrase Deleted",false,true);
	}


	/** 
	 * @param return 
	 * Return a Set that provides each {@link Phrase} associated with
     * the specified {@link Word}.
	 */
	public Set<Phrase> getPhrases(Word w) {
		if (!wordinset.keySet().contains(w)) {
			Set<Phrase> s = new HashSet<Phrase>();
			wordinset.put(w, s);
		}
		return this.wordinset.get(w);
	}
	
	/** 
	 * Drop a word completely from the KWIC 
	 * 
	 * @param w Word to be dropped
	 */
	public void deleteWord(Word w) {
		this.wordinset.get(w).clear();
		pcs.firePropertyChange("Word Deleted",false,true);
		this.wordinset.remove(w);
	}

	/** Rerturn a Set of all words */
	public Set<Word> getWords() {
		Set<Word> s = new HashSet<Word>();
		for (Word w : this.wordinset.keySet()) {
			s.add(w);
		}
		return s;
	}
	
	/** Add a word in KWIC
	 * @param word
	 */
	public void addWord(Word word) {
		if (!wordinset.containsKey(word)) {
			Set<Phrase> s = new HashSet<Phrase>();
			wordinset.put(word, s);
		}
	}
}
