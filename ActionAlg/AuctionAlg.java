package auctionAlg;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AuctionAlg {
	
	public static ArrayList<ArrayList<Integer>> values;
	
	//public static int prices[];
	public static double prices[];
	
	public static Map<Integer, Integer> assignments;
	
	public static boolean assigned[];
	
	public static void main(String args[]) {
		
		try {
			assignments = new HashMap<Integer, Integer>();
			File file = new File(args[0]);
			BufferedReader br = new BufferedReader(new FileReader(file));
			values = new ArrayList<ArrayList<Integer>>();
			String line;
			int numOfAgent = 0;
			while ( (line = br.readLine()) != null) {
				String numbers[] = line.split("\\s+");
				//System.out.println(numbers.length);
				ArrayList<Integer> row = new ArrayList<Integer>();
				for (String n : numbers) {
					row.add(Integer.parseInt(n));
				}
				values.add(row);
				numOfAgent++;
			}
			
			double epsilon = 1.0 / (numOfAgent+1);
			assigned = new boolean[numOfAgent];
			//prices = new int[numOfAgent];
			prices = new double[numOfAgent];
			while (assignments.size() != numOfAgent) {
				for (int i = 0; i < numOfAgent; i++) {
					if (!assigned[i]) {
						int maxIndex = -1;
						double maxUtility = Integer.MIN_VALUE;
						for (int j = 0; j < numOfAgent; j++) {
							double utility = values.get(i).get(j) - prices[j];
							if (utility > maxUtility) {
								maxIndex = j;
								maxUtility = utility;
							}
						}
						
						double secondMaxUtility = Integer.MIN_VALUE;
						for (int k = 0; k < numOfAgent; k++) {
							if (k != maxIndex) {
								double utility = values.get(i).get(k) - prices[k];
								if (utility > secondMaxUtility) {
									secondMaxUtility = utility;
								}
							}
						}
						
						double bidIncrement = maxUtility - secondMaxUtility + epsilon;
						if (assignments.containsKey(maxIndex)) {
							assigned[assignments.get(maxIndex)] = false;
							assignments.remove(maxIndex);
						}
						prices[maxIndex] += bidIncrement;
						assignments.put(maxIndex, i);
						assigned[i] = true;
					}
				}
			}
			
			for (int key : assignments.keySet()) {
				System.out.println("(" + (assignments.get(key)+1) + "," + (key+1) + ")");
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}
