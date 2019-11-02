package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IntSquareRoot {

	/*
	 * 
	 * the idea here is to binary search the i*i such that it is less than k
	 * 
	 * low=0 and high =k
	 * 
	 * and since it is log n in nature it is pretty efficient
	 */
	@EpiTest(testDataFile = "int_square_root.tsv")

	public static int squareRoot(int k) {
		long low = 0, high = k;

		while (low <= high) {
			long mid = (int) (low + high) / 2;
			long midSquare = mid * mid;
			if (midSquare <= k) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}

		return (int) low - 1;
	}

	public static void main(String[] args) {
		System.exit(GenericTest.runFromAnnotations(args, "IntSquareRoot.java", new Object() {
		}.getClass().getEnclosingClass()).ordinal());
	}
}
