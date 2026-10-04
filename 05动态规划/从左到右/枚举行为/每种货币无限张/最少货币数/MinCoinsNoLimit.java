package 从左到右.枚举行为.每种货币无限张.最少货币数;

/**
 * arr是面值数组，其中的值都是正数且没有重复。再给定一个正数aim。
 * 每个值都认为是一种面值，且认为张数是无限的。
 * 返回组成aim的最少货币数
 */
public class MinCoinsNoLimit {
    public static int minCoins1(int[] arr, int aim) {
        return process1(arr, 0, aim);
    }

    // arr[index...]面值，每种面值张数自由选择，
    // 搞出rest正好这么多钱，返回最小张数
    // 拿Integer.MAX_VALUE标记怎么都搞定不了
    private static int process1(int[] arr, int idx, int rest) {
        if (idx == arr.length) {
            return rest == 0 ? 0 : Integer.MAX_VALUE;
        }
        int ans = Integer.MAX_VALUE;
        for (int zhang = 0; zhang * arr[idx] <= rest; zhang++) {
            int next = process1(arr, idx + 1, rest - zhang * arr[idx]);
            if (next != Integer.MAX_VALUE) {
                ans = Math.min(ans, zhang + next);
            }
        }
        return ans;
    }

    public static int minCoins2(int[] arr, int aim) {
        int[][] dp = new int[arr.length + 1][aim + 1];
        dp[arr.length][0] = 0;
        for (int rest = 1; rest <= aim; rest++) {
            dp[arr.length][rest] = Integer.MAX_VALUE;
        }
        for (int idx = arr.length - 1; idx >= 0; idx--) {
            for (int rest = 0; rest <= aim; rest++) {
                int ans = Integer.MAX_VALUE;
                for (int zhang = 0; zhang * arr[idx] <= rest; zhang++) {
                    int next = dp[idx + 1][rest - zhang * arr[idx]];
                    if (next != Integer.MAX_VALUE) {
                        ans = Math.min(ans, zhang + next);
                    }
                }
                dp[idx][rest] = ans;
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

    // 为了测试
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // 为了测试
    public static void main(String[] args) {
        int maxLen = 20;
        int maxValue = 30;
        int testTime = 300000;
        System.out.println("功能测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * maxLen);
            int[] arr = randomArray(N, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = minCoins1(arr, aim);
            int ans2 = minCoins2(arr, aim);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(aim);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("功能测试结束");
    }
}
