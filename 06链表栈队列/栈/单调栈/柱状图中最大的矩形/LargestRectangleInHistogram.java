package 栈.单调栈.柱状图中最大的矩形;

import java.util.Stack;

/**
 * 测试链接：https://leetcode.com/problems/largest-rectangle-in-histogram
 */
public class LargestRectangleInHistogram {
    public int largestRectangleArea(int[] heights) {
        int ans = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[i] <= heights[stack.peek()]) {
                Integer pop = stack.pop();
                int left = !stack.isEmpty() ? stack.peek() : -1;
                int right = i;
                ans = Math.max(ans, heights[pop] * (right - left - 1));
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            Integer pop = stack.pop();
            int left = !stack.isEmpty() ? stack.peek() : -1;
            int right = heights.length;
            ans = Math.max(ans, heights[pop] * (right - left - 1));
        }
        return ans;
    }
}
