package fieldworks.DSA.sorting;

//take an element and place it in its correct position

public class InsertionSort {
 public static void main(String[] args) {
     int[] arr = { 13, 46, 24, 52, 20, 9 };
     int n = arr.length;

     // insertion_sort_using_recursion(arr, 0, n);

     for (int i = 0; i <= n - 1; i++) {
     int j = i;
     while (j > 0 && arr[j - 1] > arr[j]) {
     int temp = arr[j];
     arr[j] = arr[j - 1];
     arr[j - 1] = temp;
     j--;
     }
     }

     for (int val : arr) {
         System.out.println(val);
     }
 }

 // private static void insertion_sort_using_recursion(int[] arr, int i, int n) {
 //     if(i==n) return;
 //        int j = i;
 //         while (j > 0 && arr[j - 1] > arr[j]) {
 //             int temp = arr[j];
 //             arr[j] = arr[j - 1];
 //             arr[j - 1] = temp;
 //             j--;
 //         }

 //         insertion_sort_using_recursion(arr, i+1, n);
 // }
}
