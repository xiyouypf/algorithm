package 二叉树.二叉树的遍历.按层;

import 二叉树.树形DP.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LevelTraversalBT {
    public static List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> ans = new ArrayList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.addLast(root);
        while (!queue.isEmpty()) {
            List<Integer> temp = new ArrayList<>();
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                TreeNode node = queue.pollFirst();
                temp.add(node.val);
                if (node.left != null) {
                    queue.addLast(node.left);
                }
                if (node.right != null) {
                    queue.addLast(node.right);
                }
            }
            ans.add(temp);
        }
        return ans;
    }
}
