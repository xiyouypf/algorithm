package 从左到右.枚举行为.每种货币无限张.组成方法数;

public class CoinsWayNoLimit {
    public static int coinsWay1(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        return process1(arr, 0, aim);
    }

    // arr[index....] 所有的面值，每一个面值都可以任意选择张数，组成正好rest这么多钱，方法数多少？
    private static int process1(int[] arr, int idx, int rest) {
        if (idx == arr.length) {
            return rest == 0 ? 1 : 0;
        }
        int ways = 0;
        for (int zhang = 0; zhang * arr[idx] <= rest; zhang++) {
            ways += process1(arr, idx + 1, rest - zhang * arr[idx]);
        }
        return ways;
    }

    public static int coinsWay2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int[][] dp = new int[arr.length + 1][aim + 1];
        dp[arr.length][0] = 1;
        for (int idx = arr.length - 1; idx >= 0; idx--) {
            for (int rest = 0; rest <= aim; rest++) {
                int ways = 0;
                for (int zhang = 0; zhang * arr[idx] <= rest; zhang++) {
                    ways += dp[idx + 1][rest - zhang * arr[idx]];
                }
                dp[idx][rest] = ways;
            }
        }

        for (int idx = 0; idx < dp.length; idx++) {
            for (int rest = 0; rest <= aim; rest++) {
                System.out.print(dp[idx][rest] + " ");
                if (rest % 5 == 4) {
                    System.out.print("     ");
                }
            }
            System.out.println();
        }


        return dp[0][aim];
    }

    public static void main(String[] args) {
        int maxLen = 10;
        int maxValue = 30;
        int testTime = 1;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
//            int[] arr = randomArray(maxLen, maxValue);
            int[] arr = {1, 2};
//            int aim = (int) (Math.random() * maxValue);
            int aim = 15;
            int ans1 = coinsWay1(arr, aim);
            int ans2 = coinsWay2(arr, aim);
            int ans3 = dp2(arr, aim);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(aim);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                break;
            }
        }
        System.out.println("测试结束");
    }

    /**
     * 存在枚举行为，可优化
     */
    public static int dp2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[index][rest] = dp[index + 1][rest];
                if (rest - arr[index] >= 0) {
                    dp[index][rest] += dp[index][rest - arr[index]];
                }
            }
        }
        return dp[0][aim];
    }

    public static int[] randomArray(int maxLen, int maxValue) {
        int N = (int) (Math.random() * maxLen);
        int[] arr = new int[N];
        boolean[] has = new boolean[maxValue + 1];
        for (int i = 0; i < N; i++) {
            do {
                arr[i] = (int) (Math.random() * maxValue) + 1;
            } while (has[arr[i]]);
            has[arr[i]] = true;
        }
        return arr;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
