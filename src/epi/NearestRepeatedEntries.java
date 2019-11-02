package epi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class NearestRepeatedEntries {

	@EpiTest(testDataFile = "nearest_repeated_entries.tsv")
	public static int findNearestRepetition(List<String> paragraph) {
		String[] stringArr = paragraph.toString().substring(1, paragraph.toString().length() - 1).split(", ");
		int counter = 0;
		Map<String, Integer> map = new HashMap<>();
		int min = Integer.MAX_VALUE;
		boolean found = false;

		for (int i = 0; i < stringArr.length; i++) {
			if (map.containsKey(stringArr[i])) {
				int diff = counter - map.get(stringArr[i]);
				if (diff < min) {
					found = true;
					min = diff;
				}
				map.put(stringArr[i], counter);
			} else {
				map.put(stringArr[i], counter);
			}
			counter++;
		}

		if (found)
			return min;
		else
			return -1;
	}

	public static int findNearestRepetitionTrivial(List<String> paragraph) {
		String[] stringArr = paragraph.toString().substring(1, paragraph.toString().length() - 1).split(", ");
		int counter = 0;
		Map<String, List<Integer>> map = new HashMap<>();
		for (int i = 0; i < stringArr.length; i++) {

			if (map.containsKey(stringArr[i])) {
				map.get(stringArr[i]).add(counter);
			} else {
				List<Integer> list = new ArrayList<>();
				list.add(counter);
				map.put(stringArr[i], list);
			}
			counter++;
		}

		int min = Integer.MAX_VALUE;
		boolean found = false;
		for (List<Integer> list : map.values()) {

			if (list.size() >= 2) {
				int i = list.size() - 1;
				while (i > 0) {
					int difference = list.get(i--) - list.get(i);
					if (difference < min) {
						found = true;
						min = difference;
					}
				}
			}
		}
		if (found)
			return min;
		else
			return -1;
	}

	public static void main(String[] args) {
		System.exit(GenericTest.runFromAnnotations(args, "NearestRepeatedEntries.java", new Object() {
		}.getClass().getEnclosingClass()).ordinal());
	}
}
