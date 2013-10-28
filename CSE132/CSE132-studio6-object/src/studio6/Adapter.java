package studio6;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Adapter {

	private InputStream input;
	private OutputStream output;

	public Adapter(InputStream input, OutputStream output) {
		this.input = input;
		this.output = output;
	}

	public void run() {

		int got = 0;
		try {
			got = input.read();
			//System.out.println("got = " + got);
			while (got != -1) {
				//System.out.println("got " + got + " which is " + (char)got);
				output.write(got);
				got = input.read(); 
			} 
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				output.flush();
				output.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {
		Adapter io = new Adapter(new Preamble().getInputStream(20), System.out);
		io.run();
	}

}
