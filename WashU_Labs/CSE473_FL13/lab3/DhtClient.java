/** 
 * Name: Bingkun Guo
 * Date: Oct/15/2013
 * 
 * Explanation: This is a client for use with distributed hash table 
 * that stores (key,value) strings. The class will simply send a 
 * command to a remote server and wait for reply and exit.
 * 
 * Usage: DhtClient myIp cfgFile operation [key] [value]
 * 
 * myIp: The ip address that the client should bind to its own datagram
 * cfgFile: the name of configuration file containing the ip address 
 * and port number used by a DhtServer.
 * 
 * opeartion: The opeartion for the request("get" or "put")
 * 
 * key: (Optional) The key for that operation
 * 
 * value: (Optional) The value for a "put" operation
 * 
 * The DHT uses UDP packets containing ASCII text. Here's an example of the
 * UDP payload for a get request from a client.
 *  
 *  CSE473 DHTPv0.1
 *  type:get
 *  key:dungeons
 *  tag:12345
 *  ttl:100
 *  
 *  The first line is just an identifying string that is required in every
 *  DHT packet. The remaining lines all start with a keyword and :, usually
 *  followed by some additional text. Here, the type field specifies that
 *  this is a get request; the key field specifies the key to be looked up;
 *  the tag is a client-specified tag that is returned in the response; and
 *  can be used by the client to match responses with requests; the ttl is
 *  decremented by every DhtServer and if <0, causes the packet to be discarded.
 *
 */

import java.io.*;
import java.net.*;
import java.util.*;

public class DhtClient {
	public static void main(String args[]) {
		if (args.length < 3) {
			System.err.println("usage: DhtClient myIp " +
						"cfgFile opeartion key value");
			System.exit(1);
		}
		try {
			// assgin command-line arguments to local variables 
			InetAddress myIp = InetAddress.getByName(args[0]);
			DatagramSocket sock = new DatagramSocket(0, myIp);
			String cfg = args[1];
			String operation = args[2];
			String key = null;  String val = null;
			if (args.length > 3) key = args[3];
			if (args.length > 4) val = args[4];

			InetSocketAddress server = null;
			
			// read cfgFile
			BufferedReader br = new BufferedReader(
									new InputStreamReader(
										new FileInputStream(cfg),"US-ASCII"));
			String line = br.readLine();
			String chunks[] = line.split(" ");
			// get the ip address and port number for a remote server
			server = new InetSocketAddress(chunks[0], Integer.parseInt(chunks[1]));
			
			// send a request to the remote server
			Packet request = new Packet();
			request.type = operation;
			request.tag = 12345;
			request.key = key;
			request.val = val;
			request.send(sock, server, true);

			// wait for reply and exit
			Packet reply = new Packet();
			reply.receive(sock, true);

		} catch (Exception e) {
			System.err.println("usage: DhtClient myIp " +
						"cfgFile opeartion key value");
			System.exit(1);
		}
	}
}