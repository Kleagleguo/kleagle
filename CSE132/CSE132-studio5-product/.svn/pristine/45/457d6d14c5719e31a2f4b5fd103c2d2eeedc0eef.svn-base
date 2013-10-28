package lecture.series;

public class T2 implements Runnable {

	private ValueCatcher catcher;
	
	public T2(ArithmeticSeries s) {
		catcher = new ValueCatcher(s, 5);
	}
	
	public void run() {
		while (true) {
			catcher.findNext();
		}
	}

}
