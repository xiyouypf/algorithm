package 栈.单调栈.全为1的最大矩形;

import java.util.Stack;

/**
 * 测试链接：https://leetcode.com/problems/maximal-rectangle/
 */
public class MaximalRectangle {
    public int maximalRectangle(char[][] matrix) {
        int ans = 0;
        int[] heights = new int[matrix[0].length];
        for (int row = 0; row < matrix.length; row++) {
            for (int i = 0; i < matrix[0].length; i++) {
                heights[i] = matrix[row][i] != '0' ? heights[i] + 1 : 0;
            }
            ans = Math.max(ans, largestRectangleArea(heights));
        }
        return ans;
    }
    public int largestRectangleArea(int[] heights) {
        int ans = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[i] <= heights[stack.peek()]) {
                int pop = stack.pop();
                int left = !stack.isEmpty() ? stack.peek() : -1;
                int right = i;
                ans = Math.max(ans, heights[pop] * (right - left - 1));
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int pop = stack.pop();
            int left = !stack.isEmpty() ? stack.peek() : -1;
            int right = heights.length;
            ans = Math.max(ans, heights[pop] * (right - left - 1));
        }
        return ans;
    }
}
