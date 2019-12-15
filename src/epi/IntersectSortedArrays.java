package epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IntersectSortedArrays {

	public static List<Integer> intersectTwoSortedArraysV1(List<Integer> A, List<Integer> B) {
		Set<Integer> ans = new LinkedHashSet<>();
		int i = 0, j = 0;
		while (i < A.size() && j < B.size()) {
			if (A.get(i) < B.get(j))
				i++;
			else if (A.get(i) > B.get(j))
				j++;
			else {
				ans.add(A.get(i));
				i++;
				j++;
			}
		}

		return new ArrayList<Integer>(ans);
	}

	@EpiTest(testDataFile = "intersect_sorted_arrays.tsv")
	public static List<Integer> intersectTwoSortedArraysV2(List<Integer> A, List<Integer> B) {
		List<Integer> ans = new ArrayList<>();
		int i = 0, j = 0;
		while (i < A.size() && j < B.size()) {
			if (i == 0) {
				if (A.get(i)==B.get(j)) {
					ans.add(A.get(i));
					i++;
					j++;
				} else if (A.get(i) < B.get(j))
					i++;
				else
					j++;
			} else if (A.get(i).equals(A.get(i - 1))) {
				i++;
				continue;
			} else if (A.get(i).equals(B.get(j))) {
				ans.add(A.get(i));
				i++;
				j++;
			} else if (A.get(i) < B.get(j))
				i++;
			else
				j++;
		}

		return new ArrayList<Integer>(ans);
	}

	public static List<Integer> intersectTwoSortedArraysOrderMN(List<Integer> A, List<Integer> B) {
		// TODO - you fill in here.
		List<Integer> ans = new ArrayList<>();

		for (int i = 0; i < A.size(); i++) {
			if (i == 0 || A.get(i) != A.get(i - 1)) {
				for (Integer b : B) {
					if (A.get(i).equals(b)) {
						ans.add(A.get(i));
						break;
					}
				}
			}
		}
		return ans;
	}

	public static List<Integer> intersectTwoSortedArrays(List<Integer> A, List<Integer> B) {
		// TODO - you fill in here.
		List<Integer> ans = new ArrayList<>();

		for (int i = 0; i < A.size(); i++) {
			if (i == 0 || A.get(i) != A.get(i - 1)) {
				if (Collections.binarySearch(B, A.get(i)) >= 0) {
					ans.add(A.get(i));

				}
			}
		}
		return ans;
	}

	public static void main(String[] args) {
		System.exit(GenericTest.runFromAnnotations(args, "IntersectSortedArrays.java", new Object() {
		}.getClass().getEnclosingClass()).ordinal());
	}
}
