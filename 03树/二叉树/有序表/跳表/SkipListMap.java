package 二叉树.有序表.跳表;

import java.util.ArrayList;
import java.util.List;

public class SkipListMap<K extends Comparable<K>, V> {

    private static final double P = 0.5; // < 0.5 继续做，>=0.5 停
    private SkipListNode<K, V> head;
    private int size;
    private int maxLevel;

    public SkipListMap() {
        this.head = new SkipListNode<>(null, null);
        head.nextNodes.add(null);
        size = 0;
        maxLevel = 0;
    }

    public void put(K key, V value) {
        if (key == null) {
            return;
        }
        SkipListNode<K, V> less = mostRightLessNodeInTree(key);
        SkipListNode<K, V> next = less.nextNodes.get(0);
        if (next != null && next.isKeyEqual(key)) {
            next.v = value;
        } else {
            size++;
            int newNodeLevel = 0;
            while (Math.random() < P) {
                newNodeLevel++;
            }
            while (newNodeLevel > maxLevel) {
                maxLevel++;
                head.nextNodes.add(null);
            }
            SkipListNode<K, V> newNode = new SkipListNode<>(key, value);
            for (int i = 0; i <= newNodeLevel; i++) {
                newNode.nextNodes.add(null);
            }
            SkipListNode<K, V> preNode = head;
            for (int curLevel = maxLevel; curLevel >= 0; curLevel--) {
                preNode = mostRightLessNodeInLevel(key, preNode, curLevel);
                if (curLevel <= newNodeLevel) {
                    newNode.nextNodes.set(curLevel, preNode.nextNodes.get(curLevel));
                    preNode.nextNodes.set(curLevel, newNode);
                }
            }
        }
    }

    public void remove(K key) {
        if (!containsKey(key)) {
            return;
        }
        size--;
        SkipListNode<K, V> preNode = head;
        int curLevel = maxLevel;
        while (curLevel >= 0) {
            preNode = mostRightLessNodeInLevel(key, preNode, curLevel);
            SkipListNode<K, V> nextNode = preNode.nextNodes.get(curLevel);
            if (nextNode != null && nextNode.isKeyEqual(key)) {
                preNode.nextNodes.set(curLevel, nextNode.nextNodes.get(curLevel));
            }
            if (curLevel != 0 && preNode == head && preNode.nextNodes.get(curLevel) == null) {
                head.nextNodes.remove(curLevel);
                maxLevel--;
            }
            curLevel--;
        }
    }

    public boolean containsKey(K key) {
        if (key == null) {
            return false;
        }
        SkipListNode<K, V> less = mostRightLessNodeInTree(key);
        SkipListNode<K, V> next = less.nextNodes.get(0);
        return next != null && next.isKeyEqual(key);
    }

    public V get(K key) {
        if (key == null) {
            return null;
        }
        SkipListNode<K, V> less = mostRightLessNodeInTree(key);
        SkipListNode<K, V> next = less.nextNodes.get(0);
        if (next != null && next.isKeyEqual(key)) {
            return next.v;
        }
        return null;
    }

    /**
     * 核心基建方法：
     * 在整个跳表上，找到最右边小于key的节点
     */
    private SkipListNode<K, V> mostRightLessNodeInTree(K key) {
        if (key == null) {
            return null;
        }
        SkipListNode<K, V> curNode = head;
        for (int curLevel = maxLevel; curLevel >= 0; curLevel--) {
            curNode = mostRightLessNodeInLevel(key, curNode, curLevel);
        }
        return curNode;
    }

    /**
     * 核心基建方法：
     * 在某一层，从cur节点开始，找到level层最右边小于key的节点
     */
    private SkipListNode<K, V> mostRightLessNodeInLevel(K key,
                                                        SkipListNode<K, V> curNode,
                                                        int level) {
        SkipListNode<K, V> nextNode = curNode.nextNodes.get(level);
        while (nextNode != null && nextNode.k.compareTo(key) < 0) {
            curNode = nextNode;
            nextNode = nextNode.nextNodes.get(level);
        }
        return curNode;
    }

    public K firstKey() {
        return head.nextNodes.get(0) != null ? head.nextNodes.get(0).k : null;
    }

    public K lastKey() {
        int level = maxLevel;
        SkipListNode<K, V> cur = head;
        while (level >= 0) {
            SkipListNode<K, V> next = cur.nextNodes.get(level);
            while (next != null) {
                cur = next;
                next = cur.nextNodes.get(level);
            }
            level--;
        }
        return cur.k;
    }

    public K ceilingKey(K key) {
        if (key == null) {
            return null;
        }
        SkipListNode<K, V> less = mostRightLessNodeInTree(key);
        SkipListNode<K, V> next = less.nextNodes.get(0);
        return next != null ? next.k : null;
    }

    public K floorKey(K key) {
        if (key == null) {
            return null;
        }
        SkipListNode<K, V> less = mostRightLessNodeInTree(key);
        SkipListNode<K, V> next = less.nextNodes.get(0);
        return next != null && next.isKeyEqual(key) ? next.k : less.k;
    }

    public int size() {
        return size;
    }

    public static class SkipListNode<K extends Comparable<K>, V> {
        K k;
        V v;
        List<SkipListNode<K, V>> nextNodes;

        public SkipListNode(K k, V v) {
            this.k = k;
            this.v = v;
            nextNodes = new ArrayList<>();
        }

        public boolean isKeyEqual(K otherKey) {
            return (k == null && otherKey == null)
                || (k != null && otherKey != null && k.compareTo(otherKey) == 0);
        }
    }
}
