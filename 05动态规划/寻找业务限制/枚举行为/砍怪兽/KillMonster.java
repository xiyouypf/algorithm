package 寻找业务限制.枚举行为.砍怪兽;

/**
 * 给定3个参数，N，M，K
 * 怪兽有N滴血，等着英雄来砍自己
 * 英雄每一次打击，都会让怪兽流失[0~M]的血量
 * 到底流失多少？每一次在[0~M]上等概率的获得一个值
 * 求K次打击之后，英雄把怪兽砍死的概率
 */
public class KillMonster {
    public static double right1(int N, int M, int K) {
        if (N < 1 || M < 1 || K < 1) {
            return 0;
        }
        long all = (long) Math.pow(M + 1, K);
        long kill = process1(M, N, K);
        return (double) ((double) kill / (double) all);
    }

    private static long process1(int M, int restHp, int restK) {
        if (restK == 0) {
            return restHp <= 0 ? 1 : 0;
        }
        if (restHp <= 0) {
            return (long) Math.pow(M + 1, restK);
        }
        long ways = 0;
        for (int i = 0; i <= M; i++) {
            ways += process1(M, restHp - i, restK - 1);
        }
        return ways;
    }

    public static double right2(int N, int M, int K) {
        if (N < 1 || M < 1 || K < 1) {
            return 0;
        }
        long[][] dp = new long[N + 1][K + 1];
        dp[0][0] = 1;
        for (int rest = 1; rest <= K; rest++) {
            dp[0][rest] = (long) Math.pow(M + 1, rest);
        }
        for (int rest = 1; rest <= K; rest++) {
            for (int hp = 1; hp <= N; hp++) {
                long ways = 0;
                for (int i = 0; i <= M; i++) {
                    ways += pick(dp, M, hp - i, rest - 1);
                }
                dp[hp][rest] = ways;
            }
        }
        long all = (long) Math.pow(M + 1, K);
        long kill = dp[N][K];
        return (double) ((double) kill / (double) all);
    }

    private static long pick(long[][] dp, int M, int hp, int rest) {
        if (rest == 0) {
            return hp <= 0 ? 1 : 0;
        }
        if (hp <= 0) {
            return (long) Math.pow(M + 1, rest);
        }
        return dp[hp][rest];
    }

    public static void main(String[] args) {
        int NMax = 10;
        int MMax = 10;
        int KMax = 10;
        int testTime = 200;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * NMax);
            int M = (int) (Math.random() * MMax);
            int K = (int) (Math.random() * KMax);
            double ans1 = right1(N, M, K);
            double ans2 = right2(N, M, K);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }
}
