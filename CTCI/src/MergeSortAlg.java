public class MergeSortAlg {
	// A[] = 1 3 2 4 5; 
	// mid = (0+5)/2 = 2 --> A[mid] = 2

	public static void mergeSort(int[] arr, int left, int right) {
		if (left < right) {
			int mid = (left + right) / 2;
			mergeSort(arr, left, mid);
			mergeSort(arr, mid+1, right);
			merge(arr, left, mid, right);
		}
	}

	// A[] = 1,3,5, 2,4   mid = 2 
	// A[0] = 1, A[1] = 2, A[2] = 3, A[3] = 4, A[4] = 5
	// B[] = 2,4 | 1,3,5  mid = 1
	// A[0] = 1, A[1] = 2, A[2] = 3, A[3] = 4, 
	public static void merge(int[] arr, int left, int mid, int right) {
		
		int leftSize = mid - left + 1;
		int rightSize = right - mid;
		int[] leftHelper = new int[leftSize + 1];
		int[] rightHelper = new int[rightSize + 1];
		
		for (int i = 0; i < leftSize; i++) {
			leftHelper[i] = arr[left + i];
		}
		for (int i = 0; i < rightSize; i++) {
			rightHelper[i] = arr[mid + 1 + i];
		}
		leftHelper[leftSize] = Integer.MAX_VALUE;
		rightHelper[rightSize] = Integer.MAX_VALUE;
		
		int i = 0;
		int j = 0;
		for (int curr = left; curr <= right; curr++) {
			if (leftHelper[i] < rightHelper[j]) {
				arr[curr] = leftHelper[i];
				i++;
			} else {
				arr[curr] = rightHelper[j];
				j++;
			}
		}
	}
	
	// 1,3 | 2
	// 1 | 3
	public static void main(String[] args) {
		int[] arr = {1,2,3,4,5,6,7,8,9};
		mergeSort(arr, 0, arr.length-1);
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
	}
}
