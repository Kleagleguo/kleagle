package ch4;

import java.util.Arrays;


public class CH_4_4_6 {
	public static class TreeNode {
		public TreeNode left;
		public TreeNode right;
		public TreeNode parent;
		public int data;
		
		public TreeNode(int d) {
			data = d;
			parent = null;
			left = null;
			right = null;
		}
	}
	
	public static TreeNode findSmallest(TreeNode x) {
		while (x.left != null) {
			x = x.left;
		}
		return x;
	}

	public static TreeNode findSucc(TreeNode x) {
		if (x.right == null) {
			TreeNode y = x.parent;
			while (y != null) {
				if (y.right == x) {
					x = y;
					y = y.parent;
				} else {
					break;
				}
			}
			return y;
		} else {
			return findSmallest(x.right);
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
			root.left.parent = root;
			root.right.parent = root;
		} else if (length == 2) {
			root.left.parent = root;
			root.left = new TreeNode(arr[0]);
			root.right = null;
		} else if (length == 1) {
			root.left = null;
			root.right = null;
		}
		return root;
	}
	
	public static void main(String args[]) {
		int arr[] = new int[7];
		for (int i = 1; i <= 7; i++) {
			arr[i-1] = i;
		}
		TreeNode root = toBST(arr);
		System.out.println("right.right.right = " + root.right.right.data);
		System.out.println(findSucc(root.left.right).data);
	}
	
}
