package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class RealSquareRoot {
	@EpiTest(testDataFile = "real_square_root.tsv")

	public static double squareRoot(double x) {
		double low = 0, high = x;
		while (low <= high) {
			double mid = (high + low) / 2;
			double midSquare = mid * mid;
			if (midSquare < x) {
				low = mid + 1;
			} else if (midSquare == x) {
				return mid;
			} else {
				high = mid - 1;
			}

		}
		return 0.0;
	}

	public static void main(String[] args) {
		System.exit(GenericTest.runFromAnnotations(args, "RealSquareRoot.java", new Object() {
		}.getClass().getEnclosingClass()).ordinal());
	}
}
