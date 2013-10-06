package ch2;

import java.util.*;

public class CH_2_2_6 {
	public static class Node {
		public Node next;
		public int data;
		
		public Node(int d) {
			this.next = null;
			this.data = d;
		}
		
		public void printList() {
			Node temp = this;
			while (temp != null) {
				System.out.print(this.data + "");
			}
			System.out.println();
		}
	}

	public static Node checkLoop(Node head) {
		HashSet<Node> nodes = new HashSet<Node>();
		Node it = head;
		while (it != null) {
			if (nodes.contains(it)) {
				return it;
			}
			nodes.add(it);
			it = it.next;
		}
		return null;
	}
	
	public static void main(String args[]) {
		Node a = new Node(1);
		Node b = new Node(2);
		Node c = new Node(3);
		Node d = new Node(4);
		Node e = new Node(5);
		a.next = b;
		b.next = c;
		c.next = d;
		d.next = e;
		e.next = c;
		System.out.println(checkLoop(a).data);
		
	}
}
