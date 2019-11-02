package epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.function.BiPredicate;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;

public class KLargestInHeap {
	/*
	 * 
	 * In this case I used Heap data structure to extract out k largest elements
	 * in the Heap..
	 * 
	 * The Heap DS that we created had index and value and using that we
	 * inserted values from the original heap and one by one removed top from
	 * the heap that we created to get the result
	 * 
	 * hence not changing the original heap
	 * 
	 * Pretty dope
	 * 
	 */
	public static class HeapEntry implements Comparable<HeapEntry> {
		int index;
		int value;

		public HeapEntry(int index, int value) {
			this.index = index;
			this.value = value;
		}

		@Override
		public int compareTo(HeapEntry entry) {
			return this.value - entry.value;
		}
	}

	@EpiTest(testDataFile = "k_largest_in_heap.tsv")
	public static List<Integer> kLargestInBinaryHeap(List<Integer> A, int k) {
		List<Integer> result = new ArrayList<>();

		PriorityQueue<HeapEntry> queue = new PriorityQueue<HeapEntry>(Collections.reverseOrder());

		int i = 0;
		queue.add(new HeapEntry(0, A.get(0)));
		while (i < k) {
			HeapEntry entry = queue.remove();
			int index = entry.index;
			result.add(entry.value);
			int leftIndex = 2 * index + 1;
			if (A.size() > leftIndex) {
				queue.add(new HeapEntry(leftIndex, A.get(leftIndex)));
			}
			int rightIndex = 2 * index + 2;
			if (A.size() > rightIndex) {
				queue.add(new HeapEntry(rightIndex, A.get(rightIndex)));
			}
			i++;
		}

		return result;
	}


	@EpiTestComparator
	public static BiPredicate<List<Integer>, List<Integer>> comp = (expected, result) -> {
		if (result == null) {
			return false;
		}
		Collections.sort(expected);
		Collections.sort(result);
		return expected.equals(result);
	};

	public static void main(String[] args) {
		System.exit(GenericTest.runFromAnnotations(args, "KLargestInHeap.java", new Object() {
		}.getClass().getEnclosingClass()).ordinal());
	}
}
