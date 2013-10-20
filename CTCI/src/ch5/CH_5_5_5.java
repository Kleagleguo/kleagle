package ch5;

public class CH_5_5_5 {
	public static void countBits(int n) {
		String bn = Integer.toBinaryString(n);
		System.out.println(n + " : " + bn + " size = " + bn.length());
		int count = 0;
		while (n != 0) {
			//count += n & 1;
			count++;
			n = n >>> 1;
		}
		System.out.println("it has " + count + " bits");
	}
	
	public static void main(String[] args) {
		int a = 30;
		countBits(30);
	}
}
