package lecture;

import java.util.LinkedList;
import java.util.List;

public class BoundedQueue {

	private int size;

	List<Object> list;
	public BoundedQueue(int size) {
		this.size  = size;
		list = new LinkedList<Object>();
	}

	public synchronized void enq(Object o) {

		while (!  (list.size() < size) ) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}
		list.add(o);
		notifyAll();

	}

	public synchronized Object deq() {

		if (list.isEmpty()) return null;
		Object ans = list.remove(0);
		notifyAll(); // this notifies all threads to wake up

		return ans;


	}

	public synchronized Object blockingDeq() {
		while (!  (list.size() > 0) ) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}
		return deq();

	}

}
