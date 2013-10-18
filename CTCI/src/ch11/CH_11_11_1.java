package ch11;

public class CH_11_11_1 {
	public static void mergeArray(int arr1[], int arr2[], int size1, int size2) {
		int index1 = size1 - 1;
		int index2 = size2 - 1;
		int ptr = arr1.length - 1;	
	
			// A: [1,3,5,,,]  // ptr = 5
			// B: [2,6,7]	
			// A: [1,3,5,,,7]	// ptr = 4
			// A: [1,3,5,,6,7] // ptr = 3
			// A: [1,3,5,5,6,7] // ptr = 2
			// A: [1,3,3,5,6,7] // ptr = 1
			// A: [1,2,3,5,6,7] // ptr = 0
		while (index1 >= 0 && index2 >= 0) {
			if (arr1[index1] < arr2[index2]) {
				arr1[ptr] = arr2[index2];
				ptr--;
				index2--;
			} else {
				arr1[ptr] = arr1[index1];
				ptr--;
				index1--;
			}
		}
	}
	
	public static void main(String args[]) {
		int arr1[] = {1,3,5,6,12,13,17,19,21,0,0,0,0,0,0,0};
		int arr2[] = {2,6,7,8,9,10,15};
		for (int i = 0; i < arr1.length; i++) {
			System.out.print(arr1[i] + " ");
		}
		System.out.println();
		mergeArray(arr1,arr2,9,7);
		for (int i = 0; i < arr1.length; i++) {
			System.out.print(arr1[i] + " ");
		}
 	}
}
