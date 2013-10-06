package ch1;

public class CH1_1_1 {
	public static void main(String args[]) {
		String input = args[0];
		short counts[] = new short[256];
		boolean unique = true;
		for (int i = 0; i < input.length(); i++) {
			if (counts[input.charAt(i)] > 0) {
				unique = false; 
				break;
			}
			counts[input.charAt(i)] += 1;
		}

		if (unique) {
			System.out.println("it's unique");
		} else {
			System.out.println("It's not unique!");
		}
	}
}
