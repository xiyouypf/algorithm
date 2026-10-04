package 从左到右.每张货币不同;

/**
 * arr是货币数组，其中的值都是正数。再给定一个正数aim。
 * 每个值都认为是一张货币，
 * 即便是值相同的货币也认为每一张都是不同的，
 * 返回组成aim的方法数
 * 例如：arr = {1,1,1}，aim = 2
 * 第0个和第1个能组成2，第1个和第2个能组成2，第0个和第2个能组成2
 * 一共就3种方法，所以返回3
 */
public class CoinsWayEveryPaperDifferent {
    public static int coinWays1(int[] arr, int aim) {
        return process1(arr, 0, aim);
    }

    private static int process1(int[] arr, int idx, int rest) {
        if (rest < 0) {
            return 0;
        }
        if (idx == arr.length) {
            return rest == 0 ? 1 : 0;
        }
        int no = process1(arr, idx + 1, rest);
        int yes = process1(arr, idx + 1, rest - arr[idx]);
        return yes + no;
    }

    public static int coinWays2(int[] arr, int aim) {
        int[][] dp = new int[arr.length + 1][aim + 1];
        dp[arr.length][0] = 1;
        for (int idx = arr.length - 1; idx >= 0; idx--) {
            for (int rest = 0; rest <= aim; rest++) {
                int no = dp[idx + 1][rest];
                int yes = rest - arr[idx] >= 0 ? dp[idx + 1][rest - arr[idx]] : 0;
                dp[idx][rest] = yes + no;
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
        int maxLen = 20;
        int maxValue = 30;
        int testTime = 1;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
//            int[] arr = randomArray(maxLen, maxValue);
            int[] arr = {3, 2, 4, 7, 3, 1, 7};
//            int aim = (int) (Math.random() * maxValue);
            int aim = 15;
            int ans1 = coinWays1(arr, aim);
            int ans2 = coinWays2(arr, aim);
//            if (ans1 != ans2) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(aim);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
//            }
        }
        System.out.println("测试结束");
    }

    public static int[] randomArray(int maxLen, int maxValue) {
        int N = (int) (Math.random() * maxLen);
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = (int) (Math.random() * maxValue) + 1;
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
