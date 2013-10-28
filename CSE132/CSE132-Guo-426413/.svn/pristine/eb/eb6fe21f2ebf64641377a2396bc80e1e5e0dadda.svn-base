package lecture10;

import java.net.*;
import java.io.*;

public class DayTime {
	
	public DayTime() {
		
		
	}
	
	public void run() throws Exception {
		Socket socket = new Socket("nist1-ny.ustiming.org", 13);
		Runnable a = new Adapter(socket.getInputStream(), System.out);
		a.run();
		socket.close();
	}
	
	public static void main(String[] args) throws Exception {
		new DayTime().run();
	}

}
