package sumOfDigits;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {
	
	public static int sumOfDigits(String n) {
		int sum = 0;
		for (int i = 0; i < n.length(); i++) {
			sum += Character.getNumericValue(n.charAt(i));
		}
		return sum;
	}
	
	public static void main(String args[]) {
		try {
			File file = new File(args[0]);
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			while ( (line = br.readLine()) != null) {
				System.out.println(sumOfDigits(line));
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
