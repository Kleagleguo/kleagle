package studio7;

public class DemoHex {
	
	public static void main(String[] args) {
		for (int i=0; i < 256; ++i) {
			System.out.println("Int " + i + " is " + Integer.toHexString(i));
		}
	}

}
