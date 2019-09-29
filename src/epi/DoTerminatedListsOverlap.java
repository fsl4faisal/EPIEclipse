package epi;

import java.util.HashSet;
import java.util.Set;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

public class DoTerminatedListsOverlap {

	public static ListNode<Integer> overlappingNoCycleLists(ListNode<Integer> l0, ListNode<Integer> l1) {
		ListNode<Integer> tempL0 = l0, tempL1 = l1;
		int l0Size = getSize(tempL0), l1Size = getSize(tempL1);

		if (l0Size > l1Size) {
			tempL0 = moveSteps(tempL0, l0Size - l1Size);
		} else {
			tempL1 = moveSteps(tempL1, l1Size - l0Size);
		}

		while (tempL0 != null && tempL1 != null && tempL0 != tempL1) {
			tempL0 = tempL0.next;
			tempL1 = tempL1.next;
		}

		return tempL1;

	}

	public static ListNode<Integer> moveSteps(ListNode<Integer> listNode, int steps) {
		int counter = 0;
		while (counter < steps) {
			listNode = listNode.next;
			counter++;
		}
		return listNode;
	}

	public static ListNode<Integer> overlappingNoCycleListsUsingNestedLoops(ListNode<Integer> l0,
			ListNode<Integer> l1) {

		int size1 = getSize(l0);
		int size2 = getSize(l1);
		if (size1 > size2) {
			return findingConnection(l1, l0);
		} else {
			return findingConnection(l0, l1);
		}

	}

	public static ListNode<Integer> findingConnection(ListNode<Integer> l0, ListNode<Integer> l1) {
		for (ListNode<Integer> i = l0; i != null; i = i.next) {
			ListNode<Integer> j = l1;
			while (j != null) {
				if (i == j) {
					return j;
				}
				j = j.next;
			}
		}
		return null;
	}

	public static int getSize(ListNode<Integer> node) {
		int counter = 0;
		while (node != null) {
			counter++;
			node = node.next;
		}
		return counter;
	}

	public static ListNode<Integer> overlappingNoCycleListsUsingSet(ListNode<Integer> l0, ListNode<Integer> l1) {

		ListNode<Integer> tempL0 = l0, tempL1 = l1;

		Set<ListNode> set = new HashSet<>();
		while (tempL0 != null && tempL1 != null) {
			if (set.contains(tempL0)) {
				return tempL0;
			} else {
				set.add(tempL0);
			}
			if (set.contains(tempL1)) {
				return tempL1;
			} else {
				set.add(tempL1);
			}
			tempL0 = tempL0.next;
			tempL1 = tempL1.next;
		}
		while (tempL0 != null) {
			if (set.contains(tempL0)) {
				return tempL0;
			} else {
				set.add(tempL0);
			}
			tempL0 = tempL0.next;
		}

		while (tempL1 != null) {
			if (set.contains(tempL1)) {
				return tempL1;
			} else {
				set.add(tempL1);
			}
			tempL1 = tempL1.next;
		}

		return null;
	}

	@EpiTest(testDataFile = "do_terminated_lists_overlap.tsv")
	public static void overlappingNoCycleListsWrapper(TimedExecutor executor, ListNode<Integer> l0,
			ListNode<Integer> l1, ListNode<Integer> common) throws Exception {
		if (common != null) {
			if (l0 != null) {
				ListNode<Integer> i = l0;
				while (i.next != null) {
					i = i.next;
				}
				i.next = common;
			} else {
				l0 = common;
			}

			if (l1 != null) {
				ListNode<Integer> i = l1;
				while (i.next != null) {
					i = i.next;
				}
				i.next = common;
			} else {
				l1 = common;
			}
		}

		final ListNode<Integer> finalL0 = l0;
		final ListNode<Integer> finalL1 = l1;
		ListNode<Integer> result = executor.run(() -> overlappingNoCycleLists(finalL0, finalL1));

		if (result != common) {
			throw new TestFailure("Invalid result");
		}
	}

	public static void main(String[] args) {
		System.exit(GenericTest.runFromAnnotations(args, "DoTerminatedListsOverlap.java", new Object() {
		}.getClass().getEnclosingClass()).ordinal());
	}
}
