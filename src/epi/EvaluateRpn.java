package epi;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class EvaluateRpn {
	@EpiTest(testDataFile = "evaluate_rpn.tsv")

	public static int eval(String expression) throws IllegalAccessException {
		Deque<Integer> deque = new LinkedList<>();
		String[] stringArr = expression.split(",");
		int a, b;

		for (String str : stringArr) {
			if ("+/*-".contains(str)) {
				a = deque.removeFirst();
				b = deque.removeFirst();
				switch (str) {
				case "+":
					deque.addFirst(a + b);
					break;
				case "-":
					deque.addFirst(b - a);
					break;
				case "*":
					deque.addFirst(b * a);
					break;
				case "/":
					deque.addFirst(b / a);
					break;
				default:
					throw new IllegalAccessException("exception");
				}
			} else {
				deque.addFirst(Integer.parseInt(str));
			}

		}
		return deque.removeFirst();
	}

	public static int evalUsingStack(String expression) {
		Stack<String> stack = new Stack<>();
		String[] stringArr = expression.split(",");
		int a, b;

		for (String str : stringArr) {

			switch (str) {
			case "+":
				stack.push(String.valueOf(Integer.parseInt(stack.pop()) + Integer.parseInt(stack.pop())));
				break;
			case "-":
				a = Integer.parseInt(stack.pop());
				b = Integer.parseInt(stack.pop());
				stack.push(String.valueOf(b - a));
				break;
			case "*":
				stack.push(String.valueOf(Integer.parseInt(stack.pop()) * Integer.parseInt(stack.pop())));
				break;
			case "/":
				a = Integer.parseInt(stack.pop());
				b = Integer.parseInt(stack.pop());
				stack.push(String.valueOf(b / a));
				break;
			default:
				stack.push(str);
			}

		}
		return Integer.parseInt(stack.pop());
	}

	public static void main(String[] args) {
		System.exit(GenericTest.runFromAnnotations(args, "EvaluateRpn.java", new Object() {
		}.getClass().getEnclosingClass()).ordinal());
	}
}
