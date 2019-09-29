package epi;

import java.util.Deque;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IsValidParenthesization {
	@EpiTest(testDataFile = "is_valid_parenthesization.tsv")

	public static boolean isWellFormed(String s) {

		Deque<Character> dequeue = new LinkedList<>();

		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if ("{[(".contains(String.valueOf(ch))) {
				dequeue.addFirst(ch);
			} else if ("}])".contains(String.valueOf(ch))) {
				char ch2;
				try {
					ch2 = dequeue.removeFirst();
				} catch (NoSuchElementException ex) {
					return false;
				}

				switch (ch) {
				case '}':
					if (ch2 != '{') {
						return false;
					}
					break;
				case ']':
					if (ch2 != '[') {
						return false;
					}
					break;

				case ')':
					if (ch2 != '(') {
						return false;
					}
					break;
				default:
					return false;
				}
			}
		}

		return dequeue.isEmpty();
	}

	public static void main(String[] args) {
		System.exit(GenericTest.runFromAnnotations(args, "IsValidParenthesization.java", new Object() {
		}.getClass().getEnclosingClass()).ordinal());
	}
}
