package 链表.是否回文链表;

import 链表.ListNode;

import java.util.Stack;

public class IsPalindromeList {
    public boolean isPalindrome(ListNode head) {
        // 奇数个找中间节点 偶数个找中后节点
        ListNode midNode = getMidOrDownMidNode(head);
        // 反转链表
        ListNode reverseHead = reverseList(midNode);
        //判断是否回文
        ListNode p1 = reverseHead;
        ListNode p2 = head;
        while (p1 != null) {
            if (p1.val != p2.val) {
                return false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }
        // 反转回去 可选
        reverseList(reverseHead);
        return true;
    }

    public static ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    private static ListNode getMidOrDownMidNode(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    public boolean isPalindrome2(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        ListNode curNode = head;
        while (curNode != null) {
            stack.add(curNode);
            curNode = curNode.next;
        }
        curNode = head;
        while (curNode != null) {
            if (curNode.val != stack.pop().val) {
                return false;
            }
            curNode = curNode.next;
        }
        return true;
    }
}
