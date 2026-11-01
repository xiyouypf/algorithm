package 并查集.实现;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class UnionFind<V> {
    public Map<V, V> parentMap = new HashMap<>();
    public Map<V, Integer> sizeMap = new HashMap<>();

    public UnionFind(List<V> values) {
        for (V value : values) {
            parentMap.put(value, value);
            sizeMap.put(value, 1);
        }
    }

    /**
     * 给你一个节点，请你往上到不能再往上，把代表返回
     */
    public V findFather(V cur) {
        Stack<V> stack = new Stack<>();
        while (cur != parentMap.get(cur)) {
            stack.push(cur);
            cur = parentMap.get(cur);
        }
        while (!stack.isEmpty()) {
            parentMap.put(stack.pop(), cur);
        }
        return cur;
    }

    public boolean isSameSet(V a, V b) {
        return findFather(a) == findFather(b);
    }

    public void union(V a, V b) {
        V aHead = findFather(a);
        V bHead = findFather(b);
        if (aHead != bHead) {
            int aHeadSize = sizeMap.get(aHead);
            int bHeadSize = sizeMap.get(bHead);
            if (aHeadSize <= bHeadSize) {
                sizeMap.remove(aHead);
                parentMap.put(aHead, bHead);
                sizeMap.put(bHead, aHeadSize + bHeadSize);
            } else {
                sizeMap.remove(bHead);
                parentMap.put(bHead, aHead);
                sizeMap.put(aHead, aHeadSize + bHeadSize);
            }
        }
    }

    public int sets() {
        return sizeMap.size();
    }
}
