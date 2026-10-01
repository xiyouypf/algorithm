package 从左到右.背包问题;

public class Knapsack {
    // 所有的货，重量和价值，都在w和v数组里
    // 为了方便，其中没有负数
    // bag背包容量，不能超过这个载重
    // 返回：不超重的情况下，能够得到的最大价值
    public static int maxValue1(int[] w, int[] v, int bag) {
        if (w == null || v == null || w.length != v.length || w.length == 0) {
            return 0;
        }
        return process1(w, v, 0, bag);
    }

    private static int process1(int[] w, int[] v, int idx, int rest) {
        if (idx == w.length) {
            return 0;
        }
        int no = process1(w, v, idx + 1, rest);
        int yes = -1;
        if (rest - w[idx] >= 0) {
            yes = v[idx] + process1(w, v, idx + 1, rest - w[idx]);
        }
        return Math.max(yes, no);
    }

    public static int maxValue2(int[] w, int[] v, int bag) {
        if (w == null || v == null || w.length != v.length || w.length == 0) {
            return 0;
        }
        int[][] dp = new int[w.length + 1][bag + 1];
        for (int idx = w.length - 1; idx >= 0; idx--) {
            for (int rest = 0; rest <= bag; rest++) {
                int no = dp[idx + 1][rest];
                int yes = -1;
                if (rest - w[idx] >= 0) {
                    yes = v[idx] + dp[idx + 1][rest - w[idx]];
                }
                dp[idx][rest] = Math.max(yes, no);
            }
        }
        return dp[0][bag];
    }

    public static void main(String[] args) {
        int[] weights = {3, 2, 4, 7, 3, 1, 7};
        int[] values = {5, 6, 3, 19, 12, 4, 2};
        int bag = 15;
        System.out.println(maxValue1(weights, values, bag));
        System.out.println(maxValue2(weights, values, bag));
    }
}
