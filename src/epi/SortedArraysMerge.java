package epi;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class SortedArraysMerge {
	@EpiTest(testDataFile = "sorted_arrays_merge.tsv")

	public static List<Integer> mergeSortedArrays(List<List<Integer>> sortedArrays) {
		PriorityQueue<Integer> minHeap = new PriorityQueue<>();
		List<Integer> result = new ArrayList<>();
		for (int i = 0; i < sortedArrays.size(); i++) {
			for (int j = 0; j < sortedArrays.get(i).size(); j++) {
				minHeap.add(sortedArrays.get(i).get(j));
			}
		}
		while (!minHeap.isEmpty()) {
			result.add(minHeap.remove());
		}

		return result;
	}

	public static void main(String[] args) {
		System.exit(GenericTest.runFromAnnotations(args, "SortedArraysMerge.java", new Object() {
		}.getClass().getEnclosingClass()).ordinal());
	}
}
