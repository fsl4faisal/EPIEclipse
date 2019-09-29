package epi;

import java.util.HashSet;
import java.util.Set;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

public class DoListsOverlap {

	public static ListNode<Integer> overlappingLists(ListNode<Integer> l0, ListNode<Integer> l1) {

		ListNode<Integer> tempL0 = l0, tempL1 = l1;
		ListNode<Integer> cycleHead0 = isCyclic(tempL0);
		ListNode<Integer> cycleHead1 = isCyclic(tempL1);
		//System.out.println(l0);
		//System.out.println(l1);
		if (cycleHead0 == null && cycleHead1 == null) {
			return findConnectionWithoutCycle(l0, l1);
		} else if (cycleHead0 != null && cycleHead1 == null) {
			return null;
		} else if (cycleHead0 == null && cycleHead1 != null) {
			return null;
		} else if (cycleHead0 != cycleHead1) {
			return null;
		} else if(cycleHead0==cycleHead1){
			return cycleHead0;
		}else{
			return null;
		}

	}

	public static ListNode<Integer> findConnectionWithoutCycle(ListNode<Integer> l0, ListNode<Integer> l1) {

		ListNode<Integer> tempL0 = l0, tempL1 = l1;
		if (tempL0 == tempL1)
			return tempL0;
		int length0 = length(l0), length1 = length(l1);
		if (length0 > length1) {
			tempL0 = moveByKSteps(tempL0, length0 - length1);
		} else if (length0 < length1) {
			tempL1 = moveByKSteps(tempL1, length1 - length0);
		}
		ListNode<Integer> commonPoint = null;
		while (tempL0 != null && tempL1 != null) {
			if (commonPoint == null && tempL0 == tempL1) {
				commonPoint = tempL0;
			}
			tempL0 = tempL0.next;
			tempL1 = tempL1.next;
		}
		if (tempL0 != null || tempL1 != null) {
			return null;
		}
		return commonPoint;

	}

	public static ListNode<Integer> moveByKSteps(ListNode<Integer> node, int k) {
		int counter = 0;
		while (node != null) {
			node = node.next;
			counter++;
			if (counter == k)
				break;
		}
		return node;
	}

	public static ListNode<Integer> isCyclic(ListNode<Integer> head) {
		ListNode<Integer> slow = head, fast = head;

		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			if (slow == fast) {
				return slow;
			}
		}
		return null;
	}

	public static ListNode<Integer> overlappingListsUsing2Sets(ListNode<Integer> l0, ListNode<Integer> l1) {

		ListNode<Integer> tempL0 = l0, tempL1 = l1;
		Set<ListNode> set0 = new HashSet<>(), set1 = new HashSet<>();

		while (tempL0 != null) {
			if (set0.contains(tempL0)) {
				break;
			} else {
				set0.add(tempL0);
			}
			tempL0 = tempL0.next;
		}
		while (tempL1 != null) {
			if (set1.contains(tempL1)) {
				break;
			} else {
				set1.add(tempL1);
			}
			tempL1 = tempL1.next;
		}

		tempL0 = l0;
		tempL1 = l1;
		int i = 0, j = 0;
		while (tempL0 != null && tempL1 != null) {
			if (set0.contains(tempL1)) {
				return tempL1;
			}
			if (set1.contains(tempL0)) {
				return tempL0;
			}
			if (i > set0.size() && j > set1.size()) {
				return null;
			}
			tempL0 = tempL0.next;
			tempL1 = tempL1.next;
			i++;
			j++;
		}

		return null;
	}

	public static int length(ListNode<Integer> node) {
		int counter = 0;
		while (node != null) {
			node = node.next;
			counter++;
		}
		return counter;
	}

	public static int lengthWithCycle(ListNode<Integer> l0) {
		int counter = 0;
		Set<ListNode<Integer>> set = new HashSet<>();
		while (l0 != null) {
			if (set.contains(l0)) {
				break;
			} else {
				set.add(l0);
			}
			counter++;
			l0 = l0.next;
		}
		return counter;
	}

	@EpiTest(testDataFile = "do_lists_overlap.tsv")
	public static void overlappingListsWrapper(TimedExecutor executor, ListNode<Integer> l0, ListNode<Integer> l1,
			ListNode<Integer> common, int cycle0, int cycle1) throws Exception {
		if (common != null) {
			if (l0 == null) {
				l0 = common;
			} else {
				ListNode<Integer> it = l0;
				while (it.next != null) {
					it = it.next;
				}
				it.next = common;
			}

			if (l1 == null) {
				l1 = common;
			} else {
				ListNode<Integer> it = l1;
				while (it.next != null) {
					it = it.next;
				}
				it.next = common;
			}
		}

		if (cycle0 != -1 && l0 != null) {
			ListNode<Integer> last = l0;
			while (last.next != null) {
				last = last.next;
			}
			ListNode<Integer> it = l0;
			while (cycle0-- > 0) {
				if (it == null) {
					throw new RuntimeException("Invalid input data");
				}
				it = it.next;
			}
			last.next = it;
		}

		if (cycle1 != -1 && l1 != null) {
			ListNode<Integer> last = l1;
			while (last.next != null) {
				last = last.next;
			}
			ListNode<Integer> it = l1;
			while (cycle1-- > 0) {
				if (it == null) {
					throw new RuntimeException("Invalid input data");
				}
				it = it.next;
			}
			last.next = it;
		}

		Set<Integer> commonNodes = new HashSet<>();
		ListNode<Integer> it = common;
		while (it != null && !commonNodes.contains(it.data)) {
			commonNodes.add(it.data);
			it = it.next;
		}

		final ListNode<Integer> finalL0 = l0;
		final ListNode<Integer> finalL1 = l1;
		ListNode<Integer> result = executor.run(() -> overlappingLists(finalL0, finalL1));

		if (!((commonNodes.isEmpty() && result == null) || (result != null && commonNodes.contains(result.data)))) {
			throw new TestFailure("Invalid result");
		}
	}

	public static void main(String[] args) {
		System.exit(GenericTest.runFromAnnotations(args, "DoListsOverlap.java", new Object() {
		}.getClass().getEnclosingClass()).ordinal());
	}
}
