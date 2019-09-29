package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class DeleteKthLastFromList {
	@EpiTest(testDataFile = "delete_kth_last_from_list.tsv")

	public static ListNode<Integer> removeKthLast(ListNode<Integer> L, int k) {

		return null;
	}

	// Assumes L has at least k nodes, deletes the k-th last node in L.
	public static ListNode<Integer> removeKthLastUsingLength(ListNode<Integer> L, int k) {

		int i = length(L) - k;
		int counter = 0;
		ListNode<Integer> temp = L;
		while (counter < i - 1) {
			L = L.next;
			counter++;
		}
		if (length(L) == k) {
			L = L.next;
			return L;
		}
		L.next = L.next.next;

		return temp;

	}

	public static int length(ListNode<Integer> L) {
		int counter = 0;
		while (L != null) {
			counter++;
			L = L.next;
		}
		return counter;
	}

	public static void main(String[] args) {
		System.exit(GenericTest.runFromAnnotations(args, "DeleteKthLastFromList.java", new Object() {
		}.getClass().getEnclosingClass()).ordinal());
	}
}
