/**
 * Name: Bingkun Guo
 * Date: 30/Sep/2013
 * Explanation and Protocol Document: This class represents a 
 * Tcp MapServer, it holds a HashMap which stores (key, value) pairs.
 * When the server the server is running, it will be listening on a specific
 * port number, waiting for the client's connection. Once connected, a socket
 * will be created to communicate with the client. The server will respond the
 * messages sent by the server based on the protocol.
 * 
 * Usage: java TcpMapServer [IP address] [Port Number]
 * The server should take two optional command line arguments. The first is the 
 * IP address to which the server's socket is to be bound. If this is omitted, 
 * the server should use the wildcard address. The second argument is a port number. 
 * If no port number is specified, the server should use port 30123. 
 * If the port number is specified, the IP address must also be specified.
 * 
 * Here are the types of operation:
 * get(k): returns the value part of the pair whose key is k
 * put(k,v): adds the pair (k,v) to the set, possibly replacing some other pair (k,x).
 * put(k): delete the key-value pair where key is k
 * get all: The get all command requests all the key-value pairs stored in the map. 
 * All pairs are returned on a single line, with each key-value pair separated by 
 * a pair of colons.
 * 
 * * Example of messages sent from the client: 
 * (1) get:this is the key string 
 * After receiving this message, the server should return the value whose 
 * key is "this is the key string".
 *
 * (2) put:another key string:and the corresponding value
 * After receiving this message, the server should add key value pair 
 * ("another key string", "and the corresponding value")
 * 
 * (3) put:another key
 * After receiving this message, the server should remove the key value pair
 * with key equal to "another key"
 * 
 * (4) get all
 * After receiving this message, the server will return all the key value pairs
 * on a single line, with each key-value pair separated by a pair of colons. 
 *  
 * Example of messages sent from the server:
 * (1) ok:this is the value string
 * If the key-value pair exists, return the value for that key, 
 * in this case, the value is "this is the value string"  
 *
 * (2) no match
 * If the key-value pair doesn't exist, return "no match"
 * 
 * (3) error: unrecognizable input: put a copy of the input packets payload here
 * If the request has a wrong format which the server doesn't recognize, then
 * send a erro message.
 * 
 * (4) ok
 * If the put command has been successfully executed, the server will respond ok
 * 
 * (5) key:lock::major key:bee flat::public key:flabber jabber heehaw
 * After receiving command "get all". The server respond all the key value 
 * pairs on a single line, with each key-value pair separated by a pair of colons. 
 * 
 */

import java.io.*;
import java.net.*;
import java.util.*;

public class TcpMapServer {

	public static void main(String args[]) throws Exception {
		// create a empty key-value pairs hash map
		HashMap<String,String> pairs = new HashMap<String,String>();
		// set default port number to 30123
		int port = 30123;
		InetAddress servAddr = null;
		// if provided more than 2 command line arguments 
		// must specify the IP address and port number
		if (args.length == 2) {
			servAddr = InetAddress.getByName(args[0]);
			port = Integer.parseInt(args[1]);
		} else if (args.length == 1) {
			// set the IP address to the given one
			servAddr = InetAddress.getByName(args[0]);
		}
		
		// create a listening socket on the "port" and "servAddr"
		ServerSocket listenSocket = new ServerSocket(port, 0, servAddr);

		while (true) {
			// keep listening until a client establishes a TCP connection
			// the connSock will be used to communicate with the client
			Socket connSock = listenSocket.accept();
			BufferedReader in = new BufferedReader(new InputStreamReader(
								connSock.getInputStream(), "US-ASCII"));
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
								connSock.getOutputStream(), "US-ASCII"));

			while (true) {
				String line = in.readLine();
				// When the message is empty or a blank line,
				// close the connection
				if (line == null || line.length() == 0) break;
				// split the message by setting ":" delimiter
				String splitedMsg[] = line.split(":");
				// get the operation command 
				String operation = splitedMsg[0];
				String key;
				String value;
				String reply = "";

				if (operation.equals("get") && splitedMsg.length == 2) {
					// if the operation is get(),
					// return the value of that key
					key = splitedMsg[1];
					if (pairs.get(key) != null) {
						reply = "ok:" + pairs.get(key);
					} else {
						reply = "no match";
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
						reply = "error:unrecognizable input:" + line;
					}
				} else if (operation.equals("get all")) {
					// if the operation is "get all"
					// send back all the key-value pairs 
					String keyArr[] = pairs.keySet().toArray(new String[0]);
					for (int i = 0; i < keyArr.length; i++) {
						if (i != keyArr.length - 1) {
							reply += keyArr[i] + ":" + pairs.get(keyArr[i]) + "::";
						} else {
							reply += keyArr[i] + ":" + pairs.get(keyArr[i]);
						}
					}
				} else {
					reply = "error:unrecognizable input:" + line;
				}
				// send back the reply to the client
				out.write(reply);
				// send newline indication
				out.newLine();
				// flush the buffer
				out.flush();
			}
			// close the connection
			connSock.close();
		}
	}
}
