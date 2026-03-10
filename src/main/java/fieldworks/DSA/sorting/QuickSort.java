package fieldworks.DSA.sorting;

//pick a pivot and place it in its correct place in the sorted array

public class QuickSort {

 public static void main(String[] args) {
     int[] arr = { 13, 46, 24, 52, 20, 9 };
     int n = arr.length;
     qs(arr, 0, n - 1);
     for (int i = 0; i < arr.length; i++) {
         System.out.print(arr[i] + " ");

     }

 }

 private static void qs(int[] arr, int low, int high) {
     if (low < high) {
         int pIndex = partition(arr, low, high);
         qs(arr, low, pIndex - 1);
         qs(arr, pIndex + 1, high);
     }
 }

 private static int partition(int[] arr, int low, int high) {
     int pivot = arr[low];
     int i = low;
     int j = high;

     while (i < j) {
         while (arr[i] <= pivot && i <= high - 1) {
             i++;
         }

         while (arr[j] > pivot && j >= low + 1) {
             j--;
         }

         if (i < j) {
             int temp = arr[j];
             arr[j] = arr[i];
             arr[i] = temp;
         }

     }

     int temp = arr[low];
     arr[low] = arr[j];
     arr[j] = temp;

     return j;
 }
}
