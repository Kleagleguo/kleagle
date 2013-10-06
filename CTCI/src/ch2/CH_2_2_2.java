package ch2;

public class CH_2_2_2 {
	public static class Node {
	    public Node next;
	    public int data;

	    public Node(int d) {
	        this.data = d;
	        this.next = null;
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
	    
		public Node findK(int k) {
		    Node kth = this;
		    Node target = this;
		    for (int i = 0; i < k; i++) {
			  if (kth.next == null) {
		            System.out.println(k + " must be less than the length of the linkedlist");
				return null;
		        }
		        kth = kth.next;  
		    }
		    
		    while (kth.next != null) {
			  kth = kth.next;
		        target = target.next;    
		    }
		    
		    return target;
		}
	}



	public static void main(String args[]) {
	    Node a = new Node(1);   
	    Node b = new Node(2);
	    Node c = new Node(3); 
	    Node d = new Node(4); 
	    Node e = new Node(5);
	    Node f = new Node(6);
	    Node g = new Node(7);
	    Node h = new Node(8);
	    Node i = new Node(9);
	    Node j = new Node(10);
	    Node k = new Node(11);
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
	    Node target = a.findK(5);
	    if(target != null) {
	        System.out.println("5th to last element is " + target.data);
	    }
	}
}
