package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
/**
 * 
 * @author mdfaisal
 * 
 * So basically here if the tree itself is null return true
 * 
 * next if the root has not child return true
 *
 */
public class IsTreeSymmetric {
	@EpiTest(testDataFile = "is_tree_symmetric.tsv")

	public static boolean isSymmetric(BinaryTreeNode<Integer> tree) {

		if (tree == null) {
			return true;
		} else {
			return isSymmetric(tree.left, tree.right);
		}
	}

	public static boolean isSymmetric(BinaryTreeNode<Integer> child1, BinaryTreeNode<Integer> child2) {
		/*
		 * If it has no child return true
		 */
		if (child1 == null && child2 == null)
			return true;
		
		/*
		 * If both children are present then we have to check if their children is symmetric or not  
		 */
		if (child1 != null && child2 != null) {
			/*
			 * if the data matches then we have to check for both sides 
			 * i.e. 
			 * child1's left with child2's right 
			 * and
			 * child1's right with child2's left 
			 * and their AND operation will tell if the tree is symmetric or not. 
			 */
			if (child1.data == child2.data) {
				return isSymmetric(child1.left, child2.right) && isSymmetric(child1.right, child2.left);
			} else {
				return false;
			}
		}
		/*
		 * if either one of the child is present that means symmetry isn't there anymore and hence return false
		 */
		return false;
	}

	public static void main(String[] args) {
		System.exit(GenericTest.runFromAnnotations(args, "IsTreeSymmetric.java", new Object() {
		}.getClass().getEnclosingClass()).ordinal());
	}
}
