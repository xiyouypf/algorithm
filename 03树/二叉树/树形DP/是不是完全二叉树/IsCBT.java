package 二叉树.树形DP.是不是完全二叉树;

import 二叉树.树形DP.Node;

import java.util.LinkedList;

public class IsCBT {
    public static boolean isCBT1(Node head) {
        if (head == null) {
            return true;
        }
        return process(head).isCBT;
    }

    private static Info process(Node node) {
        if (node == null) {
            return new Info(0, true, true);
        }
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        boolean isFull = leftInfo.isFull && rightInfo.isFull
            && leftInfo.height == rightInfo.height;
        boolean isCBT = false;
        if (isFull) {
            // 1. 当前树是满二叉树
            isCBT = true;
        } else {
            // 2. 左树是满二叉树，右树是满二叉树，左树高度==右树高度+1
            if (leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height + 1) {
                isCBT = true;
            // 3. 左树是满二叉树，右树是完全二叉树，左树高度==右树高度
            } else if (leftInfo.isFull && rightInfo.isCBT && leftInfo.height == rightInfo.height) {
                isCBT = true;
            // 左树是完全二叉树，右树是满二叉树，左树高度==右树高度+1
            } else if (leftInfo.isCBT && rightInfo.isFull && leftInfo.height == rightInfo.height + 1) {
                isCBT = true;
            }
        }
        return new Info(height, isFull, isCBT);
    }

    private static class Info {
        int height;
        boolean isFull;
        boolean isCBT;

        public Info(int height, boolean isFull, boolean isCBT) {
            this.height = height;
            this.isFull = isFull;
            this.isCBT = isCBT;
        }
    }

    public static boolean isCBT2(Node head) {
        if (head == null) {
            return true;
        }
        LinkedList<Node> queue = new LinkedList<>();
        // 是否遇到过左右两个孩子不双全的节点
        boolean leaf = false;
        Node l = null;
        Node r = null;
        queue.add(head);
        while (!queue.isEmpty()) {
            head = queue.poll();
            l = head.left;
            r = head.right;
            if (
                // 如果遇到了不双全的节点之后，又发现当前节点不是叶节点
                (leaf && (l != null || r != null))
                    ||
                    (l == null && r != null)

            ) {
                return false;
            }
            if (l != null) {
                queue.add(l);
            }
            if (r != null) {
                queue.add(r);
            }
            if (l == null || r == null) {
                leaf = true;
            }
        }
        return true;
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
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (isCBT1(head) != isCBT2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
