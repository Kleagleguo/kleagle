package lecture.series;

import java.util.Random;

public class Sleeper {
	
	private static Random r = new Random();
	
	public static void randomSleep(int ms) {
		sleep(r.nextInt(ms));
	}
	public static void sleep(int ms) {
		try {
			Thread.sleep(ms);
		}
		catch(Throwable t) {
			throw new Error("cannot happen");
		}
	}

}
