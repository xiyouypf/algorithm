package 从左到右.枚举行为.每种货币有限张;

import java.util.HashMap;
import java.util.Map;

public class CoinsWaySameValueSamePapper {
    public static int coinsWay1(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        Info info = Info.getInfo(arr);
        return process1(info.coins, info.zhangs, 0, aim);
    }

    private static int process1(int[] coins, int[] zhangs, int idx, int rest) {
        if (idx == coins.length) {
            return rest == 0 ? 1 : 0;
        }
        int ways = 0;
        for (int zhang = 0; zhang <= zhangs[idx] && zhang * coins[idx] <= rest; zhang++) {
            ways += process1(coins, zhangs, idx + 1, rest - zhang * coins[idx]);
        }
        return ways;
    }

    public static int coinsWay2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        Info info = Info.getInfo(arr);
        int[] coins = info.coins;
        int[] zhangs = info.zhangs;
        int[][] dp = new int[coins.length + 1][aim + 1];
        dp[coins.length][0] = 1;
        for (int idx = coins.length - 1; idx >= 0; idx--) {
            for (int rest = 0; rest <= aim; rest++) {
                int ways = 0;
                for (int zhang = 0; zhang <= zhangs[idx] && zhang * coins[idx] <= rest; zhang++) {
                    ways += rest - zhang * coins[idx] >= 0 ? dp[idx + 1][rest - zhang * coins[idx]] : 0;
                }
                dp[idx][rest] = ways;
            }
        }
        return dp[0][aim];
    }

    public static class Info {
        public int[] coins;
        public int[] zhangs;

        public Info(int[] coins, int[] zhangs) {
            this.coins = coins;
            this.zhangs = zhangs;
        }

        public static Info getInfo(int[] arr) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int coin : arr) {
                map.put(coin, map.getOrDefault(coin, 0) + 1);
            }
            int[] coins = new int[map.size()];
            int[] zhangs = new int[map.size()];
            int idx = 0;
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                coins[idx] = entry.getKey();
                zhangs[idx++] = entry.getValue();
            }
            return new Info(coins, zhangs);
        }
    }

    public static int dp2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        Info info = Info.getInfo(arr);
        int[] coins = info.coins;
        int[] zhangs = info.zhangs;
        int N = coins.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[index][rest] = dp[index + 1][rest];
                if (rest - coins[index] >= 0) {
                    dp[index][rest] += dp[index][rest - coins[index]];
                }
                if (rest - coins[index] * (zhangs[index] + 1) >= 0) {
                    dp[index][rest] -= dp[index + 1][rest - coins[index] * (zhangs[index] + 1)];
                }
            }
        }
        return dp[0][aim];
    }

    // 为了测试
    public static int[] randomArray(int maxLen, int maxValue) {
        int N = (int) (Math.random() * maxLen);
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = (int) (Math.random() * maxValue) + 1;
        }
        return arr;
    }

    // 为了测试
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // 为了测试
    public static void main(String[] args) {
        int maxLen = 10;
        int maxValue = 20;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(maxLen, maxValue);
            int aim = (int) (Math.random() * maxValue);
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

}
