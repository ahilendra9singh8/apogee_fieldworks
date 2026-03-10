package fieldworks.DSA.Basic;

public class Pattern {
	public static void main(String[] ags) {
		// // 1.
		// Scanner sc = new Scanner(System.in);
		// int n = sc.nextInt();
		// for (int i = 0; i < n; i++) {
		// for (int j = 0; j < n; j++) {
		// System.out.print("* ");
		// }
		// System.out.println();
		// }

		// // 2.
		// int n = 5;
		// for (int i = 0; i < n; i++) {
		// for (int j = 0; j <= i; j++) {
		// System.out.print("* ");
		// }
		// System.out.println();
		// }

		// // 3.
		// int n = 5;
		// for (int i = 1; i <= n; i++) {
		// for (int j = 1; j <= i; j++) {
		// System.out.print(j+ " ");
		// }
		// System.out.println();
		// }

		// // 4.
		// int n = 5;
		// for (int i = 1; i <= n; i++) {
		// for (int j = 1; j <= i; j++) {
		// System.out.print(i+ " ");
		// }
		// System.out.println();
		// }

		// // 5.
		// int n = 5;
		// for (int i = 0; i < n; i++) {
		// for (int j = 0; j < n - i; j++) {
		// System.out.print("* ");
		// }
		// System.out.println();
		// }

		// // 6.
		// int n = 5;
		// for (int i = 0; i < n; i++) {
		// for (int j = 1; j <= n - i; j++) {
		// System.out.print(j + " ");
		// }
		// System.out.println();
		// }

		// // 7.
		// int n = 5;
		// for (int i = 0; i < n; i++) {
		// for (int j = 1; j <= n - i - 1; j++) {
		// System.out.print(" ");
		// }
		// for (int j = 1; j <= 2 * i + 1; j++) {
		// System.out.print("* ");
		// }
		// for (int j = 1; j <= n - i - 1; j++) {
		// System.out.print(" ");
		// }
		// System.out.println();
		// }

		// // 8.
		// int n = 5;
		// for (int i = 0; i < n; i++) {
		// for (int j = 0; j < i; j++) {
		// System.out.print(" ");
		// }
		// for (int j = 0; j < (2 * n) - (2 * i) - 1; j++) {
		// System.out.print("* ");
		// }
		// for (int j = 0; j < i; j++) {
		// System.out.print(" ");
		// }
		// System.out.println();
		// }

		// // 9.
		// int n = 5;
		// for (int i = 0; i < n-1; i++) {
		// for (int j = 1; j <= n - i - 1; j++) {
		// System.out.print(" ");
		// }
		// for (int j = 1; j <= 2 * i + 1; j++) {
		// System.out.print("* ");
		// }
		// for (int j = 1; j <= n - i - 1; j++) {
		// System.out.print(" ");
		// }
		// System.out.println();
		// }

		// for (int i = 0; i < n; i++) {
		// for (int j = 0; j < i; j++) {
		// System.out.print(" ");
		// }
		// for (int j = 0; j < (2 * n) - (2 * i) - 1; j++) {
		// System.out.print("* ");
		// }
		// for (int j = 0; j < i; j++) {
		// System.out.print(" ");
		// }
		// System.out.println();
		// }

		// // 10.
		// int n = 5;
		// for (int i = 1; i <= 2 * n - 1; i++) {
		// int stars = i;
		// if (i > n)
		// stars = 2 * n - i;
		// for (int j = 1; j <= stars; j++) {
		// System.out.print("* ");
		// }
		// System.out.println();
		// }

		// 11.
		// int n = 5;
		// for (int i = 0; i < n; i++) {
		// int start = (i % 2 == 0) ? 1 : 0;
		// for (int j = 0; j <= i; j++) {
		// System.out.print(start + " ");
		// start = 1 - start;
		// }
		// System.out.println();
		// }

		// // 12.
		// int n = 5;
		// for (int i = 1; i <= n; i++) {
		// for (int j = 1; j <= i; j++) {
		// System.out.print(j);
		// }
		// for (int j = 1; j <= n - i; j++) {
		// System.out.print(" ");
		// }
		// for (int j = i; j >= 1; j--) {
		// System.out.print(j);
		// }
		// System.out.println();
		// }

		// // 13.
		// int n = 5;
		// int val = 1;
		// for (int i = 0; i < n; i++) {
		// for (int j = 0; j <= i; j++) {
		// System.out.print(val++ + " ");
		// }
		// System.out.println();
		// }

		// // 14.
		// int n = 5;
		// for (int i = 0; i < n; i++) {
		// for (char ch = 'A'; ch <= 'A' + i; ch++) {
		// System.out.print(ch + " ");
		// }
		// System.out.println();
		// }

		// 15.
		// int n = 5;
		// for (int i = 1; i <= n; i++) {
		// for (char ch = 'A'; ch <= 'A' + n - i; ch++) {
		// System.out.print(ch + " ");
		// }
		// System.out.println();
		// }

		// // 16.
		// int n = 5;
		// for (int i = 0; i < n; i++) {
		// for (int j = 0; j <= i; j++) {
		// char ch = (char) ('A' + i);
		// System.out.print(ch + " ");
		// }
		// System.out.println();
		// }

		// // 17.
		// int n = 5;
		// for (int i = 0; i < n; i++) {
		// char ch = 'A';
		// for (int j = 0; j < n - i - 1; j++) {
		// System.out.print(" ");
		// }
		// for (int j = 0; j < (2 * i + 1); j++) {
		// System.out.print(ch + " ");
		// if (j >= i) {
		// ch--;
		// } else {
		// ch++;
		// }
		// }
		// for (int j = 0; j < n - i - 1; j++) {
		// System.out.print(" ");
		// }
		// System.out.println();
		// }

		// // 18.
		// int n = 5;
		// for (int i = 0; i < n; i++) {
		// char ch = (char) ('A' + n - i - 1);
		// for (int j = 0; j <= i; j++) {
		// System.out.print(ch + " ");
		// ch++;
		// }
		// System.out.println();
		// }

		// 19.
		// int n = 5;
		// // forward
		// int spaces1 = 0;
		// for (int i = 0; i < n; i++) {
		// for (int j = 0; j < n - i; j++) {
		// System.out.print("*");
		// }
		// for (int j = 0; j < spaces1; j++) {
		// System.out.print(" ");
		// }
		// for (int j = 0; j < n - i; j++) {
		// System.out.print("*");
		// }
		// spaces1 += 2;
		// System.out.println();
		// }

		// // backward
		// int spaces2 = 2 * n - 2;
		// for (int i = 0; i < n; i++) {
		// for (int j = 0; j <= i; j++) {
		// System.out.print("*");
		// }
		// for (int j = 0; j < spaces2; j++) {
		// System.out.print(" ");
		// }
		// for (int j = 0; j <= i; j++) {
		// System.out.print("*");
		// }
		// spaces2 -= 2;
		// System.out.println();
		// }

		// // 20.
		// int n = 5;
		// int spaces1 = 2 * n - 2;
		// for (int i = 0; i < n - 1; i++) {
		// for (int j = 0; j <= i; j++) {
		// System.out.print("*");
		// }

		// for (int j = 0; j < spaces1; j++) {
		// System.out.print(" ");
		// }

		// for (int j = 0; j <= i; j++) {
		// System.out.print("*");
		// }
		// spaces1 -= 2;
		// System.out.println();
		// }

		// int spaces2 = 0;
		// for (int i = 0; i < n; i++) {
		// for (int j = 0; j < n - i; j++) {
		// System.out.print("*");
		// }
		// for (int j = 0; j < spaces2; j++) {
		// System.out.print(" ");
		// }
		// for (int j = 0; j < n - i; j++) {
		// System.out.print("*");
		// }
		// spaces2 += 2;
		// System.out.println();
		// }

		// // 21.
		// int n = 5;
		// for (int i = 1; i <= n; i++) {
		// for (int j = 1; j <= n; j++) {
		// if (i == 1 || i == n || j == 1 || j == n) {
		// System.out.print("*");
		// } else {
		// System.out.print(" ");
		// }
		// }
		// System.out.println();
		// }

		// 22.
		int n = 4;
		for (int i = 0; i < (2 * n - 1); i++) {
			for (int j = 0; j < (2 * n - 1); j++) {
				int top = i;
				int left = j;
				int right = (2 * n - 2) - j;
				int bottom = (2 * n - 2) - i;
				System.out.print(n - Math.min(Math.min(top, bottom), Math.min(right, left)) + " ");
			}
			System.out.println();
		}
	}
}
