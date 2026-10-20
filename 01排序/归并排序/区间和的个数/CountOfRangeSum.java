package 归并排序.区间和的个数;

public class CountOfRangeSum {
    public static int countRangeSum(int[] nums, int lower, int upper) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        long[] preSum = new long[nums.length];
        preSum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            preSum[i] = preSum[i - 1] + nums[i];
        }
        return process(preSum, 0, nums.length - 1, lower, upper);
    }

    private static int process(long[] arr, int L, int R, int lower, int upper) {
        if (L == R) {
            return arr[L] >= lower && arr[L] <= upper ? 1 : 0;
        }
        int M = (L + R) / 2;
        return process(arr, L, M, lower, upper)
            + process(arr, M + 1, R, lower, upper)
            + merge(arr, L, M, R, lower, upper);
    }

    private static int merge(long[] arr, int L, int M, int R, int lower, int upper) {
        int windowL = L;
        int windowR = L;
        int ans = 0;
        for (int i = M + 1; i <= R; i++) {
            long min = arr[i] - upper;
            long max = arr[i] - lower;
            while (windowR <= M && arr[windowR] <= max) {
                windowR++;
            }
            while (windowL <= M && arr[windowL] < min) {
                windowL++;
            }
            ans += windowR - windowL;
        }
        long[] help = new long[R - L + 1];
        int p1 = L;
        int p2 = M + 1;
        int k = 0;
        while (p1 <= M && p2 <= R) {
            help[k++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= M) {
            help[k++] = arr[p1++];
        }
        while (p2 <= R) {
            help[k++] = arr[p2++];
        }
        for (int i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
        return ans;
    }
}
