package 栈.单调栈.全为1的子矩阵数量;

import java.util.Stack;

/**
 * 1504 计算全为 1 的子矩阵数量 https://leetcode.com/problems/count-submatrices-with-all-ones/description/
 */
public class CountSubmatricesWithAllOnes {
    public int numSubmat(int[][] mat) {
        int ans = 0;
        int[] height = new int[mat[0].length];
        for (int row = 0; row < mat.length; row++) {
            for (int i = 0; i < mat[0].length; i++) {
                height[i] = mat[row][i] != 0 ? height[i] + 1 : 0;
            }
            ans += countFromBottom(height);
        }
        return ans;
    }

    public static int countFromBottom(int[] height) {
        int ans = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[i] <= height[stack.peek()]) {
                int pop = stack.pop();
                if (height[i] < height[pop]) {
                    int left = !stack.isEmpty() ? stack.peek() : -1;
                    int right = i;
                    int n = right - left - 1;
                    int down = Math.max(left != -1 ? height[left] : 0, height[right]);
                    ans += (height[pop] - down) * num(n);
                }
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int pop = stack.pop();
            int left = !stack.isEmpty() ? stack.peek() : -1;
            int right = height.length;
            int n = right - left - 1;
            int down = left != -1 ? height[left] : 0;
            ans += (height[pop] - down) * (n + 1) * n / 2;
        }
        return ans;
    }

    public static int num(int n) {
        return n * (n + 1) / 2;
    }
}
