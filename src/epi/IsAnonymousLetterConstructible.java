package epi;

import java.util.HashMap;
import java.util.Map;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IsAnonymousLetterConstructible {
	@EpiTest(testDataFile = "is_anonymous_letter_constructible.tsv")

	public static boolean isLetterConstructibleFromMagazine(String letterText, String magazineText) {
		Map<Character, Integer> letterCount = new HashMap<>();
		Map<Character, Integer> magCount = new HashMap<>();
		for (int i = 0; i < letterText.length(); i++) {
			letterCount.put(letterText.charAt(i), letterCount.getOrDefault(letterText.charAt(i), 0) + 1);
		}
		for (int i = 0; i < magazineText.length(); i++) {
			magCount.put(magazineText.charAt(i), magCount.getOrDefault(magazineText.charAt(i), 0) + 1);
		}
		for (char ch : letterCount.keySet()) {
			int count1 = letterCount.get(ch);
			int count2 = magCount.getOrDefault(ch, 0);
			if (count2 < count1)
				return false;
		}
		return true;
	}

	public static void main(String[] args) {
		System.exit(GenericTest.runFromAnnotations(args, "IsAnonymousLetterConstructible.java", new Object() {
		}.getClass().getEnclosingClass()).ordinal());
	}
}
