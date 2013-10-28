package studio7.util;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ViewInputStream extends FilterInputStream {
	int tempInt = -2;

	public ViewInputStream(InputStream in) {
		super(in);
	}

	public int read() {
		try {
			tempInt= in.read();
			if(tempInt != -1){
				char ch = (char) tempInt;
				System.out.print("Hexadecimal "+Integer.toHexString(tempInt)+"  ");
				System.out.print("Integer "+tempInt+"  ");
				System.out.print("ASCII; "+ch+"\n"); 
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(tempInt == -1){
			System.out.print("end of file");
		}
		return tempInt;
	}

	public int getInt(){
		return tempInt;
	}

}
