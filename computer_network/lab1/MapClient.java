import java.io.*;
import java.net.*;

/**
 * Name: Bingkun Guo
 * Date: 07/Sep/2013
 * Explanation: This class represents a client. 
 * It will first collect the command line arguments
 * given by the user and format the payload with
 * these command line arguments using ":" delimiter. 
 * Then send the packet to the corresponding server. 
 *  Here are the two operations:
 * get(k): returns the value part of the pair whose key is k
 * put(k,v): adds the pair (k,v) to the set, possibly replacing some other pair (k,x).
 * put(k): delete the key-value pair where key is k
 *
 * Example of messages sent from the client: 
 * (1). get:this is the key string 
 * After receiving this message, the server should return the value whose 
 * key is "this is the key string".
 *
 * (2). put:another key string:and the corresponding value
 * After receiving this message, the server should add key value pair 
 * ("another key string", "and the corresponding value")
 *  
 * Example of messages sent from the server:
 * (1). ok:this is the value string
 * If the key-value pair exists, return the value for that key, 
 * in this case, the value is "this is the value string"  
 *
 * (2). no match
 * If the key-value pair doesn't exist, return "no match"
 * 
 * (3) error: unrecognizable input: put a copy of the input packet’s payload here
 * If the request has a wrong format which the server doesn't recognize, then
 * send a erro message.
 *
**/

public class MapClient {
	public static void main(String args[]) throws Exception {
		// get server address using first command-line argument
		InetAddress serverAdr = InetAddress.getByName(args[0]);
		// get server port number
		int port = Integer.parseInt(args[1]);
		// get operation
		String operation = args[2];

		// open datagram socket
		DatagramSocket sock = new DatagramSocket();

		// build packet addressed to server containing second argument,
		// encoded using US-ASCII Charset, then send it
		String message = operation + ":";
		
		for (int i = 3; i < args.length; i++) {
			if (i != args.length - 1) {
				message += args[i] + ":";
			} else {
				message += args[i];
			}
		}

		byte[] outBuf = message.getBytes("US-ASCII");
		
		DatagramPacket outPkt = new DatagramPacket(outBuf,outBuf.length,
							   serverAdr,port);
		sock.send(outPkt);	// send packet to server

		// create buffer and packet for reply, then receive it
		byte[] inBuf = new byte[1000];
		DatagramPacket inPkt = new DatagramPacket(inBuf,inBuf.length);

		sock.receive(inPkt);	// wait for reply

		// print buffer contents and close socket
		String reply = new String(inBuf,0,inPkt.getLength(),"US-ASCII");
		System.out.println(reply);
		sock.close();
	}
}
