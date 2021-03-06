package studio6;

import static org.junit.Assert.*;

import org.junit.Test;

public class CryptoTest {

	private void checkShift(Crypto crypto, boolean expectChange, char[] test) {
		for (char c : test) {
			if ('a' <= c && c <= 'z') {
				//
				// Make sure a lower case letter is affected by encryption
				//    if we expect a change (e.g., for a shift != 0
				//
				assertTrue((c != crypto.encrypt(c)) == expectChange);
			}
			else {
				//
				//  All other characters should be the same whether
				//     encrypted or decrypted
				//
				assertTrue(c==crypto.encrypt(c));
				assertTrue(crypto.encrypt(c)==crypto.decrypt(c));
			}
			
			//
			// Always, the decryption of the encryption should be the original char
			//
			assertTrue(c==crypto.decrypt(crypto.encrypt(c)));
		}
	}

	@Test
	public void check1() {
		Crypto crypt = new Crypto(3);  // Good enough for Caesar
		assertEquals(crypt.encrypt('a'), 'd');
		assertEquals(crypt.decrypt('d'), 'a');
		assertEquals(crypt.encrypt('A'), 'A');
		assertEquals(crypt.decrypt('A'), 'A');
		assertEquals(crypt.encrypt('$'), '$');
		assertEquals(crypt.decrypt('$'), '$');
	}

	
	@Test
	public void check2() {
		char[] testChars = "I hope you have fun with this studio. It's a way to learn about sockets and ciphers".toCharArray();		
		//
		// Make sure the return type of encrypt is still char
		//
		char makeSureChar = new Crypto(5).encrypt('z');
		//
		//  For the shifts below, encryption of a lower case letter should change
		//    the letter
		//
		for (int shift=1; shift < 26; ++shift) {
			Crypto crypto = new Crypto(shift);
			checkShift(crypto, true, testChars);
		}
		
		//
		// But for these, we rotate clear around or not at all
		//
		checkShift(new Crypto(26), false, testChars);
		checkShift(new Crypto(0), false, testChars);
	}

}
