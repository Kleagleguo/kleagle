package lecture10;

import java.io.FilterOutputStream;
import java.io.OutputStream;

public class ByteDumper extends FilterOutputStream {

	public ByteDumper() {
		super(System.out);
	}
	
	//
	// This method is called for *anything* that is written.  A larger message
	//    is broken into individual bytes and this method is called for
	//    each such byte.
	//
	@Override
	public void write(int b) {
		System.out.println("Writing byte value " + b + " which is char " + (char)b);
	}

}
