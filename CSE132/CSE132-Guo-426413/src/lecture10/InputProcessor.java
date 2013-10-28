package lecture10;

import java.io.InputStream;
import java.io.OutputStream;

abstract public class InputProcessor implements Runnable {

	final protected InputStream in;

	public InputProcessor(InputStream in) {
		this.in = in;
	}
	
	abstract public void processByte(int b) throws Exception;

	public void run() {
		try {
			int b;
			while ((b=in.read()) != -1) {
				processByte(b);
			}
		} catch (Throwable t) {
			throw new Error("Adapter error " + t);
		}
	}

}
