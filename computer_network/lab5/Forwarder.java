import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

/** 
 *  Name: Bingkun Guo
 *  Date: 11/21/2013
 *
 *  Forwarder for an overlay IP router.
 *	
 *  This class implements a basic packet forwarder for a simplified
 *  overlay IP router. It runs as a separate thread.
 *
 *  An application layer thread provides new packet payloads to be
 *  sent using the provided send() method, and retrieves newly arrived
 *  payloads with the receive() method. Each application layer payload
 *  is sent as a separate packet, where each packet includes a protocol
 *  field, a ttl, a source address and a destination address.
 */
public class Forwarder implements Runnable {
	private int myIp;	// this node's ip address in overlay
	private int debug;	// controls amount of debugging output
	private Substrate sub;	// Substrate object for packet IO
	private double now;	// current time in ns
	private final double sec = 1000000000; // # of ns in a second

	// forwarding table maps contains (prefix, link#) pairs
	private ArrayList<Pair<Prefix,Integer>> fwdTbl;

	// queues for communicating with SrcSnk
	private ArrayBlockingQueue<Packet> fromSrc;
	private ArrayBlockingQueue<Packet> toSnk;

	// queues for communicating with Router
	private ArrayBlockingQueue<Pair<Packet,Integer>> fromRtr;
	private ArrayBlockingQueue<Pair<Packet,Integer>> toRtr;

	private Thread myThread;
	private boolean quit;

	/** Initialize a new Forwarder object.
	 *  @param myIp is this node's IP address in the overlay network,
	 *  expressed as a raw integer.
	 *  @param sub is a reference to the Substrate object that this object
	 *  uses to handle the socket IO
	 *  @param debug controls the amount of debugging output
	 */
	Forwarder(int myIp, Substrate sub, int debug) {
		this.myIp = myIp; this.sub = sub; this.debug = debug;

		// intialize forwarding table with a default route to link 0
		fwdTbl = new ArrayList<Pair<Prefix,Integer>>();
		fwdTbl.add(new Pair<Prefix,Integer>(new Prefix(0,0), 0));

		// create queues for SrcSnk and Router
		fromSrc = new ArrayBlockingQueue<Packet>(1000,true);
		toSnk = new ArrayBlockingQueue<Packet>(1000,true);
		fromRtr = new
			  ArrayBlockingQueue<Pair<Packet,Integer>>(1000,true);
		toRtr = new
			ArrayBlockingQueue<Pair<Packet,Integer>>(1000,true);
		quit = false;
	}

	/** Start the Forwarder running. */
	public void start() throws Exception {
		myThread = new Thread(this); myThread.start();
	}

	/** Terminate the Forwarder.  */
	public void stop() throws Exception { quit = true; myThread.join(); }

	/** This is the main thread for the Forwarder object.
	 *
	 *  It receives packets from the the Substrate and forwards them either
	 *  to applicaion layer or to the the Router according to the packet type.
	 *
	 *  It also receives payloads from the applicaiton layer and insert it
	 *  into pakcets and then sends to the substrate layer according to the
	 *  longest matching prefix for the destination address in the packet.
	 * 
	 *  It also sends packets from the Routers to a specified link.
	 */
	
	public void run() {
		now = 0; double t0 = System.nanoTime()/sec;

		while (!quit) {
			now = System.nanoTime()/sec - t0;
			// if the Substrate has an incoming packet
			if (sub.incoming()) {
				Pair<Packet, Integer> inPair = sub.receive();
				inPair.left.ttl--;
	            // if it's addressed to this overlay router,
	            // send to the SrcSnk or the Router
				if (inPair.left.destAdr == myIp) {
					if (inPair.left.protocol == 1) {
						try {
							toSnk.put(inPair.left);
						} catch (Exception e) {
							System.out.println("Error: " + e);
						}
					} else {
						try {
							toRtr.put(inPair);
						} catch (Exception e) {
							System.out.println("Error: " + e);
						}
					}
				} else if (inPair.left.ttl > 0) {
					// else, forward it to the next hop
					int lnkNum = lookup(inPair.left.destAdr);
					if (lnkNum != -1 && sub.ready(lnkNum)) {
						sub.send(inPair.left, lnkNum);
					}
				}	
			} else if (fromRtr.size() > 0) {
	            // 		else if we have a packet from the Router to send
	            //       send it to the Substrate
            	Pair<Packet, Integer> outPair = fromRtr.peek();
            	if (sub.ready(outPair.right)) {
					try {
						fromRtr.take();
					} catch (Exception e) {
						System.out.println(e);
					}
            		sub.send(outPair.left, outPair.right);
            	}
			} else if (fromSrc.size() > 0) {
	            // else if we have a payload from the SrcSnk to send
	            //       lookup the outgoing link using dest IP address
	            //       format a packet containing the payload and
	            //       pass it to the Substrate	
	            Packet p = fromSrc.peek();
	            //printTable();
	            int lnkNum = lookup(p.destAdr);
	            if (sub.ready(lnkNum)) {
					try {
						fromSrc.take();
					} catch (Exception e) {
						System.out.println(e);
					}
	            	sub.send(p, lnkNum);
	            }
			} else {
                // else, nothing to do, so take a nap
				try {
					Thread.sleep(1);
				} catch(Exception e) {
					System.exit(1);
				}
			}
		}
	}

	/** Add a route to the forwarding table.
	 *
	 *  @param nuPrefix is a prefix to be added
	 *  @param nuLnk is the number of the link on which to forward
	 *  packets matching the prefix
	 *
	 *  If the table already contains a route with the specified
	 *  prefix, the route is updated to use nuLnk. Otherwise,
	 *  a route is added.
	 *
	 *  If debug>0, print the forwarding table when done
	 */
	public synchronized void addRoute(Prefix nuPrefix, int nuLnk) {
		// if table contains an entry with the same prefix,
		// just update the link; otherwise add an entry
		boolean haveMatch = false;
		for (Pair<Prefix,Integer> rte : fwdTbl) {
			if (rte.left.equals(nuPrefix)) {
				haveMatch = true;
				rte.right = nuLnk;
				break;
			}
		}
		if (!haveMatch) {
			fwdTbl.add(new Pair<Prefix, Integer>(nuPrefix, nuLnk));
		}
		if (debug > 0) {
			printTable();
		}
	}

	/** Print the contents of the forwarding table. */
	public synchronized void printTable() {
		String s = String.format("Forwarding table (%.3f)\n",now);
		for (Pair<Prefix,Integer> rte : fwdTbl)
			s += String.format("%s %s\n", rte.left, rte.right);
		System.out.println(s);
	}

	/** Lookup route in fwding table.
	 *
	 *  @param ip is an integer representing an IP address to lookup
	 *  @return nextHop link number or -1, if no matching entry.
	 */
	private synchronized int lookup(int ip) {
		int bestLink = -1;
		int bestLeng = -1;
		for (Pair<Prefix,Integer> pair : fwdTbl) {
			if (pair.left.matches(ip) && (pair.left.leng > bestLeng)) {
				bestLink = pair.right;
				bestLeng = pair.left.leng;
			}
		}
		return bestLink;
	}

	/** Send a message to another overlay host.
	 *  @param message is a string to be sent to the peer
	 */
	public void send(String payload, String destAdr) {
		Packet p = new Packet();
		p.srcAdr = myIp; p.destAdr = Util.string2ip(destAdr);
		p.protocol = 1; p.ttl = 100;
		p.payload = payload;
		try {
			fromSrc.put(p);
		} catch(Exception e) {
			System.err.println("Forwarder:send: put exception" + e);
			System.exit(1);
		}
	}
		
	/** Test if Forwarder is ready to send a message.
	 *  @return true if Forwarder is ready
	 */
	public boolean ready() { return fromSrc.remainingCapacity() > 0; }

	/** Get an incoming message.
	 *  @return next message
	 */
	public Pair<String,String> receive() {
		Packet p = null;
		try {
			p = toSnk.take();
		} catch(Exception e) {
			System.err.println("Forwarder:send: take exception" +e);
			System.exit(1);
		}
		return new Pair<String,String>(
				p.payload,Util.ip2string(p.srcAdr));
	}
	
	/** Test for the presence of an incoming message.
	 *  @return true if there is an incoming message
	 */
	public boolean incoming() { return toSnk.size() > 0; }

	// the following methods are used by the Router

	/** Send a message to another overlay Router.
	 *  @param p is a packet to be sent to another overlay node
	 *  @param lnk is the number of the link the packet should be
	 *  forwarded on
	 */
	public void sendPkt(Packet p, int lnk) {
		Pair<Packet,Integer> pp = new Pair<Packet,Integer>(p,lnk);
		try {
			fromRtr.put(pp);
		} catch(Exception e) {
			System.err.println("Forwarder:sendPkt: cannot write"
					    + " to fromRtr " + e);
			System.exit(1);
		}
	}
		
	/** Test if Forwarder is ready to send a packet from Router.
	 *  @return true if Forwarder is ready
	 */
	public boolean ready4pkt() { return fromRtr.remainingCapacity() > 0; }

	/** Get an incoming packet.
	 *  @return next packet for router, including the link on which
	 *  it arrived
	 */
	public Pair<Packet,Integer> receivePkt() {
		Pair<Packet,Integer> pp = null;
		try {
			pp = toRtr.take();
		} catch(Exception e) {
			System.err.println("Forwarder:receivePkt: cannot read"
					    + " from toRtr " + e);
			System.exit(1);
		}
		return pp;
	}
	
	/** Test for the presence of an incoming packet for Router.
	 *  @return true if there is an incoming packet
	 */
	public boolean incomingPkt() { return toRtr.size() > 0; }
}
