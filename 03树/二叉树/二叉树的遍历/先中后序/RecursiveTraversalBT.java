package 二叉树.二叉树的遍历.先中后序;

import 二叉树.树形DP.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class RecursiveTraversalBT {
    //-------------递归----------------
    public List<Integer> preorderTraversal1(TreeNode root) {
        return traversal(root);
    }

    public List<Integer> traversal(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> ans = new ArrayList<>();
        process(root, ans);
        return ans;
    }

    private static void process(TreeNode node, List<Integer> ans) {
        if (node == null) {
            return;
        }
        // ans.add(node.val); // 前序
        process(node.left, ans);
        // ans.add(node.val); // 中序
        process(node.right, ans);
        // ans.add(node.val); // 后序
    }

    //-------------非递归----------------
    public List<Integer> pre(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            ans.add(cur.val);
            if (cur.right != null) {
                stack.add(cur.right);
            }
            if (cur.left != null) {
                stack.add(cur.left);
            }
        }
        return ans;
    }

    public List<Integer> in(TreeNode cur) {
        if (cur == null) {
            return new ArrayList<>();
        }
        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.add(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            ans.add(cur.val);
            cur = cur.right;
        }
        return ans;
    }

    public List<Integer> pos1(TreeNode cur) {
        if (cur == null) {
            return new ArrayList<>();
        }
        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> stack1 = new Stack<>();
        stack1.add(cur);
        Stack<TreeNode> stack2 = new Stack<>();
        while (!stack1.isEmpty()) {
            cur = stack1.pop();
            stack2.push(cur);
            if (cur.left != null) {
                stack1.push(cur.left);
            }
            if (cur.right != null) {
                stack1.push(cur.right);
            }
        }
        while (!stack2.isEmpty()) {
            ans.add(stack2.pop().val);
        }
        return ans;
    }
}
