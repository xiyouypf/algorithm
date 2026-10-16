package 队列.用数组实现队列;

public class MyCircularQueue {
    private int[] arr;
    private int size;
    private int head;
    private int tail;

    // MyCircularQueue(k): 构造器，设置队列长度为 k 。
    public MyCircularQueue(int k) {
        arr = new int[k];
        size = 0;
        head = -1;
        tail = -1;
    }

    // enQueue(value): 向循环队列插入一个元素。如果成功插入则返回真。
    public boolean enQueue(int value) {
        if (size == arr.length) {
            return false;
        }
        tail = (tail + 1) % arr.length;
        arr[tail] = value;
        size++;
        return true;
    }

    // deQueue(): 从循环队列中删除一个元素。如果成功删除则返回真。
    public boolean deQueue() {
        if (size == 0) {
            return false;
        }
        head = (head + 1) % arr.length;
        size--;
        return true;
    }

    // Front: 从队首获取元素。如果队列为空，返回 -1 。
    public int Front() {
        if (size == 0) {
            return -1;
        }
        return arr[head];
    }

    // Rear: 获取队尾元素。如果队列为空，返回 -1 。
    public int Rear() {
        if (size == 0) {
            return -1;
        }
        return arr[tail];
    }

    // isEmpty(): 检查循环队列是否为空。
    public boolean isEmpty() {
        return size == 0;
    }

    // isFull(): 检查循环队列是否已满。
    public boolean isFull() {
        return size == arr.length;
    }

    public static void main(String[] args) {
        MyCircularQueue circularQueue = new MyCircularQueue(3); // 设置长度为 3
        circularQueue.enQueue(1);  // 返回 true
        circularQueue.enQueue(2);  // 返回 true
        circularQueue.enQueue(3);  // 返回 true
        circularQueue.enQueue(4);  // 返回 false，队列已满
        circularQueue.Rear();  // 返回 3
        circularQueue.isFull();  // 返回 true
        circularQueue.deQueue();  // 返回 true
        circularQueue.enQueue(4);  // 返回 true
        circularQueue.Rear();  // 返回 4
    }
}