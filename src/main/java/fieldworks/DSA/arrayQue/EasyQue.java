package fieldworks.DSA.arrayQue;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class EasyQue {
    public static void main(String[] args) {
        // // 1. Longest subarray with given sum k (positives)
        // int[] intArray = { 2, 3, 5 };
        // int k = 5;
        // int len = intArray.length;
        // int result = 0;
        // for (int i = 0; i < len; i++) {
        // int sum = 0;
        // for (int j = i; j < len; j++) {
        // sum += intArray[j];
        // if (sum == k) {
        // result = Math.max(result, j - i + 1);
        // break;
        // }
        // }
        // }
        // System.out.println(result);

        // // 2. Longest subarray with given sum k (positives, negatives)
        // int[] intArray = { -1, 2, 1,3 };
        // int k = 3;
        // int len = intArray.length;
        // int result = 0;
        // for (int i = 0; i < len; i++) {
        // int sum = 0;
        // for (int j = i; j < len; j++) {
        // sum += intArray[j];
        // if (sum == k) {
        // result = Math.max(result, j - i + 1);
        // // break;
        // }
        // }
        // }
        // System.out.println(result);

        // 3.Find the number that appears once , and other numbes is twice
        // // 3.1: Broute Force
        // int[] intArray = { 1,1,2 };
        // int len = intArray.length;
        // for (int i = 0; i < len; i++) {
        // int cnt = 0;
        // for (int j = 0; j < len; j++) {
        // if (intArray[i] == intArray[j]) {
        // cnt++;
        // if (cnt > 1) {
        // break;
        // }
        // }
        // }
        // if (cnt == 1) {
        // System.out.println(intArray[i]);
        // break;
        // }
        // }

        // // 3.2: Better
        // int[] intArray = { 4, 1, 2, 1, 2 };
        // int len = intArray.length;
        // Map<Integer, Integer> freMap = new HashMap<>();
        // for (int i = 0; i < len; i++) {
        // freMap.put(intArray[i], freMap.getOrDefault(intArray[i], 0) + 1);
        // }
        // for (Map.Entry<Integer, Integer> map : freMap.entrySet()) {
        // if (map.getValue() == 1) {
        // System.out.println(map.getKey());
        // break;
        // }
        // }

        // // 4.Maximum Consecutive Ones
        // int[] intArray = { 1, 1, 0, 1, 1, 1, 1 };
        // int len = intArray.length;
        // int result = 0;
        // int cnt = 0;
        // for (int i = 0; i < len; i++) {
        // if (intArray[i] == 1) {
        // cnt++;
        // } else {
        // cnt = 0;
        // }

        // result = Math.max(result, cnt);
        // }

        // System.out.println(result);

        // 5. Find The Missing Number
        // // 5.1: Broute Force Approach
        // int[] intArray = { 1, 2, 3, 5 };
        // int n = 5;
        // int len = intArray.length;
        // for (int i = 1; i <= n; i++) {
        // int cnt = 0;
        // for (int j = 0; j < len; j++) {
        // if (i == intArray[j]) {
        // cnt++;
        // if (cnt > 0) {
        // break;
        // }
        // }
        // }
        // if (cnt == 0) {
        // System.out.println(i);
        // break;
        // }
        // }

        // 5.2: Better Approch
        // int[] intArray = { 1, 2, 4, 5 };
        // int n = 5;
        // int len = intArray.length;
        // int tottalsum = (n * (n + 1)) / 2;
        // int sum = 0;
        // for (int i = 0; i < len; i++) {
        // sum += intArray[i];
        // }
        // System.out.println(tottalsum - sum);

        // // 6.Union of two sorted Array - you can use map aslo
        // int[] arr1 = { 1, 2, 3, 4, 5 };
        // int[] arr2 = { 2, 3, 4, 4, 5 };
        // HashSet<Integer> set = new HashSet<>();
        // for (int i = 0; i < arr1.length; i++) {
        // set.add(arr1[i]);
        // }
        // for (int i = 0; i < arr2.length; i++) {
        // set.add(arr2[i]);
        // }
        // for (int val : set) {
        // System.out.println(val);
        // }

        // // 7.Linear Search
        // int[] arr = { 1, 2, 3, 4, 5 };
        // int n = 3;
        // for (int i = 0; i < arr.length; i++) {
        // if (arr[i] == n) {
        // System.out.println(i);
        // }
        // }

        // // 8. Move Zeroes to end
        // 8.1: Better approach
        // int[] arr = { 1, 0, 3, 4, 0, 3, 5, 0, 6 };
        // int n = arr.length;
        // int j = -1;
        // for (int i = 0; i < n; i++) {
        // if (arr[i] == 0) {
        // j = i;
        // break;
        // }
        // }
        // for (int i = j + 1; i < n; i++) {
        // if (arr[i] != 0) {
        // int temp = arr[j];
        // arr[j] = arr[i];
        // arr[i] = temp;
        // j++;
        // }
        // }
        // for (int val : arr) {
        // System.out.println(val);
        // }

        // // 9.Remove duplicates from sorted array
        // int[] arr = { 1, 1, 1, 2, 2, 3, 3, 3, 3, 4, 4 };
        // int n = arr.length;
        // int j = 0;
        // for (int i = j + 1; i < n; i++) {
        // if (arr[j] != arr[i]) {
        // j++;
        // arr[j] = arr[i];
        // }
        // }

        // for (int i = 0; i <= j; i++) {
        // System.out.println(arr[i]);
        // }

        // // 10.check if an array is sorted
        // int[] arr = { 1, 2, 3, 4, 5 };
        // boolean flag = true;
        // for (int i = 0; i < arr.length - 1; i++) {
        // if (arr[i] > arr[i + 1]) {
        // flag = false;
        // }
        // }
        // System.out.println(flag);

        // // 11. largest element
        // int[] arr = { 1, 2, 6, 4, 5 };
        // int largest = Integer.MIN_VALUE;
        // for (int i = 0; i < arr.length; i++) {
        // if (arr[i] > largest) {
        // largest = arr[i];
        // }
        // }
        // System.out.println(largest);

        // // 12. second largest element
        // int[] arr = { 1, 2, 6, 4, 5 };
        // int largest = Integer.MIN_VALUE;
        // int secondlargest = Integer.MIN_VALUE;
        // for (int i = 0; i < arr.length; i++) {
        // if (arr[i] > largest) {
        // secondlargest = largest;
        // largest = arr[i];
        // } else if (arr[i] > secondlargest && arr[i] != largest) {
        // secondlargest = arr[i];
        // }
        // }
        // System.out.println(largest);
        // System.out.println(secondlargest);

        // // 13.Left rotate array by d place
        // 13.1
        // int[] arr = { 1, 2, 3, 4, 5 };
        // int d = 2;
        // for (int i = 0; i < d; i++) {
        // int first = arr[0];
        // for (int j = 0; j < arr.length - 1; j++) {
        // arr[j] = arr[j + 1];
        // }
        // arr[arr.length - 1] = first;
        // }
        // for (int val : arr) {
        // System.out.println(val);
        // }

        // 13.2 ==> Optimized
        // int[] nums = { 1, 2, 3, 4, 5, 6, 7 };
        // int k = 3;
        // int n = nums.length;
        // k %= n;

        // reverse(nums, 0, n - 1); // Step 1: Reverse whole array
        // reverse(nums, n - k, n - 1); // Step 2: Reverse last k
        // reverse(nums, 0, n - k - 1); // Step 3: Reverse first n-k

        // // 14.right rotate array by d place
        // 14.1
        // int[] arr = { 1, 2, 3, 4, 5 };
        // int d = 2;
        // for (int i = 0; i < d; i++) {
        // int last = arr[arr.length - 1];
        // for (int j = arr.length - 1; j > 0; j--) {
        // arr[j] = arr[j - 1];
        // }
        // arr[0] = last;
        // }
        // for (int val : arr) {
        // System.out.println(val);
        // }

        // 14.2 ==> Optimized
        // int[] nums = { 1, 2, 3, 4, 5, 6, 7 };
        // int k = 3;
        // int n = nums.length;
        // k = k % n; // Handle cases where k >= n

        // reverse(nums, 0, n - 1); // Step 1: Reverse the whole array
        // reverse(nums, 0, k - 1); // Step 2: Reverse first k elements
        // reverse(nums, k, n - 1); // Step 3: Reverse the rest

        // 15. Check if Array Is Sorted and Rotated
        // 15.1 Broute force
        // boolean flag = false;
        // int n = nums.length;

        // if(n == 1) return true;

        // // rotate n times
        // for(int i=0; i<n; i++){

        // //check sorted
        // for(int j=0; j<n-1; j++){
        // if(nums[j] > nums[j+1]){
        // break;
        // }
        // if(j == n-2){
        // flag = true;
        // break;
        // }
        // }

        // //rotate array
        // int first = nums[0];
        // for(int j=0; j<n-1; j++){
        // nums[j] = nums[j+1];
        // }
        // nums[n-1] = first;
        // }
        // return flag;

        // 15.2 Optimal
        // int count = 0;
        // int n = nums.length;

        // for (int i = 0; i < n; i++) {
        // if (nums[i] > nums[(i + 1) % n]) {
        // count++;
        // }
        // }
        // return count <= 1;
    }

    // private static void reverse(int[] nums, int start, int end) {
    // while (start < end) {
    // int temp = nums[start];
    // nums[start] = nums[end];
    // nums[end] = temp;
    // start++;
    // end--;
    // }
    // }
}

