package 二叉树.有序表.平衡搜索二叉树;

public class AVLTreeMap<K extends Comparable<K>, V> {
    private AVLNode<K, V> root;
    private int size;
    public int size() {
        return size;
    }
    public AVLTreeMap() {
        root = null;
        size = 0;
    }

    private AVLNode<K, V> rightRotate(AVLNode<K, V> cur) {
        AVLNode<K, V> leftNode = cur.l;
        cur.l = leftNode.r;
        leftNode.r = cur;
        cur.h = Math.max(cur.l != null ? cur.l.h : 0, cur.r != null ? cur.r.h : 0) + 1;
        leftNode.h = Math.max(leftNode.l != null ? leftNode.l.h : 0, leftNode.r != null ? leftNode.r.h : 0) + 1;
        return leftNode;
    }

    private AVLNode<K, V> leftRotate(AVLNode<K, V> cur) {
        AVLNode<K, V> rightNode = cur.r;
        cur.r = rightNode.l;
        rightNode.l = cur;
        cur.h = Math.max(cur.l != null ? cur.l.h : 0, cur.r != null ? cur.r.h : 0) + 1;
        rightNode.h = Math.max(rightNode.l != null ? rightNode.l.h : 0, rightNode.r != null ? rightNode.r.h : 0) + 1;
        return rightNode;
    }

    private AVLNode<K, V> maintain(AVLNode<K, V> cur) {
        if (cur == null) {
            return null;
        }
        int leftHeight = cur.l != null ? cur.l.h : 0;
        int rightHeight = cur.r != null ? cur.r.h : 0;
        if (Math.abs(leftHeight - rightHeight) > 1) {
            if (leftHeight > rightHeight) {
                int leftLeftHeight = cur.l != null && cur.l.l != null ? cur.l.l.h : 0;
                int leftRightHeight = cur.l != null && cur.l.r != null ? cur.l.r.h : 0;
                if (leftLeftHeight >= leftRightHeight) {
                    cur = rightRotate(cur);
                } else {
                    cur.l = leftRotate(cur.l);
                    cur = rightRotate(cur);
                }
            } else {
                int rightRightHeight = cur.r != null && cur.r.r != null ? cur.r.r.h : 0;
                int rightLeftHeight = cur.r != null && cur.r.l != null ? cur.r.l.h : 0;
                if (rightRightHeight >= rightLeftHeight) {
                    cur = leftRotate(cur);
                } else {
                    cur.r = rightRotate(cur.r);
                    cur = leftRotate(cur);
                }
            }
        }
        return cur;
    }

    public void put(K key, V value) {
        if (key == null) {
            return;
        }
        AVLNode<K, V> cur = findNode(key);
        if (cur != null) {
            cur.v = value;
        } else {
            size++;
            root = add(root, key, value);
        }
    }

    private AVLNode<K, V> add(AVLNode<K, V> cur, K key, V value) {
        if (cur == null) {
            return new AVLNode<>(key,value);
        } else {
            if (key.compareTo(cur.k) < 0) {
                cur.l = add(cur.l, key, value);
            } else {
                cur.r = add(cur.r, key, value);
            }
        }
        cur.h = Math.max(cur.l != null ? cur.l.h : 0, cur.r != null ? cur.r.h : 0) + 1;
        return maintain(cur);
    }

    public void remove(K key) {
        if (key == null) {
            return;
        }
        if (containsKey(key)) {
            size--;
            root = delete(root, key);
        }
    }

    private AVLNode<K, V> delete(AVLNode<K, V> cur, K key) {
        if (key.compareTo(cur.k) < 0) {
            cur.l = delete(cur.l, key);
        } else if (key.compareTo(cur.k) > 0) {
            cur.r = delete(cur.r, key);
        } else {
            if (cur.l == null && cur.r == null) {
                cur = null;
            } else if (cur.l != null && cur.r == null) {
                cur = cur.l;
            } else if (cur.r != null && cur.l == null) {
                cur = cur.r;
            } else {
                AVLNode<K, V> des = cur.r;
                while (des.l != null) {
                    des = des.l;
                }
                cur.r = delete(cur.r, des.k);
                des.l = cur.l;
                des.r = cur.r;
                cur = des;
            }
        }
        if (cur != null) {
            cur.h = Math.max(cur.l != null ? cur.l.h : 0, cur.r != null ? cur.r.h : 0) + 1;
        }
        return maintain(cur);
    }

    public V get(K key) {
        if (key == null) {
            return null;
        }
        AVLNode<K, V> node = findNode(key);
        return node != null ? node.v : null;
    }

    public boolean containsKey(K key) {
        if (key == null) {
            return false;
        }
        AVLNode<K, V> node = findNode(key);
        return node != null;
    }

    private AVLNode<K, V> findNode(K key) {
        if (key == null || root == null) {
            return null;
        }
        AVLNode<K, V> cur = root;
        while (cur != null) {
            if (key.compareTo(cur.k) < 0) {
                cur = cur.l;
            } else if (key.compareTo(cur.k) > 0) {
                cur = cur.r;
            } else {
                return cur;
            }
        }
        return null;
    }

    public K firstKey() {
        if (root == null) {
            return null;
        }
        AVLNode<K, V> cur = root;
        while (cur.l != null) {
            cur = cur.l;
        }
        return cur.k;
    }

    public K lastKey() {
        if (root == null) {
            return null;
        }
        AVLNode<K, V> cur = root;
        while (cur.r != null) {
            cur = cur.r;
        }
        return cur.k;
    }

    public K floorKey(K key) {
        if (key == null) {
            return null;
        }
        AVLNode<K, V> lastNoBigNode = findLastNoBigIndex(key);
        return lastNoBigNode == null ? null : lastNoBigNode.k;
    }

    public K ceilingKey(K key) {
        if (key == null) {
            return null;
        }
        AVLNode<K, V> lastNoSmallNode = findLastNoSmallIndex(key);
        return lastNoSmallNode == null ? null : lastNoSmallNode.k;
    }

    private AVLNode<K, V> findLastNoSmallIndex(K key) {
        AVLNode<K, V> ans = null;
        AVLNode<K, V> cur = root;
        while (cur != null) {
            if (key.compareTo(cur.k) == 0) {
                ans = cur;
                break;
            } else if (key.compareTo(cur.k) < 0) {
                ans = cur;
                cur = cur.l;
            } else {
                cur = cur.r;
            }
        }
        return ans;
    }

    private AVLNode<K, V> findLastNoBigIndex(K key) {
        AVLNode<K, V> ans = null;
        AVLNode<K, V> cur = root;
        while (cur != null) {
            if (key.compareTo(cur.k) == 0) {
                ans = cur;
                break;
            } else if (key.compareTo(cur.k) < 0) {
                cur = cur.l;
            } else {
                ans = cur;
                cur = cur.r;
            }
        }
        return ans;
    }

    public static class AVLNode<K extends Comparable<K>, V> {
        K k;
        V v;
        AVLNode<K, V> l;
        AVLNode<K, V> r;
        int h;

        public AVLNode(K k, V v) {
            this.k = k;
            this.v = v;
            h = 1;
        }
    }
}
