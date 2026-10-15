package 栈.用队列实现栈;

import java.util.LinkedList;
import java.util.Queue;

public class MyStack {
    private Queue<Integer> queue1;
    private Queue<Integer> queue2;
    public MyStack() {
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
    }

    public void push(int x) {
        queue1.add(x);
    }

    public int pop() {
        while (queue1.size() > 1) {
            queue2.add(queue1.poll());
        }
        int ans = queue1.poll();
        swapQueue();
        return ans;
    }

    public int top() {
        while (queue1.size() > 1) {
            queue2.add(queue1.poll());
        }
        int ans = queue1.poll();
        queue2.add(ans);
        swapQueue();
        return ans;
    }

    public boolean empty() {
        return queue1.isEmpty() && queue2.isEmpty();
    }

    private void swapQueue() {
        Queue<Integer> temp = queue1;
        queue1 = queue2;
        queue2 = temp;
    }
}