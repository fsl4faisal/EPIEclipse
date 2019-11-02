package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IsTreeBalanced {

	public static class BalanceWithHeight {
		int height;
		boolean isBalanced;

		public BalanceWithHeight(int height, boolean isBalanced) {
			super();
			this.height = height;
			this.isBalanced = isBalanced;
		}
	}

	@EpiTest(testDataFile = "is_tree_balanced.tsv")
	public static boolean isBalanced(BinaryTreeNode<Integer> tree) {

		if (tree == null) {
			return true;
		} else {
			int leftHeight = getHeight(tree.left);
			int rightHeight = getHeight(tree.right);

			if (Math.abs(rightHeight - leftHeight) <= 1) {
				return isBalanced(tree.left) && isBalanced(tree.right);
			} else {
				return false;
			}
		}
	}

	public static int getHeight(BinaryTreeNode<Integer> node) {
		if (node == null) {
			return 0;
		} else {
			return Math.max(getHeight(node.left), getHeight(node.right)) + 1;
		}
	}

	public static void main(String[] args) {
		System.exit(GenericTest.runFromAnnotations(args, "IsTreeBalanced.java", new Object() {
		}.getClass().getEnclosingClass()).ordinal());
	}
}
