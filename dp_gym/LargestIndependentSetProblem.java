package dp_gym;

import java.util.ArrayList;

public class LargestIndependentSetProblem {

	public static void main(String[] args) {
		TreeNode root = new TreeNode(20);
		root.left = new TreeNode(8);
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(12);
		root.left.right.left = new TreeNode(10);
		root.left.right.right = new TreeNode(14);
		root.right = new TreeNode(22);
		root.right.right = new TreeNode(25);
		
		System.out.println(LISS(root));
		System.out.println(root.res);
	}
	
	private static int LISS(TreeNode node) {
		if(node == null) return 0;
		
		if(node.lis != -1) return node.lis;
		
		if(node.left == null && node.right == null) {
			node.res.add(node.data);
			return (node.lis = 1);
		}
		
		int exc = LISS(node.left) + LISS(node.right);
		int inc = 1;
		
		if(node.left != null)
			inc += LISS(node.left.left) + LISS(node.left.right);
		
		if(node.right != null)
			inc += LISS(node.right.left) + LISS(node.right.right);
		
		if(exc > inc) {
			if(node.left != null)
				node.res.addAll(node.left.res);
			if(node.right != null)
				node.res.addAll(node.right.res);
		}
		else {
			node.res.add(node.data);
			if(node.left != null) {
				if(node.left.left != null)
					node.res.addAll(node.left.left.res);
				if(node.left.right != null)
					node.res.addAll(node.left.right.res);
			}
			if(node.right != null) {
				if(node.right.left != null)
					node.res.addAll(node.right.left.res);
				if(node.right.right != null)
					node.res.addAll(node.right.right.res);
			}
			
		}
		
		return (node.lis = Math.max(inc, exc));
	}

	static class TreeNode {
		int data;
		int lis;
		TreeNode left, right;
		ArrayList<Integer> res;
		boolean calculated;
		
		public TreeNode(int data) {
			this.data = data;
			res = new ArrayList<Integer>();
			lis = -1;
		}
	}
}
