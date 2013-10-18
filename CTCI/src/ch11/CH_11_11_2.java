package ch11;
import java.util.*;

public class CH_11_11_2 {
	public static String sortChars(String s) {
		char content[] = s.toCharArray();
		Arrays.sort(content);
		return new String(content);
	}

	public static class AnagramComparator implements Comparator<String> {
		public int compare(String s1, String s2) {
			String sortedS1 = sortChars(s1);
			String sortedS2 = sortChars(s2);
			return sortedS1.compareTo(sortedS2);
		}
	}
	
	public static void sortAnagram(String array[]) {
		Hashtable<String,LinkedList<String>> hash = new 
								Hashtable<String,LinkedList<String>>();
		for (int i = 0; i < array.length; i++) {
			String key = sortChars(array[i]);
			if (!hash.containsKey(key)) {
				hash.put(key, new LinkedList<String>());
			}
				hash.get(key).add(key);
			}
		
		Iterator<String> it = hash.keySet().iterator();
		int index = 0;
		while (it.hasNext()) {
			String key = it.next();
			for (String t : hash.get(key)) {
				array[index] = t;
				index++;
			}
		}
	}
	
	public static void main(String args[]) {
		String arr[] = {"abc", "efg", "def", "cba", "egf", "fde", "cba"};
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
		//Arrays.sort(arr, new AnagramComparator());
		sortAnagram(arr);
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
	}
}
