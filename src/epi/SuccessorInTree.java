package epi;

import java.util.Deque;
import java.util.LinkedList;

import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

public class SuccessorInTree {

	public static BinaryTree<Integer> findSuccessor(BinaryTree<Integer> node) {

		BinaryTree<Integer> iter = node;

		/*
		 * the idea here is that if node's right side exists the the successful
		 * would be the leftmost from that subtree(i.e. right child's)
		 */
		if (iter.right != null) {
			iter = iter.right;
			while (iter.left != null) {
				iter = iter.left;
			}
			return iter;
		}

		/*
		 * otherwise
		 * 
		 * go up the hierarchy i.e. parent's parent's and so on and until the
		 * iter is at the right side (iter.parent.right==iter)
		 * 
		 * and when its not at the right side means you have covered the left
		 * side completely and the get the parent i.e.(return iter.parent)
		 * 
		 * and it will return null if the node is at the rightmost end i.e. it
		 * will goto parent's parent's and so on and root's parent would be null
		 * and it would return null
		 */
		while (iter.parent != null && iter.parent.right == iter) {
			iter = iter.parent;
		}
		return iter.parent;

	}

	public static BinaryTree<Integer> findSuccessorV1(BinaryTree<Integer> node) {
		// TODO - you fill in here.
		BinaryTree<Integer> root = node;
		while (root.parent != null) {
			root = root.parent;
		}

		boolean flag = false;
		Deque<BinaryTree<Integer>> stack = new LinkedList<>();
		while (root != null || !stack.isEmpty()) {
			if (root != null) {
				stack.addFirst(root);
				root = root.left;
			} else {
				root = stack.removeFirst();
				if (flag) {
					return root;
				}
				if (root == node) {
					flag = true;
				}
				root = root.right;
			}
		}

		return null;
	}

	@EpiTest(testDataFile = "successor_in_tree.tsv")
	public static int findSuccessorWrapper(TimedExecutor executor, BinaryTree<Integer> tree, int nodeIdx)
			throws Exception {
		BinaryTree<Integer> n = BinaryTreeUtils.mustFindNode(tree, nodeIdx);

		BinaryTree<Integer> result = executor.run(() -> findSuccessor(n));

		return result == null ? -1 : result.data;
	}

	public static void main(String[] args) {
		System.exit(GenericTest.runFromAnnotations(args, "SuccessorInTree.java", new Object() {
		}.getClass().getEnclosingClass()).ordinal());
	}
}
