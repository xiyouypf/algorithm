package 寻找业务限制.枚举行为.整数分裂方法数;

/**
 * 给定一个正数n，求n的裂开方法数，
 * 规定：后面的数不能比前面的数小
 * 比如4的裂开方法有：
 * 1+1+1+1、1+1+2、1+3、2+2、4
 * 5种，所以返回5
 */
public class SplitNumber {
    // n为正数
    public static int ways(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return process1(1, n);
    }

    // 上一个拆出来的数是pre
    // 还剩rest需要去拆
    // 返回拆解的方法数
    private static int process1(int pre, int rest) {
        if (rest == 0) {
            return 1;
        }
        if (pre > rest) {
            return 0;
        }
        int ways = 0;
        for (int cur = pre; cur <= rest; cur++) {
            ways += process1(cur, rest - cur);
        }
        return ways;
    }

    public static int ways2(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int[][] dp = new int[n + 1][n + 1];
        for (int pre = 1; pre <= n; pre++) {
            dp[pre][0] = 1;
            dp[pre][pre] = 1;
        }
        for (int rest = 1; rest <= n; rest++) {
            for (int pre = n; pre >= 1; pre--) {
                int ways = 0;
                for (int cur = pre; cur <= rest; cur++) {
                    ways += rest - cur >= 0 ? dp[cur][rest - cur] : 0;
                }
                dp[pre][rest] = ways;
            }
        }
        return dp[1][n];
    }

    public static int dp2(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int[][] dp = new int[n + 1][n + 1];
        for (int pre = 1; pre <= n; pre++) {
            dp[pre][0] = 1;
            dp[pre][pre] = 1;
        }
        for (int pre = n - 1; pre >= 1; pre--) {
            for (int rest = pre + 1; rest <= n; rest++) {
                dp[pre][rest] = dp[pre + 1][rest];
                dp[pre][rest] += dp[pre][rest - pre];
            }
        }
        return dp[1][n];
    }

    public static void main(String[] args) {
        int test = 39;
        System.out.println(ways(test));
        System.out.println(ways2(test));
        System.out.println(dp2(test));
    }
}
