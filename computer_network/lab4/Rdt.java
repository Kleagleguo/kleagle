/** Reliable Data Transport class.
 *
 *  This class implements a reliable data transport service.
 *  It uses a sliding window protocol, on a packet basis,
 *  with selective repeat.
 *
 *  An application layer thread provides new packet payloads to be
 *  sent using the provided send() method, and retrieves newly arrived
 *  payloads with the receive() method. Each application layer payload
 *  is sent as a separate UDP packet, along with a sequence number and
 *  a type flag that identifies a packet as a data packet or an
 *  acknowledgment. The sequence numbers are 15 bits.
 */

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class Rdt implements Runnable {
	private int wSize;	// protocol window size
	private long timeout;	// retransmission timeout in ns
	private Substrate sub;	// Substrate object for packet IO

	// queues for communicating with source/sink
	private ArrayBlockingQueue<String> fromSrc;
	private ArrayBlockingQueue<String> toSnk;

	private Packet[] sendBuffer;
	private Packet[] rcvBuffer;
	private long[] resendTimes;
	private List<Integer> resendList;
	private short sendBase, nextSeqNum, rcvBase;
	private long oldestTime;

	private Thread myThread; // local thread for this object
	private boolean quit;	 // used to signal quitting time

	/** Initialize a new Rdt object.
	 *  @param wSize is the window size used by protocol; the sequence #
	 *  space is twice the window size
	 *  @param timeout is the time to wait before retransmitting
	 *  @param sub is a reference to the Substrate object that this object
	 *  uses to handle the socket IO
	 */
	Rdt(int wSize, double timeout, Substrate sub) {
		this.wSize = Math.min(wSize,(1 << 14) - 1);
		this.timeout = ((long) (timeout * 1000000000)); // sec to ns
		this.sub = sub;

		// create queues for application layer interface
		fromSrc = new ArrayBlockingQueue<String>(1000,true);
		toSnk = new ArrayBlockingQueue<String>(1000,true);
		quit = false;

		sendBuffer = new Packet[2*wSize];
		resendTimes = new long[2*wSize];
		resendList = new LinkedList<Integer>();
		rcvBuffer = new Packet[2*wSize];
		sendBase = nextSeqNum = rcvBase = 0;
	}

	/** Start the Rdt running. */
	public void start() throws Exception {
		myThread = new Thread(this); myThread.start();
	}

	/** Stop the Rdt.  */
	public void stop() throws Exception { quit = true; myThread.join(); }

	/** Increment sequence number, handling wrap-around.
	 *  @param x is a sequence number
	 *  @return next sequence number after x
	 */
	private short incr(short x) {
		x++; return (x < 2*wSize ? x : 0);
	}

	/** Compute the difference between two sequence numbers,
	 *  accounting for "wrap-around"
	 *  @param x is a sequence number
	 *  @param y is another sequence number
	 *  @return difference, assuming x is "clockwise" from y
	 */
	private int diff(short x, short y) {
		return (x >= y ? x-y : (x + 2*wSize) - y);
	}

	/** Main thread for the Rdt object.
	 *
	 *  Inserts payloads received from the application layer into
	 *  packets, and sends them to the substrate. The packets include
	 *  the number of packets and chars sent so far (including the
	 *  current packet). It also takes packets received from
	 *  the substrate and sends the extracted payloads
	 *  up to the application layer. To ensure that packets are
	 *  delivered reliably and in-order, using a sliding
	 *  window protocol with the selective repeat feature.
	 */
	public void run() {
		long t0 = System.nanoTime();
		long now = 0;		// current time (relative to t0)

		while (!quit || resendList.size() > 0/* we still have un-acked packets */ ) {
			now = System.nanoTime() - t0;
			if (rcvBuffer[rcvBase] != null) {
				// if receive buffer has a packet that can be
				//    delivered, deliver it to sink
				Packet p = rcvBuffer[rcvBase];
				try {
					toSnk.put(p.payload);
				} catch (Exception e) {
					System.err.println("toSnk: put exception" + e);
					System.exit(1);
				}
				rcvBuffer[rcvBase] = null;				
				rcvBase = incr(rcvBase);
			} else if (sub.incoming()) {
				//	else if the substrate has an incoming packet
				//      get the packet from the substrate and process it
				Packet p = sub.receive();
				if (p.type == 0) {  
				// 	if it's a data packet, ack it and add it
				//	   to receive buffer as appropriate
					if (diff(p.seqNum, rcvBase) < wSize) 
						if (rcvBuffer[p.seqNum] == null) 
							rcvBuffer[p.seqNum] = p;
					Packet ackPkt = new Packet();
					ackPkt.type = 1;
					ackPkt.seqNum = p.seqNum;
					sub.send(ackPkt);
				} else if (diff(p.seqNum, sendBase) < diff(nextSeqNum, sendBase)) {
					//	if it's an ack, update the send buffer and
					//	   related data as appropriate
					//	   reset the timer if necessary
					if (sendBuffer[p.seqNum] != null) { 
						sendBuffer[p.seqNum] = null;
						resendList.remove(new Integer(p.seqNum));
						while (sendBase != nextSeqNum 
							&& sendBuffer[sendBase] == null) {
							sendBase = incr(sendBase);
						}
						if (sendBase != nextSeqNum) {
							oldestTime = resendTimes[resendList.get(0)];
						}
					}
				}
			} else if (now > oldestTime && sub.ready() && sendBase != nextSeqNum) {
				//	if the resend timer has expired, re-send the
				//  oldest un-acked packet and reset timer
				int seqNum = resendList.remove(0).intValue();
				Packet resendPkt = sendBuffer[seqNum];
				sub.send(resendPkt);
				resendTimes[seqNum] = now + timeout;
				resendList.add(new Integer(seqNum));
				int oldestSeqNum = resendList.get(0).intValue();
				oldestTime = resendTimes[oldestSeqNum];
			} else if (fromSrc.size() > 0 && sub.ready() 
				&& (diff(nextSeqNum, sendBase) < wSize)) {
				//	if there is a message from the source waiting
				//      to be sent and the send window is not full
				//	and the substrate can accept a packet
				//      create a packet containing the message,
				//	and send it, after updating the send buffer
				//	and related data
				Packet p = new Packet();
				p.type = 0;
				p.payload = fromSrc.poll();
				p.seqNum = nextSeqNum;
				sendBuffer[nextSeqNum] = p;
				resendTimes[nextSeqNum] = now + timeout;
				resendList.add(new Integer(nextSeqNum));
				if (sendBase == nextSeqNum) {
					oldestTime = resendTimes[sendBase];
				}
				nextSeqNum = incr(nextSeqNum);
				sub.send(p);
			} else {
				// else nothing to do, so sleep for 1 ms
				try {
					Thread.sleep(1);
				} catch(Exception e) {
					System.exit(1);
				}
			}
		}
	}

	/** Send a message to peer.
	 *  @param message is a string to be sent to the peer
	 */
	public void send(String message) {
		try {
			fromSrc.put(message);
		} catch(Exception e) {
			System.err.println("Rdt:send: put exception" + e);
			System.exit(1);
		}
	}
		
	/** Test if Rdt is ready to send a message.
	 *  @return true if Rdt is ready
	 */
	public boolean ready() { return fromSrc.remainingCapacity() > 0; }

	/** Get an incoming message.
	 *  @return next message
	 */
	public String receive() {
		String s = null;
		try {
			s = toSnk.take();
		} catch(Exception e) {
			System.err.println("Rdt:send: take exception" + e);
			System.exit(1);
		}
		return s;
	}
	
	/** Test for the presence of an incoming message.
	 *  @return true if there is an incoming message
	 */
	public boolean incoming() { return toSnk.size() > 0; }
}
