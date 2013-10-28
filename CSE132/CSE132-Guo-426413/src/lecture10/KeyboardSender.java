package lecture10;

import java.io.OutputStream;

import javax.swing.JOptionPane;

public class KeyboardSender implements Runnable {

	final protected OutputStream out;

	public KeyboardSender(OutputStream out) {
		this.out = out;
	}

	@Override
	public void run() {
		try {
			while(true) {
				//
				// Get a String by prompting
				//
				String message = JOptionPane.showInputDialog("Message?");
				if (message != null) {
					System.out.println("Sending -->"+message+"<--");
					//
					// Send the String's bytes.  This is a different format
					//   than if I tried to send the String itself!
					// Network protocols usually expect one byte per character
					//
					out.write(message.getBytes());
					//
					//  Send carriage-return line-feed as if the message
					//     were typed in followed by "enter"
					//  Most servers need only the linefeed, but I'm
					//     sending the carriage-return just in case....
					//
					out.write('\r');
					out.write('\n');
					//
					//  Make sure nothing is waiting to be sent
					//
					out.flush();
				}
				else {
					return;
				}
			}
		}
		catch (Throwable t) {
			throw new Error("Keyboard sender problem " + t);
		}
	}

}
