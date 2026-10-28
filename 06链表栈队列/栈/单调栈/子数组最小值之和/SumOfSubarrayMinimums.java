package 栈.单调栈.子数组最小值之和;

import java.util.Stack;

/**
 * 907 子数组最小值之和 https://leetcode.com/problems/sum-of-subarray-minimums/description/
 */
public class SumOfSubarrayMinimums {
    public int sumSubarrayMins(int[] arr) {
        long ans = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[i] <= arr[stack.peek()]) {
                int pop = stack.pop();
                int left = !stack.isEmpty() ? stack.peek() : -1;
                int right = i;
                ans += (pop - left) * (right - pop) * (long) arr[pop];
                ans %= 1000000007;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int pop = stack.pop();
            int left = !stack.isEmpty() ? stack.peek() : -1;
            int right = arr.length;
            ans += (pop - left) * (right - pop) * (long) arr[pop];
            ans %= 1000000007;
        }
        return (int) ans;
    }
}
