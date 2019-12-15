package epi;

import java.util.HashSet;
import java.util.Set;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class CollatzChecker {
	@EpiTest(testDataFile = "collatz_checker.tsv")

	public static boolean testCollatzConjecture(int n) {

		Set<Long> verifiedNumbers = new HashSet<>();
		for (long i = 3; i <= n; i++) {
			Set<Long> sequence = new HashSet<>();
			long number = i;
			while (true) {
				// we are adding the numbers in sequence and if a number comes
				// which is already present in the sequence means we have a loop
				// .. this will prove that Collatz Conjecture is wrong
				if (verifiedNumbers.contains(number)) {
					verifiedNumbers.add(i);
					System.out.println(i);
					break;
				}
				if (!sequence.add(number)) {
					return false;
				}
				if (number % 2 != 0) {
					long nextNumber = 3 * number + 1;
					if (nextNumber <= number) {
						throw new ArithmeticException(" number overflow");
					}
					number = nextNumber;
				} else {
					number = number / 2;
				}
				if (number == 1) {
					verifiedNumbers.add(i);
					System.out.println(i);
					break;
				}

			}
		}

		return true;
	}

	public static void main(String[] args) {
		System.exit(GenericTest.runFromAnnotations(args, "CollatzChecker.java", new Object() {
		}.getClass().getEnclosingClass()).ordinal());
	}
}
