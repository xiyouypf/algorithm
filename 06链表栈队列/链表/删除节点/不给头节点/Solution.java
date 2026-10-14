package 链表.删除节点.不给头节点;

import 链表.ListNode;

public class Solution {
    public void deleteNode(ListNode node) {
        if (node == null) {
            return;
        }
        ListNode pre = null;
        ListNode cur = node;
        while (cur.next != null) {
            cur.val = cur.next.val;
            pre = cur;
            cur = cur.next;
        }
        if (pre != null) {
            pre.next = null;
        }
    }
}
