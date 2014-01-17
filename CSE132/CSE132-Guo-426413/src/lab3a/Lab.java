/** 
 * This class will start making mazes
 * @author 			Bingkun Guo
 * Lab Section:		C
 * Email:			guobingkun@wustl.edu
 * Lab:				Lab3B:Mazes 
 * File:			Lab.java
 */
package lab3a;
import java.util.List;
import java.util.Random;

import lab3a.model.Room;


/**
 * lab 3b
 * TA: GB
 * Score: 98/100
 * 
 * openDoors() works
 * attemptToConnectRooms(): connects all rooms w/o cycles, in multiple threads
 * CheckMaze checks for cycles and makes sure every room is connected
 * 
 * javadocs, comments good
 * get rid of unused variables (e.g. Hall.locked) -2pts
 *
 */



public class Lab {
	public static final int DELAY = 5;

	/**
	 * main thread of the program, once started,
	 * it will create mazes
	 */
	public static void main(String args[]) {

		Random rand = new Random();
		for (int i=0; i < 10; ++i) {
			int rows = 5 + rand.nextInt(4);
			int cols = 10 + rand.nextInt(4);
			int randomRoom = rand.nextInt(rows*cols);
			Maze m = new Maze(rows,cols);
			m.initMaze();
			m.workMaze();
			List<Room> rooms = m.getRooms();
			CheckMaze verify = new CheckMaze(m, rooms.get(randomRoom));
			if (verify.runCheck()) System.out.println("Passed check");
			else throw new Error("Validation failed");
			Lab.sleep(2000);
		}
		System.out.println("Over");
	}

	/**
	 * The sleep that is observed nominally.  Change this to speed things up.
	 */
	public static void sleep() {
		sleep(DELAY);
	}

	/**
	 * make the thread sleep for ms miliseconds
	 */
	public static void sleep(int ms) {
		try {
			Thread.sleep(ms);
		}
		catch (Exception e) {
			throw new Error("cannot happen " + e);
		}
	}
}