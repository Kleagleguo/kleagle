import java.io.*;
import java.net.*;
import java.util.HashMap; 

/**
 * Name: Bingkun Guo
 * Date: 07/Sep/2013
 * Explanation and Protocal Document: This class represents a MapServer,
 * it holds a HashMap which stores (key,value) pairs.
 * When the server receivs a packet message from a client, 
 * it will decompose the packet message by setting a ":"
 * delimiter, then the server will behave according
 * to the opeartion the client requests.
 * Here are the two operations:
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

public class MapServer {

	public static void main(String args[]) throws Exception {
		HashMap<String,String> pairs = new HashMap<String,String>();
		int port = 30123;

		// change the port number if the user provides
		if (args.length > 0) port = Integer.parseInt(args[0]);

		DatagramSocket sock = new DatagramSocket(port);

		// create two packets sharing a common buffer
		byte[] buf = new byte[1000];
		DatagramPacket pkt = new DatagramPacket(buf, buf.length);

		while (true) {

			// reset the packet data to make sure it has 1000 length
			buf = new byte[1000];
			pkt.setData(buf);
			// wait for incoming packet
			sock.receive(pkt);

			// retrieve the packet data
			String message = new String(pkt.getData(), 0, pkt.getLength(), "US-ASCII");
			
			// split the message by setting ":" delimiter
			String[] splitedMsg = message.split(":");
			
			String reply = "";
		 	String operation = splitedMsg[0];
		 	
		 	// assign key, value after we're sure that the array has enough length
		 	String key;
		 	String value;

		 	if (operation.equals("get") && splitedMsg.length == 2) {
		 		key = splitedMsg[1];
		 		// if the operation is get(),
		 		// return the value of that key
		 		if (pairs.get(key) != null) {
		 			reply = "ok:" + pairs.get(key);
		 		} else {
		 			reply ="no match";
		 		}
		 	} else if (operation.equals("put")) {
		 		// if the operation is put(), 
		 		// add a key pair or delete a key pair
		 		if (splitedMsg.length == 2) {
		 			key = splitedMsg[1];
		 			if (pairs.keySet().contains(key)) {
		 				pairs.remove(key);
		 			}
		 			reply = "ok";
		 		} else if (splitedMsg.length == 3) {
		 			key = splitedMsg[1];
		 			value = splitedMsg[2];
		 			pairs.put(key, value);
		 			reply = "ok";
		 		} else {
		 			reply = "error:unrecognizable input:" + message;
		 		}
		 	} else {
		 		reply = "error:unrecognizable input:" + message;
		 	}

		 	pkt.setData(reply.getBytes("US-ASCII"));
			// and send it back
			sock.send(pkt);
		}
	}
}
