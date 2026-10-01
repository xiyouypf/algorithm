package 未知.机器人左右走路;

/**
 * 题目描述：
 * 假设有排成一行的N个位置，记为1~N，N 一定大于或等于 2
 * 开始时机器人在其中的start位置上(start 一定是 1~N 中的一个)
 * 如果机器人来到1位置，那么下一步只能往右来到2位置；
 * 如果机器人来到N位置，那么下一步只能往左来到 N-1 位置；
 * 如果机器人来到中间位置，那么下一步可以往左走或者往右走；
 * 规定机器人必须走 K 步，最终能来到aim位置(aim也是1~N中的一个)的方法有多少种
 * 给定四个参数 N、start、K、aim，返回方法数。
 */
public class RobotWalk {
    public static int ways1(int N, int start, int aim, int K) {
        if (N < 2 || start < 1 || start > N || aim < 1 || aim > N || K < 1) {
            return -1;
        }
        return process1(aim, N, start, K);
    }

    private static int process1(int aim, int N, int cur, int rest) {
        if (rest == 0) {
            return cur == aim ? 1 : 0;
        }
        if (cur == 1) {
            return process1(aim, N, 2, rest - 1);
        } else if (cur == N) {
            return process1(aim, N, N - 1, rest - 1);
        } else {
            return process1(aim, N, cur + 1, rest - 1) + process1(aim, N, cur - 1, rest - 1);
        }
    }

    public static int ways2(int N, int start, int aim, int K) {
        if (N < 2 || start < 1 || start > N || aim < 1 || aim > N || K < 1) {
            return -1;
        }
        int[][] dp = new int[N + 1][K + 1];
        dp[aim][0] = 1;
        for (int rest = 1; rest <= K; rest++) {
            dp[1][rest] = dp[2][rest - 1];
            for (int cur = 2; cur <= N - 1; cur++) {
                dp[cur][rest] = dp[cur + 1][rest - 1] + dp[cur - 1][rest - 1];
            }
            dp[N][rest] = dp[N - 1][rest - 1];
        }
        for (int cur = 0; cur <= N; cur++) {
            for (int rest = 0; rest <= K; rest++) {
                System.out.print(dp[cur][rest] + " ");
            }
            System.out.println();
        }
        return dp[start][K];
    }

    public static void main(String[] args) {
        System.out.println(ways1(10, 2, 4, 8));
        System.out.println(ways2(10, 2, 4, 8));
    }
}

