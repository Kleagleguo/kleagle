package setIntersection;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {
	public static void main(String args[]) {
		try {
			File file = new File(args[0]);
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			while ( (line = br.readLine()) != null) {
				String sets[] = line.split(";");
				Set<Integer> left = new HashSet<Integer>();
				Set<Integer> right = new HashSet<Integer>();
				
				for (String l : sets[0].split(",")) {
					left.add(Integer.parseInt(l));
				}
				for (String r : sets[1].split(",")) {
					right.add(Integer.parseInt(r));
				}
				
				left.retainAll(right);
				if (left.size() == 1) {
					System.out.print(left.toArray()[0]);
				} else {
					boolean first = true;
					Integer arr[] = Arrays.copyOf(left.toArray(), left.toArray().length, Integer[].class);
					Arrays.sort(arr);
					for (int k = 0; k < arr.length; k++) {
						if (first) {
							System.out.print(arr[k]);
							first = false;
						} else {
							System.out.print("," + arr[k]);
						}
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
