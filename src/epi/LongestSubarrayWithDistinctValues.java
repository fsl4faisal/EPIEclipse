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

		int max = 0;

		int contin = 0;
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < A.size(); i++) {
			if (map.containsKey(A.get(i))) {
				if (contin > max) {
					max = contin;
				}
				contin = 0;
			}
			map.put(A.get(i), i);
			contin++;

		}
		if (contin > max)
			max = contin;
		return max;

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
