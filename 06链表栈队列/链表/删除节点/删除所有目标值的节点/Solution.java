package 链表.删除节点.删除所有目标值的节点;

import 链表.ListNode;

public class Solution {
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        // head来到第一个不需要删除的位置
        while (head != null && head.val == val) {
            head = head.next;
        }
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            if (cur.val != val) {
                pre = cur;
                cur = cur.next;
            } else {
                pre.next = cur.next;
                cur = cur.next;
            }
        }
        return head;
    }
}
