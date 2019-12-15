package epi;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class LongestSubarrayWithDistinctValues {

	public static int longestSubarrayWithDistinctEntriesTrivial(List<Integer> A) {

		int max = 0;
		if (A.size() == 1)
			return 1;
		for (int i = 0; i < A.size(); i++) {
			for (int j = i; j < A.size(); j++) {
				int size = isAllElementDistinct(A, i, j);
				if (size == -1)
					break;
				if (size > max) {
					max = size;
				}
			}
		}

		return max;
	}

	@EpiTest(testDataFile = "longest_subarray_with_distinct_values.tsv")
	public static int longestSubarrayWithDistinctEntries(List<Integer> A) {

		Map<Integer, Integer> latestIndex = new HashMap<>();
		int startIndexofLongestSubarrayWithDistinctEntries = 0, result = 0;
		for (int i = 0; i < A.size(); i++) {
			Integer dupIndex = latestIndex.put(A.get(i), i);
			if (dupIndex != null && dupIndex >= startIndexofLongestSubarrayWithDistinctEntries) {
				result = Math.max(result, i - startIndexofLongestSubarrayWithDistinctEntries);
				startIndexofLongestSubarrayWithDistinctEntries = dupIndex + 1;
			}
		}
		result = Math.max(result, A.size() - startIndexofLongestSubarrayWithDistinctEntries);
		return result;

	}

	public static int isAllElementDistinct(List<Integer> A, int low, int high) {
		Set<Integer> set = new HashSet<>();
		for (int i = low; i <= high; i++) {
			if (set.contains(A.get(i)))
				return -1;
			set.add(A.get(i));
		}
		return set.size();
	}

	public static void main(String[] args) {
		System.exit(GenericTest.runFromAnnotations(args, "LongestSubarrayWithDistinctValues.java", new Object() {
		}.getClass().getEnclosingClass()).ordinal());
	}
}
