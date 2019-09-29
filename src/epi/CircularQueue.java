package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

import java.util.ArrayList;
import java.util.List;

public class CircularQueue {

	public static class Queue {
		int[] arr;
		int start;
		int end;
		int size;
		int capacity;

		public Queue(int capacity) {
			this.capacity = capacity;
			arr = new int[capacity];
			start = 0;
			end = 0;
			size = 0;
		}

		public void enqueue(Integer x) {
			if (arr.length == size) {
				arr = resize(arr, start, end);
				start = 0;
				end = size ;
			}
			arr[end] = x;
			end = (end + 1) % arr.length;
			
			size++;
			return;
		}

		public int[] resize(int[] arr, int start, int end) {
			int[] copyQueue = new int[arr.length * 2];
			int k = 0;
			for (int i = start; i < arr.length; i++) {
				copyQueue[k++] = arr[i];
			}
			for (int i = 0; i < start; i++) {
				copyQueue[k++] = arr[i];
			}
			return copyQueue;
		}

		public Integer dequeue() {
			if (size == 0)
				throw new IllegalStateException();
			int x = arr[start];
			start = (start + 1) % arr.length;
			size--;
			return x;
		}

		public int size() {
			return size;
		}

		@Override
		public String toString() {

			return super.toString();
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

		@Override
		public String toString() {
			return op;
		}
	}

	@EpiTest(testDataFile = "circular_queue.tsv")
	public static void queueTest(List<QueueOp> ops) throws TestFailure {
		Queue q = new Queue(1);
		int opIdx = 0;
		for (QueueOp op : ops) {
			switch (op.op) {
			case "Queue":
				q = new Queue(op.arg);
				break;
			case "enqueue":
				q.enqueue(op.arg);
				break;
			case "dequeue":
				int result = q.dequeue();
				if (result != op.arg) {
					throw new TestFailure().withProperty(TestFailure.PropertyName.STATE, q)
							.withProperty(TestFailure.PropertyName.COMMAND, op).withMismatchInfo(opIdx, op.arg, result);
				}
				break;
			case "size":
				int s = q.size();
				if (s != op.arg) {
					throw new TestFailure().withProperty(TestFailure.PropertyName.STATE, q)
							.withProperty(TestFailure.PropertyName.COMMAND, op).withMismatchInfo(opIdx, op.arg, s);
				}
				break;
			}
			opIdx++;
		}
	}

	public static void main(String[] args) {
		System.exit(GenericTest.runFromAnnotations(args, "CircularQueue.java", new Object() {
		}.getClass().getEnclosingClass()).ordinal());
	}
}
