package epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class OnlineMedian {

	/*
	 * 
	 * the idea here is that we will have two heaps
	 * 
	 * one will contain values which are greater than median and one will
	 * contain values less than median
	 * 
	 * lets start with median 0
	 * 
	 * if a value comes which is greater than median it goes to minHeap (top
	 * element in minHeap will contain value which is greater than previous
	 * median but smallest among that heap)
	 * 
	 * 
	 * same goes with maxHeap i.e. if a value comes which is less than current
	 * median it goes there i.e. top of maxHeap will contains values which are
	 * less than current median but greater than whatever is in that heap
	 * 
	 * if the heaps are skwed i.e. size difference between them is more than 1
	 * remove values from the one which is greater to the one which is less
	 * 
	 * if both have same number of items get the top from both and median will
	 * be the average of top from both of them
	 * 
	 * if one heap is larger than the other by one median will be the top of the
	 * larger one
	 * 
	 * 
	 */
	public static List<Double> onlineMedian(Iterator<Integer> sequence) {

		PriorityQueue<Integer> minHeap = new PriorityQueue<>(),
				maxHeap = new PriorityQueue<>(Collections.reverseOrder());

		double median = 0;
		List<Double> result = new ArrayList<>();

		while (sequence.hasNext()) {
			int number = sequence.next();

			if (number > median) {
				minHeap.add(number);
			} else {
				maxHeap.add(number);
			}

			if (Math.abs(minHeap.size() - maxHeap.size()) > 1) {
				if (minHeap.size() > maxHeap.size()) {
					maxHeap.add(minHeap.remove());
				} else {
					minHeap.add(maxHeap.remove());
				}
			}

			if (minHeap.size() == maxHeap.size()) {
				median = ((double) (minHeap.peek() + maxHeap.peek()) / 2);
				result.add(median);
			}

			if (Math.abs(minHeap.size() - maxHeap.size()) == 1) {
				if (minHeap.size() > maxHeap.size()) {
					median = (double) minHeap.peek();
					result.add(median);
				} else {
					median = (double) maxHeap.peek();
					result.add(median);
				}
			}
		}

		return result;
	}

	@EpiTest(testDataFile = "online_median.tsv")
	public static List<Double> onlineMedianWrapper(List<Integer> sequence) {
		return onlineMedian(sequence.iterator());
	}

	public static void main(String[] args) {
		System.exit(GenericTest.runFromAnnotations(args, "OnlineMedian.java", new Object() {
		}.getClass().getEnclosingClass()).ordinal());
	}
}
