package fieldworks.DSA.sorting;

import java.util.LinkedList;
import java.util.List;

// divide and conquers(merge) algorithm
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = { 13, 46, 24, 52, 20, 9 };
        devide(arr, 0, arr.length - 1);
        for(int i=0; i< arr.length; i++){
            System.out.print(arr[i]+ " ");

        }
    }

    private static void devide(int[] arr, int low, int high) {
        if (low >= high)
            return;
        int mid = (low + high) / 2;
        devide(arr, low, mid);
        devide(arr, mid + 1, high);
        merge(arr, low, mid, high);
    }

    private static void merge(int[] arr, int low, int mid, int high) {
        List<Integer> list = new LinkedList<>();
        int left = low;
        int right = mid + 1;
        while (left <= mid && right <= high) {
            if (arr[left] <= arr[right]) {
                list.add(arr[left]);
                left++;
            } else {
                list.add(arr[right]);
                right++;
            }
        }

        while (left <= mid) {
            list.add(arr[left]);
            left++;
        }
        while (right <= high) {
            list.add(arr[right]);
            right++;
        }

        for (int i = low; i <= high; i++) {
            arr[i] = list.get(i - low);
        }

    }

}