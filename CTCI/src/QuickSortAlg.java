import java.util.*;

public class QuickSortAlg {
	
	public static int randomInt(int min, int max) {
		Random rand = new Random();
		return rand.nextInt((max - min) + 1) + min;
	}
	
	// right indexes to the pivot we want to compare when paritioning 
	public static void quickSort(int[] arr, int left, int right) {
		if (left < right) {
			int pivotPos = partition(arr, left, right);
			quickSort(arr, left, pivotPos-1);
			quickSort(arr, pivotPos+1, right);
		}	
	}

	public static void exchange(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	public static int partition(int[] arr, int left, int right) {
		int randomPivot = randomInt(left, right);
		exchange(arr, right, randomPivot);
		int i = left - 1;
		int pivot = arr[right];
		for (int k = left; k <= right-1; k++) {
			if (arr[k] < pivot) {
				i++;
				exchange(arr, i, k);
			}
		}
		exchange(arr, i+1, right);
		return i+1;
	}
	
	public static void main(String[] args) {
		int[] arr = {36,23,12,15,20,31,2,3,59,13,462,124,235,23,96,98,34,44,42,49};
		for (int i : arr) {
			System.out.print(i + " ");
		}
		quickSort(arr, 0, arr.length-1);
		System.out.println();
		for (int i : arr) {
			System.out.print(i + " ");
		}
	}
}
