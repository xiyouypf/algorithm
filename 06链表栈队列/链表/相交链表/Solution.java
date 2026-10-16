package 链表.相交链表;

import 链表.ListNode;

public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        int diffLen = 0;
        ListNode cur1 = headA;
        while (cur1 != null) {
            diffLen++;
            cur1 = cur1.next;
        }
        ListNode cur2 = headB;
        while (cur2 != null) {
            diffLen--;
            cur2 = cur2.next;
        }
        cur1 = headA;
        cur2 = headB;
        int idx = 0;
        if (diffLen > 0) {
            while (idx < diffLen) {
                idx++;
                cur1 = cur1.next;
            }
        } else if (diffLen < 0) {
            while (idx < Math.abs(diffLen)) {
                idx++;
                cur2 = cur2.next;
            }
        }
        while (cur1 != null && cur2 != null) {
            if (cur1 == cur2) {
                return cur1;
            }
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return null;
    }
}
