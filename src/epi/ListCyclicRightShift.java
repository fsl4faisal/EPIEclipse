package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class ListCyclicRightShift {
	@EpiTest(testDataFile = "list_cyclic_right_shift.tsv")

	public static ListNode<Integer> cyclicallyRightShiftList(ListNode<Integer> L, int k) {
		/*
		 * Boundary line case 1: if list itself is null
		 */

		if (L == null)
			return null;

		ListNode<Integer> tail = L;
		int n = 1;

		while (tail.next != null) {
			++n;
			tail = tail.next;
		}
		k = k % n;
		/**
		 * If the shifting the entire list by its length then return the list
		 * without shift
		 */
		if (k == 0) {
			return L;
		}

		tail.next = L;
		int stepsToNewHead = n - k;
		ListNode<Integer> newTail = tail;
		while (stepsToNewHead-- > 0) {
			newTail = newTail.next;
		}
		ListNode<Integer> newHead = newTail.next;
		newTail.next = null;

		return newHead;
	}

	public static ListNode<Integer> cyclicallyRightShiftListMyVersion(ListNode<Integer> L, int k) {

		/*
		 * Boundary line case 1: if list itself is null
		 */

		if (L == null)
			return null;

		ListNode<Integer> temp = L, lastNode = L;
		int length = length(L);
		/*
		 * Boundary Line case 2 and 3: If length is the list is 1 or k(shift
		 * parameter is 0) then return the list since no shifting has to be done
		 * here
		 */
		if (length == 1 || k == 0) {
			return L;
		}

		int counter = length - (k % length);

		/*
		 * After taking modulus of k and length counter variable contains the
		 * amount of shifting to be done, if counter is equal to length of the
		 * list itself then no shifting has to be done hence return the list
		 * itself
		 */
		if (counter == length) {
			return L;
		}
		while (counter > 1) {
			lastNode = lastNode.next;
			counter--;
		}
		ListNode<Integer> newHead = lastNode.next;
		lastNode.next = null;
		ListNode<Integer> temp2 = newHead;
		while (temp2.next != null) {
			temp2 = temp2.next;
		}
		temp2.next = L;

		return newHead;
	}

	public static int length(ListNode<Integer> node) {
		int counter = 0;
		while (node != null) {
			counter++;
			node = node.next;
		}
		return counter;
	}

	public static void main(String[] args) {
		System.exit(GenericTest.runFromAnnotations(args, "ListCyclicRightShift.java", new Object() {
		}.getClass().getEnclosingClass()).ordinal());
	}
}
