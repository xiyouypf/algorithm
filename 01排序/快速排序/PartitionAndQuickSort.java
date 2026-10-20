package 快速排序;

import java.util.Random;

public class PartitionAndQuickSort {
    public static void quickSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process1(arr, 0, arr.length - 1);
    }

    private static void process1(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        int partition = partition1(arr, L, R);
        process1(arr, L, partition - 1);
        process1(arr, partition + 1, R);
    }

    private static int partition1(int[] arr, int L, int R) {
        if (L == R) {
            return L;
        }
        swap(arr, L + new Random().nextInt(R - L + 1), R);
        int lessEqual = L - 1;
        int idx = L;
        while (idx < R) {
            if (arr[idx] <= arr[R]) {
                swap(arr, idx++, ++lessEqual);
            } else {
                idx++;
            }
        }
        swap(arr, lessEqual + 1, R);
        return lessEqual + 1;
    }

    public static void quickSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process2(arr, 0, arr.length - 1);
    }

    private static void process2(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        int[] partition = partition2(arr, L, R);
        process2(arr, L, partition[0] - 1);
        process2(arr, partition[1] + 1, R);
    }

    private static int[] partition2(int[] arr, int L, int R) {
        if (L > R) {
            return new int[]{-1, -1};
        }
        if (L == R) {
            return new int[]{L, L};
        }
        int less = L - 1;
        int more = R;
        int idx = L;
        while (idx < more) {
            if (arr[idx] < arr[R]) {
                swap(arr, idx, less + 1);
                less++;
                idx++;
            } else if (arr[idx] > arr[R]) {
                swap(arr, idx, more - 1);
                more--;
            } else {
                idx++;
            }
        }
        swap(arr, more, R);
        return new int[]{less + 1, more};
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
