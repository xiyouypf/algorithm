package 栈.最小栈;

import java.util.Stack;

public class MinStack {
    private Stack<Integer> stackData;
    private Stack<Integer> stackMin;
    public MinStack() {
        stackData = new Stack<>();
        stackMin = new Stack<>();
    }

    public void push(int value) {
        stackData.push(value);
        if (stackMin.isEmpty()) {
            stackMin.push(value);
        } else {
            int peek = stackMin.peek();
            if (value < peek) {
                stackMin.push(value);
            } else {
                stackMin.push(peek);
            }
        }
    }

    public void pop() {
        stackData.pop();
        stackMin.pop();
    }

    public int top() {
        return stackData.peek();
    }

    public int getMin() {
        return stackMin.peek();
    }
}