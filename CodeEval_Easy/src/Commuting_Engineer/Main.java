package Commuting_Engineer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Main {
	
	public static ArrayList<ArrayList<Integer>> permutations;
	
	public static class Point {
		public double x; // latitude
		public double y; // longitude
		int index;
		
		public Point(int index, double x, double y) {
			this.index = index;
			this.x = x;
			this.y = y;
		}
		
		public double distanceTo(Point p) {
		    double lat1 = Math.toRadians(x);
		    double lng1 = Math.toRadians(y);
		    double lat2 = Math.toRadians(p.x);
		    double lng2 = Math.toRadians(p.y);

		    double dlon = lng2 - lng1;
		    double dlat = lat2 - lat1;

		    double a = Math.pow((Math.sin(dlat/2)),2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon/2),2);

		    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

		    return 6400 * c;
		}
		
		public String toString() {
			return index + " (" + x + "," + y + ")";
		}
	}
	
	public  static void permutation(ArrayList<Integer> sequence) { 
	    permutation(new ArrayList<Integer>(), sequence); 
	}

	public static void permutation(ArrayList<Integer> prefix, ArrayList<Integer> str) {
	    int n = str.size();
	    if (n == 0) { 
	    	permutations.add(prefix);
	    }
	    else {
	        for (int i = 0; i < n; i++) {
				ArrayList<Integer> newPrefix = new ArrayList<Integer>(); 
	        	ArrayList<Integer> newStr = new ArrayList<Integer>();
	        	newPrefix.addAll(prefix);
	        	newPrefix.add(str.get(i));
	        	newStr.addAll(str.subList(0, i));
	        	newStr.addAll(str.subList(i+1, n));
	            permutation(newPrefix, newStr); 
	        }
	    }
	}
	
	public static void main(String args[]) {
		permutations = new ArrayList<ArrayList<Integer>>();
		File file = new File(args[0]);
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			ArrayList<Point> locations = new ArrayList<Point>();

			//
			// process input
			//
			while ((line = br.readLine()) != null) {
				StringBuilder readIndex = new StringBuilder();
				StringBuilder readXCoor = new StringBuilder();
				StringBuilder readYCoor = new StringBuilder();
				int j = 0;
				while (line.charAt(j) != ' ') {
					readIndex.append(line.charAt(j));
					j++;
				}
				for (int i = j; i < line.length(); i++) {
					if (line.charAt(i) == '(') {
						i++;
						while (line.charAt(i) != ',') {
							readXCoor.append(line.charAt(i));
							i++;
						}
						i++;
						while (line.charAt(i) != ')') {
							readYCoor.append(line.charAt(i));
							i++;
						}
						int index = Integer.parseInt(readIndex.toString());
						double x = Double.parseDouble(readXCoor.toString());
						double y = Double.parseDouble(readYCoor.toString());
						locations.add(new Point(index, x, y));
						break;
					}
				}	
			}
			
			ArrayList<Integer> sequence = new ArrayList<Integer>();
			for (int i = 1; i < locations.size(); i++) {
				sequence.add(i);
			}
			permutation(sequence);
						
			int shortestIndex = -1;
			double shortestDist = Double.POSITIVE_INFINITY;
			
			
			for (int i = 0; i < permutations.size(); i++) {
				double totalDist = 0;
				for (int j = 0; j < permutations.get(i).size()-1; j++) {
					totalDist += locations.get(permutations.get(i).get(j)).distanceTo(locations.get(permutations.get(i).get(j+1)));
				}
				
				totalDist += locations.get(0).distanceTo(locations.get(permutations.get(i).get(0)));
			
				if (totalDist < shortestDist) {
					shortestDist = totalDist;
					shortestIndex = i;
				}
			}
			
			System.out.println(1);
			for (int i = 0; i < permutations.get(shortestIndex).size(); i++) {
				System.out.println(permutations.get(shortestIndex).get(i) + 1);
			}

			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
