package ch4;

import java.util.*;

public class CH_4_4_4 {
	public static class TreeNode {
		public TreeNode left;
		public TreeNode right;
		public int data;

		public TreeNode(int d) {
			left = null;
			right = null;
			data = d;
		}
	}

	public static void collectNodes(int depth, ArrayList<LinkedList<TreeNode>> lists, TreeNode x) {
		if (x != null) {
			if (lists.size() < depth+1) {
				LinkedList<TreeNode> ll = new LinkedList<TreeNode>();
				ll.add(x);
				lists.add(ll);
			} else {
				lists.get(depth).add(x);
			}
			collectNodes(depth+1, lists, x.left);
			collectNodes(depth+1, lists, x.right);
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
	
	public static void main(String args[]) {
		ArrayList<LinkedList<TreeNode>> lists = new ArrayList<LinkedList<TreeNode>>();
		int arr[] = new int[7];
		for (int i = 1; i <= 7; i++) {
			arr[i-1] = i;
		}
		TreeNode tn = toBST(arr);
		collectNodes(0, lists, tn);
		System.out.println("list size = " + lists.size());
		System.out.println(lists.get(0).toString());
		System.out.println(lists.get(1).toString());
		System.out.println(lists.get(2).toString());
	}
}
