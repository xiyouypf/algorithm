package 二叉树.二叉树的遍历.按层.类似题目.二叉树的锯齿形层序遍历;

import 二叉树.树形DP.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> ans = new ArrayList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.addLast(root);
        boolean flag = true;
        while (!queue.isEmpty()) {
            List<Integer> temp = new ArrayList<>();
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                TreeNode node = queue.pollFirst();
                if (flag) {
                    temp.add(node.val);
                }else {
                    temp.add(0, node.val);
                }
                if (node.left != null) {
                    queue.addLast(node.left);
                }
                if (node.right != null) {
                    queue.addLast(node.right);
                }
            }
            flag = !flag;
            ans.add(temp);
        }
        return ans;
    }
}
