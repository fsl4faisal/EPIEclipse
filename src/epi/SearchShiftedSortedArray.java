package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;

public class SearchShiftedSortedArray {
	@EpiTest(testDataFile = "search_shifted_sorted_array.tsv")

	public static int searchSmallest(List<Integer> A) {

		int left = 0, right = A.size() - 1;

		while (left < right) {
			int mid = left + ((right - left) / 2);
			/*
			 * if value at mid is greater than value at right that means it is a
			 * decreasing list and the smallest lies in the right
			 */
			if (A.get(mid) > A.get(right)) {
				left = mid + 1;
			}
			/*
			 * otherwise (i.e. from mid to right most all the elements are in
			 * correct order so the smallest lies in the left side
			 * 
			 * so for the next round the right most element would be the current
			 * mid i.e. right=mid
			 */
			else {
				right = mid;
			}
		}

		/*
		 * since the while loop is continuing until left<right the moment both
		 * become equal it breaks
		 */
		return left;
	}

	public static void main(String[] args) {
		System.exit(GenericTest.runFromAnnotations(args, "SearchShiftedSortedArray.java", new Object() {
		}.getClass().getEnclosingClass()).ordinal());
	}
}
