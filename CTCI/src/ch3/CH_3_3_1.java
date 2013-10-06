package ch3;

public class CH_3_3_1 {
	public static class ArrStack {
		private int stackPtr1;
		private int stackPtr2;
		private int stackPtr3;
		private int arr[] = null;

		public ArrStack(int capacity) {
			stackPtr1 = 0;
			stackPtr2 = 1;
			stackPtr3 = 2;
			arr = new int[capacity];
		}

		public void push(int stackNum, int data) {
			switch (stackNum) {
			case 1:{
				arr[stackPtr1] = data;
				stackPtr1 += 3;
				break;
			}
			case 2: {
				arr[stackPtr2] = data;
				stackPtr2 += 3;
			break;
			}
			case 3: {
				arr[stackPtr3] = data;
				stackPtr3 += 3;
				break;
			}
			default:
				System.out.println("There are only 3 stacks");
			}
		}
		
		public int pop(int stackNum) {
			switch (stackNum) {
				case 1:{
					if (stackPtr1 < 0) {
						System.out.println("empty stack");
						return (Integer) null;
					}
					int top = arr[stackPtr1];
					stackPtr1 -= 3;
					return top;
				}
				case 2: {
					if (stackPtr2 < 0) {
						System.out.println("empty stack");
						return (Integer) null;
					}
					int top = arr[stackPtr2];
					stackPtr2 -= 3;
					return top;
				}
				case 3: {
					if (stackPtr3 < 0) {
					System.out.println("empty stack");
						return (Integer) null;
					}
					int top = arr[stackPtr3];
					stackPtr3 -= 3;
					return top;
				}
				default:
					System.out.println("There are only 3 stacks");
					return (Integer) null;
			}
		}
		
		public void printStacks() {
			System.out.println("stack 1:");
			for (int i = 0; i < stackPtr1; i+=3) {
				System.out.print(arr[i]);
			}
			
			System.out.println();
			
			System.out.println("stack 2:");
			for (int i = 1; i < stackPtr2; i+=3) {
				System.out.print(arr[i]);
			}
			
			System.out.println();
			
			System.out.println("stack 3:");
			for (int i = 2; i< stackPtr3; i+=3) {
				System.out.print(arr[i]);
			}
			
		}
	}
	
	public static void main(String args[]) {
		ArrStack as = new ArrStack(100);
		as.push(1, 1);
		as.push(2, 4);
		as.push(3, 7);
		as.push(1, 2);
		as.push(2, 5);
		as.push(3, 8);
		as.push(1, 3);
		as.push(2, 6);
		as.push(3, 9);
		as.printStacks();
	}
}