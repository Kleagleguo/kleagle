package multiplicationTable;

/**
 * Print out the grade school multiplication table up to 12*12.
 * 
 * @author Bingkun
 *
 */
public class Main {
	
	public static void main(String args[]) {
		final int twelve = 12;
		for (int i = 1; i <= twelve; i++) {
			for (int j = 1; j <= twelve; j++) {
				System.out.printf("%4d", i*j);
			}
			System.out.println();
		}
	}
}
