package epi;

import java.util.Deque;
import java.util.LinkedList;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class DirectoryPathNormalization {
	@EpiTest(testDataFile = "directory_path_normalization.tsv")

	public static String shortestEquivalentPath(String path) {

		String[] arr = path.split("/");

		Deque<String> list = new LinkedList<>();
		for (String str : arr) {
			System.out.println("str");
		}

		return "";
	}

	public static void main(String[] args) {
		System.exit(GenericTest.runFromAnnotations(args, "DirectoryPathNormalization.java", new Object() {
		}.getClass().getEnclosingClass()).ordinal());
	}
}
