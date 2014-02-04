package uniqueElement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * You are given a sorted list of numbers with duplicates. 
 * Print out the sorted list with duplicates removed.
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
				String numbers[] = line.split(",");
				int last = Integer.parseInt(numbers[0]);
				System.out.print(last);
				for (int i = 1; i < numbers.length; i++) {
					int curr = Integer.parseInt(numbers[i]);
					if (curr != last) {
						System.out.print("," + curr);
						last = curr;
					}
				}
				System.out.println();
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
