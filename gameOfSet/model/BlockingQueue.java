/** 
 * This class represents a blocking queue
 * which stores the runnables of the game
 * I didn't modify this class, it is provided
 * in studio 9
 * @author 			Bingkun Guo
 * Lab Section:		C
 * Email:			guobingkun@wustl.edu
 * Lab:				Lab5: Set Multiuser Game 
 * File:			BlockingQueue.java
 */
package lab5.model;

import java.util.LinkedList;

/**
 * As given, this code is simply a queue that holds
 *   objects of type T
 *   
 * When you make your changes, it becomes a queue
 *   that blocks on dequeue until it is nonempty,
 *   and blocks on enqueue until it is not full.
 *
 *
 * @param <T> the type of object placed on the queue
 *    For our multiuser game, it is typically a Runnable
 */
public class BlockingQueue<T> {

	private LinkedList<T> queue;
	private int maxSize;
	boolean empty;
	boolean full;


	/**
	 * A queue that causes its caller to wait if
	 * the queue is empty for dequeue or
	 * the queue is full for enqueue
	 * @param maxSize -- maximum size of the queue
	 */
	public BlockingQueue(int maxSize) {
		this.maxSize = maxSize;
		queue = new LinkedList<T>();
		empty = true;
		full = false;
	}

	/**
	 * Return the next element from the queue, 
	 * waiting until the queue is not empty.
	 * @return first element in the queue
	 */
	public synchronized T dequeue() {
		while (empty) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		T ans = queue.removeFirst();
		if (queue.size() == 0) {
			empty = true;
			this.notifyAll();
		}
		full = false;
		this.notifyAll();
		return ans;
	}

	/**
	 * Add an element to the queue,
	 * blocking until the queue is not full
	 * @param thing added to end of the queue
	 */
	public  synchronized void enqueue(T thing) {
		while (full) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		queue.addLast(thing);
		if (queue.size() == maxSize) {
			full = true;
			this.notifyAll();
		}
		empty = false;
		this.notifyAll();
	}

	private static void sleep(int secs) {
		try {
			Thread.sleep(secs*1000);
		}
		catch (Throwable t) {

		}
	}

	/**
	 * Test code: do NOT change this for the demo
	 */
	public static void main(String[] args) {
		final BlockingQueue<Integer> queue = new BlockingQueue<Integer>(10);

		//
		// Add 20 things to the queue
		//
		new Thread() {
			public void run() {
				BlockingQueue.sleep(3);
				for (int i=0; i < 20; ++i) {
					queue.enqueue(new Integer(i));
					System.out.println("Enqueued " + i);
				}
			}
		}.start();
		System.out.println("Started enqueue thread");

		//
		// while at the same time...
		//

		System.out.println("Trying to dequeue - should wait a bit");
		//
		// Pull 20 things off the queue
		//
		for (int i=0; i < 20; ++i) {
			System.out.println("dequeue " + queue.dequeue().toString());
			sleep(1);
		}

	}

}
