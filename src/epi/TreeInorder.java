package epi;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class TreeInorder {
	@EpiTest(testDataFile = "tree_inorder.tsv")

	public static List<Integer> inorderTraversal(BinaryTreeNode<Integer> tree) {
		List<Integer> list = new ArrayList<>();
		Deque<BinaryTreeNode<Integer>> stack = new LinkedList<>();
		BinaryTreeNode<Integer> curr = tree;
		while (!stack.isEmpty() || curr != null) {
			if (curr != null) {
				stack.addFirst(curr);
				curr = curr.left;
			} else {
				curr = stack.removeFirst();
				list.add(curr.data);
				curr = curr.right;
			}
		}

		return list;
	}

	public static List<Integer> inOrderTraversalWithRecursion(BinaryTreeNode<Integer> tree) {
		List<Integer> list = new ArrayList<>();
		inOrder(tree, list);

		return list;
	}

	public static void inOrder(BinaryTreeNode<Integer> node, List<Integer> list) {

		if (node != null) {
			inOrder(node.left, list);
			list.add(node.data);
			inOrder(node.right, list);
		}
	}

	public static void main(String[] args) {
		System.exit(GenericTest.runFromAnnotations(args, "TreeInorder.java", new Object() {
		}.getClass().getEnclosingClass()).ordinal());
	}
}
