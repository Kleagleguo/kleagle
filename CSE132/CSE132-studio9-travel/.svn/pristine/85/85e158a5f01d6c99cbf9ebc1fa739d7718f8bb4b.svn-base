package studio9;

public class GameServerOnly {
	private static void snooze(int secs) {
		try {
			Thread.sleep(secs*1000);
		}
		catch(Throwable t) {
			throw new Error("Bad sleep: " + t);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			java.net.InetAddress localMachine = java.net.InetAddress.getLocalHost();
			System.out.println("Hostname of local machine: " + localMachine.getHostName());
		}
		catch(java.net.UnknownHostException ex) {
			//handle exception
		} 
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


	}

}
