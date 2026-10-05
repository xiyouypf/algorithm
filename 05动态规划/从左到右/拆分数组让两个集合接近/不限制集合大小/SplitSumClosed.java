package 从左到右.拆分数组让两个集合接近.不限制集合大小;

/**
 * 给定一个正数数组arr，
 * 请把arr中所有的数分成两个集合，尽量让两个集合的累加和接近
 * 返回：
 * 最接近的情况下，较小集合的累加和
 */
public class SplitSumClosed {
    public static int right1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        return process1(arr, 0, sum / 2);
    }

    private static int process1(int[] arr, int idx, int rest) {
        if (idx == arr.length) {
            return 0;
        }
        int no = process1(arr, idx + 1, rest);
        int yes = 0;
        if (arr[idx] <= rest) {
            yes = arr[idx] + process1(arr, idx + 1, rest - arr[idx]);
        }
        return Math.max(no, yes);
    }

    public static int right2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        int total = sum / 2;
        int[][] dp = new int[arr.length + 1][total + 1];
        for (int idx = arr.length - 1; idx >= 0; idx--) {
            for (int rest = 0; rest <= total; rest++) {
                int no = dp[idx + 1][rest];
                int yes = 0;
                if (arr[idx] <= rest) {
                    yes = arr[idx] + dp[idx + 1][rest - arr[idx]];
                }
                dp[idx][rest] = Math.max(no, yes);
            }
        }
        for (int idx = 0; idx <= arr.length; idx++) {
            for (int rest = 0; rest <= total; rest++) {
                System.out.print(dp[idx][rest] + " ");
                if (rest % 5 == 4) {
                    System.out.print("    ");
                }
            }
            System.out.println();
            if (idx % 4 == 3) {
                System.out.println();
            }
        }
        return dp[0][sum / 2];
    }

    public static int[] randomArray(int len, int value) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * value);
        }
        return arr;
    }

    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int maxLen = 20;
        int maxValue = 50;
        int testTime = 1;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
//            int len = (int) (Math.random() * maxLen);
//            int[] arr = randomArray(len, maxValue);
            int[] arr = {3, 2, 4, 7, 3, 1, 7};
            int ans1 = right1(arr);
            int ans2 = right2(arr);
            System.out.println(ans2);
            if (ans1 != ans2) {
                printArray(arr);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }
}
