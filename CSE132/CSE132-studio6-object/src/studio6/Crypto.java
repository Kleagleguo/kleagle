package studio6;

public class Crypto {
	private int shift;

	public Crypto(int shift) {
		this.shift = shift;
	}

	/**
	 * Return Caeser's cipher of c according to the following:  
	 * If c is a lower case letter (a, b, c, ..., z)
	 *   then return the shift of that letter through the lower case alphabet, wrapping
	 *   around the end.  Given a lower case letter, this method returns a lower case letter.
	 *   Otherwise, return c untransformed.
	 * @param c
	 * @return encryption of c as described.
	 */
	public char encrypt(char c) {
		if ('a' <= c && c <= 'z') {
			// TODO: really encrypt by "shift"
			//    if the character is between a and z
			//System.out.println(c);
			c += shift;
			if (c > 'z') {
				//c = (char) ('a' + c - 'z' - 1);
				c -= 26;
			}
			if (c < 'a') {
				c += 26;
			}
			//System.out.println(c);
			return c;
		}
		else {
			return c;
		}
	}

	/**
	 * Return corresponding decryption of c.  Try to implement this method
	 * by reducing from encrypt.
	 * @param c
	 * @return decryption of c as described
	 */
	public char decrypt(char c) {
		//return new Crypto(26-shift).encrypt(c);
//		if ('a' <= c && c <= 'z') {
//			c -= shift;
//			if (c < 'a') {
//				c += 26;
//			}
//			return c;
//		} else {
//			return c;
//		}
		return new Crypto(-shift).encrypt(c);
	}

}
