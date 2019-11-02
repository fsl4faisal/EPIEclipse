package epi;

import java.util.Deque;
import java.util.LinkedList;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

public class KthNodeInTree {
	public static class BinaryTreeNode<T> {
		public T data;
		public BinaryTreeNode<T> left, right;
		public int size;

		public BinaryTreeNode(T data, BinaryTreeNode<T> left, BinaryTreeNode<T> right, int size) {
			this.data = data;
			this.left = left;
			this.right = right;
			this.size = size;
		}
	}

	public static BinaryTreeNode<Integer> findKthNodeBinaryTreeTrivial(BinaryTreeNode<Integer> tree, int k) {

		int counter = 0;
		BinaryTreeNode<Integer> curr = tree;
		Deque<BinaryTreeNode<Integer>> stack = new LinkedList<>();
		BinaryTreeNode<Integer> result = null;
		while (curr != null || !stack.isEmpty()) {
			if (counter == k) {
				break;
			}
			if (curr != null) {
				stack.addFirst(curr);
				curr = curr.left;
			} else {
				curr = stack.removeFirst();
				result = curr;
				curr = curr.right;
				counter++;
			}
		}
		return result;

	}

	public static BinaryTreeNode<Integer> findKthNodeBinaryTree(BinaryTreeNode<Integer> tree, int k) {
		BinaryTreeNode<Integer> curr = tree;

		while (curr != null) {
			int leftSize = curr.left != null ? curr.left.size : 0;
			/*
			 * leftSize+1 because curr.left.size is size of the tree in the left
			 * side of the curr so to include curr and the whole tree in the
			 * left-->leftSize+1
			 */
			if (k > (leftSize + 1)) {
				/*
				 * removing leftSize+1 from k because since in the side kth node
				 * will not be there and the first node in thr right side would
				 * after (leftSize+1)th node
				 */
				k = k - (leftSize + 1);
				curr = curr.right;
			}
			/*
			 * if k== leftSize+1--> it would mean that the current node is the
			 * kth node
			 */
			else if (k == (leftSize + 1)) {
				return curr;
			} else {
				curr = curr.left;
			}
		}

		return null;
	}

	public static BinaryTreeNode<Integer> convertToTreeWithSize(BinaryTree<Integer> original) {
		if (original == null)
			return null;
		BinaryTreeNode<Integer> left = convertToTreeWithSize(original.left);
		BinaryTreeNode<Integer> right = convertToTreeWithSize(original.right);
		int lSize = left == null ? 0 : left.size;
		int rSize = right == null ? 0 : right.size;
		return new BinaryTreeNode<>(original.data, left, right, 1 + lSize + rSize);
	}

	@EpiTest(testDataFile = "kth_node_in_tree.tsv")
	public static int findKthNodeBinaryTreeWrapper(TimedExecutor executor, BinaryTree<Integer> tree, int k)
			throws Exception {
		BinaryTreeNode<Integer> converted = convertToTreeWithSize(tree);

		BinaryTreeNode<Integer> result = executor.run(() -> findKthNodeBinaryTree(converted, k));

		if (result == null) {
			throw new TestFailure("Result can't be null");
		}
		return result.data;
	}

	public static void main(String[] args) {
		System.exit(GenericTest.runFromAnnotations(args, "KthNodeInTree.java", new Object() {
		}.getClass().getEnclosingClass()).ordinal());
	}
}
