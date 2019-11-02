package epi;

import java.util.HashMap;
import java.util.Map;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IsStringPermutableToPalindrome {
	@EpiTest(testDataFile = "is_string_permutable_to_palindrome.tsv")

	public static boolean canFormPalindrome(String s) {
		Map<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < s.length(); i++) {
			map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
		}
		for (char ch : map.keySet()) {
			map.put(ch, map.get(ch) % 2);
		}
		int counter = 0;
		for (char ch : map.keySet()) {
			if (map.get(ch) == 1)
				counter++;
			if (counter > 1) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		System.exit(GenericTest.runFromAnnotations(args, "IsStringPermutableToPalindrome.java", new Object() {
		}.getClass().getEnclosingClass()).ordinal());
	}
}
