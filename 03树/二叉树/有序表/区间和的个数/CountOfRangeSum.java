package 二叉树.有序表.区间和的个数;

import java.util.HashSet;
import java.util.Set;

public class CountOfRangeSum {
    public static int countRangeSum(int[] nums, int lower, int upper) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        long sum = 0;
        int ans = 0;
        SBTree sbTree = new SBTree();
        sbTree.put(0L);
        for (int num : nums) {
            sum += num;
            int max = sbTree.lessKeySize(sum - lower + 1);
            int min = sbTree.lessKeySize(sum - upper);
            ans += max - min;
            sbTree.put(sum);
        }
        return ans;
    }

    public static class SBTree {
        SBTreeNode root;
        Set<Long> set = new HashSet<>();

        public int lessKeySize(long key) {
            SBTreeNode cur = root;
            int ans = 0;
            while (cur != null) {
                if (key < cur.k) {
                    cur = cur.l;
                } else if (key > cur.k) {
                    ans += cur.all - (cur.r != null ? cur.r.all : 0);
                    cur = cur.r;
                } else {
                    return ans + (cur.l != null ? cur.l.all : 0);
                }
            }
            return ans;
        }

        public void put(Long sum) {
            if (sum == null) {
                return;
            }
            boolean contains = set.contains(sum);
            root = add(root, sum, contains);
            set.add(sum);
        }


        private SBTreeNode add(SBTreeNode cur,long k, boolean contains) {
            if (cur == null) {
                return new SBTreeNode(k);
            } else {
                cur.all++;
                if (cur.k == k) {
                    return cur;
                } else {
                    if (!contains) {
                        cur.size++;
                    }
                    if (k < cur.k) {
                        cur.l = add(cur.l, k, contains);
                    } else {
                        cur.r = add(cur.r, k, contains);
                    }
                    return maintain(cur);
                }
            }
        }

        private SBTreeNode leftRoute(SBTreeNode cur) {
            int same = cur.all - (cur.l != null ? cur.l.all : 0) - (cur.r != null ? cur.r.all : 0);
            SBTreeNode rightNode = cur.r;
            cur.r = rightNode.l;
            rightNode.l = cur;
            rightNode.size = cur.size;
            cur.size = (cur.l != null ? cur.l.size : 0) + (cur.r != null ? cur.r.size : 0) + 1;
            rightNode.all = cur.all;
            cur.all = (cur.l != null ? cur.l.all : 0) + (cur.r != null ? cur.r.all : 0) + same;
            return rightNode;
        }

        private SBTreeNode rightRoute(SBTreeNode cur) {
            int same = cur.all - (cur.l != null ? cur.l.all : 0) - (cur.r != null ? cur.r.all : 0);
            SBTreeNode leftNode = cur.l;
            cur.l = leftNode.r;
            leftNode.r = cur;
            leftNode.size = cur.size;
            cur.size = (cur.l != null ? cur.l.size : 0) + (cur.r != null ? cur.r.size : 0) + 1;
            leftNode.all = cur.all;
            cur.all = (cur.l != null ? cur.l.all : 0) + (cur.r != null ? cur.r.all : 0) + same;
            return leftNode;
        }

        private SBTreeNode maintain(SBTreeNode cur) {
            int leftSize = cur.l != null ? cur.l.size : 0;
            int leftLeftSize = cur.l != null && cur.l.l != null ? cur.l.l.size : 0;
            int leftRightSize = cur.l != null && cur.l.r != null ? cur.l.r.size : 0;
            int rightSize = cur.r != null ? cur.r.size : 0;
            int rightLeftSize = cur.r != null && cur.r.l != null ? cur.r.l.size : 0;
            int rightRightSize = cur.r != null && cur.r.r != null ? cur.r.r.size : 0;
            if (leftLeftSize > rightSize) {
                cur = rightRoute(cur);
                cur.r = maintain(cur.r);
                cur = maintain(cur);
            } else if (leftRightSize > rightSize) {
                cur.l = leftRoute(cur.l);
                cur = rightRoute(cur);
                cur.l = maintain(cur.l);
                cur.r = maintain(cur.r);
                cur = maintain(cur);
            } else if (rightRightSize > leftSize) {
                cur = leftRoute(cur);
                cur.l = maintain(cur.l);
                cur = maintain(cur);
            } else if (rightLeftSize > leftSize) {
                cur.r = rightRoute(cur.r);
                cur = leftRoute(cur);
                cur.l = maintain(cur.l);
                cur.r = maintain(cur.r);
                cur = maintain(cur);
            }
            return cur;
        }
    }

    public static class SBTreeNode {
        Long k;
        SBTreeNode l;
        SBTreeNode r;
        int size;
        int all;

        public SBTreeNode(Long k) {
            this.k = k;
            size = 1;
            all = 1;
        }
    }
}
