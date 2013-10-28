/** 
 * Create a grid of rooms, hallways between the rooms
 * Then, hallways are picked at random with their adjoining doors
 * @author 			Bingkun Guo
 * Lab Section:		C
 * Email:			guobingkun@wustl.edu
 * Lab:				Lab3B:Mazes 
 * File:			Maze.java
 */
package lab3a;
import java.awt.Container;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;

import lab3a.model.Hall;
import lab3a.model.HorizHall;
import lab3a.model.Room;
import lab3a.model.VertHall;
import lab3a.view.RoomViz;

public class Maze {
	public static boolean debug=false;
	final private int rows, cols;
	final private JFrame frame;

	private ArrayList<Hall> halls;	// all halls
	private List<Room> rooms;		// all rooms

	/**
	 * Constructor of the maze
	 * @param rows Row number of the maze
	 * @param cols Column number of the maze
	 */
	public Maze(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		frame = new JFrame("Maze");
		frame.setSize(1200,800);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * return the row number of this maze
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * return the column number of this maze
	 */
	public int getCols() {
		return cols;
	}

	/**
	 * return all the rooms in this maze
	 */
	public List<Room> getRooms () {
		return rooms;
	}

	/**
	 * initialize the maze numTimes times
	 * @param numTimes
	 */
	public void demo(int numTimes) {
		for (int i=0; i < numTimes; ++i) {
			initMaze();
			workMaze();
			Lab.sleep(1000);
		}
	}

	/**
	 * Generalize the maze into a m*n maze
	 */
	public void initMaze() {	
		halls = new ArrayList<Hall>();

		Container viz = frame.getContentPane();
		viz.removeAll();
		viz.setLayout(new GridLayout(this.rows,this.cols));

		rooms = new LinkedList<Room>();
		for (int i = 0; i < rows*cols; i++) {
			Room n = new Room();
			rooms.add(n);
			viz.add(new RoomViz(n));
		}

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++ ) {
				if (j + 1 < cols) {
					halls.add(new HorizHall(rooms.get( i*cols + j), rooms.get( i*cols + j + 1 ) ) );
				}
			}
		}

		for (int j = 0; j < cols; j++) {
			for (int i = 0; i < rows; i++) {
				if (i + 1 < rows) {
					halls.add(new VertHall(rooms.get( i*cols + j), rooms.get( i*cols + j + cols) ) );
				}
			}
		}


		//
		// When all done, make sure the viz shows itself
		//
		frame.validate();
		frame.repaint();

	}

	/**
	 * make workMaze public so it can be called outside the class
	 */
	public void workMaze() {	
		// Reset the rooms to have just themselves in their sets
		for (Room r : rooms) {
			HashSet<Room> s = new HashSet<Room>();
			s.add(r);
			r.setSet(s);
		}
		shuffleHalls();
		System.out.println("Starting with halls ordered: " + halls);

		for (Hall h : halls) {
			h.attemptToConnectRooms();
		}

		// Wait for all the halls' work to finish
		for (Hall h : halls) {
			try { h.getThread().join(); } 
			catch (Throwable t) { throw new Error("cannot happen " + t); }
		}

		System.out.println("Done");
	}

	/**
	 * Switch up the halls so we get random behavior
	 * in establishing the maze.
	 */
	private void shuffleHalls() {
		Random r = new Random();
		int size = halls.size();
		for (int i=size; i > 0; --i) {
			int at = r.nextInt(size);
			Hall move = halls.get(i-1);
			Hall old  = halls.get(at);
			halls.set(at, move);
			halls.set(i-1, old);
		}
	}

	/**
	 * A sleep method that catches the exception so
	 *   that other code need not worry about that.
	 * @param ms
	 */

	public static void sleep(int ms) {
		try {
			Thread.sleep(ms); 
		} catch(Throwable t) { }
	}

	/**
	 * return all the halls in the maze
	 * will be used when I check the maze
	 */
	public ArrayList<Hall> getHalls() {
		return halls;
	}

}
