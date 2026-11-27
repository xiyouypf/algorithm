package 二叉树.有序表.力扣480滑动窗口中位数;


public class SlidingWindowMedian {
    public double[] medianSlidingWindow(int[] nums, int k) {
        SBTree<Node> sbTree = new SBTree<>();
        for (int i = 0; i < k - 1; i++) {
            sbTree.put(new Node(i, nums[i]));
        }
        double[] ans = new double[nums.length - k + 1];
        int idx = 0;
        for (int i = k - 1; i < nums.length; i++) {
            sbTree.put(new Node(i, nums[i]));
            if (k % 2 != 0) {
                Node mid = sbTree.getIndexKey(k / 2);
                ans[idx++] = mid.val;
            } else {
                Node left = sbTree.getIndexKey(k / 2 - 1);
                Node right = sbTree.getIndexKey(k / 2);
                ans[idx++] = ((double) left.val + (double) right.val) / 2;
            }
            sbTree.remove(new Node(i - k + 1, nums[i - k + 1]));
        }
        return ans;
    }

    public static class SBTree<K extends Comparable<K>> {
        SBTreeNode<K> root;

        public K getIndexKey(int idx) {
            if (idx < 0 || idx >= size()) {
                return null;
            }
            SBTreeNode<K> node = getIndex(root, idx);
            return node.key;
        }

        public SBTreeNode<K> getIndex(SBTreeNode<K> cur, int idx) {
            int curLeftSize = cur.l != null ? cur.l.size : 0;
            if (curLeftSize == idx) {
                return cur;
            } else if (idx < curLeftSize) {
                return getIndex(cur.l, idx);
            } else {
                return getIndex(cur.r, idx - curLeftSize - 1);
            }
        }
        public int size() {
            return root == null ? 0 : root.size;
        }

        public void remove(K key) {
            if (!containsKey(key)) {
                return;
            }
            root = delete(root, key);
        }

        private SBTreeNode<K> delete(SBTreeNode<K> cur, K key) {
            cur.size--;
            if (cur.key.compareTo(key) < 0) {
                cur.r = delete(cur.r, key);
            } else if (cur.key.compareTo(key) > 0) {
                cur.l = delete(cur.l, key);
            } else {
                if (cur.l == null && cur.r == null) {
                    cur = null;
                } else if (cur.l != null && cur.r == null) {
                    cur = cur.l;
                } else if (cur.l == null && cur.r != null) {
                    cur = cur.r;
                } else {
                    SBTreeNode<K> pre = null;
                    SBTreeNode<K> des = cur.r;
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
                    des.size = des.l.size + (des.r != null ? des.r.size : 0) + 1;
                    cur = des;
                }
            }
            return maintain(cur);
        }

        private boolean containsKey(K key) {
            if (key == null) {
                return false;
            }
            return findNode(key) != null;
        }

        private SBTreeNode<K> findNode(K key) {
            if (key == null) {
                return null;
            }
            SBTreeNode<K> cur = root;
            while (cur != null) {
                if (cur.key.compareTo(key) < 0) {
                    cur = cur.r;
                } else if (cur.key.compareTo(key) > 0) {
                    cur = cur.l;
                } else {
                    return cur;
                }
            }
            return null;
        }

        public void put(K key) {
            if (key == null) {
                return;
            }
            root = add(root, key);
        }

        private SBTreeNode<K> add(SBTreeNode<K> cur, K key) {
            if (cur == null) {
                return new SBTreeNode<>(key);
            } else {
                cur.size++;
                if (cur.key.compareTo(key) < 0) {
                    cur.r = add(cur.r, key);
                } else {
                    cur.l = add(cur.l, key);
                }
                return maintain(cur);
            }
        }

        private SBTreeNode<K> leftRotate(SBTreeNode<K> cur) {
            SBTreeNode rightNode = cur.r;
            cur.r = rightNode.l;
            rightNode.l = cur;
            rightNode.size = cur.size;
            cur.size = (cur.l != null ? cur.l.size : 0) + (cur.r != null ? cur.r.size : 0) + 1;
            return rightNode;
        }

        private SBTreeNode<K> rightRotate(SBTreeNode<K> cur) {
            SBTreeNode leftNode = cur.l;
            cur.l = leftNode.r;
            leftNode.r = cur;
            leftNode.size = cur.size;
            cur.size = (cur.l != null ? cur.l.size : 0) + (cur.r != null ? cur.r.size : 0) + 1;
            return leftNode;
        }

        private SBTreeNode<K> maintain(SBTreeNode<K> cur) {
            if (cur == null) {
                return null;
            }
            int leftSize = cur.l != null ? cur.l.size : 0;
            int leftLeftSize = cur.l != null && cur.l.l != null ? cur.l.l.size : 0;
            int leftRightSize = cur.l != null && cur.l.r != null ? cur.l.r.size : 0;
            int rightSize = cur.r != null ? cur.r.size : 0;
            int rightRightSize = cur.r != null && cur.r.r != null ? cur.r.r.size : 0;
            int rightLeftSize = cur.r != null && cur.r.l != null ? cur.r.l.size : 0;
            if (leftLeftSize > rightSize) {
                cur = rightRotate(cur);
                cur.l = maintain(cur.l);
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
                cur.r = maintain(cur.r);
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
    }
    public static class SBTreeNode<K extends Comparable<K>> {
        K key;
        int size;
        SBTreeNode l;
        SBTreeNode r;

        public SBTreeNode(K k) {
            this.key = k;
            size = 1;
        }
    }

    public static class Node implements Comparable<Node> {
        public int idx;
        public int val;

        public Node(int i, int v) {
            idx = i;
            val = v;
        }

        @Override
        public int compareTo(Node o) {
            return val != o.val ? Integer.valueOf(val).compareTo(o.val)
                : Integer.valueOf(idx).compareTo(o.idx);
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        SlidingWindowMedian windowMedian = new SlidingWindowMedian();
        double[] doubles = windowMedian.medianSlidingWindow(nums, k);
        for (double v : doubles) {
            System.out.print(v + "    ");
        }
    }
}
