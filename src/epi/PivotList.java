package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PivotList {

	public static ListNode<Integer> listPivoting(ListNode<Integer> l, int x) {
		if (l == null) {
			return null;
		}
		ListNode<Integer> lessHead = new ListNode<>(0, null);
		ListNode<Integer> equalHead = new ListNode<>(0, null);
		ListNode<Integer> greaterHead = new ListNode<>(0, null);

		ListNode<Integer> lessIter = lessHead, equalIter = equalHead, greaterIter = greaterHead, iter = l;

		while (iter != null) {
			if (iter.data < x) {
				lessIter.next = iter;
				lessIter = iter;
			} else if (iter.data == x) {
				equalIter.next = iter;
				equalIter = iter;
			} else {
				greaterIter.next = iter;
				greaterIter = iter;
			}
			iter = iter.next;
		}
		greaterIter.next = null;
		equalIter.next = greaterHead.next;
		lessIter.next = equalHead.next;

		return lessHead.next;
	}

	public static ListNode<Integer> listPivotingMyVersion(ListNode<Integer> l, int x) {

		if (l == null) {
			return l;
		}
		ListNode<Integer> beforePivotDummy = new ListNode<>(0, null), pivotDummy = new ListNode<>(0, null),
				afterPivotDummy = new ListNode<>(0, null);
		ListNode<Integer> beforePivotDummyTemp = beforePivotDummy, afterPivotDummyTemp = afterPivotDummy,
				pivotDummyTemp = pivotDummy;
		while (l != null) {
			ListNode<Integer> node = l;
			if (node.data < x) {
				beforePivotDummyTemp.next = node;
				beforePivotDummyTemp = node;
				l = l.next;
				node.next = null;
			} else if (node.data > x) {
				afterPivotDummyTemp.next = node;
				afterPivotDummyTemp = node;
				l = l.next;
				node.next = null;
			} else {
				pivotDummyTemp.next = node;
				pivotDummyTemp = node;
				l = l.next;
				node.next = null;
			}
		}
		ListNode<Integer> newHead = null;

		if (beforePivotDummy.next != null)
			newHead = beforePivotDummy.next;

		if (pivotDummy.next != null && newHead != null) {
			beforePivotDummyTemp.next = pivotDummy.next;
		}
		if (pivotDummy.next != null && newHead == null) {
			newHead = pivotDummy.next;
		}

		if (afterPivotDummy.next != null && newHead != null)
			pivotDummyTemp.next = afterPivotDummy.next;
		if (afterPivotDummy.next != null && newHead == null) {
			newHead = afterPivotDummy.next;
		}
		return newHead;
	}

	public static List<Integer> linkedToList(ListNode<Integer> l) {
		List<Integer> v = new ArrayList<>();
		while (l != null) {
			v.add(l.data);
			l = l.next;
		}
		return v;
	}

	@EpiTest(testDataFile = "pivot_list.tsv")
	public static void listPivotingWrapper(TimedExecutor executor, ListNode<Integer> l, int x) throws Exception {
		List<Integer> original = linkedToList(l);

		final ListNode<Integer> finalL = l;
		l = executor.run(() -> listPivoting(finalL, x));

		List<Integer> pivoted = linkedToList(l);

		int mode = -1;
		for (Integer i : pivoted) {
			switch (mode) {
			case -1:
				if (i == x) {
					mode = 0;
				} else if (i > x) {
					mode = 1;
				}
				break;
			case 0:
				if (i < x) {
					throw new TestFailure("List is not pivoted");
				} else if (i > x) {
					mode = 1;
				}
				break;
			case 1:
				if (i <= x) {
					throw new TestFailure("List is not pivoted");
				}
			}
		}

		Collections.sort(original);
		Collections.sort(pivoted);
		if (!original.equals(pivoted))
			throw new TestFailure("Result list contains different values");
	}

	public static void main(String[] args) {
		System.exit(GenericTest.runFromAnnotations(args, "PivotList.java", new Object() {
		}.getClass().getEnclosingClass()).ordinal());
	}
}
