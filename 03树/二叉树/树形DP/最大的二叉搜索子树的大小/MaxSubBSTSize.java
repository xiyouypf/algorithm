package 二叉树.树形DP.最大的二叉搜索子树的大小;

import 二叉树.树形DP.TreeNode;

/**
 * 在线测试链接 : https://leetcode.com/problems/largest-bst-subtree
 */
public class MaxSubBSTSize {
    public static int largestBSTSubtree(TreeNode head) {
        Info info = process(head);
        return info.maxSubBSTSize;
    }

    private static Info process(TreeNode node) {
        if (node == null) {
            return new Info(Integer.MAX_VALUE, Integer.MIN_VALUE, true, 0);
        }
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);
        int min = Math.min(node.val, Math.min(leftInfo.min, rightInfo.min));
        int max = Math.max(node.val, Math.max(leftInfo.max, rightInfo.max));
        boolean isBST = leftInfo.isBST && rightInfo.isBST && leftInfo.max < node.val && rightInfo.min > node.val;
        int maxSubBSTSize = Math.max(leftInfo.maxSubBSTSize, rightInfo.maxSubBSTSize);
        if (isBST) {
            maxSubBSTSize = leftInfo.maxSubBSTSize + rightInfo.maxSubBSTSize + 1;
        }
        return new Info(min, max, isBST, maxSubBSTSize);
    }

    public static class Info {
        int min;
        int max;
        boolean isBST;
        int maxSubBSTSize;

        public Info(int min, int max, boolean isBST, int maxSubBSTSize) {
            this.min = min;
            this.max = max;
            this.isBST = isBST;
            this.maxSubBSTSize = maxSubBSTSize;
        }
    }
}
