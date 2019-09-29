package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class RemoveDuplicatesFromSortedList {
	@EpiTest(testDataFile = "remove_duplicates_from_sorted_list.tsv")

	public static ListNode<Integer> removeDuplicates(ListNode<Integer> L) {
		ListNode<Integer> current = L;
		while (current != null && current.next != null) {
			while ( current.next != null && current.data == current.next.data) {
				current.next = current.next.next;
			}
			current = current.next;
		}
		return L;
	}

	public static void main(String[] args) {
		System.exit(GenericTest.runFromAnnotations(args, "RemoveDuplicatesFromSortedList.java", new Object() {
		}.getClass().getEnclosingClass()).ordinal());
	}
}
