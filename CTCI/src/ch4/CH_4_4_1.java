package ch4;

public class CH_4_4_1 {
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

	public static int checkHeight(TreeNode root) {
		if (root == null) {
			return 0;
		}

		int leftHeight = checkHeight(root.left);
		int rightHeight = checkHeight(root.right);	
		if (leftHeight == -1 || rightHeight == -1) {
			return -1;
		}	
		
		int diffHeight = leftHeight - rightHeight;
		if (diffHeight > 1) {
			return -1;
		}
	
		return (Math.max(leftHeight, rightHeight) + 1);	
	}
	
	public static boolean isBalanced(TreeNode root) {
		if (checkHeight(root) == -1) {
			return false;
		} 
		return true;
	}
	
	public static void main(String args[]) {
		TreeNode root = new TreeNode(1);
		TreeNode left = new TreeNode(2);
		TreeNode right = new TreeNode(3);
		TreeNode left2 = new TreeNode(4);
		TreeNode left3 = new TreeNode(5);
		TreeNode right2 = new TreeNode(6);
		
		root.left = left;
		root.right = right;
		left.left = left2;
		left2.left = left3;
		right.right = right2;
		
		int result = checkHeight(root);
		if (result != -1) {
			System.out.println("it's balanced");
		} else {
			System.out.println("not balanced");
		}
 	}
}
