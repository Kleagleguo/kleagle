package auctionAlg;

import java.util.ArrayList;

/**
 * Stable marriage algorithm
 * Gale¨CShapley algorithm
 * http://en.wikipedia.org/wiki/Stable_marriage_problem
 * @author Bingkun
 *
 */
public class SMP {
	
	public static ArrayList<Man> men = new ArrayList<Man>();
	public static ArrayList<Woman> women = new ArrayList<Woman>();
	public static ArrayList<Man> freeMan = new ArrayList<Man>();
	
	public static class Man {
		public int id;
		public int prefList[];
		public int nextToProposeIndex;
		
		public Man(int id, int list[]) {
			this.id = id;
			prefList = list;
			nextToProposeIndex = 0;
		}
		
		public boolean propseToNextFeasibleWoman() {
			int womanId = prefList[nextToProposeIndex];
			nextToProposeIndex++;
			return women.get(womanId).engageSomeone(id);
		}
	}
	
	public static class Woman {
		public int id;
		public int prefList[];
		public boolean engaged;
		private int fianceId;
		
		public Woman(int id, int list[]) {
			this.id = id;
			prefList = list;
			engaged = false;
			fianceId = 999;
		}
		
		public boolean engageSomeone(int id) {
			if (!engaged) {
				fianceId = id;
				engaged = true;
				return true;
			} else if (lookUp(id) < lookUp(fianceId)) {
				freeMan.add(men.get(fianceId));
				fianceId = id;
				return true;
			} else {
				return false;
			}
		}
		
		private int lookUp(int index) {
			for (int i = 0; i < prefList.length; i++) {
				if (prefList[i] == index) {
					return i;
				}
			}
			throw new RuntimeException("impossible");
		}
	}
	
	public static void main(String args[]) {
		
		int l1[] = {2, 3, 4, 5, 1}; int l2[] = {3, 4, 5, 1, 2};
		int l3[] = {5, 1, 4, 2, 3}; int l4[] = {3, 1, 2, 4, 5};
		int l5[] = {1, 5, 2, 3, 4};
		
		int k1[] = {1, 2, 3, 5, 4}; int k2[] = {2, 1, 4, 5, 3};
		int k3[] = {3, 2, 5, 1, 4}; int k4[] = {4, 5, 1, 2, 3};
		int k5[] = {5, 1, 2, 3, 4};
		
		int arr[] = {0, 1, 2, 3, 4};
		Permutation p = new Permutation(arr);
		
		while (p.hasNext()) {
			k1 = p.getNext().clone();
			for (int i = 0; i < k1.length; i++) {
				k1[i]++;
				//System.out.print(k1[i] + " ");
			}
			//System.out.println();
			
			men = new ArrayList<Man>();
			women = new ArrayList<Woman>();
			freeMan = new ArrayList<Man>();
			
			Man m1 = new Man(1, l1); Man m2 = new Man(2, l2);
			Man m3 = new Man(3, l3); Man m4 = new Man(4, l4);
			Man m5 = new Man(5, l5);
			
			Woman w1 = new Woman(1, k1); Woman w2 = new Woman(2, k2);
			Woman w3 = new Woman(3, k3); Woman w4 = new Woman(4, k4); 
			Woman w5 = new Woman(5, k5);

			// add padding for men and women, since the ranking indices don't start from 0
			men.add(new Man(999, null)); men.add(m1); men.add(m2); men.add(m3); men.add(m4); men.add(m5);
			women.add(new Woman(999, null)); women.add(w1); women.add(w2); women.add(w3); women.add(w4); women.add(w5);
			
			freeMan.add(men.get(1)); freeMan.add(men.get(2)); freeMan.add(men.get(3));
			freeMan.add(men.get(4)); freeMan.add(men.get(5));
			
			while (freeMan.size() > 0) {
				if (freeMan.get(0).propseToNextFeasibleWoman()) {
					freeMan.remove(0);
				} else {
					freeMan.add(freeMan.remove(0));
				}
			}
			
			
			women.remove(0);
			
			// w1's original preference list was 1 2 3 5 4, 
			// but changing this preference list might get w1 a better fiance
			for (Woman w : women) {
				if (w.id == 1) {
					if (w.fianceId == 3) {
						System.out.println("(" + w.fianceId + "," + w.id + ")");
						for (int i = 0; i < k1.length; i++) {
							System.out.print(k1[i] + " ");
						}
						System.out.println("\n#####################################");
					}
				}
			}
		}

	}
}
