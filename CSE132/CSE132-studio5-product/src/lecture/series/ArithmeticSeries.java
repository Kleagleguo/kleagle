package lecture.series;

import java.util.Random;

public class ArithmeticSeries {
	
	final private int start, diff;
	private int curr, i;

	public ArithmeticSeries(int start, int diff) {
		this.start = start;
		this.diff  = diff;
		this.curr  = start;
		this.i     = 0;
	}
	
	public synchronized ArithmeticSeries advance() {
		// Sleeper.randomSleep(2000);
		curr = curr + diff;
		i = i + 1;
		this.notifyAll();
		return this;
	}
	
	public int getValue() {
		return curr;
	}
	
	public String toString() {
		return "Arithmetic series: " + curr + " = " + start + " + " + i + " x " + diff;
	}
	
	public static void main(String[] args) {
		Random r = new Random();
		int start = r.nextInt(10);
		int diff  = r.nextInt(10);
		ArithmeticSeries s =new ArithmeticSeries(start, diff);
		System.out.println(s);
		for (int i=0; i < 10; ++i) {
			System.out.println(s.advance());
			// s.advance();
		}
	}

}
