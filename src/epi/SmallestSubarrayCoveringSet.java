package epi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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

		public String toString() {
			return "(" + start + "," + end + ")";
		}
	}

	//book's version--not working
	public static Subarray findSmallestSubarrayCoveringSet(List<String> paragraph, Set<String> keywords) {
		Map<String, Integer> keywordsToCover = new HashMap<>();
		for (String keyword : keywords) {
			keywordsToCover.put(keyword, keywordsToCover.containsKey(keyword) ? keywordsToCover.get(keyword) + 1 : 1);
		}

		Subarray result = new Subarray(-1, -1);
		int remainingToCover = keywords.size();

		int left = 0, right = 0;
		Integer keywordCount;
		for (; right < paragraph.size(); right++) {
			keywordCount = keywordsToCover.get(paragraph.get(right));
			if (keywordCount != null) {
				keywordsToCover.put(paragraph.get(right), --keywordCount);
				if (keywordCount >= 0) {
					--remainingToCover;
				}
			}
		}

		while (remainingToCover == 0) {
			if ((result.start == -1 && result.end == -1) || (right - left) < result.end - result.start) {
				result.start = left;
				result.end = right;
			}
			keywordCount = keywordsToCover.get(paragraph.get(left));

			if (keywordCount != null) {
				keywordsToCover.put(paragraph.get(left), ++keywordCount);
				if (keywordCount > 0) {
					++remainingToCover;
				}
			}
			++left;
		}

		return result;
	}

	// shift search window to the right--working..
	public static Subarray findSmallestSubarrayCoveringSetV2(List<String> paragraph, Set<String> keywords) {
		Subarray min = new Subarray(-10000, 10000);
		int startIndex = 0, endIndex = keywords.size() - 1;
		int minDiff = Integer.MAX_VALUE;
		while (startIndex <= endIndex && endIndex < paragraph.size()) {
			// System.out.println(paragraph.subList(startIndex, endIndex));
			if (checkIfAllPresent(paragraph, startIndex, endIndex, keywords)) {
				int diff = endIndex - startIndex;
				if (diff < minDiff) {
					minDiff = diff;
					min.start = startIndex;
					min.end = endIndex;
				}
				startIndex++;
			} else {
				endIndex++;
			}
		}

		return min;
	}

	static boolean checkIfAllPresent(List<String> paragraph, int start, int end, Set<String> keywords) {
		Set<String> copyKeywords = new HashSet<>();
		copyKeywords.addAll(keywords);
		for (int i = start; i <= end; i++)
			copyKeywords.remove(paragraph.get(i));

		if (copyKeywords.isEmpty())
			return true;
		return false;
	}

	public static Subarray findSmallestSubarrayCoveringSetBruteForce(List<String> paragraph, Set<String> keywords) {
		Subarray min = new Subarray(-10000, 10000);
		for (int i = 0; i < paragraph.size(); i++) {
			// int j = i + 1;
			int j = keywords.size() + i;
			while (j <= paragraph.size()) {
				Subarray currentMin = smallestSubarray(paragraph.subList(i, j), keywords);
				if (currentMin != null) {
					min = minSubarray(currentMin, min);
				}

				j++;
			}

			ArrayList<ArrayList<Integer>> mem = new ArrayList<>();

		}
		return min;
	}

	public static Subarray minSubarray(Subarray a, Subarray b) {
		if (a.end - a.start < b.end - b.start)
			return a;
		return b;
	}

	public static Subarray smallestSubarray(List<String> subArray, Set<String> keywords) {
		Map<String, Integer> map = new HashMap<>();
		for (int i = 0; i < subArray.size(); i++) {
			if (keywords.contains(subArray.get(i))) {
				map.put(subArray.get(i), i);
			}
		}
		int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
		if (map.size() < keywords.size())
			return null;
		for (int i : map.values()) {
			if (i < min)
				min = i;
			if (i > max)
				max = i;
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
