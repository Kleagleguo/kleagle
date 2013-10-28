/**
 * This class represents a phrase.
 *   
 * @author 			Bingkun Guo
 * Lab Section:		C
 * Email:			guobingkun@wustl.edu
 * Lab:				Lab2A:KWIC Index
 * File:			Phrase.java
 */

package kwic;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Phrase {

	final protected String phrase;

	/**
	 * Constructor, clean the string in it
	 * @param s
	 */
	public Phrase(String s){
		phrase = cleanUp(s);
	}

	/** 
	 * Provide the words of a phrase.  
	 * Each word may have to be cleaned up:  
	 * punctuation removed, put into lower case
	 */

	@SuppressWarnings("null")
	public Set<Word> getWords() {
		Set<Word> wordset = new HashSet<Word>();
		StringTokenizer st = new StringTokenizer(this.phrase);
		while (st.hasMoreTokens()) {
			String tempword = st.nextToken();
			Word word = new Word(tempword);
			wordset.add(word);
		}
		return wordset;
	}

	/** The behavior of this lab depends on how you view this method.
      Are two phrases the same because they have the same words?
      Or are they the same because they are string-equlivalent.
      <UL>
       <LI> What song,  Is that Becky
       <LI> What song is that, Becky
      </UL>
      The above phrases have the same words but are different strings.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Phrase other = (Phrase) obj;
		if (phrase == null) {
			if (other.phrase != null)
				return false;
		} else if (!phrase.equals(other.phrase))
			return false;
		return true;
	}

	/** This method must also be properly defined, or else your {@link HashSet}
      structure won't operate properly.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((phrase == null) ? 0 : phrase.hashCode());
		return result;
	}


	/** Filter the supplied {@link String} (which is the String of
      a {@link Phrase} presumably) into a canonical form
      for subsequent matching.
      The actual filtering depends on what you consider to be
      insignificant in terms of matching.  
      <UL> <LI> If punctuation is
      irrelevant, remove puncutation.
           <LI> If case does not matter, than convert to lower (or upper)
	        case.
      </UL>
	 */
	protected static String cleanUp(String s){
		s = s.replaceAll("[^a-z\\sA-Z]","");
		return s;
	}

	/**
	 * return the string phrase
	 */
	public String toString(){
		return phrase;
	}

}
