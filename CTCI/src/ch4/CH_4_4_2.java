package ch4;

import java.util.*;

public class CH_4_4_2 {
	
	public enum State {
		Unvisited, Visiting, Visited;
	}
	
	public static class Graph {
		public ArrayList<Vertex> vertices;
		
		public Graph() {
			vertices = new ArrayList<Vertex>();
		}
	
		public ArrayList<Vertex> getVertices() {
			return vertices;
		}
	
		public int size() {
			return vertices.size();
		}
	
		public void addVertex(Vertex v) {
			vertices.add(v);
		}
	}

	public static class Vertex {
		public int data;
		public ArrayList<Vertex> neighbours;
		public State state;
		
		public Vertex(int d) {
			neighbours = new ArrayList<Vertex>();
			data = d;
			state = State.Unvisited;
		}
	}
	
	public static boolean hasRouteBFS(Vertex a, Vertex b) {
		LinkedList<Vertex> queue = new LinkedList<Vertex>();
		queue.add(a);
		a.state = State.Visiting;
		while (!queue.isEmpty()) {
			Vertex u = queue.removeFirst();
			if (u != null) {
				for (Vertex neighbour : u.neighbours) {
					if (neighbour == b) {
						return true;
					}
					if (neighbour.state == State.Unvisited) {
						neighbour.state = State.Visiting;
						queue.add(neighbour);
					}
				}
				u.state = State.Visited;
			}
		}
		return false;
	}
	
	public static boolean hasRouteDFS(Vertex a, Vertex b) {
		a.state = State.Visiting;
		for (Vertex neighbour : a.neighbours) {
			if (neighbour == b) {
				return true;
			}
			if (neighbour.state == State.Unvisited) {
				if(hasRouteDFS(neighbour, b)) {
					return true;
				}
			}
		}
		a.state = State.Visited;
		return false;
	}
	
	public static void main(String args[]) {
		Vertex a = new Vertex(1);
		Vertex b = new Vertex(2);
		Vertex c = new Vertex(3);
		Vertex d = new Vertex(4);
		Vertex e = new Vertex(5);
		Vertex f = new Vertex(6);
		a.neighbours.add(b);
		b.neighbours.add(c);
		c.neighbours.add(d);
//		b.neighbours.add(f);
//		f.neighbours.add(e);
		
		if (hasRouteDFS(a,f)) {
			System.out.println("has route");
		} else {
			System.out.println("no route");
		}
	}
	
}
