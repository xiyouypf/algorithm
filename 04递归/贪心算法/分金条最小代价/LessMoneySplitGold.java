package 贪心算法.分金条最小代价;

import java.util.PriorityQueue;

public class LessMoneySplitGold {
    public static int lessMoney1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return process(arr);
    }

    private static int process(int[] arr) {
        if (arr.length == 1) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                int next = process(copyAndMergeTwo(arr, i, j));
                ans = Math.min(ans, arr[i] + arr[j] + next);
            }
        }
        return ans;
    }

    public static int[] copyAndMergeTwo(int[] arr, int i, int j) {
        int[] ans = new int[arr.length - 1];
        int k = 0;
        for (int idx = 0; idx < arr.length; idx++) {
            if (idx == i || idx == j) {
                continue;
            }
            ans[k++] = arr[idx];
        }
        ans[k] = arr[i] + arr[j];
        return ans;
    }

    public static int lessMoney2(int[] arr) {
        PriorityQueue<Integer> minQueue = new PriorityQueue<>();
        for (int num : arr) {
            minQueue.add(num);
        }
        int ans = 0;
        while (minQueue.size() > 1) {
            int temp = minQueue.poll() + minQueue.poll();
            ans += temp;
            minQueue.add(temp);
        }
        return ans;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    public static void main(String[] args) {
        int testTime = 100000;
        int maxSize = 6;
        int maxValue = 1000;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            if (lessMoney1(arr) != lessMoney2(arr)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
