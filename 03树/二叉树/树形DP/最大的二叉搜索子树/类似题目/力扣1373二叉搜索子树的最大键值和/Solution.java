package 二叉树.树形DP.最大的二叉搜索子树.类似题目.力扣1373二叉搜索子树的最大键值和;

import 二叉树.树形DP.TreeNode;

public class Solution {
    public static int maxSumBST(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Info info = process(root);
        return info.maxSum;
    }

    public static Info process(TreeNode node) {
        if (node == null) {
            return new Info(Integer.MAX_VALUE, Integer.MIN_VALUE, true, 0, 0);
        }
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);
        int min = Math.min(node.val, Math.min(leftInfo.min, rightInfo.min));
        int max = Math.max(node.val, Math.max(leftInfo.max, rightInfo.max));
        boolean isBST = leftInfo.isBST && rightInfo.isBST && leftInfo.max < node.val && rightInfo.min > node.val;
        int total = leftInfo.total + rightInfo.total + node.val;
        int maxSum = Math.max(leftInfo.maxSum, rightInfo.maxSum);
        if (isBST) {
            maxSum = Math.max(maxSum, total);
        }
        return new Info(min, max, isBST, total, maxSum);
    }

    public static class Info {
        int min;
        int max;
        boolean isBST;
        int total;
        int maxSum;

        public Info(int min, int max, boolean isBST, int total, int maxSum) {
            this.min = min;
            this.max = max;
            this.isBST = isBST;
            this.total = total;
            this.maxSum = maxSum;
        }
    }

    public static void main(String[] args) {
        System.out.println(Integer.MIN_VALUE);
        System.out.println(-2147483640);
    }
}
