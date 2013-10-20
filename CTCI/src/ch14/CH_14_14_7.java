package ch14;

import java.util.*;

public class CH_14_14_7 {
	public static class CircularArray<T> implements Iterable<T> {

		public T[] items;
		private int head = 0;

		public CircularArray(int size) {
			items = (T[]) new Object[size];
		}
		
		public int convert(int index) {
			while (index < 0) {
				index += items.length;
			}
			return (head + index) % items.length;
		}

		public void rotate(int shiftRight) {
			head = convert(shiftRight);
		}
	
		public T get(int i) {
			//return items[convert(i % (items.length - head))];
			if (i < 0 || i > items.length) {
				try {
					throw new Exception();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (i < head) {
				return items[i + items.length - head];
			} else {
				return items[i - head];
			}
		}
	
		public void set(int i, T item) {
			items[convert(i)] = item;
		}
	
		private class CircularArrayIterator<T1> implements Iterator<T1> {
			private int _current = -1;
			private T1[] _items;
			
			public CircularArrayIterator(CircularArray<T1> array) {
				_items = array.items;
			}
	
			public boolean hasNext() {
				return _current < _items.length - 1;
			}
		
			public T1 next() {
				_current++;
				T1 item = (T1) _items[convert(_current)];
				return item;
			}
		
			public void remove() {
		
			}
			
		}
		
		public Iterator<T> iterator() {
			return new CircularArrayIterator<T>(this);
		}
		
		public static void main(String[] args) {
			CircularArray<Integer> circularArr = new CircularArray<Integer>(5);
			for (int i = 0; i < 5; i++) {
				circularArr.set(i, i+1);
			}
//			for (int item : circularArr) {
//				System.out.print(item + " ");
//			}
			for (int i = 0; i < 5; i++) {
				System.out.print(circularArr.get(i) + " ");
			}
			circularArr.rotate(-1);
			System.out.println();
//			for (int item : circularArr) {
//				System.out.print(item + " ");
//			}
			for (int i = 0; i < 5; i++) {
				System.out.print(circularArr.get(i) + " ");
			}
			circularArr.get(10);
		}
	
	}
}
