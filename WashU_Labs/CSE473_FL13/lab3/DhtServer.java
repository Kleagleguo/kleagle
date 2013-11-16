/** 
 * 	Name: Bingkun Guo
 * 	Date: Oct/15/2013
 * 
 * 	Explanation: This is a server for simple distributed hash table that 
 * 	stores (key,value) strings.
 *  
 *  usage: DhtServer myIp numRoutes cfgFile [ cache ] [ debug ] [ predFile ]
 *  
 *  myIp	is the IP address to use for this server's socket
 *  numRoutes	is the max number of nodes allowed in the DHT's routing table;
 *  		typically lg(numNodes)
 *  cfgFile	is the name of a file in which the server writes the IP
 *		address and port number of its socket
 *  cache	is an optional argument; if present it is the literal string
 *		"cache"; when cache is present, the caching feature of the
 *		server is enabled; otherwise it is not
 *  debug	is an optional argument; if present it is the literal string
 *		"debug"; when debug is present, a copy of every packet received
 *		and sent is printed on stdout
 *  predFile	is an optional argument specifying the configuration file of
 *		this node's predecessor in the DHT; this file is used to obtain
 *		the IP address and port number of the precessor's socket,
 *		allowing this node to join the DHT by contacting predecessor
 *  
 *  The DHT uses UDP packets containing ASCII text. Here's an example of the
 *  UDP payload for a get request from a client.
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
 *  Possible responses to the above request include:
 *  
 *  CSE473 DHTPv0.1
 *  type:success
 *  key:dungeons
 *  value:dragons
 *  tag:12345
 *  ttl:95
 *  
 *  or
 *  
 *  CSE473 DHTPv0.1
 *  type:no match
 *  key:dungeons
 *  tag:12345
 *  ttl:95
 *  
 *  Put requests are formatted similarly, but in this case the client typically
 *  specifies a value field (omitting the value field causes the pair with the
 *  specified key to be removed).
 *  
 *  The packet type "failure" is used to indicate an error of some sort;
 *  in this case, the "reason" field provides an explanation of the failure.
 *  Other packet types are listed below. The "join" type is used by a server
 *  to join an existing DHT. The "transfer" is used to transfer (key,value)
 *  pairs to a newly added server.
 *  
 *  Other fields and their use are described briefly below
 *  
 *  clientAdr 	is used to specify the IP address and port number of the client
 *  	  	that sent a particular request; it is added to a request packet
 *  	  	by the first server to receive the request, before forwarding
 *  	  	the packet to another node in the DHT; an example of the format
 *  	  	is 123.45.67.89:51349
 *  relayAdr  	is used to specify the IP address and port number of the first
 *  	  	server to receive a request packet from the client; it is added
 *  	  	to the packet by the first server before forwarding the packet
 *  hashRange 	is a pair of integers separated by a colon, specifying a range
 *  	  	of hash indices; it is included in the response to a "join"
 *  	  	packet, to inform the new DHT server of the set of nodes
 *  	  	it is responsible for
 *  succInfo  	is the IP address and port number of a server, followed by its
 *  	  	first hash index; this information is included in the response
 *  	  	to a join packet to inform the new DHT server about its
 *		immediate successor; an example of the format is
 *		123.45.6.7:5678:987654321
 *  senderInfo 	is the IP address and port number of a DHT server, followed by
 *  	  	its first hash index; this information is sent by a DHT to 
 *  	  	provide routing information that can be used by another
 */

import java.io.*;
import java.net.*;
import java.util.*;

public class DhtServer {
	private static int numRoutes;	// number of routes in routing table
	private static boolean cacheOn;	// enables caching when true
	private static boolean debug;	// enables debug messages when true

	private static HashMap<String,String> map;	// key/value pairs
	private static HashMap<String,String> cache;	// cached pairs
	private static List<Pair<InetSocketAddress,Integer>> rteTbl;

	private static DatagramSocket sock;
	private static InetSocketAddress myAdr;
	private static InetSocketAddress predecessor; // DHT predecessor
	private static Pair<InetSocketAddress,Integer> succInfo; // successor
	private static Pair<Integer,Integer> hashRange; // my DHT hash range
	private static int sendTag;		// tag for new outgoing packets

	/** Main method for DHT server.
	 *  Processes command line arguments, initializes data, joins DHT,
	 *  then starts processing requests from clients.
	 */
	public static void main(String[] args) {
		// process command-line arguments
		if (args.length < 3) {
			System.err.println("usage: DhtServer myIp numRoutes " +
					   "cfgFile [debug] [ predFile ] ");
			System.exit(1);
		}
		numRoutes = Integer.parseInt(args[1]);
		String cfgFile = args[2];
		cacheOn = debug = false;
		String predFile = null;
		for (int i = 3; i < args.length; i++) {
			if (args[i].equals("cache")) cacheOn = true;
			else if (args[i].equals("debug")) debug = true;
			else predFile = args[i];
		}
		
		// open socket for receiving packets
		// write ip and port to config file
		// read predecessor's ip/port from predFile (if there is one)
		InetAddress myIp = null; sock = null; predecessor = null;
		try {	
			myIp = InetAddress.getByName(args[0]);
			sock = new DatagramSocket(0,myIp);
			BufferedWriter cfg =
				new BufferedWriter(
				    new OutputStreamWriter(
					new FileOutputStream(cfgFile),
					"US-ASCII"));
			cfg.write("" +	myIp.getHostAddress() + " " +
					sock.getLocalPort());
			cfg.newLine();
			cfg.close();
			if (predFile != null) {
				BufferedReader pred =
					new BufferedReader(
					    new InputStreamReader(
						new FileInputStream(predFile),
						"US-ASCII"));
				String s = pred.readLine();
				String[] chunks = s.split(" ");
				predecessor = new InetSocketAddress(
					chunks[0],Integer.parseInt(chunks[1]));
			}
		} catch(Exception e) {
			System.err.println("usage: DhtServer myIp numRoutes " +
					   "cfgFile [ cache ] [ debug ] " +
					   "[ predFile ] ");
			System.exit(1);
		}
		myAdr = new InetSocketAddress(myIp,sock.getLocalPort());
		
		// initialize data structures	
		map = new HashMap<String,String>();
		cache = new HashMap<String,String>();
		rteTbl = new LinkedList<Pair<InetSocketAddress,Integer>>();

		// join the DHT (if not the first node)
		hashRange = new Pair<Integer,Integer>(0,Integer.MAX_VALUE);
		succInfo = null;
		if (predecessor != null) {
			join(predecessor);
		} else {
			succInfo = new Pair<InetSocketAddress,Integer>(myAdr,0);
		}

		// start processing requests from clients
		Packet p = new Packet();
		Packet reply = new Packet();
		InetSocketAddress sender = null;
		sendTag = 1;
		while (true) {
			try { sender = p.receive(sock,debug);
			} catch(Exception e) {
				System.err.println("received packet failure in catch");
				continue;
			}
			if (sender == null) {
				System.err.println("received packet failure");
				continue;
			}
			if (!p.check()) {
				System.out.println("didn't pass check!!!");
				reply.clear();
                		reply.type = "failure";
                		reply.reason = p.reason;
                		reply.tag = p.tag;
                		reply.ttl = p.ttl;
                		reply.send(sock,sender,debug);
                		continue;
			}
			handlePacket(p,sender);
		}
	}

	/** Hash a string, returning a 32 bit integer.
	 *  @param s is a string, typically the key from some get/put operation.
	 *  @return and integer hash value in the interval [0,2^31).
	 */
	public static int hashit(String s) {
		while (s.length() < 16) s += s;
		byte[] sbytes = null;
		try { sbytes = s.getBytes("US-ASCII"); 
		} catch(Exception e) {
			System.out.println("illegal key string");
			System.exit(1);
		}
		int i = 0;
		int h = 0x37ace45d;
		while (i+1 < sbytes.length) {
			int x = (sbytes[i] << 8) | sbytes[i+1];
			h *= x;
			int top = h & 0xffff0000;
			int bot = h & 0xffff;
			h = top | (bot ^ ((top >> 16)&0xffff));
			i += 2;
		}
		if (h < 0) h = -(h+1);
		return h;
	}
	
	/** Join an existing DHT.
	 *  @param predAdr is the socket address of a server in the DHT,
	 *  
	 *  This method will send a "join" packet to predAdr, then wait
	 *  for the reply. After receiving the reply from predAdr, it 
	 *  will update its hashRange, succInfo, and add predAdr to its
	 *  shortcut.
	 */
	public static void join(InetSocketAddress predAdr) {
		Packet p = new Packet();
		p.type = "join";
		p.send(sock, predAdr, debug);
		Packet reply = new Packet();
		InetSocketAddress adr = reply.receive(sock, debug);
		// update hashRange, succInfo and add shortcut
		if (adr.equals(predAdr) && reply.type.equals("success")) {
			hashRange = reply.hashRange;
			succInfo = reply.succInfo;
			addRoute(succInfo);
		}
	}
	
	/** Handle a join packet from a prospective DHT node.
	 *  @param p is the received join packet
	 *  @param succAdr is the socket address of the host that
	 *  sent the join packet (the new successor)
	 *
	 *  When the server receives a "join" packet, this method will
	 *  be called. It will divide its hash range into half, 
	 *  update its succInfo to newly joined server info and send a 
	 *  packet to the newly joined server to tell it its hashRange
	 *  and succInfo. Then this server will also transfer some key,value
	 *  pairs the newly joined server.
	 */
	public static void handleJoin(Packet p, InetSocketAddress succAdr) {
		Packet reply = new Packet();
		reply.type = "success";
		int lastHash = hashRange.left - 1 + ((hashRange.right-hashRange.left)/2);
		reply.hashRange = new Pair<Integer,Integer>(lastHash+1, hashRange.right);
		hashRange.right = lastHash;
		reply.succInfo = succInfo;
		succInfo = new Pair<InetSocketAddress,Integer>(succAdr,lastHash+1);
		addRoute(succInfo);
		reply.send(sock, succAdr, debug);

		Packet transfer = new Packet();
		transfer.type = "transfer";
		List<String> keysToDelete = new ArrayList<String>();
		for (String key : map.keySet()) {
			if (hashit(key) > lastHash) {
				keysToDelete.add(key);
				transfer.key = key;
				transfer.val = map.get(key);
				transfer.send(sock, succAdr, debug);
			}
		}
		// remove the keys that have been transfered
		for (int i = 0; i < keysToDelete.size(); i++) {
			map.remove(keysToDelete.get(i));
		}
	}
	
	/** Handle a get packet.
	 *  @param p is a get packet
	 *  @param senderAdr is the the socket address of the sender
	 *
	 *  If this server is responsible for that key, then look up 
	 *  the key in the map and respond to the relay server. Otherwise,
	 *  if cache is turned on, it will first look up in the cache if 
	 *  it has the key, if not, forward this get packet to the 
	 *  appropriate server in the forwarding table.
	 */
	public static void handleGet(Packet p, InetSocketAddress senderAdr) {
		InetSocketAddress replyAdr;
		int hash = hashit(p.key);
		int left = hashRange.left.intValue();
		int right = hashRange.right.intValue();
		if (left <= hash && hash <= right) {
			if (p.relayAdr != null) {
				replyAdr = p.relayAdr;
				p.senderInfo = new Pair<InetSocketAddress,Integer>(myAdr,left);
			} else {
				replyAdr = senderAdr;
			}
			if (map.containsKey(p.key)) {
				p.type = "success"; p.val = map.get(p.key);
			} else {
				p.type = "no match";
			}
			p.send(sock, replyAdr, debug);
		} else if (cacheOn && cache.containsKey(p.key)) {
			// check cache, if cache has it, respond it
			if (p.relayAdr != null) {
				replyAdr = p.relayAdr;
				p.senderInfo = new Pair<InetSocketAddress,Integer>(myAdr, left);
			} else {
				replyAdr = senderAdr;
			}
			p.type = "success"; p.val = cache.get(p.key);
			p.send(sock, replyAdr, debug);
		} else {
			if (p.relayAdr == null) {
				p.relayAdr = myAdr; p.clientAdr = senderAdr;
			}
			forward(p,hash);
		}
	}
	
	/** Handle a put packet.
	 *  @param p is a put packet
	 *  @param senderAdr is the the socket address of the sender
	 *  
	 *  If this server is responsible for the key, then it will
	 *  do the operation as the request. Otherwise, it will
	 *  first check its cache if it has that key, if it does,
	 *  then it will remove that key infomation from the cache,
	 *  After that, the current server will simply forward the
	 *  "put" packet to the appropriate server in its forwarding table.
	 */
	public static void handlePut(Packet p, InetSocketAddress senderAdr) {
		InetSocketAddress replyAdr;
		int hash = hashit(p.key);
		int left = hashRange.left.intValue();
		int right = hashRange.right.intValue();
		if (left <= hash && hash <= right) {
			if (p.relayAdr != null) {
				replyAdr = p.relayAdr;
				p.senderInfo = new Pair<InetSocketAddress, Integer>(myAdr, left); 
			} else {
				replyAdr = senderAdr;
			}
			if (p.val == null || p.val.length() == 0) {
				if (map.containsKey(p.key)) {
					map.remove(p.key);
				}
			} else {
				map.put(p.key, p.val);
			}
			p.type = "success";
			p.send(sock,replyAdr,debug);
		} else {
			if (cacheOn && cache.containsKey(p.key)) {
				// if cache has it, remove it
				cache.remove(p.key);
			}
			if (p.relayAdr == null) {
				p.relayAdr = myAdr; p.clientAdr = senderAdr;
			}
			forward(p,hash);
		}
	}
	
	/** Handle a transfer packet.
	 *  @param p is a transfer packet
	 *  @param senderAdr is the the address (ip:port) of the sender
	 *  
	 *  When receiving "transfer" packet, this server will simply
	 *  read the key,val from the packet and add this pair to its
	 *  map.
	 */
	public static void handleXfer(Packet p, InetSocketAddress senderAdr) {
		String key = p.key;
		String val = p.val;
		int hash = hashit(key);
		// add the transfered key,val pair
		if (hashRange.left.intValue() <= hash && hash <= hashRange.right.intValue()) {
			map.put(key, val);
		}
	}
	
	/** Handle a reply packet.
	 *  @param p is a reply packet, more specifically, a packet of type
	 *  "success", "failure" or "no match"
	 *  @param senderAdr is the the address (ip:port) of the sender
	 *  
	 *  Handle the reply from another server. And forward the reply
	 *  packet to the client after removing the clientAdr,relayAdr
	 *  and senderInfo fields. When a success packet is received 
	 *  and cache is turned on, it will cache that key-value pair.
	 */
	public static void handleReply(Packet p, InetSocketAddress senderAdr) {
		InetSocketAddress clientAdr = p.clientAdr;
		Pair<InetSocketAddress,Integer> senderInfo = p.senderInfo;
		// remove unnecessary fields
		p.clientAdr = null;
		p.relayAdr = null;
		p.senderInfo = null;
		if (clientAdr != null) {
			p.send(sock, clientAdr, debug);
		}
		// cache the result if its type is "success"
		if (cacheOn && p.type.equals("success")) cache.put(p.key, p.val);
		addRoute(senderInfo);
	}
	
	/** Handle packets received from clients or other servers
	 *  @param p is a packet
	 *  @param senderAdr is the address (ip:port) of the sender
	 */
	public static void handlePacket(Packet p, InetSocketAddress senderAdr) {
		if (p.senderInfo != null) addRoute(p.senderInfo);
		if (p.type.equals("get")) {
			handleGet(p,senderAdr);
		} else if (p.type.equals("put")) {
			handlePut(p, senderAdr);
		} else if (p.type.equals("transfer")) {
			handleXfer(p, senderAdr);
		} else if (p.type.equals("success") ||
			   p.type.equals("no match") ||
		     	   p.type.equals("failure")) {
			handleReply(p, senderAdr);
		} else if (p.type.equals("join")) {
			handleJoin(p, senderAdr);
		}
	}
	
	/** Add an entry to the route tabe.
	 *  @param newRoute is a pair (addr,hash) where addr is the socket
	 *  address for some server and hash is the first hash in that
	 *  server's range
	 *  If the number of entries in the table exceeds the max
	 *  number allowed, the first entry that does not refer to
	 *  the successor of this server, is removed.
	 *  If debug is true and the set of stored routes does change,
	 *  print the string "rteTbl=" + rteTbl. (IMPORTANT)
	 */
	public static void addRoute(Pair<InetSocketAddress,Integer> newRoute){
		Iterator<Pair<InetSocketAddress,Integer>> it = rteTbl.iterator();
		// if the newRoute is already in the forwarding table
		// simple end this method and return
		while (it.hasNext()) {
			Pair<InetSocketAddress,Integer> route = it.next();
			if (route.equals(newRoute)) {
				return;
			}
		}
		// add the newRoute into the forwarding table, if the size
		// of the forwarding table exceeds the maximum limit, 
		// it will remove the oldest route which isn't its successor
		rteTbl.add(newRoute);
		if (rteTbl.size() > numRoutes) {
			it = rteTbl.iterator();
			while (it.hasNext()) {
				Pair<InetSocketAddress,Integer> route = it.next();
				if (!route.equals(succInfo)) {
					it.remove();
					break;
				}
			}
		}
		if (debug) {
			System.out.println("rteTbl=" + rteTbl);
		}
	}
	
	/** Forward a packet using the local routing table.
	 *  @param p is a packet to be forwarded
	 *  @param hash is the hash of the packet's key field
	 *  
	 *  This method selects a server from its route table that is
	 *  "closest" to the target of this packet (based on hash).
	 *  If firstHash is the first hash in a server's range, then
	 *  we seek to minimize the difference hash-firstHash, where
	 *  the difference is interpreted modulo the range of hash values.
	 *  IMPORTANT POINT - handle "wrap-around" correctly. 
	 *  Once a server is selected, p is sent to that server.
	 */
	public static void forward(Packet p, int hash) {
		Pair<InetSocketAddress,Integer> nearestRte = null;
		int minimumDiff = Integer.MAX_VALUE;
		Iterator<Pair<InetSocketAddress,Integer>> it = rteTbl.iterator();
		Pair<InetSocketAddress,Integer> route = null;
		// forward the packet to the next server which has the minimum
		while (it.hasNext()) {
			route = it.next();
			int diff;
			if (hash >= route.right.intValue()) {
				diff = hash - route.right.intValue();
			} else {
				// if the key's hash is less than a server's first hash,
				// add the full range to "wrap-around"
				diff = Integer.MAX_VALUE - route.right.intValue() + hash;
			}
			if (diff < minimumDiff) {
				minimumDiff = diff;
				nearestRte = route;
			}
		}
		p.send(sock, nearestRte.left, debug);
	}
}
