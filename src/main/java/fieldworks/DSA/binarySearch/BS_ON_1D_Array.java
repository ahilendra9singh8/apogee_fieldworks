package fieldworks.DSA.binarySearch;

public class BS_ON_1D_Array {
	public static void main(String[] args) {
		// // 1.Binary Search to find X in sorted array
		// // 1.1 => Iterative Implementation
		// int[] nums = { -1, 0, 3, 5, 9, 12 };
		// int target = 9;
		// int low = 0;
		// int high = nums.length - 1;
		// int index = -1;
		// while (low <= high) {
		// int mid = (low + high) / 2;
		// if (target == nums[mid]) {
		// index = mid;
		// break;
		// } else if (target > nums[mid]) {
		// low = mid + 1;
		// } else {
		// high = mid - 1;
		// }
		// }
		// System.out.println(index);

		// // 1.2 => Recursive implementation:
		// int[] nums = { 3, 4, 6, 7, 9, 12, 16, 17 };
		// int target = 6;
		// int low = 0;
		// int high = nums.length - 1;
		// int ind = binarySearch(nums, low, high, target);
		// System.out.println(ind);

		// 2.Implement Lower Bound
		// // 2.1=> Broute Force Approach (Using linear search)
		// int[] nums = { 3, 5, 8, 15, 19 };
		// int n= nums.length;
		// int x = 9;
		// int result = n;
		// for (int i = 0; i < nums.length; i++) {
		// if (nums[i] >= x) {
		// result = i;
		// break;
		// }
		// }
		// System.out.println(result);

		// // 2.2 => Optimal Approach (Using Binary Search):
		// int[] nums = { 3, 5, 8, 15, 19 };
		// int x = 9;

		// int low = 0;
		// int high = nums.length - 1;
		// int lowerBound = nums.length;
		// while (low <= high) {
		// int mid = (low + high) / 2;
		// if (nums[mid] >= x) {
		// lowerBound = mid;
		// high = mid - 1;
		// } else if (nums[mid] < x) {
		// low = mid + 1;
		// }
		// }
		// System.out.println(lowerBound);

		// 3.Implement Upper Bound
		// // 3.1 => Broute Force Approach (Using linear search):
		// int[] nums = { 3, 5, 8, 9, 15, 19 };
		// int x = 9;
		// int n = nums.length;
		// int result = n;
		// for (int i = 0; i < n; i++) {
		// if (nums[i] > x) {
		// result = i;
		// break;
		// }
		// }
		// System.out.println(result);

		// // 3.2 => Optimal Approach (Using Binary Search):
		// int[] nums = { 3, 5, 8, 9, 15, 19 };
		// int x = 9;
		// int n = nums.length;
		// int result = n;
		// int low = 0;
		// int high = n - 1;
		// while (low <= high) {
		// int mid = (low + high) / 2;
		// if (nums[mid] > x) {
		// result = mid;
		// high = mid - 1;
		// } else {
		// low = mid + 1;
		// }
		// }
		// System.out.println(result);

		// // 4.Search Insert Position(same lower bound)
		// int[] nums = {1,2,4,7};
		// int x=6;
		// int low = 0;
		// int high = nums.length - 1;
		// int lowerBound = nums.length;
		// while (low <= high) {
		// int mid = (low + high) / 2;
		// if (nums[mid] >= x) {
		// lowerBound = mid;
		// high = mid - 1;
		// } else if (nums[mid] < x) {
		// low = mid + 1;
		// }
		// }
		// System.out.println(lowerBound);

		// // 5.Floor/Ceil in Sorted Array
		// int[] nums = {3, 4, 4, 7, 8, 10};
		// int x = 5;
		// // int[] nums = { 3, 4, 4, 7, 8, 10 };
		// // int x = 8;
		// int floor = nums[0];
		// int ceil = nums[nums.length - 1];
		// int low = 0;
		// int high = nums.length - 1;
		// while (low <= high) {
		// int mid = (low + high) / 2;
		// if (nums[mid] <= x) {
		// floor = nums[mid];
		// low = mid + 1;
		// }
		// if (nums[mid] >= x) {
		// ceil = nums[mid];
		// high = mid - 1;
		// }
		// }
		// System.out.println(floor);
		// System.out.println(ceil);

		// // 6.Find the first or last occurrence of a given number in a sorted array
		// 6.1 ==> First Approach (agar ap dono ko(first and second) ek sath nikalna
		// chahte h to two function bnakar alag alag logic likh lo dene dono ek sath
		// return kr skte h)
		// int[] nums = { 3, 4, 13, 13, 13, 20, 40 };
		// int target = 13;
		// int index = -1;
		// int low = 0;
		// int high = nums.length - 1;
		// while (low <= high) {
		// int mid = (low + high) / 2;
		// if (nums[mid] == target) {
		// index = mid;
		// // first occurrence
		// high = mid - 1;
		// // last occurrence
		// // low = mid + 1;

		// } else if (nums[mid] < target) {
		// low = mid + 1;
		// } else {
		// high = mid - 1;
		// }
		// }
		// System.out.println(index);

		// // 6.2 ==> second approach (using lowebound or upperbound)
		// int[] nums = { 3, 4, 13, 13, 13, 20, 40 };
		// int target = 13;
		// int first = lb(nums, target);
		// int second = ub(nums, target);
		// if (nums[first] != target || first == nums.length) {
		// first = -1;
		// second = -1;
		// } else {
		// second = second - 1;
		// }
		// System.out.println(first);
		// System.out.println(second);

		// 7.Count occurrences of a number in a sorted array with duplicates
		// answer==> find first occurrences and second occurrences than (second-first+1)
		// uper bala hi question h bs sime count krna tha

		// 8.Search in Rotated Sorted Array I
		// 8.1=> linear serach (Broute Force approach)

		// // 8.2 ==> Binary Search (Optimal Approach)
		// int[] nums = { 7, 8, 9, 1, 2, 3, 4, 5, 6 };
		// int target = 1;
		// int low = 0;
		// int high = nums.length - 1;
		// while (low <= high) {
		// int mid = (low + high) / 2;
		// if (nums[mid] == target) {
		// System.out.println(mid);
		// break;
		// } else if (nums[low] <= nums[mid]) {
		// if (nums[low] <= target && target <= nums[mid]) {
		// high = mid - 1;
		// } else {
		// low = mid + 1;
		// }
		// } else {
		// if (nums[mid] <= target && target <= nums[high]) {
		// low = mid + 1;
		// } else {
		// high = mid - 1;
		// }
		// }
		// }

		// 9.Search in Rotated Sorted Array II
		// 9.1==> Broute Force Approach
		// // 9.2 ==> Binary Search (Optimal Approach)
		// int[] nums = { 7, 8, 1, 2, 3, 3, 3, 4, 5, 6 };
		// int target = 3;
		// int low = 0;
		// int high = nums.length - 1;
		// boolean flag = false;
		// while (low <= high) {
		// int mid = (low + high) / 2;
		// if (nums[mid] == target) {
		// flag = true;
		// break;
		// } else if (nums[low] == nums[mid] && nums[mid] == nums[high]) {
		// low = low + 1;
		// high = high - 1;
		// continue;
		// } else if (nums[low] <= nums[mid]) {
		// if (nums[low] <= target && target <= nums[mid]) {
		// high = mid - 1;
		// } else {
		// low = mid + 1;
		// }
		// } else {
		// if (nums[mid] <= target && target <= nums[high]) {
		// low = mid + 1;
		// } else {
		// high = mid - 1;
		// }
		// }
		// }

		// System.out.println(flag);

		// 10.Find minimum in Rotated Sorted Array

	}

	// private static int lb(int[] nums, int target) {
	// int low = 0;
	// int high = nums.length - 1;
	// int index = nums.length;
	// while (low <= high) {
	// int mid = (low + high) / 2;
	// if (nums[mid] >= target) {
	// index = mid;
	// high = mid - 1;
	// } else {
	// low = mid + 1;
	// }
	// }
	// return index;
	// }

	// private static int ub(int[] nums, int target) {
	// int index = nums.length;
	// int low = 0;
	// int high = nums.length - 1;
	// while (low <= high) {
	// int mid = (low + high) / 2;
	// if (nums[mid] > target) {
	// index = mid;
	// high = mid - 1;
	// } else {
	// low = mid + 1;
	// }
	// }
	// return index;
	// }

	// public static int binarySearch(int[] nums, int low, int high, int target) {
	// if (low > high)
	// return -1; // Base case.

	// // Perform the steps:
	// int mid = (low + high) / 2;
	// if (nums[mid] == target)
	// return mid;
	// else if (target > nums[mid])
	// return binarySearch(nums, mid + 1, high, target);
	// return binarySearch(nums, low, mid - 1, target);
	// }
}
