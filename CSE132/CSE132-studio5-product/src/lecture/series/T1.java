package lecture.series;

public class T1 implements Runnable {

	private ArithmeticSeries series;
	public T1(ArithmeticSeries series) {
		this.series = series;
		
	}
	
	public void run() {
		while (true) {
			series.advance();
		}
	}
}
