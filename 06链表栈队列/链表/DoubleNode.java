package 链表;

public class DoubleNode {
    public int val;
    public DoubleNode last;
    public DoubleNode next;
    public DoubleNode(int val) {
        this.val = val;
    }
    public DoubleNode(int val, DoubleNode last, DoubleNode next) {
        this.val = val;
        this.last = last;
        this.next = next;
    }
}
