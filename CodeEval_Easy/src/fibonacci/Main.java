package fibonacci;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * The Fibonacci series is defined as: F(0) = 0; F(1) = 1; 
 * F(n) = F(n-1) + F(n-2) when n>1. Given a positive integer 'n', print out the F(n).
 * @author Bingkun
 *
 */
public class Main {
	
	public static Map<Integer, Integer> fibs = new HashMap<Integer, Integer>();
	
	public static int fibonacci(int n) {
		if (n == 0) {
			return 0;
		}
		if (n == 1) {
			return 1;
		}
		if (fibs.containsKey(n)) {
			return fibs.get(n);
		}
		int result = fibonacci(n-1) + fibonacci(n-2);
		fibs.put(n, result);
 		return result;
	}
	
	public static void main(String args[]) {
		fibs.put(0,0);
		fibs.put(1,1);
		try {
			File file = new File(args[0]);
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			while ( (line = br.readLine()) != null) {
				int n = Integer.parseInt(line);
				System.out.println(fibonacci(n));
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
