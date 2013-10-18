package ch4;

import java.util.*;

public class CH_4_4_3 {
	public static class TreeNode {
		public TreeNode left;
		public TreeNode right;
		public int data;

		public TreeNode(int d) {
			data = d;
			left = null;
			right = null;
		}
	}

	public static TreeNode toBST(int arr[]) {
		int length = arr.length;
		TreeNode root = new TreeNode(arr[length/2]);
			
		if (length > 2) {
			int leftPart[] = Arrays.copyOfRange(arr, 0, length/2);
			int rightPart[] = Arrays.copyOfRange(arr, length/2 + 1, length);
			root.left = toBST(leftPart);
			root.right = toBST(rightPart);
		} else if (length == 2) {
			root.left = new TreeNode(arr[0]);
			root.right = null;
		} else if (length == 1) {
			root.left = null;
			root.right = null;
		}
		return root;
	}
	
	public static void treeWalk(TreeNode x) {
		if (x != null) {
			treeWalk(x.left);
			System.out.println(x.data);
			treeWalk(x.right);
		}
	}
	
	public static void main(String args[]) {
		int arr[] = new int[10];
		for (int i = 1; i <= 10; i++) {
			//System.out.print(i + " ");
			arr[i-1] = i*2 + 1;
		}
		TreeNode tn = toBST(arr);
		treeWalk(tn);
	}
}
