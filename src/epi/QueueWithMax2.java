package epi;

import java.util.List;
import java.util.NoSuchElementException;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

public class QueueWithMax2 {

	StackWithMax.Stack s1 = new StackWithMax.Stack();
	StackWithMax.Stack s2 = new StackWithMax.Stack();

	public void enqueue(int x) {
		s1.push(x);
		return;
	}

	public int dequeue() {
		if (s1.empty() && s2.empty()) {
			throw new NoSuchElementException();
		} else if (s2.empty()) {
			while (!s1.empty()) {
				s2.push(s1.pop());
			}
		}
		return s2.pop();
	}

	public int max() {
		if (s1.empty() && s2.empty()) {
			throw new NoSuchElementException();
		} else if (s1.empty() && !s2.empty()) {
			return s2.max();
		} else if (!s1.empty() && s2.empty()) {
			return s1.max();
		} else {
			return s1.max() > s2.max() ? s1.max() : s2.max();
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
			QueueWithMax2 q = new QueueWithMax2();

			for (QueueOp op : ops) {
				switch (op.op) {
				case "QueueWithMax":
					q = new QueueWithMax2();
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
