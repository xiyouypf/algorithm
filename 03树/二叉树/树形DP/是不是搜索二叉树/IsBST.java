package 二叉树.树形DP.是不是搜索二叉树;

import 二叉树.树形DP.Node;

import java.util.ArrayList;

/**
 * 是不是搜索二叉树
 */
public class IsBST {

    public static boolean isBST1(Node head) {
        if (head == null) {
            return true;
        }
        return process(head).isBST;
    }

    private static Info process(Node node) {
        if (node == null) {
            return new Info(Integer.MAX_VALUE, Integer.MIN_VALUE, true);
        }
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);
        int min = Math.min(node.val, Math.min(leftInfo.min, rightInfo.min));
        int max = Math.max(node.val, Math.max(leftInfo.max, rightInfo.max));
        boolean isBST = leftInfo.isBST && rightInfo.isBST;
        if (isBST) {
            if (node.val <= leftInfo.max || node.val >= rightInfo.min) {
                isBST = false;
            }
        }
        return new Info(min, max, isBST);
    }

    public static class Info {
        int min;
        int max;
        boolean isBST;

        public Info(int min, int max, boolean isBST) {
            this.min = min;
            this.max = max;
            this.isBST = isBST;
        }
    }

    public static boolean isBST2(Node head) {
        if (head == null) {
            return true;
        }
        ArrayList<Node> arr = new ArrayList<>();
        in(head, arr);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).val <= arr.get(i - 1).val) {
                return false;
            }
        }
        return true;
    }

    public static void in(Node head, ArrayList<Node> arr) {
        if (head == null) {
            return;
        }
        in(head.left, arr);
        arr.add(head);
        in(head.right, arr);
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
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            boolean ans1 = isBST1(head);
            boolean ans2 = isBST2(head);
            if (ans1 != ans2) {
                System.out.println("ans1 = " + ans1);
                System.out.println("ans2 = " + ans2);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("finish!");
    }
}
