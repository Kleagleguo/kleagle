package reverseWords;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Write a program to reverse the words of an input sentence.
 * @author Bingkun
 *
 */
public class Main {

	public static String reverseWords(String string) {
		String words[] = string.split("\\s+");
		String reversed = "";
		for (int i = words.length-1; i >= 0; i--) {
			if (i == 0) {
				reversed += words[i];
			} else {
				reversed += words[i] + " ";
			}
		}
		return reversed;
	}
	
	public static void main(String args[]) {
		try {
			File file = new File(args[0]);
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			while ( (line = br.readLine()) != null) {
				System.out.println(reverseWords(line));
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
