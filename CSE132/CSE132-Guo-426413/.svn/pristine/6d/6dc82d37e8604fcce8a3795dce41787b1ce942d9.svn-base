package lecture10;

import java.io.InputStream;
import java.io.OutputStream;

public class ForkStream extends InputProcessor {

	final protected OutputStream out1, out2;
	public ForkStream(InputStream in, OutputStream out1, OutputStream out2) {
		super(in);
		this.out1 = out1;
		this.out2 = out2;
	}

	@Override
	public void processByte(int b) throws Exception {
		out1.write(b);
		out2.write(b);
	}

}
