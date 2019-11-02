package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class TreePreorder {
	@EpiTest(testDataFile = "tree_preorder.tsv")

	public static List<Integer> preorderTraversal(BinaryTreeNode<Integer> tree) {
		List<Integer> list = new ArrayList<>();
		Deque<BinaryTreeNode<Integer>> stack = new LinkedList<>();
		if (tree != null)
			stack.addFirst(tree);
		while (!stack.isEmpty()) {
			BinaryTreeNode<Integer> root = stack.removeFirst();
			list.add(root.data);
			if (root.right != null) {
				stack.addFirst(root.right);
			}
			if (root.left != null) {
				stack.addFirst(root.left);
			}
		}

		return list;
	}

	public static void main(String[] args) {
		System.exit(GenericTest.runFromAnnotations(args, "TreePreorder.java", new Object() {
		}.getClass().getEnclosingClass()).ordinal());
	}
}
