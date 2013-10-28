package lecture;
import studio5.RWLock;

public class RWLockingRectangle {

	private int width, height;

	private static Object justOne    = new Object();
	private static int    totalRects = 0;

	private RWLock rwlock;

	/**
	 * Constructors never need a lock unless they reference
	 *   static variables.  For example, what would be needed
	 *   if the constructor keeps count of the total number of
	 *   Rectangle objects instantiated?
	 * @param w width upon construction
	 * @param h height upon construction
	 */
	public RWLockingRectangle(int w, int h) {
		rwlock = new RWLock();
		setDimens(w, h);
	}

	/**
	 * What kind of lock does this method need?
	 *   it changes the object, so a writer's lock
	 * @param w
	 * @param h
	 */
	public void setDimens(int w, int h) {
		try {
			rwlock.acquireReadWrite();
			width  = w;
			height = h;
		}
		// finally blocks execute after the try,
		//  no matter how the try exits (normally or
		//    by exception)
		finally {
			rwlock.release();
		}
	}

	/**
	 * What kind of lock does this method need?
	 * @return an array that holds the current width and height
	 */
	public int[] getDimens() {
		try {
			rwlock.acquireReadOnly();
			int w = getWidth();
			int h = getHeight();
			return new int[] { w, h };
		}
		finally {
			rwlock.release();
		}
	}

	/**
	 * This method actually does not need a lock. Why?
	 * @return
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Same here, no lock needed...why?
	 * @return
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * What kind of lock is needed here?
	 * @return
	 */
	public int getPerimeter() {
		try {
			rwlock.acquireReadOnly();
			return 2*(width+height);
		}
		finally {
			rwlock.release();
		}
	}

	/**
	 * What kind of locks do we need here
	 * so .equals works correctly?
	 */
	public boolean equals(Object o) {
		RWLockingRectangle other = (RWLockingRectangle)o;


		return false;
	}

	/**
	 * What kind of locks do we need here
	 * so .hashCode works correctly?
	 */
	public int hashCode() {


		return 0;


	}

	/**
	 * What kind of lock is needed here?
	 * What if toString() revealed more about this Rectangle?
	 */
	public String toString() {
		return "A rectangle";
	}
}