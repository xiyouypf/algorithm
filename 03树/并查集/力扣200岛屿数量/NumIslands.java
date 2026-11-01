package 并查集.力扣200岛屿数量;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class NumIslands {
    public int numIslands(char[][] grid) {
        UnionFind unionFind = new UnionFind(grid);
        for (int i = 1; i < grid.length; i++) {
            if (grid[i][0] == '1' && grid[i - 1][0] == '1') {
                unionFind.union(unionFind.nodeMap.get(i + "_" + 0), unionFind.nodeMap.get((i - 1) + "_" + 0));
            }
        }
        for (int j = 1; j < grid[0].length; j++) {
            if (grid[0][j] == '1' && grid[0][j - 1] == '1') {
                unionFind.union(unionFind.nodeMap.get(0 + "_" + j), unionFind.nodeMap.get(0 + "_" + (j - 1)));
            }
        }
        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    if (grid[i - 1][j] == '1') {
                        unionFind.union(unionFind.nodeMap.get(i + "_" + j), unionFind.nodeMap.get((i - 1) + "_" + j));
                    }
                    if (grid[i][j - 1] == '1') {
                        unionFind.union(unionFind.nodeMap.get(i + "_" + j), unionFind.nodeMap.get(i + "_" + (j - 1)));
                    }
                }
            }
        }
        return unionFind.setSize();
    }

    public static class UnionFind {
        Map<String, Node> nodeMap = new HashMap<>();
        Map<Node, Node> parentMap = new HashMap<>();
        Map<Node, Integer> sizeMap = new HashMap<>();

        public UnionFind(char[][] grid) {
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == '1') {
                        Node node = new Node(i + "_" + j);
                        nodeMap.put(i + "_" + j, node);
                        parentMap.put(node, node);
                        sizeMap.put(node, 1);
                    }
                }
            }
        }

        private Node findParent(Node cur) {
            Stack<Node> stack = new Stack<>();
            while (cur != parentMap.get(cur)) {
                stack.push(cur);
                cur = parentMap.get(cur);
            }
            while (!stack.isEmpty()) {
                parentMap.put(stack.pop(), cur);
            }
            return cur;
        }

        public void union(Node a, Node b) {
            Node aHead = findParent(a);
            Node bHead = findParent(b);
            if (aHead != bHead) {
                int aHeadSize = sizeMap.get(aHead);
                int bHeadSize = sizeMap.get(bHead);
                if (aHeadSize <= bHeadSize) {
                    parentMap.put(aHead, bHead);
                    sizeMap.put(bHead, aHeadSize + bHeadSize);
                    sizeMap.remove(aHead);
                } else {
                    parentMap.put(bHead, aHead);
                    sizeMap.put(aHead, aHeadSize + bHeadSize);
                    sizeMap.remove(bHead);
                }
            }
        }

        public int setSize() {
            return sizeMap.size();
        }
    }

    public static class Node {
        String val;

        public Node(String val) {
            this.val = val;
        }
    }
}
