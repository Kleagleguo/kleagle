package lecture10;

import java.io.InputStream;
import java.io.OutputStream;

public class Adapter extends InputProcessor {

	final protected OutputStream out;
	public Adapter(InputStream in, OutputStream out) {
		super(in);
		this.out = out;
	}

	@Override
	public void processByte(int b) throws Exception {
		out.write(b);
	}

}
