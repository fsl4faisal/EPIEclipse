package faisal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MyHeap {

	public static class CustomHeap {

		List<Integer> list;

		public CustomHeap(int capacity) {
			list = new ArrayList<Integer>(capacity);
		}

		public Integer remove() {
			Integer number = list.get(0);
			list.set(0, list.get(list.size() - 1));
			list.remove(list.size() - 1);
			trickleDown(0);
			return number;
		}

		public void trickleDown(int index) {
			int leftChildIndex = getLeftChild(index);
			int rightChildIndex = getRightChild(index);
			if (list.size() > leftChildIndex && list.size() > rightChildIndex) {
				int leftChild = list.get(leftChildIndex);
				int rightChild = list.get(rightChildIndex);
				if (list.get(index) > leftChild || list.get(index) > rightChild) {
					if (leftChild > rightChild) {
						swap(index, rightChildIndex);
						trickleDown(rightChildIndex);
					} else {
						swap(index, leftChildIndex);
						trickleDown(leftChildIndex);
					}
				}
			} else if (list.size() > leftChildIndex) {
				if (list.get(index) > list.get(leftChildIndex)) {
					swap(index, leftChildIndex);
					trickleDown(leftChildIndex);
				}
			}

		}

		public int getLeftChild(int index) {
			return 2 * index + 1;
		}

		public int getRightChild(int index) {
			return 2 * index + 2;
		}

		public void add(Integer number) {
			list.add(number);
			heapify(list.size() - 1);
		}

		private void heapify(int index) {
			if (index > 0) {
				if (list.get(index) < list.get(getParent(index))) {
					swap(index, getParent(index));
					heapify(getParent(index));
				}

			}

		}

		private void swap(int i, int j) {
			int temp = list.get(i);
			list.set(i, list.get(j));
			list.set(j, temp);

		}

		private int getParent(int index) {
			if (index % 2 == 0) {
				return index / 2 - 1;
			} else {
				return index / 2;
			}
		}

		public boolean isEmpty() {
			return list.isEmpty();
		}

	}

	public static void main(String[] args) {

		CustomHeap heap = new CustomHeap(10);
		Random r = new Random();
		int[] arr = { 80, 98, 89, 70, 8, 36, 51, 54, 70, 54 };
		List<Integer> control1 = new ArrayList<>();

		for (int i = 0; i < 1000; i++) {
			int number = r.nextInt(100);
			heap.add(number);
			control1.add(number);
		}

		List<Integer> control2 = new ArrayList<>();
		while (!heap.isEmpty()) {
			control2.add(heap.remove());
		}

		for (int i = 0; i < control1.size(); i++) {
			System.out.print(control1.get(i) + ", ");
		}
		System.out.println();

		Collections.sort(control1);
		for (int i = 0; i < control1.size(); i++) {
			System.out.print(control1.get(i) + ", ");
		}
		System.out.println();

		for (int i = 0; i < control2.size(); i++) {
			System.out.print(control2.get(i) + ", ");
		}
		for (int i = 0; i < control1.size(); i++) {
			if (control1.get(i) != control2.get(i)) {
				System.out.println("not equal mismatch at " + i);
				break;
			}
		}

	}
}
