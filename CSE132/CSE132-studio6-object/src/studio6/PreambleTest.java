package studio6;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;


public class PreambleTest {
	
	@Test
	public void test() throws IOException {
		Preamble preamble = new Preamble();
		InputStream stream  = preamble.getInputStream(10);
		int next = 0;
		String ans = "";
		while ((next=stream.read()) != -1) {
			System.out.println("saw \t" + next + " \twhich is " + (char)next);
			ans += (char)next;
		}
		System.out.println("String:");
		System.out.println(ans);
		
	}

}
