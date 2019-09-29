package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Arrays;
import java.util.List;

public class EvenOddListMerge {
	@EpiTest(testDataFile = "even_odd_list_merge.tsv")

	public static ListNode<Integer> evenOddMerge(ListNode<Integer> L) {
		if (L == null)
			return L;
		ListNode<Integer> dummyEven = new ListNode<>(0, null), dummyOdd = new ListNode<>(0, null);
		List<ListNode<Integer>> tails = Arrays.asList(dummyEven, dummyOdd);
		int turn = 0;
		for (ListNode<Integer> iter = L; iter != null; iter = iter.next) {
			tails.get(turn).next = iter;
			tails.set(turn, tails.get(turn).next);
			turn = turn ^ 1;
		}
		tails.get(1).next = null;
		tails.get(0).next = dummyOdd.next;
		return dummyEven.next;
	}

	public static ListNode<Integer> evenOddMergeMySolutionMoreEfficient(ListNode<Integer> L) {

		ListNode<Integer> temp = L, even = new ListNode<Integer>(0, null), odd = new ListNode<Integer>(0, null);

		int i = 0;
		ListNode<Integer> tempEven = even, tempOdd = odd;
		while (temp != null) {
			if (i % 2 == 0) {
				ListNode<Integer> node = temp;
				even.next = node;
				temp = temp.next;
				node.next = null;
				even = node;
			} else {
				ListNode<Integer> node = temp;
				odd.next = node;
				temp = temp.next;
				node.next = null;
				odd = node;
			}
			i++;
		}
		L = tempEven.next;
		while (tempEven.next != null) {
			tempEven = tempEven.next;
		}
		tempEven.next = tempOdd.next;
		if (L != null)
			return L;
		else
			return null;
	}

	public static ListNode<Integer> evenOddMergeTooSlow(ListNode<Integer> L) {

		ListNode<Integer> temp = L, even = new ListNode<Integer>(0, null), odd = new ListNode<Integer>(0, null);

		int i = 0;
		while (temp != null) {
			if (i % 2 == 0) {
				ListNode<Integer> node = temp;
				ListNode<Integer> iNode = even.next;
				while (iNode != null && iNode.next != null)
					iNode = iNode.next;
				if (iNode != null)
					iNode.next = node;
				else
					even.next = node;
				temp = temp.next;
				node.next = null;
			} else {
				ListNode<Integer> node = temp;
				ListNode<Integer> iNode = odd.next;
				while (iNode != null && iNode.next != null)
					iNode = iNode.next;
				if (iNode != null)
					iNode.next = node;
				else
					odd.next = node;
				temp = temp.next;
				node.next = null;

			}
			i++;
		}
		L = even.next;
		while (even.next != null) {
			even = even.next;
		}
		even.next = odd.next;
		if (L != null)
			return L;
		else
			return null;
	}

	public static void addToTheEnd(ListNode<Integer> head, ListNode<Integer> node) {
		ListNode<Integer> temp = head.next;
		while (temp != null && temp.next != null) {
			temp = temp.next;
		}
		if (temp != null)
			temp.next = node;
		else
			head.next = node;
		node.next = null;
	}

	public static void main(String[] args) {
		System.exit(GenericTest.runFromAnnotations(args, "EvenOddListMerge.java", new Object() {
		}.getClass().getEnclosingClass()).ordinal());
	}
}
