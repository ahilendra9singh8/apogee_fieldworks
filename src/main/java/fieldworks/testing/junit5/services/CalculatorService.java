package fieldworks.testing.junit5.services;

public class CalculatorService {

	public static int addTwoNumbers(int a, int b) {
		return a + b;
	}

	public static int productTwoNumbers(int a, int b) {
		return a * b;
	}

	public static int divideTwoNumbers(int a, int b) {
		return a / b;
	}

	public static int sumAnyNumbers(int... Numbers) {
		int s = 0;
		for (int val : Numbers) {
			s += val;
		}

		return s;
	}

}

