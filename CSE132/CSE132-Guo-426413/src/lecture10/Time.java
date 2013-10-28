package lecture10;

import java.net.*;
import java.io.*;

public class Time {
	
	public Time() {
		
	}
	
	public void run() throws Exception {
		Socket socket = new Socket("nist1-ny.ustiming.org", 37);
		InputStream input = socket.getInputStream();
		int b;
		//
		// The syntax below is handy in that it
		//    assigns b the result of input.read()
		//    and that value is then compared against -1
		//
		// code below builds a 32 bit number from 4 bytes,but
		//  the result is negative because so much time has passed
		//   since Jan 1 1900.
		//
//		DataInputStream dis = new DataInputStream(input);
//		int tod = dis.readInt();
//		System.out.print("time of day in secs is " + tod);
		long biggie = 0;
		while ((b=input.read()) != -1) {
			System.out.println("saw  " + b);
			biggie = biggie * 256 + b;
		}
		System.out.println("biggie is " + biggie);
		socket.close();
	}
	
	public static void main(String[] args) throws Exception {
		new Time().run();
	}

}
