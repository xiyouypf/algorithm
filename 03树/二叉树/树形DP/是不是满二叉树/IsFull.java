package 二叉树.树形DP.是不是满二叉树;

import 二叉树.树形DP.Node;

public class IsFull {

    public static boolean isFull1(Node head) {
        if (head == null) {
            return true;
        }
        return process1(head).isFull;
    }

    private static Info1 process1(Node node) {
        if (node == null) {
            return new Info1(0, true);
        }
        Info1 leftInfo = process1(node.left);
        Info1 rightInfo = process1(node.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        boolean isFull = leftInfo.isFull && rightInfo.isFull;
        if (isFull && leftInfo.height != rightInfo.height) {
            isFull = false;
        }
        return new Info1(height, isFull);
    }

    public static class Info1 {
        int height;
        boolean isFull;

        public Info1(int height, boolean isFull) {
            this.height = height;
            this.isFull = isFull;
        }
    }

    public static boolean isFull2(Node head) {
        if (head == null) {
            return true;
        }
        Info2 info = process2(head);
        return Math.pow(2, info.height) - 1 == info.nodes;
    }

    private static Info2 process2(Node node) {
        if (node == null) {
            return new Info2(0, 0);
        }
        Info2 leftInfo = process2(node.left);
        Info2 rightInfo = process2(node.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        int nodes = leftInfo.nodes + rightInfo.nodes + 1;
        return new Info2(height, nodes);
    }

    public static class Info2 {
        int height;
        int nodes;

        public Info2(int height, int nodes) {
            this.height = height;
            this.nodes = nodes;
        }
    }

    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (isFull1(head) != isFull2(head)) {
                System.out.println("出错了!");
            }
        }
        System.out.println("测试结束");
    }
}
