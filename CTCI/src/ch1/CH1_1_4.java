package ch1;

public class CH1_1_4 {
	public static void main(String args[]) {
		String input = args[0];
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) == ' ') {
				sb.append("%20");
			} else {
				sb.append(input.charAt(i));
			}
		}
		System.out.println(sb.toString());
	}
}
