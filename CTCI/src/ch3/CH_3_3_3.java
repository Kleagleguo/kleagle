package ch3;

import java.util.*;

public class CH_3_3_3 {
	public static class SetOfStacks {
		public ArrayList<Stack<Integer>> stacks;
		private int threshold;	
		private int currentStack;
	
		public SetOfStacks(int threshold) {
			stacks = new ArrayList<Stack<Integer>>();
			this.threshold = threshold;
			currentStack = 0;
			stacks.add(new Stack<Integer>());
		}
	
		public void push(int value) {
			if (stacks.get(currentStack).size() >= threshold) {
				stacks.add(new Stack<Integer>());
				currentStack++;
				stacks.get(currentStack).push(value);
			} else {
				stacks.get(currentStack).push(value);
			}
		} 
	
		public int pop() {
			if (!stacks.get(currentStack).empty()) {
				int value = stacks.get(currentStack).pop();
				return value;
			} else if (currentStack == 0) {
				System.out.println("stack is empty");
				return (Integer) null;
			} else {
				stacks.remove(currentStack);
				currentStack--;
				int value = pop();
				return value;
			}
		}
	
		public int popAt(int index) {
			if (!stacks.get(index).empty()) {
				int value = stacks.get(index).pop();
					return value;
			} else {
				System.out.println("stack " + index + " is empty");
				return (Integer) null;
			}
		}
	}
	
	public static void main(String args[]) {
		SetOfStacks sos = new SetOfStacks(3);
		for (int i = 1; i <= 9; i++) {
			sos.push(i);
		}
		System.out.println(sos.stacks);
		sos.pop();
		System.out.println(sos.stacks);
		sos.pop();
		System.out.println(sos.stacks);
		sos.pop();
		System.out.println(sos.stacks);
		sos.pop();
		System.out.println(sos.stacks);
	}
}
