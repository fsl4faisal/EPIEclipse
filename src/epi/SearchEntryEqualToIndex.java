package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
import java.util.List;

public class SearchEntryEqualToIndex {

	public static int searchEntryEqualToItsIndex(List<Integer> A) {
		int left = 0, right = A.size() - 1;
		int result = -1;
		while (left <= right) {
			int mid = left + ((right - left) / 2);

			/*
			 * 
			 * the idea here is that if A[j]>j then since it's a sorted list
			 * there can not be any A[j] such that it is equal to j
			 * 
			 * Hence in above case go right i.e. right=mid-1
			 * 
			 * otherwise if A[j]==j return j
			 * 
			 * else
			 * 
			 * left=mid+1
			 */
			if (A.get(mid) > mid) {
				right = mid - 1;
			} else if (A.get(mid) == mid) {
				result = mid;
				break;
			} else {
				left = mid + 1;
			}
		}
		return result;
	}

	@EpiTest(testDataFile = "search_entry_equal_to_index.tsv")
	public static void searchEntryEqualToItsIndexWrapper(TimedExecutor executor, List<Integer> A) throws Exception {
		int result = executor.run(() -> searchEntryEqualToItsIndex(A));

		if (result != -1) {
			if (A.get(result) != result) {
				throw new TestFailure("Entry does not equal to its index");
			}
		} else {
			for (int i = 0; i < A.size(); ++i) {
				if (A.get(i) == i) {
					throw new TestFailure("There are entries which equal to its index");
				}
			}
		}
	}

	public static void main(String[] args) {
		System.exit(GenericTest.runFromAnnotations(args, "SearchEntryEqualToIndex.java", new Object() {
		}.getClass().getEnclosingClass()).ordinal());
	}
}
