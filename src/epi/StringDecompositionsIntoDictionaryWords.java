package epi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class StringDecompositionsIntoDictionaryWords {

	@EpiTest(testDataFile = "string_decompositions_into_dictionary_words.tsv")
	public static List<Integer> findAllSubstrings(String s, List<String> words) {

		int totalNumberOfWords = words.get(0).length() * words.size();
		int unitSize = words.get(0).length();
		List<Integer> result = new ArrayList<>();
		Map<String, Integer> mapOfWords = new HashMap<>();
		for (int i = 0; i < words.size(); i++)
			increment(words.get(i), mapOfWords);

		for (int i = 0; i <= s.length() - totalNumberOfWords; i++) {
			String subString = s.substring(i, totalNumberOfWords + i);
			checkIfCurrentSubStringContainAllWords(subString, unitSize, totalNumberOfWords, mapOfWords, result, i);

		}

		return result;
	}

	public static void checkIfCurrentSubStringContainAllWords(String string, int unitSize, int totalNumberOfWords,
			Map<String, Integer> mapOfWords, List<Integer> result, int startIndex) {
		Map<String, Integer> currentMapOfWords = new HashMap<>();
		for (int i = 0; i < string.length(); i += unitSize) {
			increment(string.substring(i, i + unitSize), currentMapOfWords);
		}
		for (String word : mapOfWords.keySet()) {
			if (!currentMapOfWords.containsKey(word)) {
				return;
			} else if (currentMapOfWords.get(word) != mapOfWords.get(word)) {
				return;
			}
		}
		result.add(startIndex);
		return;
	}

	public static void increment(String str, Map<String, Integer> dict) {
		Integer count = dict.get(str);
		if (count == null) {
			count = 0;
		}
		++count;
		dict.put(str, count);
	}

	public static List<Integer> findAllSubstringsUglyCode(String s, List<String> words) {

		int totalNumberOfWords = words.get(0).length() * words.size();
		int startIndex = 0;
		List<Integer> result = new ArrayList<>();

		Map<String, Integer> mapOfWords = new HashMap<>();
		for (int i = 0; i < words.size(); i++) {
			mapOfWords.put(words.get(i), mapOfWords.getOrDefault(words.get(i), 0) + 1);
		}

		for (int i = 0; i <= s.length() - totalNumberOfWords; i++) {
			Map<String, Integer> currentMapOfWords = new HashMap<>();
			currentMapOfWords.putAll(mapOfWords);
			String subString = s.substring(i, i + totalNumberOfWords);
			boolean notCurrentSubString = false;
			for (int j = 0; j < subString.length(); j = j + words.get(0).length()) {
				String word = subString.substring(j, j + words.get(0).length());
				if (!currentMapOfWords.containsKey(word)) {
					notCurrentSubString = true;
					break;
				} else {
					int freq = currentMapOfWords.get(word);
					currentMapOfWords.put(word, --freq);
					if (freq < 0) {
						notCurrentSubString = true;
						break;
					}
				}
			}
			if (!notCurrentSubString) {
				result.add(i);
			}
		}
		return result;
	}

	public static void main(String[] args) {
		System.exit(GenericTest.runFromAnnotations(args, "StringDecompositionsIntoDictionaryWords.java", new Object() {
		}.getClass().getEnclosingClass()).ordinal());
	}
}
