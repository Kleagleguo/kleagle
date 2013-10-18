package ch4;

public class CH_4_4_5 {
	public static class TreeNode {
		public TreeNode left;
		public TreeNode right;
		public int data;

		public TreeNode(int d) {
			data = d;
		}
	}

	public static boolean checkBST(TreeNode x) {
		if (x != null) {
			if (x.left != null) {
				if (x.left.data > x.data) return false;
				if (!checkBST(x.left)) return false;
			}
			if (x.right != null) {
				if (x.right.data < x.data) return false;
				if (!checkBST(x.right)) return false;
			}
			return true;
		} else {
			return true;
		}
	}
	
	public static void main(String args[]) {
		TreeNode a = new TreeNode(1);
		TreeNode b = new TreeNode(2);
		TreeNode c = new TreeNode(3);
		a.left = b;
		a.right = c;
		
		if (checkBST(a)) {
			System.out.println("it's bst");
		} else {
			System.out.println("it's not bst");
		}
	}
}
