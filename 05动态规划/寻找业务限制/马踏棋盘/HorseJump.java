package 寻找业务限制.马踏棋盘;

public class HorseJump {
    // 当前来到的位置是（x,y）
    // 还剩下rest步需要跳
    // 跳完rest步，正好跳到a，b的方法数是多少？
    // 10 * 9
    public static int jump1(int a, int b, int k) {
        return process(0, 0, k, a, b);
    }

    public static int process(int curX, int curY, int rest, int aimX, int aimY) {
        if (curX < 0 || curX > 9 || curY < 0 || curY > 8) {
            return 0;
        }
        if (rest == 0) {
            return curX == aimX && curY == aimY ? 1 : 0;
        }
        int ways = 0;
        ways += process(curX + 2, curY + 1, rest - 1, aimX, aimY);
        ways += process(curX + 1, curY + 2, rest - 1, aimX, aimY);
        ways += process(curX - 1, curY + 2, rest - 1, aimX, aimY);
        ways += process(curX - 2, curY + 1, rest - 1, aimX, aimY);
        ways += process(curX - 2, curY - 1, rest - 1, aimX, aimY);
        ways += process(curX - 1, curY - 2, rest - 1, aimX, aimY);
        ways += process(curX + 1, curY - 2, rest - 1, aimX, aimY);
        ways += process(curX + 2, curY - 1, rest - 1, aimX, aimY);
        return ways;
    }

    public static int jump2(int aimX, int aimY, int k) {
        int[][][] dp = new int[10][9][k + 1];
        dp[aimX][aimY][0] = 1;
        for (int rest = 1; rest <= k; rest++) {
            for (int curX = 0; curX <= 9; curX++) {
                for (int curY = 0; curY <= 8; curY++) {
                    int ways = 0;
                    ways += pick(dp, curX + 2, curY + 1, rest - 1, aimX, aimY);
                    ways += pick(dp, curX + 1, curY + 2, rest - 1, aimX, aimY);
                    ways += pick(dp, curX - 1, curY + 2, rest - 1, aimX, aimY);
                    ways += pick(dp, curX - 2, curY + 1, rest - 1, aimX, aimY);
                    ways += pick(dp, curX - 2, curY - 1, rest - 1, aimX, aimY);
                    ways += pick(dp, curX - 1, curY - 2, rest - 1, aimX, aimY);
                    ways += pick(dp, curX + 1, curY - 2, rest - 1, aimX, aimY);
                    ways += pick(dp, curX + 2, curY - 1, rest - 1, aimX, aimY);
                    dp[curX][curY][rest] = ways;
                }
            }
        }
        return dp[0][0][k];
    }

    private static int pick(int[][][] dp, int curX, int curY, int rest, int aimX, int aimY) {
        if (curX < 0 || curX > 9 || curY < 0 || curY > 8) {
            return 0;
        }
        return dp[curX][curY][rest];
    }

    public static void main(String[] args) {
        int x = 7;
        int y = 7;
        int step = 10;
        System.out.println(jump1(x, y, step));
        System.out.println(jump2(x, y, step));
    }
}
