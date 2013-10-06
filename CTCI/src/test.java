import java.util.*;

public class test {
	public static void main(String args[]) {
		Stack<Integer> s = new Stack<Integer>();
		s.push(1); 
		if (s.empty()) {
			System.out.println("empty");
		} else {
			System.out.println("not empty");
		}
		s.pop();
		if (s.empty()) {
			System.out.println("empty");
		} else {
			System.out.println("not empty");
		}
	}
}
