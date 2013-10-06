package ch2;

import java.util.*;


public class CH_2_2_3 {
	public static class Node {
	    public Node next;
	    public int data;
	    
	    public Node(int d) {
	    	  next = null;
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
	    
	    public Node partition(int x) {
	        ArrayList<Node> smallNodes = new ArrayList<Node>();
	        ArrayList<Node> bigNodes = new ArrayList<Node>();
	        Node iterator = this;
	        while (iterator != null) {
	        	if (iterator.data < x) {
	        		smallNodes.add(iterator); 
		  		} else {
		            bigNodes.add(iterator);
		  		}
	        	iterator = iterator.next;
	        }
	        
	        Node newHead = null;
	        Node temp = newHead;
	        if (smallNodes.size() != 0) {
	        	newHead = smallNodes.get(0);
				temp = newHead;
				for (int i = 1; i < smallNodes.size(); i++) {
			        temp.next = smallNodes.get(i);
			        temp = temp.next;
				}
	        } else {
	        	newHead = bigNodes.get(0);
	        	temp = newHead;
	        }
	  
			for (int i = 0; i < bigNodes.size(); i++) {
				temp.next = bigNodes.get(i);
			    temp = temp.next;
			}
			temp.next = null;
			return newHead;
	    }
	}
	
	public static void main(String args[]) {
	    Node a = new Node(6);   
	    Node b = new Node(8);
	    Node c = new Node(9); 
	    Node d = new Node(12); 
	    Node e = new Node(6);
	    Node f = new Node(6);
	    Node g = new Node(6);
	    Node h = new Node(6);
	    Node i = new Node(6);
	    Node j = new Node(6);
	    Node k = new Node(6);
	    a.next = b;
	    b.next = c;
	    c.next = d;
	    d.next = e;
	    e.next = f;
	    f.next = g;
	    g.next = h;
	    h.next = i;
	    i.next = j;
	    j.next = k;
	    a.printList();
	    Node newHead = a.partition(5);
	    newHead.printList();
	}
}
