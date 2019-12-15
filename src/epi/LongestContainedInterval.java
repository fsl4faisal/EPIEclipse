package epi;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class LongestContainedInterval {
	@EpiTest(testDataFile = "longest_contained_interval.tsv")

	public static int longestContainedRange(List<Integer> A) {
		Set<Integer> set = new HashSet<>(A);
		int maxContainedInterval = Integer.MIN_VALUE;
		while (!set.isEmpty()) {
			int currentNumber = set.iterator().next();
			int lowerBound = currentNumber - 1;
			set.remove(currentNumber);
			while (set.contains(lowerBound)) {
				set.remove(lowerBound);
				lowerBound--;
			}
			int upperBound = currentNumber + 1;

			while (set.contains(upperBound)) {
				set.remove(upperBound);
				upperBound++;
			}
			maxContainedInterval = Math.max(upperBound - lowerBound - 1, maxContainedInterval);
		}
		return maxContainedInterval;
	}

	public static void main(String[] args) {
		System.exit(GenericTest.runFromAnnotations(args, "LongestContainedInterval.java", new Object() {
		}.getClass().getEnclosingClass()).ordinal());
	}
}
