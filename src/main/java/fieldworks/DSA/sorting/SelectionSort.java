package fieldworks.DSA.sorting;

//select minimum element and swap with ith index;
//we will swap the minimum element with the first element of the selected range  {0,n-1} {1,n-1}....

public class SelectionSort {

 public static void main(String[] args) {
     int[] arr = { 13, 46, 24, 52, 20, 9 };
     int n = arr.length;
     for (int i = 0; i < n - 1; i++) {
         int mini = i;
         for (int j = i + 1; j < n; j++) {
             if (arr[j] < arr[mini]) {
                 int temp = arr[mini];
                 arr[mini] = arr[j];
                 arr[j] = temp;
             }
         }
     }

     for (int val : arr) {
         System.out.println(val);
     }

 }

}

