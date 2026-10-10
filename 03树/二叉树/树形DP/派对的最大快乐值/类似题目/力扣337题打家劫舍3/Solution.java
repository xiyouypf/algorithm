package 二叉树.树形DP.派对的最大快乐值.类似题目.力扣337题打家劫舍3;

import 二叉树.树形DP.TreeNode;

public class Solution {
    public static int rob(TreeNode root) {
        Info info = process(root);
        return Math.max(info.no, info.yes);
    }

    private static Info process(TreeNode node) {
        if (node == null) {
            return new Info(0, 0);
        }
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);
        int no = Math.max(leftInfo.no, leftInfo.yes) + Math.max(rightInfo.no, rightInfo.yes);
        int yes = node.val + leftInfo.no + rightInfo.no;
        return new Info(no, yes);
    }

    public static class Info {
        int no;
        int yes;

        public Info(int no, int yes) {
            this.no = no;
            this.yes = yes;
        }
    }
}
