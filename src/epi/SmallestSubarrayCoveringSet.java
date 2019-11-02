package epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

public class SmallestSubarrayCoveringSet {

	// Represent subarray by starting and ending indices, inclusive.
	private static class Subarray {
		public Integer start;
		public Integer end;

		public Subarray(Integer start, Integer end) {
			this.start = start;
			this.end = end;
		}
	}

	public static Subarray findSmallestSubarrayCoveringSet(List<String> paragraph, Set<String> keywords) {
		Set<String> copyKeywords = new HashSet<>();
		int min = 0, max = 0, diff, minDiff = Integer.MAX_VALUE;
		for (int i = 0; i < paragraph.size(); i++) {
			int counter = 0;
			for (int j = 0; j <= i; j++) {
				for (String word : keywords) {
					if (word.equals(paragraph.get(j))) {
						counter++;
					}
				}
				if (counter == keywords.size()) {
					diff = j - i;
					if (diff < minDiff) {
						min = i;
						max = j;
					}
				}
			}
		}

		return new Subarray(min, max);
	}

	@EpiTest(testDataFile = "smallest_subarray_covering_set.tsv")
	public static int findSmallestSubarrayCoveringSetWrapper(TimedExecutor executor, List<String> paragraph,
			Set<String> keywords) throws Exception {
		Set<String> copy = new HashSet<>(keywords);

		Subarray result = executor.run(() -> findSmallestSubarrayCoveringSet(paragraph, keywords));

		if (result.start < 0 || result.start >= paragraph.size() || result.end < 0 || result.end >= paragraph.size()
				|| result.start > result.end)
			throw new TestFailure("Index out of range");

		for (int i = result.start; i <= result.end; i++) {
			copy.remove(paragraph.get(i));
		}

		if (!copy.isEmpty()) {
			throw new TestFailure("Not all keywords are in the range");
		}
		return result.end - result.start + 1;
	}

	public static void main(String[] args) {
		System.exit(GenericTest.runFromAnnotations(args, "SmallestSubarrayCoveringSet.java", new Object() {
		}.getClass().getEnclosingClass()).ordinal());
	}
}
