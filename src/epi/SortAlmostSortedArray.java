package epi;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class SortAlmostSortedArray {

	public static List<Integer> sortApproximatelySortedData(Iterator<Integer> sequence, int k) {

		PriorityQueue<Integer> minQueue = new PriorityQueue<>();
		while (sequence.hasNext()) {
			minQueue.add(sequence.next());
		}
		List<Integer> result = new ArrayList<>();
		while (!minQueue.isEmpty()) {
			result.add(minQueue.remove());
		}
		return result;
	}

	@EpiTest(testDataFile = "sort_almost_sorted_array.tsv")
	public static List<Integer> sortApproximatelySortedDataWrapper(List<Integer> sequence, int k) {
		return sortApproximatelySortedData(sequence.iterator(), k);
	}

	public static void main(String[] args) {
		System.exit(GenericTest.runFromAnnotations(args, "SortAlmostSortedArray.java", new Object() {
		}.getClass().getEnclosingClass()).ordinal());
	}
}
