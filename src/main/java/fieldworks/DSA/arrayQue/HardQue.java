package fieldworks.DSA.arrayQue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HardQue {
    public static void main(String[] args) {

        // 1.Pascal's Triangle
        // // 1.1 -> Variation 1: Given row number r and column number c. Print the
        // element at position (r, c) in Pascal’s triangle.
        // // give r(rows) and c(columns) than i want output direct
        // int r = 5;
        // int c = 3;
        // int result = fun_nCr(r - 1, c - 1);
        // System.out.println(result);

        // 1.2 : Variation 2: Given the row number n. Print the n-th row of Pascal’s
        // Broute Force
        // int r = 5;
        // for (int i = 1; i <= r; i++) {
        // int result = fun_nCr(r - 1, i - 1);
        // System.out.print(result + " ");
        // }

        // // Better Approach
        // int r = 5;
        // int ans = 1;
        // System.out.print(ans + " ");
        // for (int i = 1; i < r; i++) {
        // ans = ans * (r - i);
        // ans = ans / (i);
        // System.out.print(ans + " ");
        // }

        // // 1.3: print pascals Triangle
        // int n = 5;
        // for (int i = 1; i <= n; i++) {
        // // here i is a row
        // int ans = 1;
        // System.out.print(ans + " ");
        // for (int j = 1; j < i; j++) {
        // ans = ans * (i - j);
        // ans = ans / j;
        // System.out.print(ans + " ");
        // }
        // System.out.println();
        // }

        // 2.Majority Element (n/3 times)
        // int[] nums = {3,2,3};
        // // 2.1 ==>Broute Force Approach
        // int n = nums.length;
        // List<Integer> result = new ArrayList<>();
        // for(int i=0; i<n; i++){
        // int cnt = 0;
        // for(int j=0; j<n; j++){
        // if(nums[i] == nums[j]){
        // cnt++;
        // }
        // }
        // if(cnt > n/3){
        // if(!result.contains(nums[i])){
        // result.add(nums[i]);
        // }
        // }
        // }
        // here print result

        // // 2.2 => Better Approach
        // int n = nums.length;
        // List<Integer> result = new ArrayList<>();
        // HashMap<Integer,Integer> freqMap = new HashMap<>();
        // for(int i=0; i<n; i++) {
        // freqMap.put(nums[i], freqMap.getOrDefault(nums[i],0)+ 1);
        // }

        // for(Map.Entry<Integer, Integer> entry : freqMap.entrySet()){
        // if(entry.getValue() > n/3){
        // if(!result.contains(entry.getKey())){
        // result.add(entry.getKey());
        // }
        // }
        // }

        // here print result;

        // 3 ==> 3Sum
        // int[] nums = { -1, 0, 1, 2, -1, -4 };
        // 3.1 ==> Broute Force Approach
        // int n = nums.length;
        // Set<List<Integer>> set = new HashSet<>();
        // for(int i=0; i<n; i++){
        // for(int j=i+1; j<n; j++){
        // for(int k=j+1; k<n; k++){
        // if(nums[i] + nums[j] + nums[k] == 0){
        // List<Integer> list = Arrays.asList(nums[i], nums[j], nums[k]);
        // list.sort(null);
        // set.add(list);
        // }
        // }
        // }
        // }
        // List<List<Integer>> result = new ArrayList<>(set);
        // return result;

        // 3.2 ==> Better Approach
        // int n = nums.length;
        // Set<List<Integer>> resultSet = new HashSet<>();
        // for(int i=0; i<n; i++){
        // HashSet<Integer> set = new HashSet<>();
        // for(int j=i+1; j<n; j++){
        // int k = -(nums[i] + nums[j]);
        // if(set.contains(k)){
        // List<Integer> list = Arrays.asList(nums[i], nums[j], k);
        // list.sort(null);
        // resultSet.add(list);
        // }
        // set.add(nums[j]);
        // }
        // }
        // List<List<Integer>> result = new ArrayList<>(resultSet);
        // return result;

        // // 3.3 ==> Optimal Approach
        // int n = nums.length;
        // List<List<Integer>> result = new ArrayList<>();
        // Arrays.sort(nums);
        // for(int i=0; i<n; i++){
        // if(i!=0 && nums[i] == nums[i-1]) continue;
        // int j = i+1;
        // int k = n-1;
        // while(j<k){
        // int sum = nums[i] + nums[j] + nums[k];
        // if(sum>0){
        // k--;
        // while(j<k && nums[k] == nums[k+1]) k--;
        // }else if(sum<0){
        // j++;
        // while(j<k && nums[j] == nums[j-1]) j++;
        // }else{
        // List<Integer> list = Arrays.asList(nums[i], nums[j], nums[k]);
        // result.add(list);
        // j++;
        // k--;

        // while(j<k && nums[j] == nums[j-1]) j++;
        // while(j<k && nums[k] == nums[k+1]) k--;
        // }
        // }
        // }
        // return result;

        // // 4 ==> 4-Sum Problem
        // int[] nums = { 1, 0, -1, 0, -2, 2 };
        // int target = 0;
        // // 4.1 => Broute Force Approach
        // int n = nums.length;
        // Set<List<Integer>> set = new HashSet<>();
        // for (int i = 0; i < n; i++) {
        // for (int j = i + 1; j < n; j++) {
        // for (int k = j + 1; k < n; k++) {
        // for (int l = k + 1; l < n; l++) {
        // long sum = nums[i] + nums[j];
        // sum+=nums[k];
        // sum+=nums[l];
        // if (sum == target) {
        // List<Integer> temp = Arrays.asList(nums[i], nums[j], nums[k], nums[l]);
        // Collections.sort(temp);
        // set.add(temp);
        // }
        // }
        // }
        // }
        // }
        // List<List<Integer>> ans = new ArrayList<>(set);
        // return ans;

        // 4.2 => Better Approach
        // int n = nums.length;
        // Set<List<Integer>> st = new HashSet<>();
        // for(int i=0; i<n; i++){
        // for(int j=i+1; j<n; j++){
        // Set<Long> hashset = new HashSet<>();
        // for(int k=j+1; k<n; k++){
        // long sum = nums[i] + nums[j];
        // sum += nums[k];
        // long fourth = target - sum;
        // if (hashset.contains(fourth)) {
        // List<Integer> temp = new ArrayList<>();
        // temp.add(nums[i]);
        // temp.add(nums[j]);
        // temp.add(nums[k]);
        // temp.add((int) fourth);
        // temp.sort(Integer::compareTo);
        // st.add(temp);
        // }
        // hashset.add((long) nums[k]);
        // }
        // }
        // }
        // List<List<Integer>> ans = new ArrayList<>(st);
        // return ans;

        // // 4.3 ==> Better Approach
        // Arrays.sort(nums);
        // int n = nums.length;
        // Set<List<Integer>> st = new HashSet<>();
        // for(int i=0; i<n; i++){
        // if (i > 0 && nums[i] == nums[i - 1]) continue;
        // for(int j=i+1; j<n; j++){
        // if (j > i + 1 && nums[j] == nums[j - 1]) continue;
        // int k=j+1;
        // int l=n-1;
        // while(k<l){
        // long sum = nums[i]+nums[j];
        // sum+=nums[k];
        // sum+=nums[l];
        // if(sum>target){
        // l--;
        // }else if(sum<target){
        // k++;
        // }else{
        // List<Integer> list = Arrays.asList(nums[i],nums[j],nums[k],nums[l]);
        // st.add(list);
        // k++;
        // l--;

        // while(k<l && nums[k]==nums[k-1]) k++;
        // while(k<l && nums[l]==nums[l+1]) l--;
        // }
        // }
        // }
        // }
        // List<List<Integer>> result = new ArrayList<>(st);
        // return result;

        // // 5.Largest Subarray with 0 Sum
        // // 5.1 ==> Broute Force Approach
        // int[] arr = { 6, -2, 2, -8, 1, 7, 4, -10 };
        // int n = arr.length;
        // int longest = 0;
        // for (int i = 0; i < n; i++) {
        // int sum = 0;
        // for (int j = i; j < n; j++) {
        // sum += arr[j];
        // if (sum == 0) {
        // longest = Math.max(longest, j - i + 1);
        // }
        // }
        // }
        // System.out.println(longest);

        // // 5.2 ==> Optimal Approach ==> By prefix sum technique
        // int[] arr = { 6, -2, 2, -8, 1, 7, 4, -10 };
        // int n = arr.length;
        // HashMap<Integer, Integer> map = new HashMap<>();
        // int longest = 0;
        // int sum = 0;
        // for (int i = 0; i < n; i++) {
        // sum += arr[i];
        // if (sum == 0) {
        // longest = i + 1;
        // } else if (map.get(sum) != null) {
        // longest = Math.max(longest, i - map.get(sum));
        // } else {
        // map.put(sum, i);
        // }
        // }
        // System.out.println(longest);

        // 6.Count the number of subarrays with given sum equal K
        // // 6.1 ==> Better Approach
        // int[] arr = { 4, 2, 2, 6, 4 };
        // int n = arr.length;
        // int k = 6;
        // int cnt = 0;
        // for (int i = 0; i < n; i++) {
        // int sum = 0;
        // for (int j = i; j < n; j++) {
        // sum += arr[j];
        // if (sum == k) {
        // cnt++;
        // }
        // }
        // }
        // System.out.println(cnt);

        // // 6.2 ==> Optimal Approach
        // int[] arr = { 4, 2, 2, 6, 4 };
        // int k = 6;
        // int cnt = 0;
        // int sum = 0;

        // HashMap<Integer, Integer> map = new HashMap<>();
        // map.put(0, 1); // sum=0 ka ek occurrence

        // for (int i = 0; i < arr.length; i++) {
        // sum += arr[i];
        // int x = sum-k;
        // if (map.containsKey(x)) {
        // cnt += map.get(x);
        // }

        // map.put(sum, map.getOrDefault(sum, 0) + 1);
        // }

        // System.out.println(cnt);

        // 7. Count the number of subarrays with given xor K
        // // 7.1 ==> Better Force Approach
        // int[] arr = { 4, 2, 2, 6, 4 };
        // int k = 6;
        // int n = arr.length;
        // int cnt = 0;
        // for (int i = 0; i < n; i++) {
        // int xor = 0;
        // for (int j = i; j < n; j++) {
        // xor = xor ^ arr[j];
        // if (xor == k) {
        // cnt++;
        // }
        // }
        // }
        // System.out.println(cnt);

        // // 7.2 ==> Optimal Approach
        // int[] arr = { 4, 2, 2, 6, 4 };
        // int k = 6;
        // int n = arr.length;
        // int cnt = 0;
        // int xor = 0;
        // HashMap<Integer, Integer> map = new HashMap<>();
        // map.put(xor, 1);
        // for (int i = 0; i < n; i++) {
        // xor = xor ^ arr[i];
        // int x = xor ^ k;
        // if (map.containsKey(x)) {
        // cnt += map.get(x);
        // }
        // map.put(xor, map.getOrDefault(xor, 0) + 1);
        // }
        // System.out.println(cnt);

        // 8.Merge Overlapping Subintervals
        // // 8.1=> Broute Force Approach
        // int[][] intervals = { { 1, 3 }, { 2, 6 }, { 8, 10 }, { 15, 18 } };
        // int n = intervals.length;
        // // Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        // Arrays.sort(intervals, new Comparator<int[]>() {
        // public int compare(int[] a, int[] b) {
        // return a[0] - b[0];
        // }
        // });

        // List<List<Integer>> list = new ArrayList<>();
        // for (int i = 0; i < n; i++) {
        // // select an interval
        // int start = intervals[i][0];
        // int end = intervals[i][1];
        // // skip merged intervals
        // if (!list.isEmpty() && end <= list.get(list.size() - 1).get(1)) {
        // continue;
        // }
        // // check the rest of the intervals
        // for (int j = i + 1; j < n; j++) {
        // if (intervals[j][0] <= end) {
        // end = Math.max(end, intervals[j][1]);
        // } else {
        // break;
        // }
        // }
        // list.add(Arrays.asList(start, end));
        // }
        // // Convert List<List<Integer>> to int[][]
        // int[][] result = new int[list.size()][2];
        // for (int i = 0; i < list.size(); i++) {
        // result[i][0] = list.get(i).get(0);
        // result[i][1] = list.get(i).get(1);
        // }

        // // return result;

        // // 8.2 ==> Optimal Approach: ==> Isko arraylist se bhi kr skte h
        // int[][] intervals = { { 1, 3 }, { 2, 6 }, { 8, 10 }, { 15, 18 } };
        // int n = intervals.length;
        // // if (intervals.length <= 1) return intervals;

        // // Step 1: Sort by start times
        // Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        // int[][] result = new int[n][2];
        // int index = 0;

        // result[0][0] = intervals[0][0];
        // result[0][1] = intervals[0][1];

        // for (int i = 1; i < n; i++) {
        // int[] current = intervals[i];
        // int[] last = result[index];

        // if (last[1] >= current[0]) {
        // last[1] = Math.max(last[1], current[1]);
        // } else {
        // index++;
        // result[index][0] = intervals[i][0];
        // result[index][1] = intervals[i][1];
        // }
        // }

        // for (int[] interval : result) {
        // System.out.println(Arrays.toString(interval));
        // }

        // 9.Merge two sorted arrays without extra space
        // // 9.1 ==> Broute Force Approach (Isme extra space liya h)
        // int[] arr1 = { 1, 4, 8, 10 };
        // int[] arr2 = { 2, 3, 9 };
        // int n = 4;
        // int m = 3;

        // int[] arr3 = new int[n + m];
        // int left = 0;
        // int right = 0;
        // int index = 0;

        // while (left < n && right < m) {
        // if (arr1[left] <= arr2[right]) {
        // arr3[index++] = arr1[left++];
        // } else {
        // arr3[index++] = arr2[right++];
        // }
        // }
        // while (left < n) {
        // arr3[index++] = arr1[left++];
        // }
        // while (right < m) {
        // arr3[index++] = arr2[right++];
        // }

        // for (int i = 0; i < n + m; i++) {
        // if (i < n) {
        // arr1[i] = arr3[i];
        // } else {
        // arr2[i - n] = arr3[i];
        // }
        // }

        // for (int i = 0; i < n; i++) {
        // System.out.print(arr1[i] + " ");
        // }

        // System.out.println();

        // for (int i = 0; i < m; i++) {
        // System.out.print(arr2[i] + " ");
        // }

        // // 9.2 ==> Optimal Approach 1
        // int[] nums1 = { 1, 4, 8, 10 };
        // int[] nums2 = { 2, 3, 9 };
        // int m = 4;
        // int n = 3;
        // int left = m - 1;
        // int right = 0;
        // while (left > 0 && right < n) {
        // if (nums1[left] > nums2[right]) {
        // int temp = nums1[left];
        // nums1[left] = nums2[right];
        // nums2[right] = temp;
        // left--;
        // right++;
        // } else {
        // break;
        // }
        // }

        // Arrays.sort(nums1);
        // Arrays.sort(nums2);

        // for (int i = 0; i < m; i++) {
        // System.out.print(nums1[i] + " ");
        // }

        // System.out.println();

        // for (int i = 0; i < n; i++) {
        // System.out.print(nums2[i] + " ");
        // }

        // // 9.3 => Optimal Approach 2 (Using gap method):
        // int[] nums1 = { 1, 4, 8, 10 };
        // int[] nums2 = { 2, 3, 9 };
        // int m = 4;
        // int n = 3;
        // int len = m + n;

        // int gap = (len / 2) + (len % 2);
        // while (gap > 0) {
        // int left = 0;
        // int right = left + gap;
        // while (right < len) {
        // if (left < m && right >= m) {
        // swap(nums1, nums2, left, right - m);
        // } else if (left >= m) {
        // swap(nums2, nums2, left - m, right - m);
        // } else {
        // swap(nums1, nums1, left, right);
        // }
        // left++;
        // right++;
        // }
        // if (gap == 1) {
        // break;
        // }
        // gap = (gap / 2) + (gap % 2);

        // }

        // for (int i = 0; i < m; i++) {
        // System.out.print(nums1[i] + " ");
        // }

        // System.out.println();

        // for (int i = 0; i < n; i++) {
        // System.out.print(nums2[i] + " ");
        // }

        // // 10. Find the repeating and missing number
        // // 10.1 Broute Force Approach
        // int[] arr = { 3, 1, 2, 5, 3 };
        // int n = arr.length;
        // List<Integer> result = new ArrayList<>();
        // for (int i = 1; i <= n; i++) {
        // int cnt = 0;
        // for (int j = 0; j < n; j++) {
        // if (i == arr[j]) {
        // cnt++;
        // }
        // }
        // if (cnt == 0 || cnt > 1) {
        // result.add(i);
        // }
        // }

        // for (int val : result) {
        // System.out.println(val);
        // }

        // // 10.2 ==> Better Approach (Using Hashing): hash array
        // int[] arr = { 3, 1, 2, 5, 4, 6, 7, 5 };
        // int n = arr.length;
        // List<Integer> result = new ArrayList<>();
        // int[] hash = new int[n + 1];
        // for (int i = 0; i < n; i++) {
        // hash[arr[i]] = hash[arr[i]] + 1;
        // }

        // for (int i = 1; i <= n; i++) {
        // if (hash[i] == 0 || hash[i] == 2) {
        // result.add(i);
        // }
        // }

        // for (int val : result) {
        // System.out.println(val);
        // }

        // // 10.3 ==> Optimal Approach 1 (Using Maths):
        // int[] arr = { 3, 1, 2, 5, 4, 6, 7, 5 };
        // int n = arr.length;
        // long sN = (n * (n + 1)) / 2;
        // long s2N = (n * (n + 1) * (2 * n + 1)) / 6;
        // long s = 0;
        // long s2 = 0;
        // for (int i = 0; i < n; i++) {
        // s += (long) arr[i];
        // s2 += (long) arr[i] * (long) arr[i];
        // }

        // // x-y = s-sN
        // long val1 = s - sN;
        // // x^2-y^2=s2-s2N
        // long val2 = s2 - s2N;
        // // x+y=(x^2-y^2)/x-y
        // val2 = val2 / val1;

        // long x = (val1 + val2) / 2;
        // long y = x - val1;

        // int[] result = { (int) x, (int) y };
        // for (int val : result) {
        // System.out.println(val);
        // }

        // 10.4 ==> Optimal Approach 2 (Using XOR):

        // 11. ==> Count inversions in an array
        // // 11.1 ==> Broute Force Approach
        // int[] arr = { 5, 3, 2, 1, 4 };
        // int n = arr.length;
        // int cnt = 0;
        // for (int i = 0; i < n; i++) {
        // for (int j = i + 1; j < n; j++) {
        // if (arr[i] > arr[j]) {
        // cnt++;
        // }
        // }
        // }
        // System.out.println(cnt);

        // // 11.2 ==> Optimal Approach
        // int[] arr = { 5, 3, 2, 1, 4 };
        // int n = arr.length;
        // int cnt = numberOfInversions(arr, n);
        // System.out.println(cnt);

        // 12. ==> Count Reverse Pairs
        // // 12.1 ==> Broute Force Approach
        // int[] arr = { 3, 2, 1, 4 };
        // int n = arr.length;
        // int cnt = 0;
        // for (int i = 0; i < n; i++) {
        // for (int j = i + 1; j < n; j++) {
        // if (arr[i] > (2 * arr[j])) {
        // cnt++;
        // }
        // }
        // }
        // System.out.println(cnt);

        // // 12.2 Optimal Approach
        // int[] arr = { 1, 3, 2, 3, 1 };
        // int n = arr.length;
        // int cnt = reverseOfPairs(arr, n);
        // System.out.println(cnt);

        // 13. Maximum Product Subarray
        // // 13.1 ==> Better Approach
        // int[] nums = { 1, 2, -3, 0, -4, -5 };
        // int n = nums.length;
        // // if(n==1) return nums[0];
        // int max = 0;
        // for (int i = 0; i < n; i++) {
        // int product = 1;
        // for (int j = i; j < n; j++) {
        // product *= nums[j];
        // max = Math.max(product, max);
        // }
        // }
        // System.out.println(max);

        // // 13.2 ==> Optimal Approch (Observations)
        // int[] nums = { 1, 2, -3, 0, -4, -5 };
        // int n = nums.length;
        // int prefix = 1;
        // int suffix = 1;
        // int maxi = Integer.MIN_VALUE;
        // for (int i = 0; i < n; i++) {
        // if (prefix == 0)
        // prefix = 1;
        // if (suffix == 0)
        // suffix = 1;
        // prefix *= nums[i];
        // suffix *= nums[n - i - 1];
        // maxi = Math.max(maxi, Math.max(prefix, suffix));
        // }
        // System.out.println(maxi);

        // 13.3 ==> Optimal Approch (kadane algorithm)
    }

    // private static int reverseOfPairs(int[] arr, int n) {
    // int cnt = mergeSortForReverseOfPairs(arr, 0, n - 1);
    // return cnt;
    // }

    // private static int mergeSortForReverseOfPairs(int[] arr, int low, int high) {
    // int cnt = 0;
    // if (low >= high)
    // return cnt;
    // int mid = (low + high) / 2;
    // cnt += mergeSortForReverseOfPairs(arr, low, mid); // left half
    // cnt += mergeSortForReverseOfPairs(arr, mid + 1, high); // right half
    // cnt += countPairs(arr, low, mid, high); // Modification
    // mergeForReversePairs(arr, low, mid, high); // merging sorted halves
    // return cnt;
    // }

    // private static int countPairs(int[] arr, int low, int mid, int high) {
    // int right = mid + 1;
    // int cnt = 0;
    // for (int i = low; i <= mid; i++) {
    // while (right <= high && arr[i] > 2 * arr[right])
    // right++;
    // cnt += (right - (mid + 1));
    // }
    // return cnt;
    // }

    // private static void mergeForReversePairs(int[] arr, int low, int mid, int
    // high) {
    // ArrayList<Integer> temp = new ArrayList<>(); // temporary array
    // int left = low; // starting index of left half of arr
    // int right = mid + 1; // starting index of right half of arr

    // // storing elements in the temporary array in a sorted manner//

    // while (left <= mid && right <= high) {
    // if (arr[left] <= arr[right]) {
    // temp.add(arr[left]);
    // left++;
    // } else {
    // temp.add(arr[right]);
    // right++;
    // }
    // }

    // // if elements on the left half are still left //

    // while (left <= mid) {
    // temp.add(arr[left]);
    // left++;
    // }

    // // if elements on the right half are still left //
    // while (right <= high) {
    // temp.add(arr[right]);
    // right++;
    // }

    // // transfering all elements from temporary to arr //
    // for (int i = low; i <= high; i++) {
    // arr[i] = temp.get(i - low);
    // }
    // }

    // private static int numberOfInversions(int[] arr, int n) {
    // int cnt = mergeSort(arr, 0, n - 1);
    // return cnt;
    // }

    // private static int mergeSort(int[] arr, int low, int high) {
    // int cnt = 0;
    // if (low >= high)
    // return cnt;
    // int mid = (low + high) / 2;
    // cnt += mergeSort(arr, low, mid);
    // cnt += mergeSort(arr, mid + 1, high);
    // cnt += merge(arr, low, mid, high);
    // return cnt;
    // }

    // private static int merge(int[] arr, int low, int mid, int high) {
    // int cnt = 0;
    // int left = low;
    // int right = mid + 1;
    // ArrayList<Integer> list = new ArrayList<>();

    // while (left <= mid && right <= high) {
    // if (arr[left] < arr[right]) {
    // list.add(arr[left]);
    // left++;
    // } else {
    // list.add(arr[right]);
    // cnt += mid - left + 1;
    // right++;
    // }
    // }

    // while (left <= mid) {
    // list.add(arr[left]);
    // left++;
    // }
    // while (right <= high) {
    // list.add(arr[right]);
    // right++;
    // }

    // // transfering all elements from temporary to arr //
    // for (int i = low; i <= high; i++) {
    // arr[i] = list.get(i - low);
    // }

    // return cnt;
    // }

    // private static void swap(int[] nums1, int[] nums2, int left, int right) {
    // if (nums1[left] > nums2[right]) {
    // int temp = nums1[left];
    // nums1[left] = nums2[right];
    // nums2[right] = temp;
    // }
    // }

    // private static int fun_nCr(int n, int r) { // nCr = n!/r!*(n-r)!
    // int result = 1;
    // for (int i = 0; i < r; i++) {
    // result = result * (n - i);
    // result = result / (i + 1);
    // }
    // return result;
    // }
}

