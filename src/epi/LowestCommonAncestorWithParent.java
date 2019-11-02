package epi;

import java.util.HashSet;
import java.util.Set;

import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

public class LowestCommonAncestorWithParent {

	public static BinaryTree<Integer> LCAWithHashSet(BinaryTree<Integer> node0, BinaryTree<Integer> node1) {

		Set<BinaryTree<Integer>> parents = new HashSet<>();
		while (node0 != null) {
			parents.add(node0);
			node0 = node0.parent;
		}
		while (node1 != null) {
			if (parents.contains(node1)) {
				return node1;
			}
			node1 = node1.parent;
		}

		return null;
	}

	public static BinaryTree<Integer> LCA(BinaryTree<Integer> node0, BinaryTree<Integer> node1) {

		BinaryTree<Integer> temp = node0;
		int height0 = 0, height1 = 0;

		/*
		 * count height of node0
		 */
		while (temp.parent != null) {
			height0++;
			temp = temp.parent;
		}
		temp = node1;
		
		/*
		 * count height of node1
		 */
		while (temp.parent != null) {
			height1++;
			temp = temp.parent;
		}
		/*
		 * if height is different first come up to same level by going up the parent ladder 
		 */
		if (height0 > height1) {
			while (height0 != height1) {
				height0--;
				node0 = node0.parent;
			}
		} else if (height0 < height1) {
			while (height0 != height1) {
				height1--;
				node1 = node1.parent;
			}
		}

		/*
		 * if after coming up the ladder both nodes are same that means node0 was child of node1 or vice versa 
		 * 
		 * so check for that first
		 */
		
		if (node0 == node1) {
			return node0;
		}

		/*
		 * now here, both nodes are at same level , now go up the parent ladder and 
		 * stop when both's parent are same because it would mean that nearest parent has been found
		 */
		while (node0 != node1) {
			node0 = node0.parent;
			height0--;
			node1 = node1.parent;
			height1--;
		}

		return node0;
	}

	@EpiTest(testDataFile = "lowest_common_ancestor.tsv")
	public static int lcaWrapper(TimedExecutor executor, BinaryTree<Integer> tree, Integer key0, Integer key1)
			throws Exception {
		BinaryTree<Integer> node0 = BinaryTreeUtils.mustFindNode(tree, key0);
		BinaryTree<Integer> node1 = BinaryTreeUtils.mustFindNode(tree, key1);

		BinaryTree<Integer> result = executor.run(() -> LCA(node0, node1));

		if (result == null) {
			throw new TestFailure("Result can not be null");
		}
		return result.data;
	}

	public static void main(String[] args) {
		System.exit(GenericTest.runFromAnnotations(args, "LowestCommonAncestorWithParent.java", new Object() {
		}.getClass().getEnclosingClass()).ordinal());
	}
}
