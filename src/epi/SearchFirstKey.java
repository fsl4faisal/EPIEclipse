package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;

public class SearchFirstKey {
	@EpiTest(testDataFile = "search_first_key.tsv")

	public static int searchFirstOfK(List<Integer> A, int k) {
		int left = 0, right = A.size() - 1, result = -1;

		while (left <= right) {
			int mid = left + ((right - left) / 2);
			if (A.get(mid) < k) {
				left = mid + 1;
			} else if (A.get(mid) == k) {
				result = mid;
				right = mid - 1;
			} else {
				right = mid - 1;
			}
		}
		return result;
	}

	public static int searchFirstOfKV1(List<Integer> A, int k) {
		return search(A, 0, A.size() - 1, k);
	}

	public static int search(List<Integer> A, int low, int high, int k) {
		int mid = (low + high) / 2;
		if (low > high) {
			return -1;
		}
		if (A.get(mid) == k) {
			while (mid - 1 >= 0 && A.get(mid - 1) == k) {
				mid--;
			}
			return mid;
		}
		if (A.get(mid) > k) {
			return search(A, low, mid - 1, k);
		} else {
			return search(A, mid + 1, high, k);
		}
	}

	public static void main(String[] args) {
		System.exit(GenericTest.runFromAnnotations(args, "SearchFirstKey.java", new Object() {
		}.getClass().getEnclosingClass()).ordinal());
	}
}
