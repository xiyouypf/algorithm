package 斐波那契数列.力扣509斐波那契数;

/**
 * F(0) = 0，F(1) = 1
 * F(n) = F(n - 1) + F(n - 2)，其中 n > 1
 * 给定 n ，请计算 F(n) 。
 */
public class FibonacciProblem {
    public int fib1(int n) {
        if (n <= 1) {
            return n;
        } else if (n == 2) {
            return 1;
        }
        return fib1(n - 1) + fib1(n - 2);
    }

    public int fib2(int n) {
        if (n <= 1) {
            return n;
        } else if (n == 2) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public int fib3(int n) {
        if (n <= 1) {
            return n;
        } else if (n == 2) {
            return 1;
        }
        int pre = 1;
        int cur = 1;
        for (int i = 3; i <= n; i++) {
            int temp = cur;
            cur += pre;
            pre = temp;
        }
        return cur;
    }
}
