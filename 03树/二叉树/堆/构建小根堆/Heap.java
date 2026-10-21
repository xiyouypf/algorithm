package 二叉树.堆.构建小根堆;

import java.util.PriorityQueue;

public class Heap {
    public static class MyMinHeap {
        private int limit;
        private int heapSize;
        private int[] arr;

        public MyMinHeap(int limit) {
            this.limit = limit;
            arr = new int[limit];
            heapSize = 0;
        }

        public boolean isEmpty() {
            return heapSize == 0;
        }

        public boolean isFull() {
            return heapSize == limit;
        }

        public void push(int value) {
            arr[heapSize] = value;
            heapInsert(arr, heapSize++);
        }

        // 用户此时，让你返回最小值，并且在小根堆中，把最小值删掉
        // 剩下的数，依然保持小根堆组织
        public int pop() {
            int ans = arr[0];
            swap(arr, 0, heapSize - 1);
            heapify(arr, 0, --heapSize);
            return ans;
        }

        // 新加进来的数，现在停在了cur位置，请依次往上移动，
        // 移动到0位置，或者干不掉自己的父亲了，停！
        private void heapInsert(int[] arr, int cur) {
            while (arr[cur] < arr[(cur - 1) / 2]) {
                swap(arr, cur, (cur - 1) / 2);
                cur = (cur - 1) / 2;
            }
        }

        // 从cur位置，往下看，不断的下沉
        // 停：较小的孩子都不再比cur位置的数小；已经没孩子了
        private void heapify(int[] arr, int cur, int heapSize) {
            while (cur < heapSize) {
                int minIdx = cur;
                if (2 * cur + 1 < heapSize && arr[2 * cur + 1] < arr[minIdx]) {
                    minIdx = 2 * cur + 1;
                }
                if (2 * cur + 2 < heapSize && arr[2 * cur + 2] < arr[minIdx]) {
                    minIdx = 2 * cur + 2;
                }
                if (minIdx == cur) {
                    break;
                }
                swap(arr, cur, minIdx);
                cur = minIdx;
            }
        }

        private void swap(int[] arr, int i, int j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    public static void main(String[] args) {
        int value = 1000;
        int limit = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            int curLimit = (int) (Math.random() * limit) + 1;
            MyMinHeap my = new MyMinHeap(curLimit);
            PriorityQueue<Integer> test = new PriorityQueue<>(curLimit);
            int curOpTimes = (int) (Math.random() * limit);
            for (int j = 0; j < curOpTimes; j++) {
                if (my.isEmpty() != test.isEmpty()) {
                    System.out.println("Oops!");
                }
                if (my.isEmpty()) {
                    int curValue = (int) (Math.random() * value);
                    my.push(curValue);
                    test.add(curValue);
                } else if (my.isFull()) {
                    if (my.pop() != test.poll()) {
                        System.out.println("Oops!");
                    }
                } else {
                    if (Math.random() < 0.5) {
                        int curValue = (int) (Math.random() * value);
                        my.push(curValue);
                        test.add(curValue);
                    } else {
                        if (my.pop() != test.poll()) {
                            System.out.println("Oops!");
                        }
                    }
                }
            }
        }
        System.out.println("finish!");
    }
}
