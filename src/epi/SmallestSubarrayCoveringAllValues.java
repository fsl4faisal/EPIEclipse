package epi;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

public class SmallestSubarrayCoveringAllValues {

	public static class Subarray {
		// Represent subarray by starting and ending indices, inclusive.
		public Integer start;
		public Integer end;

		public Subarray(Integer start, Integer end) {
			this.start = start;
			this.end = end;
		}
	}

	public static Subarray findSmallestSequentiallyCoveringSubset(List<String> paragraph, List<String> keywords) {
		// TODO - you fill in here.
		Map<String, List<Integer>> keywordIndexMap = new LinkedHashMap<>();

		for (String s : keywords) {
			keywordIndexMap.put(s, new ArrayList<>());
		}
		
		for (String keyword : keywordIndexMap.keySet()) {
			int i = 0;
			while (i < paragraph.size()) {
				if (paragraph.get(i).equals(keyword)) {
					keywordIndexMap.get(keyword).add(i);
					//break;
				}
				i++;
			}

		}
		Iterator<String> itr = keywordIndexMap.keySet().iterator();

		List<Integer> list1 = keywordIndexMap.get(keywords.get(0));
		List<Integer> list2 = keywordIndexMap.get(keywords.get(keywords.size() - 1));
		return new Subarray(list1.get(0), list2.get(list2.size() - 1));
	}

	@EpiTest(testDataFile = "smallest_subarray_covering_all_values.tsv")
	public static int findSmallestSequentiallyCoveringSubsetWrapper(TimedExecutor executor, List<String> paragraph,
			List<String> keywords) throws Exception {
		Subarray result = executor.run(() -> findSmallestSequentiallyCoveringSubset(paragraph, keywords));

		int kwIdx = 0;
		if (result.start < 0) {
			throw new TestFailure("Subarray start index is negative");
		}
		int paraIdx = result.start;

		while (kwIdx < keywords.size()) {
			if (paraIdx >= paragraph.size()) {
				throw new TestFailure("Not all keywords are in the generated subarray");
			}
			if (paraIdx >= paragraph.size()) {
				throw new TestFailure("Subarray end index exceeds array size");
			}
			if (paragraph.get(paraIdx).equals(keywords.get(kwIdx))) {
				kwIdx++;
			}
			paraIdx++;
		}
		return result.end - result.start + 1;
	}

	public static void main(String[] args) {
		System.exit(GenericTest.runFromAnnotations(args, "SmallestSubarrayCoveringAllValues.java", new Object() {
		}.getClass().getEnclosingClass()).ordinal());
	}
}
