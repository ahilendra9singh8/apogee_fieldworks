package fieldworks.DSA.sorting;

//push the maximum to the last by adjacent swap

public class BubbleSort {
 public static void main(String[] args) {
     int[] arr = { 13, 46, 24, 52, 20, 9 };
     int n = arr.length;

     // bubble_Sort_Using_Recursion(arr, n - 1);

     for (int i = n - 1; i >= 0; i--) {
         int didSwap = 0;
         for (int j = 0; j <= i - 1; j++) {
             if (arr[j] > arr[j + 1]) {
                 int temp = arr[j];
                 arr[j] = arr[j + 1];
                 arr[j + 1] = temp;
                 didSwap = 1;
             }
         }

         if (didSwap == 0) {
             break;
         }
     }

     for (int val : arr) {
         System.out.println(val);
     }
 }

 // private static void bubble_Sort_Using_Recursion(int[] arr, int i) {
 // if (i == 0)
 // return;

 // int didSwap = 0;
 // for (int j = 0; j <= i - 1; j++) {
 // if (arr[j] > arr[j + 1]) {
 // int temp = arr[j];
 // arr[j] = arr[j + 1];
 // arr[j + 1] = temp;
 // didSwap = 1;
 // }
 // }

 // if (didSwap == 0)
 // return;

 // bubble_Sort_Using_Recursion(arr, i - 1);
 // }
}
