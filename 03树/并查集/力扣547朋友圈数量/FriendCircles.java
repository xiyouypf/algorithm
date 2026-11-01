package 并查集.力扣547朋友圈数量;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class FriendCircles {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        UnionFind unionFind = new UnionFind(n);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isConnected[i][j] == 1) {
                    unionFind.union(i, j);
                }
            }
        }
        return unionFind.setSize();
    }

    public static class UnionFind {
        Map<Integer, Integer> parentMap = new HashMap<>();
        Map<Integer, Integer> sizeMap = new HashMap<>();

        public UnionFind(int n) {
            for (int i = 0; i < n; i++) {
                parentMap.put(i, i);
                sizeMap.put(i, 1);
            }
        }

        private Integer findFather(Integer cur) {
            Stack<Integer> stack = new Stack<>();
            while (cur != parentMap.get(cur)) {
                stack.push(cur);
                cur = parentMap.get(cur);
            }
            while (!stack.isEmpty()) {
                parentMap.put(stack.pop(), cur);
            }
            return cur;
        }

        public void union(Integer a, Integer b) {
            Integer aHead = findFather(a);
            Integer bHead = findFather(b);
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

        public Integer setSize() {
            return sizeMap.size();
        }
    }
}
