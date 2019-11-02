package epi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;

public class SearchForMissingElement {
	@EpiUserType(ctorParams = { Integer.class, Integer.class })

	public static class DuplicateAndMissing {
		public Integer duplicate;
		public Integer missing;

		public DuplicateAndMissing(Integer duplicate, Integer missing) {
			this.duplicate = duplicate;
			this.missing = missing;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (o == null || getClass() != o.getClass()) {
				return false;
			}

			DuplicateAndMissing that = (DuplicateAndMissing) o;

			if (!duplicate.equals(that.duplicate)) {
				return false;
			}
			return missing.equals(that.missing);
		}

		@Override
		public int hashCode() {
			int result = duplicate.hashCode();
			result = 31 * result + missing.hashCode();
			return result;
		}

		@Override
		public String toString() {
			return "duplicate: " + duplicate + ", missing: " + missing;
		}
	}

	@EpiTest(testDataFile = "find_missing_and_duplicate.tsv")

	public static DuplicateAndMissing findDuplicateMissing(List<Integer> A) {

		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < A.size(); i++)
			map.put(i, 0);

		for (int i = 0; i < A.size(); i++)
			map.put(A.get(i), map.getOrDefault(A.get(i), 0) + 1);

		int missing = 0, dup = 0;
		for (int i : map.keySet()) {
			if (map.get(i) == 0)
				missing = i;
			if (map.get(i) == 2) {
				dup = i;
			}
		}

		return new DuplicateAndMissing(dup, missing);
	}

	public static void main(String[] args) {
		System.exit(GenericTest.runFromAnnotations(args, "SearchForMissingElement.java", new Object() {
		}.getClass().getEnclosingClass()).ordinal());
	}
}
