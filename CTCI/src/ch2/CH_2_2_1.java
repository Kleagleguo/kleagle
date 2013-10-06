package ch2;
import java.util.*;

public class CH_2_2_1 {


	public static class Node {
	    public Node next;
	    public int data;            
	    
	    public Node(int d) {
	        data = d;
	        next = null;
	    }

	    public void printList() {
	        Node temp = this.next;
	        System.out.print(this.data + "");
	        while (temp != null) {
	        	System.out.print(temp.data + "");
	        	temp = temp.next;
	        }
	        System.out.println();
	    }    
	}

	public static void removeDuplicate(Node head) {
	    Node ptr = head.next;
	    HashMap<Integer,Boolean> checker = new HashMap<Integer, Boolean>();
	    checker.put(head.data,true);
	    while (ptr != null) {
	        if (checker.get(ptr.data) == null) {
	            checker.put(ptr.data,true); 
	        } else {
	        	head.next = ptr.next;
	  }
	  head = ptr;
	  ptr = ptr.next;
	    }
	}

	public static void main(String args[]) {
	    Node a = new Node(1);   
	    Node b = new Node(2);
	    Node c = new Node(2);
	    Node d = new Node(3);
	    Node e = new Node(4);
	    Node f = new Node(4);
	    Node g = new Node(5);
	    Node h = new Node(5);
	    a.next = b;
	    b.next = c;
	    c.next = d;
	    d.next = e;
	    e.next = f;
	    f.next = g;
	    g.next = h;
	    a.printList();
	    removeDuplicate(a);
	    a.printList();
	}

}
