package sumOfPrime;

/**
 * Write a program to determine the sum of the first 1000 prime numbers.
 * @author Bingkun
 *
 */
public class Main {
	public static boolean isPrime(int n) {
		if (n == 1)
			return false;
		if (n % 2 == 0 && n != 2) 
			return false;
		for (int i = 3; i*i <= n; i+=2) {
			if (n % i == 0)
				return false;
		}
		return true;
	}
	
	public static void main(String args[]) {
		int count = 0;
		int i = 1;
		int sum = 0;
		while (count < 1000) {
			if (isPrime(i)) {
				System.out.println(i);
				sum += i;
				count++;
			}
			i++;
		}
		System.out.println(sum);
	}
}
