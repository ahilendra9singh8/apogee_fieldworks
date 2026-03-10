package fieldworks.DSA.Basic;

import java.util.ArrayList;
import java.util.List;

public class BasicMath {
	public static void main(String[] args) {
		// // 1. Count Digits
		// int n = 12345;
		// int cnt = 0;
		// while (n > 0) {
		// cnt++;
		// n = n / 10;
		// }
		// System.out.println(cnt);

		// // 2.Reverse Digits of A Number
		// int x = 12345;
		// int reverse = 0;
		// while (x != 0) {
		// int lastdigit = x % 10;
		// // Overflow check
		// if (reverse > Integer.MAX_VALUE / 10 ||
		// (reverse == Integer.MAX_VALUE / 10 && lastdigit > 7)) return 0;
		// if (reverse < Integer.MIN_VALUE / 10 ||
		// (reverse == Integer.MIN_VALUE / 10 && lastdigit < -8)) return 0;

		// reverse = (reverse * 10) + lastdigit;
		// x = x / 10;
		// }
		// System.out.println(reverse);

		// // 3.Palindrome or Not
		// int n = 4554;
		// int real = n;
		// int result = 0;
		// while (n > 0) {
		// int rem = n % 10;
		// result = result * 10 + rem;
		// n = n / 10;
		// }
		// if (result == real) {
		// System.out.println("it is pallindrome");
		// } else {
		// System.out.println("it is not pallindrome");
		// }

		// // 4.Armstrong Number or not
		// int n = 371;
		// int len = String.valueOf(n).length();
		// int real = n;
		// int result = 0;
		// while (n > 0) {
		// int rem = n % 10;
		// result += Math.pow(rem, len);
		// n = n / 10;
		// }
		// if(real == result){
		// System.out.println("yes");
		// }else{
		// System.out.println("no");
		// }

		// // 5.Print all Divisors of a given Number
		// int n = 12;
		// List<Integer> list = new ArrayList<>();
		// for (int i = 1; i <= n; i++) {
		// if (n % i == 0) {
		// list.add(i);
		// }
		// }
		// for (int val : list) {
		// System.out.println(val);
		// }

		// // 6.Check for prime
		// int n = 2;
		// int cnt = 0;
		// for (int i = 1; i <= n; i++) {
		// if (n % i == 0) {
		// cnt++;
		// }
		// }
		// if (cnt == 2) {
		// System.out.println("yes");
		// } else {
		// System.out.println("no");
		// }

		// // 7.GCD (or) HCF
		// int n1 = 9;
		// int n2 = 12;
		// int min = Math.min(n1, n2);
		// int gcd = 1;
		// for (int i = 1; i <= min / 2; i++) {
		// if (n1 % i == 0 && n2 % i == 0) {
		// gcd = i;
		// }
		// }
		// System.out.println(gcd);

		// // 8.LCM-Least Common Multiple.
		// int n1 = 4;
		// int n2 = 5;
		// int lcm = Math.max(n1, n2);
		// while (true) {
		// if (lcm % n1 == 0 && lcm % n2 == 0) {
		// break;
		// }
		// lcm++;
		// }
		// System.out.println(lcm);

//       //9. Swap Two Numbers Without Third Variable
//		int a = 10, b = 20;
//		a = a + b;
//		b = a - b;
//		a = a - b;
//		System.out.println("a = " + a + ", b = " + b);

////		10. Find Largest of 3 Numbers
//		int a = 10, b = 20, c = 15;
//		if (a > b && a > c)
//		    System.out.println("a is largest");
//		else if (b > c)
//		    System.out.println("b is largest");
//		else
//		    System.out.println("c is largest");

	}

}
