package 寻找业务限制.数组压缩.最小路径和;

/**
 * 力扣第64题《最小路径和》
 * https://leetcode.cn/problems/minimum-path-sum/?utm_source=LCUS&utm_medium=ip_redirect&utm_campaign=transfer2china
 * 给定一个二维数组matrix，一个人必须从左上角出发，最后到达右下角
 * 沿途只可以向下或者向右走，沿途的数字都累加就是距离累加和
 * 返回最小距离累加和
 */
public class MinPathSum {
    public static int minPathSum1(int[][] m) {
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return 0;
        }
        int rowLen = m.length;
        int colLen = m[0].length;
        int[][] dp = new int[rowLen][colLen];
        dp[0][0] = m[0][0];
        for (int col = 1; col < colLen; col++) {
            dp[0][col] = dp[0][col - 1] + m[0][col];
        }
        for (int row = 1; row < rowLen; row++) {
            dp[row][0] = dp[row - 1][0] + m[row][0];
        }
        for (int row = 1; row < rowLen; row++) {
            for (int col = 1; col < colLen; col++) {
                dp[row][col] = Math.min(dp[row - 1][col], dp[row][col - 1]) + m[row][col];
            }
        }
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        return dp[rowLen - 1][colLen - 1];
    }

    public static int minPathSum2(int[][] m) {
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return 0;
        }
        int rowLen = m.length;
        int colLen = m[0].length;
        int[] dp = new int[colLen];
        dp[0] = m[0][0];
        for (int col = 1; col < colLen; col++) {
            dp[col] = dp[col - 1] + m[0][col];
        }
        for (int row = 1; row < rowLen; row++) {
            dp[0] += m[row][0];
            for (int col = 1; col < colLen; col++) {
                dp[col] = Math.min(dp[col - 1], dp[col]) + m[row][col];
            }
        }
        return dp[colLen - 1];
    }

    public static void main(String[] args) {
        int rowSize = 10;
        int colSize = 10;
//        int[][] m = generateRandomMatrix(rowSize, colSize);
        int[][] m = {{1, 3, 1, 3}, {1, 5, 1, 2}, {4, 2, 1, 7}, {2, 1, 6, 2}};
        System.out.println(minPathSum1(m));
        System.out.println(minPathSum2(m));
    }

    public static int[][] generateRandomMatrix(int rowSize, int colSize) {
        if (rowSize < 0 || colSize < 0) {
            return null;
        }
        int[][] result = new int[rowSize][colSize];
        for (int i = 0; i != result.length; i++) {
            for (int j = 0; j != result[0].length; j++) {
                result[i][j] = (int) (Math.random() * 100);
            }
        }
        return result;
    }
}

