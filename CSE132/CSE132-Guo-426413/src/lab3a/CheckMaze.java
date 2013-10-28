/** 
 * Check whether the maze has a loop in it
 * @author 			Bingkun Guo
 * Lab Section:		C
 * Email:			guobingkun@wustl.edu
 * Lab:				Lab3B:Mazes 
 * File:			CheckMaze.java
 */
package lab3a;
import java.util.HashSet;
import java.util.Set;

import lab3a.model.Hall;
import lab3a.model.Room;

/**
 * Check a maze to make sure it has no cycle and that all rooms are reachable.
 *
 */
public class CheckMaze {

	final private Maze maze;
	final private Room root;
	final int expect;
	final Set<Room> visited;
	int count;

	/**
	 * Constructor 
	 * @param maze The maze to be checked
	 * @param root Any room of the maze:  the check starts there.
	 */
	public CheckMaze(Maze maze, Room root) {
		this.maze = maze;	
		this.expect = maze.getRows() * maze.getCols();
		this.root = root;
		this.visited = new HashSet<Room>();
		this.count = 0;
	}

	/**
	 * Perform the check.
	 * @return true if the check succeeds.  If it doesn't succeed, an exception is thrown.
	 */
	public boolean runCheck() {
		for (Room r : maze.getRooms()) {
			if (r.getSet() != root.getSet()) {
				return false;
			}
		}

		for (Hall h : maze.getHalls()) {
			if (h.isOpen()) {
				count++;
			}
		}

		return (count == (maze.getRooms().size() - 1) );

	}

}