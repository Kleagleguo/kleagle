package find_a_square;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {
	
	public static class Point {
		
		public int x;
		public int y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public double distanceTo(Point p) {
			return (x - p.x) * (x - p.x) + (y - p.y) * (y - p.y);
		}
		
		public double calculateSlopeWith(Point p) {
			return (y - p.y) / (x - p.x);
		}
		
		public Vector minus(Point p) {
			return new Vector(x-p.x, y-p.y);
		}
		
		public String toString() {
			return "(" + x + "," + y + ")";
		}
	}
	
	public static class Vector {
		public int x;
		public int y;
		
		public Vector(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public double length() {
			return x*x + y*y;
		}
		
		public double multiply(Vector p) {
			return (x*p.x + y*p.y);
		}
		
	}
	
	public static void main(String args[]) {
		File file = new File(args[0]);
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			while ((line = br.readLine()) != null) {
				//System.out.println(line);
				Point[] points = new Point[4];
				boolean isSquare = false;
				String[] lineArray = line.split("\\s");
				for (int i = 0; i < 4; i++) {
					//System.out.println(lineArray[i]);
//					System.out.println("char at 1 = " + lineArray[i].charAt(1));
//					System.out.println("char at 3 = " + lineArray[i].charAt(3));
					int x;
					int y;
					StringBuilder xx = new StringBuilder();
					StringBuilder yy = new StringBuilder();
					int index = 1;
					if (lineArray[i].charAt(1) == '-') {
						index = 2;
						while (Character.isDigit(lineArray[i].charAt(index))) {
							xx.append(lineArray[i].charAt(index));
							index++;
						}
						x = -Integer.parseInt(xx.toString());
						index++;
						if (lineArray[i].charAt(index) == '-') {
							index++;
							while (Character.isDigit(lineArray[i].charAt(index))) {
								yy.append(lineArray[i].charAt(index));
								index++;
							}
							y = -Integer.parseInt(yy.toString());
						} else {
							while (Character.isDigit(lineArray[i].charAt(index))) {
								yy.append(lineArray[i].charAt(index));
								index++;
							}
							y = Integer.parseInt(yy.toString());
						}
					} else {
						while (Character.isDigit(lineArray[i].charAt(index))) {
							xx.append(lineArray[i].charAt(index));
							index++;
						}
						x = Integer.parseInt(xx.toString());
						index++;
						if (lineArray[i].charAt(index) == '-') {
							index++;
							while (Character.isDigit(lineArray[i].charAt(index))) {
								yy.append(lineArray[i].charAt(index));
								index++;
							}
							y = -Integer.parseInt(yy.toString());
						} else {
							while (Character.isDigit(lineArray[i].charAt(index))) {
								yy.append(lineArray[i].charAt(index));
								index++;
							}
							y = Integer.parseInt(yy.toString());
						}
					}
			
					//System.out.println("x = " + x + " y = " + y);
					points[i] = new Point(x,y);
					
				}
				int a = -1;
				int b = -1;
				int c = -1;
				double distance = 0;
				for (int i = 1; i < 4; i++) {
					double newDistance = points[0].distanceTo(points[i]);
					if (newDistance > distance) {
						distance = newDistance;
						a = i;
					}
				}
				for (int i = 1; i < 4; i++) {
					if (i != a && b == -1) {
						b = i;
						continue;
					}
					if (i != a && b != -1 && c == -1) c = i;
				}
				//System.out.println(distance);
				Vector dia1 = points[0].minus(points[a]);
				//System.out.println("dia1= " + dia1.length());
				Vector dia2 = points[b].minus(points[c]);
				if (dia1.length() == dia2.length() && dia1.multiply(dia2) == 0) {
					isSquare = true;
				}
				System.out.println(isSquare);
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
