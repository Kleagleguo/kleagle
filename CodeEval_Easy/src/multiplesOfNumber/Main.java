package multiplesOfNumber;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Given numbers x and n, where n is a power of 2, print out the smallest 
 * multiple of n which is greater than or equal to x. Do not use division 
 * or modulo operator.
 * @author Bingkun
 *
 */
public class Main {
	
	public static void main(String args[]) {
		try {
			File file = new File(args[0]);
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			while ( (line = br.readLine()) != null) {
				String words[] = line.split(",");
				int left = Integer.parseInt(words[0]);
				int right = Integer.parseInt(words[1]);
				int i = 2;
				while (left > i * right) {
					i++;
				}
				System.out.println(right*i);
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		//System.out.println(Math.pow(2, 3));
	}
}
