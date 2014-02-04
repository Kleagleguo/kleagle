import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

/** 
 *  Name: Bingkun Guo
 *  Date: 11/21/2013 
 *
 * Router module for an overlay router.
 *
 * The Router will use a path-vector style protocol, similar to what BGP does
 * except that the protocol will be an intra-domian protocol and will include
 * a path cost equal to the sum of the link cost. A path- vector consists of an
 * advertisement, a path cost an a list of IP addresses deifing the path to the
 * router that issued the advertisement.
 *
 * The router thread sends " hello packets" to its neighbors once every second,
 * the hello packets contain a timestamp that the Router uses to measure the
 * round trip delay for the link, and th link cost is then set to half the round
 * trip delay. The Router use the exponential weighted moving average method (with
 * a parameter a = 0.1) to smooth out variations in the individual measurements.
 * The link costs should be set based on the "smoothed" delay values. If a Router
 * fail to get response to three conscutive hello packets from a given neighbor,
 * it will change the status of that link to "failed". It countinues to send hello
 * packets to failed links, and restores the link as soon as it gets a response.
 *
 * The payload of routing packets are formatted as ASCII text. A hello packet
 *  is simply shown as below
 *
 *  RPv0
 *  type: hello
 *  timestamp: 123.456
 *
 * The reply to a hello packet is shown as below
 *
 *  RPv0
 *  type: hello2u
 *  timestamp: 123.456
 *
 * the timestamp in the hello packet is the time in seconds from an arbitrary
 * starting poing at the sendeing router. Timestamp are echo back in replies
 * to hello packets
 *
 * The other type of packet used by the routing protocol is a route advertisement
 * packet. An example is shown below.
 *
 *  RPv0
 *  type: advert
 *  pathvec: 1.5.0.0/16 345.678 .346 1.2.0.1 1.2.3.4 1.5.4.3
 *
 * Each path vector starts with advertised prefix, followed by a timestamp for
 * the vector and the cost of the path(in seconds). The remaider of the path
 * vector is a list of the IP addresses of the routes along the path, ending
 * with the router theat originated this advertisement (1.5.4.3 in the example).
 * The timestamp for a path vector represents the time at whic the advertising
 * router first sent this path vector. It is forwarded without change by other
 * routers and used by routers when deciding how to update their routing table
 * entries.
 *
 * Each Routers sends an advertisemnt for each of its own prefixes to each of
 * its neighbors periodically(every 10 seconds). The path vectors for these
 * advertisements will have a cost of zero and a path consisting only the IP
 *  address of the sending Router.
 *
 * When a Router recieves an advertisement from one of its neighbors, it first
 * checks to see if its own IP address appears in the path vector. If so, it
 * just discards this advertisement. Otherwise, it decides whether or not to
 * update its routing table, based on the contents of the path vector. Each
 * entry path vector contains the following fields.
 *
 *  prefix, timestamp, cost, path, outputlink
 *
 * The prefix is an IP address prefix for the subnet that this route tell us
 * how to reach. The timestamp is the timestamp of the most recent advertisement
 * packet that caused an update to this routing table entry. The cost is the
 * sum of the link costs in the path to the destination subnet. The path is a
 * list of IP adress strings defining the path to the destination subnet.
 * The output link is the link used to reach the first router on the path.
 *
 * When a Router received an advertisement, it affects the routing table.
 *
 * First, if the routing table has no entry for the subnet whose prefix is
 * specified in the advertisement,then a new route is added to the table
 * based on the information in the advertisement. The cost field of the routing
 * table entry is obtained by adding the cost in the received advertisement to
 * the of the link on which the advertisement was received.
 *
 * Second, if the routing table already had an entry for the subnet whose prefix
 * is specified in the advertisement then the program may need to modify the
 * existing route.  The program modifies the routing table according to
 * following subcase
 *
 * If the advertisement arrived on a link that is currently disabled, then
 * the new advertisement is ignored
 *
 * Otherwise, if the new advertisement uses the same path as the current routing
 * table entry, then the program updates the timestamp field and the cost of the
 * existing routing entry, based on the information in the advertisement.
 *
 * Otherwise, the new adveritsement defines a new route that uses a different
 * path than the old ones, We'll update the current entry based on this new route
 * if any of the following three conditions is true.
 *
 *  the cost of the new route is at least 10% smaller than a cost of
 *  the current route
 *
 *  the new route is at least 20 seconds newer than the old route (as defined
 *  by the timestamp of the current route and timestamp of the advertisemen)
 *
 *  the current route uses a lnk that is disabled.
 *
 * If a new route is added to the routing table, or if the link field of an
 * existing entry is changed, the nthe Router should also change the correspnding
 * entry in he Forwarder's intenal table.
 *
 * whenever the debuggingis enabled and a received advertisement either
 * cause us to add a route to the table or modify the path, contents of the routing
 * table should be printed
 *
 * Whenever a received advertisement cause any change at all to the routing
 * table, then that advertisement should be extened and forwarded to all the
 *  neighboring routers (with the exception of the router does  two things.
 * First, it modifies the cost field in the advertisement to include the
 * cost of the link on which it recieved the advertisement. Second, it adds its
 * own IP address to the front of the path portion of the advertisement.
 *
 */

public class Router implements Runnable {
	private Thread myThread;	// thread that executes run() method

	private int myIp;		// ip address in the overlay
	private String myIpString;	// String representation
	private ArrayList<Prefix> pfxList; // list of prefixes to advertise
	private ArrayList<NborInfo> nborList; // list of info about neighbors

	private class LinkInfo { // class used to record link information
		public int peerIp;	// IP address of peer in overlay net
		public double cost;	// in seconds
		public boolean gotReply; // flag to detect hello replies
		public int helloState;	// set to 3 when hello reply received
					// decremented whenever hello reply
					// is not received; when 0, link is down

		// link cost statistics
		public int count;
		public double totalCost;
		public double minCost;
		public double maxCost;

		LinkInfo() {
			cost = 0; gotReply = true; helloState = 3;
			count = 0; totalCost = 0; minCost = 10; maxCost = 0;
		}
	}
	private ArrayList<LinkInfo> lnkVec;  // indexed by link number

	private class Route { // routing table entry
		public Prefix pfx;	// destination prefix for route
		public double timestamp; // time this route was generated
		public double cost;	// cost of route in ns
		public LinkedList<Integer> path; // list of router IPs;
					// destination at end of list
		public int outLink;	// outgoing link for this route
	}
	private ArrayList<Route> rteTbl;  // routing table

	private Forwarder fwdr;		// reference to Forwarder object

	private double now;		// current time in ns
	private static final double sec = 1000000000;  // ns per sec

	private int debug;		// controls debugging output
	private boolean quit;		// stop thread when true


	/** Initialize a new Router object.
	 *  
	 *  @param myIp is an integer representing the overlay IP address of
	 *  this node in the overlay network
	 *  @param fwdr is a reference to the Forwarder object through which
	 *  the Router sends and receives packets
	 *  @param pfxList is a list of prefixes advertised by this router
	 *  @param nborList is a list of neighbors of this node
	 *
	 *  @param debug is an integer that controls the amount of debugging
	 *  information that is to be printed
	 */

	Router(int myIp, Forwarder fwdr, ArrayList<Prefix> pfxList,
			ArrayList<NborInfo> nborList, int debug) {
		this.myIp = myIp; this.myIpString = Util.ip2string(myIp);
		this.fwdr = fwdr; this.pfxList = pfxList;
		this.nborList = nborList; this.debug = debug;

		lnkVec = new ArrayList<LinkInfo>();
		for (NborInfo nbor : nborList) {
			LinkInfo lnk = new LinkInfo();
			lnk.peerIp = nbor.ip;
			lnk.cost = nbor.delay;
			lnkVec.add(lnk);
		}
		rteTbl = new ArrayList<Route>();
		quit = false;
	}

	/** Instantiate and start a thread to execute run(). */
	public void start() {
		myThread = new Thread(this); myThread.start();
	}

	/** Terminate the thread. */
	public void stop() throws Exception { quit = true; myThread.join(); }

	/** This is the main thread for the Router object.
	 *
     *  Every second, we send a hello packet to the neighbors and every 10
     * seconds we advertise our prefixes to the neighbors.
     * when we get a hello, we echo it back and when we get a reply to
     * our own hello, we use it to update our link costs
     *
     * When we get an advertisement from neigbor, we first check if we'are in
     *  the path and discard it, If not, we update our own routing state and
     * forward, as appropriate.
     *
     */

	public void run() {
		double t0 = System.nanoTime()/sec;
		now = 0;
		double helloTime = 0;
		double adTime = 0;
 		while (!quit) {
			now = System.nanoTime()/sec - t0;
			if (now > helloTime + 1) {
				// if it's time to send hello packets, do it
				sendHellos();
				helloTime = now;
			} else if (now > adTime + 10) {
				// else if it's time to send advertisements, do it
				sendAdverts();
				adTime = now;
			} else if (fwdr.incomingPkt()) {		
				// else if the forwarder has an incoming packet
				// to be processed, retrieve it and process it
				handleIncoming();
			} else {
				// else nothing to do, so take a nap
				try {
					Thread.sleep(1);
				} catch(Exception e) {
					System.exit(1);
				}
			}
		}
		String s = String.format("Router link cost statistics\n" + 
			"peerIp count avgCost minCost maxCost\n");
		for (LinkInfo lnk : lnkVec) {
			if (lnk.count == 0) continue;
			s += String.format("%s %d %.3f %.3f %.3f\n",
				Util.ip2string(lnk.peerIp), lnk.count,
				lnk.totalCost/lnk.count,
				lnk.minCost, lnk.maxCost);
		}
		System.out.println(s);
	}

	/** Lookup route in routing table.
	 *
	 * @param pfx is IP address prefix to be looked up.
	 * @return a reference to the Route that matches the prefix or null
	 */
	private Route lookupRoute(Prefix pfx) {
		for (Route rte : rteTbl) {
			if (rte.pfx.equals(pfx)) return rte;
		}
		return null;
	}

	/** Add a route to the routing table.
	 * 
	 *  @param rte is a route to be added to the table; no check is
	 *  done to make sure this route does not conflict with an existing
	 *  route; just add it
	 */
	private void addRoute(Route rte) { 
		rteTbl.add(rte);
	 }

	 /** Update a route in the routing table.
	 *
	 *  @param rte is a reference to a route in the routing table.
	 *  @param nuRte is a reference to a new route that has the same
	 *  prefix as rte
	 *  @return true if rte is modified in any way, else false
	 *
	 *  This method replaces certain fields in rte with fields
	 *  in nuRte. Specifically,
	 *
	 *  if nuRte has a link field that refers to a disabled
	 *  link, ignore it and return false
	 *
	 *  else, if both routes have the same path and link,
	 *  then the timestamp and cost fields of rte are updated
	 *
	 *  else, if nuRte has a cost that is less than .9 times the
	 *  cost of rte, then all fields in rte except the prefix fields
	 *  are replaced with the corresponding fields in nuRte
	 *
	 *  else, if nuRte is at least 20 seconds newer than rte
	 *  (as indicated by their timestamps), then all fields of
	 *  rte except the prefix fields are replaced
	 *
	 *  else, if the link field for rte refers to a link that is
	 *  currently disabled, replace all fields in rte but the
	 *  prefix fields
	 */
	private boolean updateRoute(Route rte, Route nuRte) {
		// TODO
		if (lnkVec.get(nuRte.outLink).helloState == 0) {
			return false;
		} else if (rte.path.equals(nuRte.path)) {
			rte.timestamp = nuRte.timestamp;
			rte.cost = nuRte.cost;
			return true;
		} else if ( (nuRte.cost < 0.9 * rte.cost)
					|| (nuRte.timestamp > rte.timestamp + 20)
					|| (lnkVec.get(rte.outLink).helloState == 0)) {
			rte.timestamp = nuRte.timestamp;
			rte.cost = nuRte.cost;
			rte.path = nuRte.path;
			rte.outLink = nuRte.outLink;
			return true;
		}
		return false;
	}
				
	/** Send hello packet to all neighbors.
	 *
	 *  First check for replies. If no reply received on some link,
	 *  update the helloState by subtracting 1. If that makes it 0,
	 *  the link is considered down, so we increase the cost of all
	 *  routes using that link.
	 */
	public void sendHellos() {
		int lnk = 0;
		for (LinkInfo lnkInfo : lnkVec) {
			// TODO
			if (!lnkInfo.gotReply) {
			// if no reply to the last hello, subtract 1 from
			// helloState if it's not already 0
				if (lnkInfo.helloState != 0) {
					lnkInfo.helloState--;
				}
			}
			// send new hello, after setting gotReply to false
			lnkInfo.gotReply = false;
			Packet p = new Packet();
			p.protocol = 2;
			p.ttl = 100;
			p.srcAdr = myIp;
			p.destAdr = lnkInfo.peerIp;
			p.payload = "RPv0\ntype: hello\n" 
					  + "timestamp: " + Double.toString(now) + "\n";
			fwdr.sendPkt(p, lnk);
			lnk++;
		}
	}

	/** Send initial advertisement to each of our neighbors.  */
	public void sendAdverts() {
		// TODO
		for (Prefix pfx : pfxList) {
			int lnk = 0;
			for (NborInfo nbr : nborList) {
				Packet p = new Packet();
				p.protocol = 2; 
				p.ttl = 100;
				p.srcAdr = myIp;
				p.destAdr = nbr.ip;
				p.payload = "RPv0\ntype: advert\n" 
						  + "pathvec: " + pfx.toString() 
						  + " " + now + " 0 " 
						  + myIpString + "\n";
				fwdr.sendPkt(p, lnk);
				lnk++;
			}
		}
	}

	/** Retrieve and process packet received from Forwarder.
	 *
	 *  For hello packets, we simply echo them back.
	 *  For replies to our own hello packets, we update costs.
	 *  For advertisements, we update routing state and propagate
	 *  as appropriate.
	 */
	public void handleIncoming() {
		// parse the packet payload
		Pair<Packet,Integer> pp = fwdr.receivePkt();
		Packet p = pp.left; int lnk = pp.right;
		String[] lines = p.payload.split("\n");
		if (!lines[0].equals("RPv0")) return;

		String[] chunks = lines[1].split(":");
		if (!chunks[0].equals("type")) return;
		String type = chunks[1].trim();

		// TODO
		if (type.equals("advert")) {
			// if it's an advert, call handleAdvert
			handleAdvert(lines, lnk);			
		} else if (type.equals("hello")) {
			// if it's a hello, echo it back
			Packet reply = new Packet();
			reply.protocol = 2;
			reply.ttl = 100;
			reply.srcAdr = myIp;
			reply.destAdr = p.srcAdr;
			reply.payload = "RPv0\ntype: hello2u\n"
						  + lines[2] + "\n";
			if (fwdr.ready4pkt()) {
				fwdr.sendPkt(reply, lnk);
			}			
		} else if (type.equals("hello2u")) {
			// else it's a reply to a hello packet
			// use timestamp to determine round-trip delay
			// use this to update the link cost using exponential
			// weighted moving average method
			// also, update link cost statistics
			// also, set gotReply to true
			chunks = lines[2].split(":");
			double timestamp = Double.parseDouble(chunks[1].trim());
			double halfRtt = (now - timestamp) / 2;
			lnkVec.get(lnk).gotReply = true;
			lnkVec.get(lnk).helloState = 3;
			lnkVec.get(lnk).cost = 0.1 * halfRtt 
								 + 0.9 * lnkVec.get(lnk).cost;
			lnkVec.get(lnk).count++;
			lnkVec.get(lnk).totalCost += halfRtt;
			lnkVec.get(lnk).minCost 
					= Math.min(lnkVec.get(lnk).minCost, halfRtt);
			lnkVec.get(lnk).maxCost 
					= Math.max(lnkVec.get(lnk).maxCost, halfRtt);
		}
	}

	/** Handle an advertisement received from another router.
	 *
	 *  @param lines is a list of lines that defines the packet;
	 *  the first two lines have already been processed at this point
	 *
	 *  @param lnk is the number of link on which the packet was received
	 */
	private void handleAdvert(String[] lines, int lnk) {
		// example path vector line
		// pathvec: 1.2.0.0/16 345.678 .052 1.2.0.1 1.2.3.4

		// TODO
        // Parse the path vector line.
		String[] chunks = lines[2].split(":");
		int ip;
		LinkedList<Integer> path = new LinkedList<Integer>();
		double timestamp;
		double cost;
		int leng;
		boolean loopFound = false;

		if (!chunks[0].trim().equals("pathvec")) {
			System.out.println("wrong name!!!");
			return;
		}
		chunks = chunks[1].trim().split("\\s+");
		timestamp = Double.parseDouble(chunks[1]);
		cost = Double.parseDouble(chunks[2]);
		for (int i = 3; i < chunks.length; i++) {
			path.add(Util.string2ip(chunks[i]));
		}

		Prefix newPfx = new Prefix(chunks[0].trim());

		// If there is loop in path vector, ignore this packet.
		for (int adr : path) {
			if (adr == myIp) {
				loopFound = true;
				break;
			}
		}
		if (loopFound) 
			return;

        // Form a new route, with cost equal to path vector cost
        // plus the cost of the link on which it arrived.
		Route newRoute = new Route();
		newRoute.pfx = newPfx;
		newRoute.timestamp = timestamp;
		newRoute.cost = cost + lnkVec.get(lnk).cost;
		newRoute.path = path;
		newRoute.outLink = lnk;

        // Look for a matching route in the routing table
        // and update as appropriate; 
        Route rte = lookupRoute(newRoute.pfx);
        boolean rteTblChanged = false;

        if (rte == null) {
        	addRoute(newRoute);
        	if (debug > 0) {
        		printTable();
        	}
        	fwdr.addRoute(newRoute.pfx, newRoute.outLink);
        	rteTblChanged = true;
        } else {
        	boolean pathChanged = !newRoute.path.equals(rte.path);
        	boolean linkChanged = (newRoute.outLink != rte.outLink);

        	if (updateRoute(rte, newRoute)) {
        		rteTblChanged = true;
	        	// whenever an update
           		// changes the path, print the table if debug>0;
        		if (debug > 0 && pathChanged) {
        			printTable();
        		}
        		// whenever an update changes the output link,
                // update the forwarding table as well.
        		if (linkChanged) {
        			fwdr.addRoute(newRoute.pfx, newRoute.outLink);
        		}
        	}
        }

        // If the new route changed the routing table,
        // extend the path vector and send it to other neighbors.
        if (rteTblChanged) {
        	StringBuilder pathStr = new StringBuilder();
        	for (int adr : newRoute.path) {
        		pathStr.append(" ");
        		pathStr.append(Util.ip2string(adr));
        	}
        	String payload = "RPv0\ntype: advert\n"
        				+ "pathvec: " + newRoute.pfx.toString()
        				+ " " + Double.toString(newRoute.timestamp)
        				+ " " + Double.toString(newRoute.cost)
        				+ " " + myIpString 
        				+ pathStr.toString() + "\n";

        	int outputLnk = 0;
        	for (NborInfo nbr : nborList) {
				if (outputLnk != lnk) {
					Packet p = new Packet();
					p.protocol = 2; 
					p.ttl = 100;
					p.srcAdr = myIp;
					p.destAdr = nbr.ip;
					p.payload = payload;
					fwdr.sendPkt(p, outputLnk);
				}
				outputLnk++;        		
        	}
        }        
                

	}

	/** Print the contents of the routing table. */
	public void printTable() {
		String s = String.format("Routing table (%.3f)\n"
			+ "prefix timestamp cost path link\n",now);
		for (Route rte : rteTbl) {
			s += String.format("%s %.3f %.3f",
				rte.pfx.toString(), rte.timestamp, rte.cost);
			for (int r :rte.path)
				s += String.format (" %s",Util.ip2string(r));
			s += String.format(" %d", rte.outLink);
			if (lnkVec.get(rte.outLink).helloState == 0)
				s += String.format(" ** disabled link");
			s += "\n";
		}
		System.out.println(s);
	}
}
