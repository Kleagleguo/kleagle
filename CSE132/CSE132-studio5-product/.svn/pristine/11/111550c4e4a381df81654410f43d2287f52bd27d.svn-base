package lecture.series;

public class ValueCatcher {
	private final ArithmeticSeries series;
	private final int mult;
	public ValueCatcher(ArithmeticSeries s, int mult) {
		this.series = s;
		this.mult   = mult;
	}

	public void findNext() {
		synchronized(series) {
			while (series.getValue() % mult != 0) {
				// Sleeper.randomSleep(2000);
				//  spin and wait
				try {
					series.wait();
				} catch (InterruptedException e) {
					// FIXME Auto-generated catch block
					e.printStackTrace();
				}
			}
			// Sleeper.randomSleep(2000);
			System.out.println("Aha, found multple of " + mult + ": " + series);
		}

	}

}
