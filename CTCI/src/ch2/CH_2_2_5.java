package ch2;

public class CH_2_2_5 {
	public static class Node {
	    public Node next;
		public int data;

		public Node(int d) {
			data = d;
		}

    public void printList() {
		Node temp = this.next;
       	System.out.print(this.data + " ");
    	while (temp != null) {
    		System.out.print(temp.data + " ");
    		temp = temp.next;
    	}
	  		System.out.println();
    	}
	}

	public static Node addReverse(Node head1, Node head2) {
		Node it1 = head1;
		Node it2 = head2;
		Node sumHead = null;
		StringBuffer sb1 = new StringBuffer();
		StringBuffer sb2 = new StringBuffer();	
		StringBuffer sb3 = null;		
		while (it1 != null) {
			sb1.append(it1.data);
			it1 = it1.next;		
		}

		while (it2 != null) {
			sb2.append(it2.data);
			it2 = it2.next;
		}

		int num1 = Integer.parseInt(sb1.reverse().toString());
		int num2 = Integer.parseInt(sb2.reverse().toString());	
		int sum = num1 + num2;
		String strSum = String.valueOf(sum);
		sb3 = new StringBuffer(strSum);
		strSum = sb3.reverse().toString();
		sumHead = new Node(Character.getNumericValue(strSum.charAt(0)));
		Node it3 = sumHead;
		for (int i = 1; i < strSum.length(); i++) {
			it3.next = new Node(Character.getNumericValue(strSum.charAt(i)));
			it3 = it3.next;
		}
		return sumHead;
	}
	
	public static Node addForward(Node head1, Node head2) {
			Node it1 = head1;
			Node it2 = head2;
			Node sumHead = null;
			StringBuffer sb1 = new StringBuffer();
			StringBuffer sb2 = new StringBuffer();	
			StringBuffer sb3 = null;		
			while (it1 != null) {
				sb1.append(it1.data);
				it1 = it1.next;		
			}
	
			while (it2 != null) {
				sb2.append(it2.data);
				it2 = it2.next;
			}
	
			int num1 = Integer.parseInt(sb1.toString());
			int num2 = Integer.parseInt(sb2.toString());	
			int sum = num1 + num2;
			String strSum = String.valueOf(sum);
			sb3 = new StringBuffer(strSum);
			strSum = sb3.toString();
			sumHead = new Node(Character.getNumericValue(strSum.charAt(0)));
			Node it3 = sumHead;
			for (int i = 1; i < strSum.length(); i++) {
				it3.next = new Node(Character.getNumericValue(strSum.charAt(i)));
				it3 = it3.next;
			}
			return sumHead;
		}
	
	public static void main(String args[]) {
//		Node a1 = new Node(7);
//		Node a2 = new Node(1);
//		Node a3 = new Node(6);
//		a1.next = a2; a2.next = a3;
//		a1.printList();
//		
//		Node b1 = new Node(5);
//		Node b2 = new Node(9);
//		Node b3 = new Node(2);
//		b1.next = b2; b2.next = b3;
//		b1.printList();
//		
//		Node sum = addReverse(a1, b1);
//		sum.printList();
			
		Node a1 = new Node(6);
		Node a2 = new Node(1);
		Node a3 = new Node(7);
		a1.next = a2; a2.next = a3;
		a1.printList();
		
		Node b1 = new Node(2);
		Node b2 = new Node(9);
		Node b3 = new Node(5);
		b1.next = b2; b2.next = b3;
		b1.printList();
		
		Node sum = addForward(a1, b1);
		sum.printList();
		
	}

}
