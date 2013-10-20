package ch5;

public class CH_5_5_1 {
	public static void main(String[] args) {
		String bN = "10001111111";
		String bM = "10011";
		int n = Integer.parseInt(bN,2);
		//System.out.println(n);
		int m = Integer.parseInt(bM,2);
		int i = 2;
		int j = 6;
		int p = 1;
		int x = ~ ( (1 << (j+1)) - 1);
		System.out.println(Integer.toBinaryString(x));
		n = n & (x | ( (1 << i) - 1));
		n = n | (m << i);
		System.out.println(Integer.toBinaryString(n));
	}
}
