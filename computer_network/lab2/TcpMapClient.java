/**
 * Name: Bingkun Guo
 * Date: 30/Sep/2013
 * Explanation: This class represents a Tcp MapClient.
 * After this client is running, a socket will be created to 
 * establish a TCP connection with the server. Then it will take the 
 * inputs from the user by System.in, and then send the user's command to 
 * the server, and then receive and output the server's response.
 * 
 * Usage: java TcpMapClient [IP address] [Port Number]
 * The client program should take from 1-2 arguments. The first argument 
 * is the name or IP address of the host that the server is running 
 * on and the second (if present) is the port number on which it's listening. 
 * The second argument defaults to 30123.
 * 
 * Here are the types of operation users should enter:
 * get(k): returns the value part of the pair whose key is k
 * put(k,v): adds the pair (k,v) to the set, possibly replacing some other pair (k,x).
 * put(k): delete the key-value pair where key is k
 * get all: get all the key-value pairs
 *
 * Example of messages sent from the client: 
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

public class TcpMapClient {
	
	public static void main(String args[]) throws Exception {
		// default port number is 30123
		int port = 30123;      
		// get server address using first command-line argument
		InetAddress addr = InetAddress.getByName(args[0]);
		// if provided, update the new port number
		if (args.length > 1) port = Integer.parseInt(args[1]);
		// bind the new socket to the server
		Socket sock = new Socket(addr, port);
	
		BufferedReader in = new BufferedReader(new InputStreamReader(
							sock.getInputStream(), "US-ASCII"));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
							 sock.getOutputStream(), "US-ASCII"));
		
		// use sysin to read inputs from the user
		BufferedReader sysin = new BufferedReader(new InputStreamReader(
							   System.in));

		String line;
		// while the users doesn't enter a blank line, the client is active
		// to collect inputs from the user
		while (true) {
			line = sysin.readLine();
			// if the user enters a blank line, close the connection
			if (line == null || line.length() == 0) break;
			// send the inputs from the user
			out.write(line);
			// send a newline indication
			out.newLine();
			// flush the buffer
			out.flush();
			// read the reply from the server and then output it
			String reply = in.readLine();
			System.out.println(reply);
		}
		// close the connection
		sock.close();
	}
}
