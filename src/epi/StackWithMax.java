package epi;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

public class StackWithMax {

	public static class ElementWithCacheMax {
		public int element;
		public int max;

		public ElementWithCacheMax(int element, int max) {
			this.element = element;
			this.max = max;
		}
	}

	public static class MaxWithCount {
		public int max;
		public int count;

		public MaxWithCount(int max, int count) {
			super();
			this.max = max;
			this.count = count;
		}
	}

	public static class Stack {
		List<MaxWithCount> max = new ArrayList<>();
		List<Integer> list = new ArrayList<>();

		public boolean empty() {
			return max.isEmpty();
		}

		public Integer max() {
			return max.get(max.size() - 1).max;
		}

		public Integer pop() {
			int number = list.remove(list.size() - 1);
			if (number == max.get(max.size() - 1).max) {
				max.get(max.size() - 1).count--;
				if (max.get(max.size() - 1).count == 0) {
					max.remove(max.size() - 1);
				}
			}
			return number;
		}

		public void push(Integer x) {
			if (list.isEmpty()) {
				max.add(new MaxWithCount(x, 1));
			} else {
				int maxNumber = max.get(max.size() - 1).max;
				if (maxNumber == x) {
					max.get(max.size() - 1).count += 1;
				} else if (x > maxNumber) {
					max.add(new MaxWithCount(x, 1));
				}
			}
			list.add(x);
		}
	}

	public static class StackBookVersion1 {
		List<ElementWithCacheMax> list = new ArrayList<>();

		public boolean empty() {
			return list.isEmpty();
		}

		public Integer max() {
			return list.get(list.size() - 1).max;
		}

		public Integer pop() {
			return list.remove(list.size() - 1).element;
		}

		public void push(Integer x) {
			if (list.isEmpty()) {
				list.add(new ElementWithCacheMax(x, x));
			} else {
				int max = list.get(list.size() - 1).max;
				if (max > x) {
					list.add(new ElementWithCacheMax(x, max));
				} else {
					list.add(new ElementWithCacheMax(x, x));
				}
			}
		}
	}

	public static class StackMyTrivialVersion {
		List<Integer> list = new ArrayList<>();
		int topIndex = -1;
		List<Integer> max = new ArrayList<>();

		public boolean empty() {
			return list.isEmpty();
		}

		public Integer max() {
			return max.get(topIndex);
		}

		public Integer pop() {
			int x = list.remove(topIndex);
			max.remove(topIndex);
			topIndex--;
			return x;
		}

		public void push(Integer x) {
			if (list.isEmpty()) {
				list.add(x);
				max.add(x);
			} else {
				list.add(x);
				if (max.get(topIndex) < x) {
					max.add(x);
				} else {
					max.add(max.get(topIndex));
				}
			}
			topIndex++;
			return;
		}
	}

	@EpiUserType(ctorParams = { String.class, int.class })
	public static class StackOp {
		public String op;
		public int arg;

		public StackOp(String op, int arg) {
			this.op = op;
			this.arg = arg;
		}
	}

	@EpiTest(testDataFile = "stack_with_max.tsv")
	public static void stackTest(List<StackOp> ops) throws TestFailure {
		try {
			Stack s = new Stack();
			int result;
			for (StackOp op : ops) {
				switch (op.op) {
				case "Stack":
					s = new Stack();
					break;
				case "push":
					s.push(op.arg);
					break;
				case "pop":
					result = s.pop();
					if (result != op.arg) {
						throw new TestFailure(
								"Pop: expected " + String.valueOf(op.arg) + ", got " + String.valueOf(result));
					}
					break;
				case "max":
					result = s.max();
					if (result != op.arg) {
						throw new TestFailure(
								"Max: expected " + String.valueOf(op.arg) + ", got " + String.valueOf(result));
					}
					break;
				case "empty":
					result = s.empty() ? 1 : 0;
					if (result != op.arg) {
						throw new TestFailure(
								"Empty: expected " + String.valueOf(op.arg) + ", got " + String.valueOf(s));
					}
					break;
				default:
					throw new RuntimeException("Unsupported stack operation: " + op.op);
				}
			}
		} catch (NoSuchElementException e) {
			throw new TestFailure("Unexpected NoSuchElement exception");
		}
	}

	public static void main(String[] args) {
		System.exit(GenericTest.runFromAnnotations(args, "StackWithMax.java", new Object() {
		}.getClass().getEnclosingClass()).ordinal());
	}
}
