/**
 * The Word class helps you get the original word 
 * and maching word. Besides, if the maching words
 * of two classes are the same, then they are eqaul
 *   
 * @author 			Bingkun Guo
 * Lab Section:		C
 * Email:			guobingkun@wustl.edu
 * Lab:				Lab2A:KWIC Index
 * File:			Word.java

 * Represents the original and matching forms of a word.  
 * You must implement 
 * {@link Object#hashCode()} correctly as well as
 * {@link Object#equals(Object)} 
 * for this to work.
 */
package kwic;
public class Word {

	final protected String originalword;
	final protected String matchword;

	/** Represent a word of a {@link Phrase}
	 * @param w The original word
	 */
	public Word(String w){
		this.originalword = w;
		this.matchword = w.toLowerCase();
	}

	/**
	 * The word used for matching is the original word run throgh
	 * the WordCanonical filter.
	 * @return the form of the word used for matching.
	 * 
	 */
	public String getMatchWord() {
		return this.matchword;
	}

	/**
	 * 
	 * @return the original word
	 */
	public String getOriginalWord() {
		return this.originalword;
	}

	/** 
	 * You must implement this right, see lab writeup notes.
	 * 
	 * This is commented out so you can have eclipse generate
	 * a skeleton of it for you.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((matchword == null) ? 0 : matchword.hashCode());
		return result;
	}

	/**
	 * You must implement this so that two words equal each
	 * other if their matched forms equal each other.
	 * You can let eclipse generate this method automatically,
	 * but you have to modify the resulting code to get the
	 * desired effect.
	 * 
	 * This method is commented out so you can have eclipse generate
	 * a skeleton of it for you.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Word other = (Word) obj;
		if (this.matchword.equals(other.getMatchWord())) {
			return true;
		}
		else {
			return false;
		}
	}


	/**
	 * @return the word and its matching form, if different
	 */
	public String toString(){
		if (getOriginalWord().equals(getMatchWord()))
			return getOriginalWord();
		else
			return getOriginalWord();
	}


}
