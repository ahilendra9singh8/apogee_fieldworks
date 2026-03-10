package fieldworks.DSA.arrayQue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MediumQue {
    public static void main(String[] args) {
        // // 1.Two sum problem
        // 1.1 Broute force approach
        // int[] arr = { 2, 11, 15, 7 };
        // int n = 9;
        // for (int i = 0; i < arr.length; i++) {
        // for (int j = i + 1; j <= arr.length; j++) {
        // if ((arr[i] + arr[j]) == n) {
        // System.out.println(i + " : " + j);
        // break;
        // }
        // }
        // }

        // // 1.2 Better approach
        // int[] nums = { 2, 11, 15, 7 };
        // int target = 9;
        // HashMap<Integer, Integer> mpp = new HashMap<>();
        // int[] out = new int[] { -1, -1 };
        // int n = nums.length;
        // for (int i = 0; i < n; i++) {
        // int num = nums[i];
        // int needed = target - num;
        // if (mpp.containsKey(needed)) {
        // out[0] = mpp.get(needed);
        // out[1] = i;
        // break;
        // }
        // mpp.put(num, i);
        // }
        // for (int val : nums) {
        // System.out.println(val);
        // }

        // 2. Sort an array of 0s, 1s,2s
        // // 2.1: Broute Force approach - using sort method
        // int[] intArray = { 2, 0, 2, 1, 1, 0 };
        // Arrays.sort(intArray);
        // for (int val : intArray) {
        // System.out.println(val);
        // }

        // // 2.2: Better Approach
        // int[] intArray = { 2, 0, 2, 1, 1, 0 };
        // int cnt0 = 0, cnt1 = 0, cnt2 = 0;
        // for (int i = 0; i < intArray.length; i++) {
        // if (intArray[i] == 0)
        // cnt0++;
        // if (intArray[i] == 1)
        // cnt1++;
        // if (intArray[i] == 2)
        // cnt2++;
        // }
        // for (int i = 0; i < cnt0; i++) {
        // intArray[i] = 0;
        // }
        // for (int i = cnt0; i < cnt0 + cnt1; i++) {
        // intArray[i] = 1;
        // }
        // for (int i = cnt0 + cnt1; i < cnt0 + cnt1 + cnt2; i++) {
        // intArray[i] = 2;
        // }

        // for (int i = 0; i < intArray.length; i++) {
        // System.out.println(intArray[i]);
        // }

        // 3.Majority Element
        // // 3.1: Broute Force Approach
        // int[] intArray = { 2, 2, 1, 1, 1, 2, 2 };
        // int n = intArray.length;
        // int result = 0;
        // for (int i = 0; i < n; i++) {
        // int cnt = 0;
        // for (int j = 0; j < n; j++) {
        // if (intArray[i] == intArray[j]) {
        // cnt++;
        // }
        // if (cnt > n / 2) {
        // result = intArray[i];
        // }
        // }
        // }
        // System.out.println(result);

        // // 3.2: Better Approach
        // int[] intArray = { 2, 2, 1, 1, 1, 2, 2 };
        // int n = intArray.length;
        // Map<Integer, Integer> map = new HashMap<>();
        // for (int i = 0; i < n; i++) {
        // map.put(intArray[i], map.getOrDefault(intArray[i], 0) + 1);
        // }
        // for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
        // if (entry.getValue() > n / 2) {
        // System.out.println(entry.getKey());
        // }
        // }

        // // 4.Kadane algorithm - maximum subarray sum
        // int[] arr = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
        // int maxsum = 0;
        // int n = arr.length;
        // for (int i = 0; i < n; i++) {
        // int sum = 0;
        // for (int j = i; j < n; j++) {
        // sum += arr[j];
        // maxsum = Math.max(maxsum, sum);
        // }
        // }
        // System.out.println(maxsum);

        // // 5. print subarray of maximum subarray sum
        // int[] arr = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
        // int maxsum = 0;
        // int start = -1;
        // int end = -1;
        // int n = arr.length;
        // for (int i = 0; i < n; i++) {
        // int sum = 0;
        // for (int j = i; j < n; j++) {
        // sum += arr[j];
        // if (sum > maxsum) {
        // maxsum = sum;
        // start = i;
        // end = j;
        // }
        // }
        // }
        // for (int k = start; k <= end; k++) {
        // System.out.println(arr[k]);
        // }

        // // 6. Stock Buy and Sell
        // 6.1 Broute Force
        // int[] arr = { 7, 1, 5, 3, 6, 4 };
        // int profit = 0;
        // int n = arr.length;
        // for (int i = 0; i < n; i++) {
        // for (int j = i + 1; j < n; j++) {
        // if (arr[j] > arr[i]) {
        // int val = arr[j] - arr[i];
        // profit = Math.max(val, profit);
        // }
        // }
        // }
        // System.out.println(profit);

        // // 6.2 Optimal
        // int[] prices = { 7, 1, 5, 3, 6, 4 };
        // int n = prices.length;
        // int minPrice = Integer.MAX_VALUE;
        // int profit = 0;
        // for(int i=0; i<n; i++){
        // minPrice = Math.min(minPrice,prices[i]);
        // profit = Math.max(profit,prices[i]-minPrice);
        // }
        // System.out.println(profit);

        // // 7. Rearrange Array Elements by Sign
        // // 7.1- Broute Force approach
        // int[] arr = { 1, 2, -4, -5 };
        // int n = arr.length;
        // List<Integer> pos = new ArrayList<>();
        // List<Integer> neg = new ArrayList<>();
        // for (int i = 0; i < n; i++) {
        // if (arr[i] > 0) {
        // pos.add(arr[i]);
        // } else {
        // neg.add(arr[i]);
        // }
        // }

        // for (int i = 0; i < n / 2; i++) {
        // arr[i * 2] = pos.get(i);
        // arr[i * 2 + 1] = neg.get(i);
        // }

        // for (int val : arr) {
        // System.out.println(val);
        // }

        // // 7.2-Better Approach
        // int[] arr = { 1, 2, -4, -5 };
        // int n = arr.length;
        // int[] resultArr = new int[n];
        // int posindex = 0;
        // int negindex = 1;

        // for (int i = 0; i < n; i++) {
        // if (arr[i] > 0) {
        // resultArr[posindex] = arr[i];
        // posindex += 2;
        // } else {
        // resultArr[negindex] = arr[i];
        // negindex += 2;
        // }
        // }

        // for (int val : resultArr) {
        // System.out.println(val);
        // }

        // 8.Next Permutation

        // 9. Leaders in an Array
        // // 9.1: Broute Force Approach
        // int[] arr = { 4, 7, 1, 0 };
        // int n = arr.length;
        // for (int i = 0; i < n; i++) {
        // boolean isLeader = true;
        // for (int j = i + 1; j < n; j++) {
        // if (arr[i] < arr[j]) {
        // isLeader = false;
        // break;
        // }
        // }
        // if (isLeader) {
        // System.out.println(arr[i]);
        // }
        // }

        // // 9.2: Optimal Approach
        // int[] arr = { 4, 7, 1, 0 };
        // int n = arr.length;
        // int max = arr[n - 1];
        // List<Integer> list = new ArrayList<>();
        // list.add(max);
        // for (int i = n - 2; i >= 0; i--) {
        // if (arr[i] > max) {
        // list.add(arr[i]);
        // max = arr[i];
        // }
        // }

        // for (int val : list) {
        // System.out.println(val);
        // }

        // // 10.Longest Consecutive Sequence in an Array
        // // 10.1: Broute Force Approach
        // int[] arr = { 100, 200, 1, 3, 2, 4 };
        // int longest = 1;
        // for (int i = 0; i < arr.length; i++) {
        // int ele = arr[i];
        // int cnt = 1;
        // while (ls(arr, ele + 1) == true) {
        // ele += 1;
        // cnt += 1;
        // }
        // longest = Math.max(longest, cnt);
        // }
        // System.out.println(longest);

        // // 10.2 : Better Approach
        // int[] arr = { 2, 2, 2, 3, 3, 4, 100, 100, 1, 1, 1, 101, 101, 102 };
        // Arrays.sort(arr);
        // int lastSmaller = Integer.MIN_VALUE;
        // int longest = 1;
        // int cnt = 0;
        // for (int i = 0; i < arr.length; i++) {
        // if (arr[i] - 1 == lastSmaller) {
        // lastSmaller = arr[i];
        // cnt += 1;
        // } else if (arr[i] != lastSmaller) {
        // cnt = 1;
        // lastSmaller = arr[i];
        // }
        // longest = Math.max(longest, cnt);
        // }

        // System.out.println(longest);

        // 10.3 : Optimal Approach

        // // Matrix
        // int[][] matrix = { { 1, 2, 3 }, { 4, 5, 6 } };
        // int row = matrix.length;
        // int column = matrix[0].length;
        // for(int i=0; i< row; i++){
        // for(int j=0; j<column; j++){
        // System.out.println(matrix[i][j]);
        // }
        // }

        // 11. Set Matrix Zero
        // // 10.1 : Broute Force Approach
        // int[][] matrix = { { 1, 1, 1 }, { 1, 0, 1 }, { 1, 1, 1 } };
        // int rows = matrix.length;
        // int columns = matrix[0].length;
        // for (int i = 0; i < rows; i++) {
        // for (int j = 0; j < columns; j++) {
        // if (matrix[i][j] == 0) {
        // // setrows is -1
        // matrix = markRows(matrix, i);
        // // set columns is -1
        // matrix = markColumns(matrix, j);
        // }
        // }
        // }

        // // Now set 0 where found -1
        // for (int i = 0; i < rows; i++) {
        // for (int j = 0; j < columns; j++) {
        // if (matrix[i][j] == -1) {
        // matrix[i][j] = 0;
        // }
        // }
        // }

        // for (int i = 0; i < rows; i++) {
        // for (int j = 0; j < columns; j++) {
        // System.out.println(matrix[i][j]);
        // }
        // }

        // 12. Rotate Image by 90 degree
        // 12.1: Broute Force approach
        // int[][] matrix = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
        // int n = matrix.length;
        // int[][] ans = new int[n][n];
        // for (int i = 0; i < n; i++) {
        // for (int j = 0; j < n; j++) {
        // ans[j][n - 1 - i] = matrix[i][j];
        // }
        // }

        // for (int i = 0; i < n; i++) {
        // for (int j = 0; j < n; j++) {
        // System.out.println(ans[i][j]);
        // }
        // }

        // // 12.2 Better Approach
        // int[][] matrix = {
        //         { 1, 2, 3 },
        //         { 4, 5, 6 },
        //         { 7, 8, 9 }
        // };

        // int n = matrix.length;
        // // Transpose the matrix.
        // for (int i = 0; i < n; i++) {
        //     for (int j = i + 1; j < n; j++) {
        //         int temp = matrix[i][j];
        //         matrix[i][j] = matrix[j][i];
        //         matrix[j][i] = temp;
        //     }
        // }

        // // Reverse each row of the matrix.
        // for (int i = 0; i < n; i++) {
        //     int start = 0;
        //     int end = n - 1;
        //     while (start < end) {
        //         int temp = matrix[i][start];
        //         matrix[i][start] = matrix[i][end];
        //         matrix[i][end] = temp;
        //         start++;
        //         end--;
        //     }
        // }

        // for (int i = 0; i < n; i++) {
        //     for (int j = 0; j < n; j++) {
        //         System.out.print(matrix[i][j] + " ");
        //     }
        //     System.out.println();
        // }

        // // 13. Print the matrix in spiral manner
        // int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13,
        // 14, 15, 16 } };
        // List<Integer> ans = new ArrayList<>();
        // int top = 0;
        // int bottom = matrix.length - 1;
        // int left = 0;
        // int right = matrix[0].length - 1;

        // while (left <= right && top <= bottom) {

        // for (int i = left; i <= right; i++) {
        // ans.add(matrix[top][i]);
        // }
        // top++;
        // for (int i = top; i <= bottom; i++) {
        // ans.add(matrix[i][right]);
        // }
        // right--;

        // if (top <= bottom) {
        // for (int i = right; i >= left; i--) {
        // ans.add(matrix[bottom][i]);
        // }
        // bottom--;
        // }

        // if (left <= right) {
        // for (int i = bottom; i >= top; i--) {
        // ans.add(matrix[i][left]);
        // }
        // left++;
        // }

        // }

        // for (int val : ans) {
        // System.out.print(val + " ");
        // }

        // // 14. Count subarrays with given sum
        // int[] arr = { 3, 1, 2, 4 }; // {1,-1,0} ,k=0, countSubArray =3
        // int k = 6;
        // int countSubArray = 0;

        // for (int i = 0; i < arr.length; i++) {
        // int sum = 0;
        // for (int j = i; j < arr.length; j++) {
        // sum += arr[j];
        // if (sum == k) {
        // countSubArray++;
        // }
        // }
        // }

        // System.out.print(countSubArray);

    }

    // private static boolean ls(int[] arr, int ele) {
    // for (int i = 0; i < arr.length; i++) {
    // if (arr[i] == ele) {
    // return true;
    // }
    // }
    // return false;
    // }

    // private static int[][] markRows(int[][] matrix, int i) {
    // for (int k = 0; k < matrix[0].length; k++) {
    // if (matrix[i][k] != 0) {
    // matrix[i][k] = -1;
    // }

    // }

    // return matrix;
    // }

    // private static int[][] markColumns(int[][] matrix, int j) {
    // for (int k = 0; k < matrix.length; k++) {
    // if (matrix[k][j] != 0) {
    // matrix[k][j] = -1;
    // }

    // }
    // return matrix;
    // }

}
