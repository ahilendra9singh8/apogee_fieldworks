package fieldworks.DSA.Basic;

public class BasicRecursion {
	public static void main(String[] args) {
		// // 1.Print name N times
		// int n = 4;
		// printName(1, n);

		// // 2.Print 1 to N using recursion
		// int n = 10;
		// print1toN(1, n);

		// // 3.Print N to 1 using recursion
		// int n = 10;
		// printNto1(n, 1);

		// // 4.Sum of first N numbers
		// int n = 3;
		// int sum = printSum(n, 0);
		// int sum = printSum2(n);
		// System.out.println(sum);

		// // 5. Factorial of N numbers
		// int n = 5;
		// int fact = factorial(n, 1);
		// int fact = factorial2(n);
		// System.out.println(fact);

		// // 6.Fibonacci Number
		// 6.1: using loop
		// int n = 5;
		// int a = 0;
		// int b = 1;
		// for (int i = 0; i <= n; i++) {
		// System.out.println(a);
		// int c = a + b;
		// a = b;
		// b = c;
		// }

		// // 6.2: using recursion
		// //6.2.1==> return only number
		// System.out.println(fibonacci(5));

		// //6.2.1==> return series
		// int n = 5;
		// for (int i = 0; i < n; i++) {
		// System.out.print(fibonacci2(i) + " ");
		// }

		// 7.Reverse an array
		// 7.1 : using loop\
		// int[] arr = { 5, 4, 3, 2, 1 };
		// int n = arr.length;
		// int i = 0;
		// int j = n - 1;
		// while (i <= j) {
		// int temp = arr[i];
		// arr[i] = arr[j];
		// arr[j] = temp;
		// i++;
		// j--;
		// }
		// for (int val : arr) {
		// System.out.println(val);
		// }

		// 7.2 using recursion

		// 8.check string is pallindrome or not
	}

	// // 6.2.1
	// private static int fibonacci(int n) {
	// if (n <= 1) {
	// return n;
	// }
	// return fibonacci(n - 1) + fibonacci(n - 2);
	// }

	// // 6.2.2
	// private static int fibonacci2(int n) {
	// if (n <= 1) {
	// return n;
	// }
	// return fibonacci2(n - 1) + fibonacci2(n - 2);
	// }

	// // 5.1
	// private static int factorial(int n, int fact) {
	// if (n == 1) {
	// return fact;
	// }
	// return factorial(n - 1, fact * n);
	// }

	// // 5.2
	// private static int factorial2(int n) {
	// if (n == 1) {
	// return 1;
	// }
	// return n * factorial2(n - 1);
	// }

	// // // 4.1
	// private static int printSum(int n, int sum) {
	// if (n < 1) {
	// return sum;
	// }
	// return printSum(n - 1, sum + n);
	// }

	// // 4.2
	// private static int printSum2(int n) {
	// if (n == 1) {
	// return 1;
	// }
	// return n + printSum2(n - 1);
	// }

	// // 3.
	// private static void printNto1(int n, int i) {
	// if (n < i)
	// return;
	// System.out.println(n);
	// printNto1(n - 1, i);
	// }

	// // 2.
	// private static void print1toN(int i, int n) {
	// if (i > n)
	// return;
	// System.out.println(i);
	// print1toN(i + 1, n);
	// }

	// // 1.
	// private static void printName(int i, int n) {
	// if (i > n)
	// return;
	// System.out.println("Raj");
	// printName(i + 1, n);
	// }
}
