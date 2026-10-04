package 寻找业务限制.枚举行为.醉汉Bob存活概率;

/**
 * 给定5个参数，N，M，row，col，k
 * 表示在N*M的区域上，醉汉Bob初始在(row,col)位置
 * Bob一共要迈出k步，且每步都会等概率向上下左右四个方向走一个单位
 * 任何时候Bob只要离开N*M的区域，就直接死亡
 * 返回k步之后，Bob还在N*M的区域的概率
 */
public class BobDie {
    public static double livePosibility1(int row, int col, int k, int N, int M) {
        return (double) process1(N, M, row, col, k) / Math.pow(4, k);
    }

    private static long process1(int N, int M, int curX, int curY, int rest) {
        if (curX < 0 || curX >= N || curY < 0 || curY >= M) {
            return 0;
        }
        if (rest == 0) {
            return 1;
        }
        long left = process1(N, M, curX - 1, curY, rest - 1);
        long right = process1(N, M, curX + 1, curY, rest - 1);
        long up = process1(N, M, curX, curY - 1, rest - 1);
        long down = process1(N, M, curX, curY + 1, rest - 1);
        return left + right + up + down;
    }

    public static double livePosibility2(int row, int col, int k, int N, int M) {
        long[][][] dp = new long[N][M][k + 1];
        for (int curX = 0; curX < N; curX++) {
            for (int curY = 0; curY < M; curY++) {
                dp[curX][curY][0] = 1;
            }
        }
        for (int rest = 1; rest <= k; rest++) {
            for (int curX = 0; curX < N; curX++) {
                for (int curY = 0; curY < M; curY++) {
                    long left = pick(dp, N, M, curX - 1, curY, rest - 1);
                    long right = pick(dp, N, M, curX + 1, curY, rest - 1);
                    long up = pick(dp, N, M, curX, curY - 1, rest - 1);
                    long down = pick(dp, N, M, curX, curY + 1, rest - 1);
                    dp[curX][curY][rest] = left + right + up + down;
                }
            }
        }
        return (double) dp[row][col][k] / Math.pow(4, k);
    }

    private static long pick(long[][][] dp, int N, int M, int curX, int curY, int rest) {
        if (curX < 0 || curX >= N || curY < 0 || curY >= M) {
            return 0;
        }
        return dp[curX][curY][rest];
    }

    public static void main(String[] args) {
        System.out.println(livePosibility1(6, 6, 10, 50, 50));
        System.out.println(livePosibility2(6, 6, 10, 50, 50));
    }
}
