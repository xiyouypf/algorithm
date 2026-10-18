package 归并排序;

/**
 * https://leetcode.cn/problems/sort-an-array/description/
 * 整体是递归，左边排好序+右边排好序+merge让整体有序
 */
public class MergeSort {
    /**
     * 递归实现
     */
    public static void mergeSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    private static void process(int[] arr, int L, int R) {
        if (L == R) {
            return;
        }
        int mid = (L + R) / 2;
        process(arr, L, mid);
        process(arr, mid + 1, R);
        merge(arr, L, mid, R);
    }

    private static void merge(int[] arr, int L, int mid, int R) {
        int[] temp = new int[R - L + 1];
        int p1 = L;
        int p2 = mid + 1;
        int k = 0;
        while (p1 <= mid && p2 <= R) {
            temp[k++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= mid) {
            temp[k++] = arr[p1++];
        }
        while (p2 <= R) {
            temp[k++] = arr[p2++];
        }
        for (int i = 0; i < temp.length; i++) {
            arr[L + i] = temp[i];
        }
    }

    /**
     * 迭代实现
     */
    public static void mergeSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int N = arr.length;
        int stepSize = 1;
        while (stepSize < N) {
            int L = 0;
            while (L < N) {
                int mid = L + stepSize - 1;
                if (mid >= N - 1) {
                    break;
                }
                int R = Math.min(mid + stepSize, N - 1);
                merge(arr, L, mid, R);
                L = R + 1;
            }
            stepSize *= 2;
        }
    }
}
