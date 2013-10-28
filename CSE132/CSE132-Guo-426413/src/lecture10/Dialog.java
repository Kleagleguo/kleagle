package lecture10;

import java.net.*;
import java.io.*;

public class Dialog {
	
	final protected String ipaddr;
	final protected int port;
	
	public Dialog(String ipaddr, int port) {
		this.ipaddr = ipaddr;
		this.port = port;
		
	}
	
	public void run() throws Exception {
		Socket socket = new Socket(ipaddr, port);
		// sockets have both input and output
		// I don't to block waiting to read input from the socket
		//
		Runnable input = new Adapter(socket.getInputStream(), System.out);
		// so put the reading activity in a new thread and start that
		new Thread(input).start();
		
		// hook up the keyboard sender to write to the socket
		//
		Runnable output = new KeyboardSender(socket.getOutputStream());
		output.run();
		socket.close();
	}
	
	public static void main(String[] args) throws Exception {
		// new Dialog("www.google.com", 80).run();
		// new Dialog("nist1-ny.ustiming.org", 13).run();
		// new Dialog("www.wustl.edu", 80).run();
		new Dialog("freedb.org", 888).run();
		// new Dialog("mail.sbcglobal.net", 25).run();
	}

}
