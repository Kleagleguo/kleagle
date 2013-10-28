package lecture;
import studio5.RWLock;

public class SynchronizedRectangle {

	private int width, height;

	private static Object justOne    = new Object();
	private static int    totalRects = 0;

	/**
	 * Constructors never need a lock unless they reference
	 *   static variables.  For example, what would be needed
	 *   if the constructor keeps count of the total number of
	 *   Rectangle objects instantiated?
	 * @param w width upon construction
	 * @param h height upon construction
	 */
	public SynchronizedRectangle(int w, int h) {
		setDimens(w, h);
		synchronized(justOne) {
			totalRects = totalRects + 1;
		}
	}

	/**
	 * What kind of lock does this method need?
	 * @param w
	 * @param h
	 */
	public synchronized void setDimens(int w, int h) {
		width  = w;
		// Unless we are careful....
		// this method is only half-done at this point
		// some other thread could read this Rectangle's
		// width and height and see this method only
		// half-worked.  That possibility breaks the
		// atomicity of setDimens
		height = h;
	}

	/**
	 * What kind of lock does this method need?
	 * @return an array that holds the current width and height
	 */
	public synchronized int[] getDimens() {
		int w = getWidth();
		// evil could happen here too if we're not careful
		int h = getHeight();
		return new int[] { w, h };
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
	public synchronized int getPerimeter() {
		return 2*(width+height);
	}

	/**
	 * What kind of locks do we need here
	 * so .equals works correctly?
	 */
	public boolean equals(Object o) {
		SynchronizedRectangle other = (SynchronizedRectangle)o;

		// have to agree on width and height to be equal
		// no need to lock both objects at same time
		// just look at dimensions and compare them
		//   getDimens is already safe
		int[] mydimens = this.getDimens();
		int[] otherdimens = other.getDimens();
		return mydimens[0] == otherdimens[0] && mydimens[1] == otherdimens[1];
	}

	/**
	 * What kind of locks do we need here
	 * so .hashCode works correctly?
	 */
	public synchronized int hashCode() {


		return width+height;


	}

	/**
	 * What kind of lock is needed here?
	 * What if toString() revealed more about this Rectangle?
	 */
	public String toString() {
		return "A rectangle";
	}
}