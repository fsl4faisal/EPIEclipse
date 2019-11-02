package epi;

import java.util.Collections;
import java.util.Comparator;
import java.util.EmptyStackException;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Stack;

public class StackUsingHeap {

	public static class Entry {
		int index;
		int value;

		public Entry(int index, int value) {
			this.index = index;
			this.value = value;

		}
	}

	public static class EntryComparator implements Comparator<Entry> {

		public int compare(Entry entry1, Entry entry2) {
			return entry1.index - entry2.index;

		}
	}

	public static class StackFromHeap {
		PriorityQueue<Entry> queue;
		int index;

		public StackFromHeap() {
			queue = new PriorityQueue<Entry>(Collections.reverseOrder(new EntryComparator()));
			int index = 0;
		}

		public int peek() {
			return queue.peek().value;
		}

		public int pop() {
			index--;
			return queue.remove().value;
		}

		public void push(int i) {
			queue.add(new Entry(index, i));
			index++;
		}

	}

	public static void main(String[] args) {
		Stack<Integer> factoryStack = new Stack<Integer>();
		StackFromHeap customStack = new StackFromHeap();

		Random r = new Random();

		for (int i = 0; i < 100; i++) {
			int choice = new Random().nextInt(3);
			int item = r.nextInt(100);
			switch (choice) {
			// push
			case 0:
				factoryStack.push(item);
				customStack.push(item);
				break;
			// peek
			case 1:
				try {
					if (factoryStack.peek() != customStack.peek()) {
						System.out.println("Not corretly implemented");
						System.exit(12);
						break;
					} else {
						System.out.println("All good");
					}
					break;

				} catch (EmptyStackException e) {
					System.out.println("Empty stack");
				}
				// pop
			case 2:
				try {
					int value1 = factoryStack.pop();
					int value2 = customStack.pop();
					if (value1 != value2) {
						System.out.println("Not corretly implemented");
						System.exit(12);
					} else {
						System.out.println("All good");
					}
					break;

				} catch (EmptyStackException e) {

				}
			}
		}
	}
}
