package ch1;

public class CH_1_1_3 {
	public static boolean isPermutation(String s1, String s2) {
		if (s1.length() != s2.length()) {
			return false;
		} else {
			int val1 = 0;
			int val2 = 0;
			for (int i = 0; i < s1.length(); i++) {
				val1 += (1 << s1.charAt(i));
			}
			for (int i = 0; i < s2.length(); i++) {
				val2 += (1 << s2.charAt(i)); 
			}
			if (val1 == val2) {
				return true;
			} else {
				return false;
			}
		}
	}

	public static void main(String args[]) {
		String s1 = args[0];
		String s2 = args[1];
		if (isPermutation(s1, s2)) {
			System.out.println("is permutation");
		} else {
			System.out.println("is not permutation");
		}

	}
}
