package epi;

import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class SunsetView {

	public static class BuildingIdWithHeight {
		int id;
		int height;

		public BuildingIdWithHeight(int id, int height) {
			super();
			this.id = id;
			this.height = height;
		}

	}

	// Work on it laterr
	public static List<Integer> examineBuildingsWithSunset(Iterator<Integer> sequence) {
		Deque<BuildingIdWithHeight> sunsetWithView = new LinkedList<>();
		int id = 0;
		while (sequence.hasNext()) {
			int height = sequence.next();
			if (!sunsetWithView.isEmpty() && height > sunsetWithView.getLast().height) {
				sunsetWithView.addLast(new BuildingIdWithHeight(id, height));
			}
			if (sunsetWithView.isEmpty()) {
				sunsetWithView.addLast(new BuildingIdWithHeight(id, height));
			}
			id++;
		}
		Deque<BuildingIdWithHeight> westEnabled = new LinkedList<>();

		List<Integer> list = new LinkedList<>();
		while (!sunsetWithView.isEmpty()) {
			list.add(westEnabled.removeLast().id);
		}

		return list;
	}

	public static List<Integer> examineBuildingsWithSunsetMyVersion(Iterator<Integer> sequence) {
		Deque<BuildingIdWithHeight> stack = new LinkedList<>();
		int id = 0;
		while (sequence.hasNext()) {
			stack.addFirst(new BuildingIdWithHeight(id, sequence.next()));
			id++;
		}
		Deque<BuildingIdWithHeight> westEnabled = new LinkedList<>();

		while (!stack.isEmpty()) {
			if (westEnabled.isEmpty()) {
				westEnabled.addFirst(stack.removeFirst());
			} else if (stack.getFirst().height > westEnabled.getFirst().height) {
				westEnabled.addFirst(stack.removeFirst());
			} else {
				stack.removeFirst();
			}
		}
		List<Integer> list = new LinkedList<>();
		while (!westEnabled.isEmpty()) {
			list.add(westEnabled.removeLast().id);
		}

		return list;
	}

	@EpiTest(testDataFile = "sunset_view.tsv")
	public static List<Integer> examineBuildingsWithSunsetWrapper(List<Integer> sequence) {
		return examineBuildingsWithSunset(sequence.iterator());
	}

	public static void main(String[] args) {
		System.exit(GenericTest.runFromAnnotations(args, "SunsetView.java", new Object() {
		}.getClass().getEnclosingClass()).ordinal());
	}
}
