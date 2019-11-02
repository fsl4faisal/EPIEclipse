package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;

public class SearchRowColSortedMatrix {
	@EpiTest(testDataFile = "search_row_col_sorted_matrix.tsv")
	public static boolean matrixSearch(List<List<Integer>> A, int x) {

		int i = 0, j = A.get(i).size() - 1;
		while (i < A.size() && j >= 0) {
			if(x<A.get(i).get(j))
				j--;
			else if(x==A.get(i).get(j))
				return true;
			else if(x>A.get(i).get(j))
				i++;
		}
		return false;
	}

	public static void main(String[] args) {
		System.exit(GenericTest.runFromAnnotations(args, "SearchRowColSortedMatrix.java", new Object() {
		}.getClass().getEnclosingClass()).ordinal());
	}
}
