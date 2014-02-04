package prime_palindrome;

/**
 * Write a program to determine the biggest prime palindrome under 1000.
 * @author Bingkun
 *
 */
public class Main {
	
	public static boolean isPrime(int n) {
		if (n % 2 == 0) 
			return false;
		for (int i = 3; i*i < n; i+=2) {
			if (n % i == 0)
				return false;
		}
		return true;
	}
	
	public static boolean isPalindrome(int n) {
		char chars[] = Integer.toString(n).toCharArray();
		int last = chars.length - 1;
		for (int i = 0; i < chars.length/2; i++) {
			if (chars[i] != chars[last-i])
				return false;
		}
		return true;
	}
	
	public static void main(String args[]) {
		for (int i = 1000; i >= 0; i--) {
			if (isPrime(i) && isPalindrome(i)) {
				System.out.println(i);
				break;
			}
		}
	}
}
