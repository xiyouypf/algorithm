package 找规律.装满N个苹果的最少袋子数量;

public class AppleMinBags {
    public static int minBags1(int apple) {
        if (apple < 0) {
            return -1;
        }
        int[] arr = new int[]{6, 8};
        int ans = process1(arr, 0, apple);
        return ans != Integer.MAX_VALUE ? ans : -1;
    }

    private static int process1(int[] arr, int idx, int rest) {
        if (idx == arr.length) {
            if (rest == 0) {
                return 0;
            }
            return Integer.MAX_VALUE;
        }
        if (rest < 0) {
            return Integer.MAX_VALUE;
        }
        int ans = Integer.MAX_VALUE;
        for (int ge = 0; ge * arr[idx] <= rest; ge++) {
            int next = process1(arr, idx + 1, rest - ge * arr[idx]);
            if (next != Integer.MAX_VALUE) {
                ans = Math.min(ans, next + ge);
            }
        }
        return ans;
    }

    public static int minBags2(int apple) {
        if (apple < 0) {
            return -1;
        }
        int[] arr = new int[]{6, 8};
        int[][] dp = new int[3][apple + 1];
        for (int idx = 0; idx <= 2; idx++) {
            for (int rest = 0; rest <= apple; rest++) {
                dp[idx][rest] = Integer.MAX_VALUE;
            }
        }
        dp[2][0] = 0;
        for (int idx = 1; idx >= 0; idx--) {
            for (int rest = 0; rest <= apple; rest++) {
                int ans = Integer.MAX_VALUE;
                for (int ge = 0; ge * arr[idx] <= rest; ge++) {
                    int next = dp[idx + 1][rest - ge * arr[idx]];
                    if (next != Integer.MAX_VALUE) {
                        ans = Math.min(ans, next + ge);
                    }
                }
                dp[idx][rest] = ans;
            }
        }
        return dp[0][apple] != Integer.MAX_VALUE ? dp[0][apple] : -1;
    }

    public static int minBags3(int apple) {
        if (apple < 0) {
            return -1;
        }
        int bag8 = apple / 8;
        int rest = apple - (bag8 * 8);
        while (bag8 >= 0) {
            if (rest % 6 == 0) {
                return bag8 + rest / 6;
            } else {
                bag8--;
                rest += 8;
            }
        }
        return -1;
    }

    public static int minBags4(int apple) {
        if (apple < 6 || apple % 2 != 0 || apple == 10) {
            return -1;
        }
        if (apple <= 16) {
            return apple / 6;
        }
        if (apple % 8 == 0) {
            return apple / 8;
        } else {
            return apple / 8 + 1;
        }
    }

    public static void main(String[] args) {
        for(int apple = 1; apple < 2000;apple++) {
            if (minBags1(apple) != minBags2(apple)
                || minBags2(apple) != minBags3(apple)
                || minBags3(apple) != minBags4(apple)) {
                System.out.println("oops");
                System.out.println(apple);
            }
            if (apple % 2 == 0) {
//                System.out.println(apple + " : "+ minBags(apple));
            }
        }
    }
}
