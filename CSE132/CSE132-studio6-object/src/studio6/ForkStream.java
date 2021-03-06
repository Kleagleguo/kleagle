package studio6;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ForkStream {

	private InputStream input;
	private OutputStream output1, output2;
	private Crypto crypto;

	public ForkStream(InputStream input, OutputStream output1, OutputStream output2) {
		this.input = input;
		this.output1 = output1;
		this.output2 = output2;
		crypto = new Crypto(0);
	}

	public void run() {

		int got = 0;
		try {
			got = input.read();
			//System.out.println("got = " + got);
			while (got != -1) {
				//System.out.println("got " + got + " which is " + (char)got);
				output1.write(got);
				got = crypto.encrypt( (char) got );
				System.out.println("");
				output2.write(got);
				got = input.read(); 
			} 
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				output1.flush();
				output1.close();
				output2.flush();
				output2.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {
		ForkStream io = new ForkStream(new Preamble().getInputStream(20), System.out, System.out);
		io.run();
	}


}
