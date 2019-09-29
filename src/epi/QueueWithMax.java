package epi;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

public class QueueWithMax<T extends Comparable<T>> {

	Deque<T> queue = new LinkedList<>();
	Deque<T> maxElements = new LinkedList<>();

	public void enqueue(T x) {
		queue.addLast(x);
		if (maxElements.isEmpty()) {
			maxElements.addLast(x);
		} else {
			while (!maxElements.isEmpty() && maxElements.getLast().compareTo(x) < 0) {
				maxElements.removeLast();
			}
			maxElements.addLast(x);
		}
		return;
	}

	public T dequeue() {
		if (!queue.isEmpty()) {
			T x = queue.removeFirst();
			if (x == maxElements.getFirst()) {
				maxElements.removeFirst();
			}
			return x;

		} else {
			throw new NoSuchElementException();
		}
	}

	public T max() {
		if (!queue.isEmpty()) {
			return maxElements.getFirst();
		} else {
			throw new NoSuchElementException();
		}
	}

	@EpiUserType(ctorParams = { String.class, int.class })
	public static class QueueOp {
		public String op;
		public int arg;

		public QueueOp(String op, int arg) {
			this.op = op;
			this.arg = arg;
		}
	}

	@EpiTest(testDataFile = "queue_with_max.tsv")
	public static void queueTest(List<QueueOp> ops) throws TestFailure {
		try {
			QueueWithMax q = new QueueWithMax();

			for (QueueOp op : ops) {
				switch (op.op) {
				case "QueueWithMax":
					q = new QueueWithMax();
					break;
				case "enqueue":
					q.enqueue(op.arg);
					break;
				case "dequeue":
					int result = (int) q.dequeue();
					if (result != op.arg) {
						throw new TestFailure(
								"Dequeue: expected " + String.valueOf(op.arg) + ", got " + String.valueOf(result));
					}
					break;
				case "max":
					int s = (int) q.max();
					if (s != op.arg) {
						throw new TestFailure("Max: expected " + String.valueOf(op.arg) + ", got " + String.valueOf(s));
					}
					break;
				}
			}
		} catch (NoSuchElementException e) {
			throw new TestFailure("Unexpected NoSuchElement exception");
		}
	}

	public static void main(String[] args) {
		System.exit(GenericTest.runFromAnnotations(args, "QueueWithMax.java", new Object() {
		}.getClass().getEnclosingClass()).ordinal());
	}
}
