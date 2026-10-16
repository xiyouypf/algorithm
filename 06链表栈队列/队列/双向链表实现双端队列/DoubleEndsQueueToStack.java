package 队列.双向链表实现双端队列;

public class DoubleEndsQueueToStack {
    public static class Node<T> {
        public T value;
        public Node<T> last;
        public Node<T> next;

        public Node(T data) {
            value = data;
        }
    }

    public static class DoubleEndsQueue<T> {
        public Node<T> head;
        public Node<T> tail;
        public void addFromHead(T value) {
            Node<T> node = new Node<>(value);
            if (head == null) {
                head = node;
                tail = node;
            } else {
                node.next = head;
                head.last = node;
                head = node;
            }
        }
        public void addFromBottom(T value) {
            Node<T> node = new Node<>(value);
            if (tail == null) {
                head = node;
                tail = node;
            } else {
                node.last = tail;
                tail.next = node;
                tail = node;
            }
        }
        public T popFromHead() {
            if (head == null) {
                return null;
            }
            Node<T> popNode = head;
            head = head.next;
            popNode.next = null;
            if (head != null) {
                head.last = null;
            } else {
                tail = null;
            }
            return popNode.value;
        }
        public T popFromBottom() {
            if (tail == null) {
                return null;
            }
            Node<T> popNode = tail;
            tail = tail.last;
            popNode.last = null;
            if (tail != null) {
                tail.next = null;
            } else {
                head = null;
            }
            return popNode.value;
        }
        public boolean isEmpty() {
            return head == null;
        }
    }
}
