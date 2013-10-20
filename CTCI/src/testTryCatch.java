
public class testTryCatch {
	public static String foo() {
		try {
			System.out.println("start foo");
			int x = 0;
			if (x == 0) {
				throw new Exception("it's zero!!!");
			}
			System.out.println("all is well");
			return "foo";
		} catch (Exception e) {
			System.out.println(e.toString());
			System.out.println("i am here");
			return "in exception";
		} finally {
			System.out.println("hahaha");
		}
	}
	
	public static void main(String[] args) {
		String s = foo();
		System.out.println(s);
	}
}
