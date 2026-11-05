package 链表.分隔链表;

import 链表.ListNode;

import java.util.Random;

public class SmallerEqualBigger {
    public static ListNode partition(ListNode head, int x) {
        ListNode smallHead = null;
        ListNode smallTail = null;
        ListNode equalHead = null;
        ListNode equalTail = null;
        ListNode bigHead = null;
        ListNode bigTail = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = null;
            if (cur.val < x) {
                if (smallHead == null) {
                    smallHead = cur;
                    smallTail = cur;
                } else {
                    smallTail.next = cur;
                    smallTail = cur;
                }
            } else if (cur.val == x) {
                if (equalHead == null) {
                    equalHead = cur;
                    equalTail = cur;
                } else {
                    equalTail.next = cur;
                    equalTail = cur;
                }
            } else {
                if (bigHead == null) {
                    bigHead = cur;
                    bigTail = cur;
                } else {
                    bigTail.next = cur;
                    bigTail = cur;
                }
            }
            cur = next;
        }
        if (smallHead != null) {
            if (equalHead != null) {
                smallTail.next = equalHead;
                equalTail.next = bigHead;
            } else {
                smallTail.next = bigHead;
            }
            return smallHead;
        } else if (equalHead != null) {
            equalTail.next = bigHead;
            return equalHead;
        } else {
            return bigHead;
        }
    }

    public static ListNode listPartition1(ListNode head, int pivot) {
        if (head == null) {
            return head;
        }
        ListNode cur = head;
        int i = 0;
        while (cur != null) {
            i++;
            cur = cur.next;
        }
        ListNode[] nodeArr = new ListNode[i];
        i = 0;
        cur = head;
        for (i = 0; i != nodeArr.length; i++) {
            nodeArr[i] = cur;
            cur = cur.next;
        }
        arrPartition(nodeArr, pivot);
        for (i = 1; i != nodeArr.length; i++) {
            nodeArr[i - 1].next = nodeArr[i];
        }
        nodeArr[i - 1].next = null;
        return nodeArr[0];
    }

    public static void arrPartition(ListNode[] nodeArr, int pivot) {
        int small = -1;
        int big = nodeArr.length;
        int index = 0;
        while (index != big) {
            if (nodeArr[index].val < pivot) {
                swap(nodeArr, ++small, index++);
            } else if (nodeArr[index].val == pivot) {
                index++;
            } else {
                swap(nodeArr, --big, index);
            }
        }
    }

    public static void swap(ListNode[] nodeArr, int a, int b) {
        ListNode tmp = nodeArr[a];
        nodeArr[a] = nodeArr[b];
        nodeArr[b] = tmp;
    }

    public static ListNode listPartition2(ListNode head, int pivot) {
        ListNode sH = null; // small head
        ListNode sT = null; // small tail
        ListNode eH = null; // equal head
        ListNode eT = null; // equal tail
        ListNode mH = null; // big head
        ListNode mT = null; // big tail
        ListNode next = null; // save next node
        // every node distributed to three lists
        while (head != null) {
            next = head.next;
            head.next = null;
            if (head.val < pivot) {
                if (sH == null) {
                    sH = head;
                    sT = head;
                } else {
                    sT.next = head;
                    sT = head;
                }
            } else if (head.val == pivot) {
                if (eH == null) {
                    eH = head;
                    eT = head;
                } else {
                    eT.next = head;
                    eT = head;
                }
            } else {
                if (mH == null) {
                    mH = head;
                    mT = head;
                } else {
                    mT.next = head;
                    mT = head;
                }
            }
            head = next;
        }
        // 小于区域的尾巴，连等于区域的头，等于区域的尾巴连大于区域的头
        if (sT != null) { // 如果有小于区域
            sT.next = eH;
            eT = eT == null ? sT : eT; // 下一步，谁去连大于区域的头，谁就变成eT
        }
        // 下一步，一定是需要用eT 去接 大于区域的头
        // 有等于区域，eT -> 等于区域的尾结点
        // 无等于区域，eT -> 小于区域的尾结点
        // eT 尽量不为空的尾巴节点
        if (eT != null) { // 如果小于区域和等于区域，不是都没有
            eT.next = mH;
        }
        return sH != null ? sH : (eH != null ? eH : mH);
    }

    public static void printLinkedList(ListNode node) {
        System.out.print("Linked List: ");
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 1000000; i++) {
            int randomX = new Random().nextInt(100);
            int randomLen = new Random().nextInt(100);
            ListNode head1 = new ListNode(new Random().nextInt(100));
            ListNode cur = head1;
            for (int j = 0; j < randomLen; j++) {
                cur.next = new ListNode(new Random().nextInt(100));
                cur = cur.next;
            }
            ListNode head2 = new ListNode(head1.val);
            ListNode node2 = head2;
            ListNode node1 = head1;
            while (node1.next != null) {
                node2.next = new ListNode(node1.next.val);
                node2 = node2.next;
                node1 = node1.next;
            }

//            printLinkedList(head1);
//            printLinkedList(head2);

            head1 = listPartition2(head1, randomX);
            head2 = partition(head2, randomX);

            node1 = head1;
            node2 = head2;
            while (node1 != null) {
                if (node1.val != node2.val) {
                    System.out.println("oops");
                    break;
                }
                node1 = node1.next;
                node2 = node2.next;
            }
//            printLinkedList(head1);
//            printLinkedList(head2);
        }
    }
}
