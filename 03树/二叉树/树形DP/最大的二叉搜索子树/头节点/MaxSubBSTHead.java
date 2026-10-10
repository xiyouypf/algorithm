package 二叉树.树形DP.最大的二叉搜索子树.头节点;

import java.util.ArrayList;
import 二叉树.树形DP.TreeNode;

public class MaxSubBSTHead {
    public static TreeNode maxSubBSTHead1(TreeNode head) {
        if (head == null) {
            return null;
        }
        Info info = process(head);
        return info.maxSubBSTHead;
    }

    private static Info process(TreeNode node) {
        if (node == null) {
            return new Info(Integer.MAX_VALUE, Integer.MIN_VALUE, 0, true, null);
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
        TreeNode maxSubBSTHead = null;
        if (isBST) {
            maxSubBSTHead = node;
        } else if (leftInfo.maxSubBSTSize >= rightInfo.maxSubBSTSize) {
            maxSubBSTHead = leftInfo.maxSubBSTHead;
        } else {
            maxSubBSTHead = rightInfo.maxSubBSTHead;
        }
        return new Info(min, max, maxSubBSTSize, isBST, maxSubBSTHead);
    }

    public static class Info {
        int min;
        int max;
        int maxSubBSTSize;
        boolean isBST;
        TreeNode maxSubBSTHead;

        public Info(int min, int max, int maxSubBSTSize, boolean isBST, TreeNode maxSubBSTHead) {
            this.min = min;
            this.max = max;
            this.maxSubBSTSize = maxSubBSTSize;
            this.isBST = isBST;
            this.maxSubBSTHead = maxSubBSTHead;
        }
    }
    public static TreeNode maxSubBSTHead2(TreeNode head) {
        if (head == null) {
            return null;
        }
        if (getBSTSize(head) != 0) {
            return head;
        }
        TreeNode leftAns = maxSubBSTHead2(head.left);
        TreeNode rightAns = maxSubBSTHead2(head.right);
        return getBSTSize(leftAns) >= getBSTSize(rightAns) ? leftAns : rightAns;
    }

    public static int getBSTSize(TreeNode head) {
        if (head == null) {
            return 0;
        }
        ArrayList<TreeNode> arr = new ArrayList<>();
        in(head, arr);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).val <= arr.get(i - 1).val) {
                return 0;
            }
        }
        return arr.size();
    }

    public static void in(TreeNode head, ArrayList<TreeNode> arr) {
        if (head == null) {
            return;
        }
        in(head.left, arr);
        arr.add(head);
        in(head.right, arr);
    }

    // for test
    public static TreeNode generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static TreeNode generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        TreeNode head = new TreeNode((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 8;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            TreeNode head = generateRandomBST(maxLevel, maxValue);
            if (maxSubBSTHead1(head) != maxSubBSTHead2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
