package ch3;
import java.util.*;

public class CH_3_3_2 {
	public static class myStack extends Stack<Integer> {
		private Stack<Integer> minStack;
		
		public myStack() {
			minStack = new Stack<Integer>();
		}	

		public void push(int value) {
			if (value <= getMin()) {
				minStack.push(value);
			}
			System.out.println("size = " + this.size());
			super.push(value);
			System.out.println("size = " + this.size());
		}	

		public Integer pop() {
			if (!this.empty()) {
				int value = super.pop();
				if (value == getMin()) {
					minStack.pop();
				}
				return value;
			} else {
				System.out.println("the stack is empty");
				return (Integer) null;
			}
		}
		
		public int getMin() {
			if (!minStack.empty()) {
				return minStack.peek();
			}
			return Integer.MAX_VALUE;
		}
	}
	
	public static void main(String args[]) {
		myStack ms = new myStack();
		ms.push(5);
//		ms.push(6);
//		ms.push(1);
//		ms.push(4);
//		System.out.println("min = " + ms.getMin());
//		ms.pop();
//		System.out.println("min = " + ms.getMin());
//		ms.pop();
//		System.out.println("min = " + ms.getMin());
//		ms.pop();
//		System.out.println("min = " + ms.getMin());
//		ms.pop();
//		ms.pop();
	}

}
