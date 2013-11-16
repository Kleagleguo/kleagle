import java.io.*;
import java.net.*;

public class UdpEchoServer {
	public static void main(String args[]) throws Exception {

		InetAddress myIp = null; int port = 30123;
		if (args.length > 0) myIp = InetAddress.getByName(args[0]);
		if (args.length > 1) port = Integer.parseInt(args[1]);

		DatagramSocket sock = new DatagramSocket(port,myIp);

		// create two packets sharing a common buffer
		byte[] buf = new byte[1000];
		DatagramPacket pkt = new DatagramPacket(buf, buf.length);

		while (true) {
			// wait for incoming packet
			pkt.setData(buf);
			sock.receive(pkt);
			System.out.println("getdata length = " + pkt.getData().length);
			System.out.println("pkt.getLength = " + pkt.getLength());
			byte[] receivedData = pkt.getData();
			String decoded = new String(receivedData, 0, pkt.getLength(), "US-ASCII");
			decoded = decoded.toUpperCase();
			receivedData = decoded.getBytes("US-ASCII");
			System.out.println("sending " + decoded);
			pkt.setData(receivedData);
			// and send it back
			sock.send(pkt);
		}
	}
}
