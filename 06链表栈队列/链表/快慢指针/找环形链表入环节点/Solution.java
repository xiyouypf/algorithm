package 链表.快慢指针.找环形链表入环节点;

import 链表.ListNode;

public class Solution {
    /**
     * 找到链表第一个入环节点，如果无环，返回null
     */
    public ListNode getLoopNode(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                break;
            }
        }
        if (fast == null || fast.next == null) {
            return null;
        }
        fast = head;
        while (slow != null && fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }
}
