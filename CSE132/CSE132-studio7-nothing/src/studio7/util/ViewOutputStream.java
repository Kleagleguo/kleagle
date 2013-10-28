package studio7.util;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ViewOutputStream extends FilterOutputStream {

    public ViewOutputStream(OutputStream out) {
        super(out);
        
    }

    public void write(int b) {
    	try {
			out.write(b);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

}
