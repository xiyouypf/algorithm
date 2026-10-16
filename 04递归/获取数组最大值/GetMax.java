package 获取数组最大值;

public class GetMax {
    // 求arr中的最大值
    public static int getMax(int[] arr) {
        return process(arr, 0, arr.length - 1);
    }

    private static int process(int[] arr, int L, int R) {
        if (L == R) {
            return arr[L];
        }
        int mid = (L + R) / 2;
        return Math.max(process(arr, L, mid), process(arr, mid + 1, R));
    }
}
