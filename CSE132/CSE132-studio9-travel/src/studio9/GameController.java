package studio9;

import java.util.HashSet;
import java.util.Set;

public class GameController extends Thread {

	BlockingQueue<Runnable> runqueue; /* thread-safe */
	Set<PlayerHandler> players;

	public GameController() {
		runqueue = new BlockingQueue<Runnable>(10);
		players = new HashSet<PlayerHandler>();
	}

	public void addRunnable(Runnable r) {
		runqueue.enqueue(r);
	}

	//
	// Even though the following methods are called
	//    from multiple threads concurrently,
	//    none of them needs synchronization.
	// Each one simply adds a Runnable to the queue,
	//    and since the queue is serviced one Runnable
	//    at a time, the work is not performed in a
	//    multithreaded manner
	//
	public void addPlayer(final PlayerHandler h) {
		addRunnable(
				new Runnable() {
					public void run() {
						players.add(h);
						message("Player joined: " + h);
						for (PlayerHandler p : players) {
							tellClient(p, "New player here! " + h);
						}
					}
				}
		);
	}

	public void removePlayer(final PlayerHandler h) {
		addRunnable(
				new Runnable() {
					public void run() {
						players.remove(h);
						message("Player left: " + h);
					}
				}
		);
	}

	public void killPlayer(final int num) {
		addRunnable(
				new Runnable() {
					public void run() {
						for (PlayerHandler p : players) {
							if (p.getPlayerNum() == num) 
								p.die();
						}
					}
				}
		);
	}

	// FIXME
	public void showPlayers() {
		//System.out.println("showPlayers not yet implemented");
		addRunnable(
				new Runnable() {
					public void run() {
						for (PlayerHandler p : players) {
							System.out.println(p.getName());
						}
					}
				});
	}

	public void tellClient(final PlayerHandler p, final String s) {
		addRunnable(
				new Runnable() {
					public void run() {

						// FIXME
						p.tellClient(s);
					}
				}
		);
	}

	public void shutDown() {
		addRunnable(
				new Runnable() {
					public void run() {
						System.out.println("GameController shutting down");
						System.exit(0);
					}
				}
		);
	}

	public void message(final String s) {
		addRunnable(
				new Runnable() {
					public void run() {
						System.out.println("message: " + s);
					}
				}
		);
	}

	public void run() {
		new Server(3989, this).start();
		while (true) {
			Runnable r = runqueue.dequeue();
			r.run();
		}
	}

	private static void snooze(int secs) {
		try {
			Thread.sleep(secs*1000);
		}
		catch(Throwable t) {
			throw new Error("Bad sleep: " + t);
		}
	}

	public static void main(String[] args) {
		//
		// Start the game server
		//
		GameController game = new GameController();
		game.start();
		//
		// Wait 5 seconds so its socket is surely ready
		//
		snooze(5);
		//
		// Run the local console handler
		//
		new ConsoleHandler(game).start();

		//
		// Simulate some clients that will attach to the
		//    server using localhost
		//
		String[] names = { "Alice", "Bob", "Carol", "Devon", "Eugeue" };
		for (int i=0; i < names.length; ++i) {
			new SimClient("localhost", 3989, names[i]).start();
		}
	}

}
