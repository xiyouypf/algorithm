package 栈.单调栈.子数组最小乘积的最大值;

import java.util.Stack;

public class Solution {
    public int maxSumMinProduct(int[] arr) {
        int N = arr.length;
        long[] preSum = new long[N];
        preSum[0] = arr[0];
        for (int i = 1; i < N; i++) {
            preSum[i] = preSum[i - 1] + arr[i];
        }
        Stack<Integer> stack = new Stack<>();
        long ans = 0;
        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && arr[i] <= arr[stack.peek()]) {
                Integer pop = stack.pop();
                int left = stack.isEmpty() ? -1 : stack.peek();
                long sum = left != -1 ? preSum[i - 1] - preSum[left] : preSum[i - 1];
                ans = Math.max(ans, sum * arr[pop]);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            Integer pop = stack.pop();
            int left = stack.isEmpty() ? -1 : stack.peek();
            long sum = left != -1 ? preSum[N - 1] - preSum[left] : preSum[N - 1];
            ans = Math.max(ans, sum * arr[pop]);
        }
        return (int) (ans % 1000000007);
    }
}
