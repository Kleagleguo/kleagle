package lecture.series;

public class Main {
	
	public static void main(String[] args) {
		ArithmeticSeries series = new ArithmeticSeries(0,7);
		new Thread(new T1(series)).start();
		new Thread(new T2(series)).start();
	}

}
