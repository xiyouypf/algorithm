package 二叉树.有序表.SB树;

public class SizeBalancedTreeMap<K extends Comparable<K>, V> {
    private SBTNode<K, V> root;
    public int size() {
        return root != null ? root.size : 0;
    }

    public SizeBalancedTreeMap() {
        this.root = null;
    }

    private SBTNode<K, V> leftRotate(SBTNode<K, V> cur) {
        SBTNode<K, V> rightNode = cur.r;
        cur.r = rightNode.l;
        rightNode.l = cur;
        rightNode.size = cur.size;
        cur.size = (cur.l != null ? cur.l.size : 0) + (cur.r != null ? cur.r.size : 0) + 1;
        return rightNode;
    }

    private SBTNode<K, V> rightRotate(SBTNode<K, V> cur) {
        SBTNode<K, V> leftNode = cur.l;
        cur.l = leftNode.r;
        leftNode.r = cur;
        leftNode.size = cur.size;
        cur.size = (cur.l != null ? cur.l.size : 0) + (cur.r != null ? cur.r.size : 0) + 1;
        return leftNode;
    }

    private SBTNode<K, V> maintain(SBTNode<K, V> cur) {
        if (cur == null) {
            return null;
        }
        int leftSize = cur.l != null ? cur.l.size : 0;
        int leftLeftSize = cur.l != null && cur.l.l != null ? cur.l.l.size : 0;
        int leftRightSize = cur.l != null && cur.l.r != null ? cur.l.r.size : 0;
        int rightSize = cur.r != null ? cur.r.size : 0;
        int rightLeftSize = cur.r != null && cur.r.l != null ? cur.r.l.size : 0;
        int rightRightSize = cur.r != null && cur.r.r != null ? cur.r.r.size : 0;
        if (leftLeftSize > rightSize) {
            cur = rightRotate(cur);
            cur.r = maintain(cur.r);
            cur = maintain(cur);
        } else if (leftRightSize > rightSize) {
            cur.l = leftRotate(cur.l);
            cur = rightRotate(cur);
            cur.l = maintain(cur.l);
            cur.r = maintain(cur.r);
            cur = maintain(cur);
        } else if (rightRightSize > leftSize) {
            cur = leftRotate(cur);
            cur.l = maintain(cur.l);
            cur = maintain(cur);
        } else if (rightLeftSize > leftSize) {
            cur.r = rightRotate(cur.r);
            cur = leftRotate(cur);
            cur.l = maintain(cur.l);
            cur.r = maintain(cur.r);
            cur = maintain(cur);
        }
        return cur;
    }

    public void put(K key, V value) {
        if (key == null) {
            return;
        }
        SBTNode<K, V> cur = findNode(key);
        if (cur != null) {
            cur.v = value;
        } else {
            root = add(root, key, value);
        }
    }

    private SBTNode<K, V> add(SBTNode<K, V> cur, K key, V value) {
        if (cur == null) {
            return new SBTNode<>(key, value);
        } else {
            cur.size++;
            if (key.compareTo(cur.k) < 0) {
                cur.l = add(cur.l, key, value);
            } else {
                cur.r = add(cur.r, key, value);
            }
            return maintain(cur);
        }
    }

    public void remove(K key) {
        if (key == null) {
            return;
        }
        if (containsKey(key)) {
            root = delete(root, key);
        }
    }

    private SBTNode<K, V> delete(SBTNode<K, V> cur, K key) {
        cur.size--;
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
                SBTNode<K, V> pre = null;
                SBTNode<K, V> des = cur.r;
                des.size--;
                while (des.l != null) {
                    pre = des;
                    des = des.l;
                    des.size--;
                }
                if (pre != null) {
                    pre.l = des.r;
                    des.r = cur.r;
                }
                des.l = cur.l;
                des.size = des.l.size + (des.r == null ? 0 : des.r.size) + 1;
                cur = des;
            }
        }
        return maintain(cur);
    }

    public V get(K key) {
        if (key == null) {
            throw new RuntimeException("invalid parameter.");
        }
        SBTNode<K, V> node = findNode(key);
        return node != null ? node.v : null;
    }

    public boolean containsKey(K key) {
        if (key == null) {
            return false;
        }
        SBTNode<K, V> node = findNode(key);
        return node != null;
    }

    private SBTNode<K, V> findNode(K key) {
        if (key == null || root == null) {
            return null;
        }
        SBTNode<K, V> cur = root;
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
        SBTNode<K, V> cur = root;
        while (cur.l != null) {
            cur = cur.l;
        }
        return cur.k;
    }

    public K lastKey() {
        if (root == null) {
            return null;
        }
        SBTNode<K, V> cur = root;
        while (cur.r != null) {
            cur = cur.r;
        }
        return cur.k;
    }

    public K floorKey(K key) {
        if (key == null) {
            throw new RuntimeException("invalid parameter.");
        }
        SBTNode<K, V> lastNoBigNode = findLastNoBigIndex(key);
        return lastNoBigNode == null ? null : lastNoBigNode.k;
    }

    public K ceilingKey(K key) {
        if (key == null) {
            throw new RuntimeException("invalid parameter.");
        }
        SBTNode<K, V> lastNoSmallNode = findLastNoSmallIndex(key);
        return lastNoSmallNode == null ? null : lastNoSmallNode.k;
    }

    private SBTNode<K, V> findLastNoBigIndex(K key) {
        SBTNode<K, V> ans = null;
        SBTNode<K, V> cur = root;
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

    private SBTNode<K, V> findLastNoSmallIndex(K key) {
        SBTNode<K, V> ans = null;
        SBTNode<K, V> cur = root;
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

    public static class SBTNode<K extends Comparable<K>, V> {
        K k;
        V v;
        int size;
        SBTNode<K, V> l;
        SBTNode<K, V> r;

        public SBTNode(K k, V v) {
            this.k = k;
            this.v = v;
            size = 1;
        }
    }
}
