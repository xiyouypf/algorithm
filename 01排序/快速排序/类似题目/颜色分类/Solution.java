package 快速排序.类似题目.颜色分类;

public class Solution {
    public static void sortColors(int[] arr) {
        int less = -1;
        int more = arr.length;
        int idx = 0;
        while (idx < more) {
            if (arr[idx] < 1) {
                swap(arr, ++less, idx++);
            } else if (arr[idx] > 1) {
                swap(arr, --more, idx);
            } else {
                idx++;
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
