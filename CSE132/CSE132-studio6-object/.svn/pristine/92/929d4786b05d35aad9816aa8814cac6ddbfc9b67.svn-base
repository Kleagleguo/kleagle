package lecture;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Google {

	public Google() {
	}

	public void run() {

		try {

			// How do I connect to a site whose address I know, 
			// for example www.google.com
			InetAddress addr = InetAddress.getByName("www.google.com");
			System.out.println("I got " + addr);

			// addr is now a host to which I can connect a Socket

			final Socket socket = new Socket(addr, 80);
			System.out.println("Socket is " + socket);
			//  connect to port 80 of www.google.com
			//   because I know that's where the web server is

			// tell Google to send me its home page
			String request = "GET /\n";
			OutputStream output = socket.getOutputStream();


			// make a new Thread to do the reading, so we don't
			// wait for the read and write at the same time

			new Thread() {
				public void run() {
					try {
						// read all I can from the socket
						InputStream input = socket.getInputStream();

						int got;
						got = input.read();
						while (got != -1) {
							System.out.println("got " + got + " which is " + (char)got);
							got = input.read();
						}
					} catch(Throwable t) { System.exit(-1); }
				}
			}.start();

			// The code below runs in the original thread
			//   so it executes concurrently with the code
			//   reading from the socket.
			output.write(request.getBytes());

		} catch(Throwable t) {
			throw new Error("bad thing: " + t);
		}

	}



	public static void main(String[] args) {

		Google g = new Google();
		g.run();

	}

}
