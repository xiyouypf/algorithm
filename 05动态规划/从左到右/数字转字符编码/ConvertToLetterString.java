package 从左到右.数字转字符编码;

public class ConvertToLetterString {
    // str只含有数字字符0~9
    // 返回多少种转化方案
    public static int number1(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        return process1(str.toCharArray(), 0);
    }

    // str[0..i-1]转化无需过问
    // str[i.....]去转化，返回有多少种转化方法
    private static int process1(char[] chars, int idx) {
        if (idx == chars.length) {
            return 1;
        }
        // i没到最后，说明有字符
        if (chars[idx] == '0') { // 之前的决定有问题
            return 0;
        }
        // str[i] != '0'
        // 可能性一，i单转
        int ways = process1(chars, idx + 1);
        if (idx + 1 < chars.length && (chars[idx] - '0') * 10 + chars[idx + 1] - '0' <= 26) {
            // 可能性二：i和i+1一起转
            ways += process1(chars, idx + 2);
        }
        return ways;
    }

    // 从右往左的动态规划
    // dp[i]表示：str[i...]有多少种转化方式
    public static int number2(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] chars = str.toCharArray();
        int[] dp = new int[chars.length + 1];
        dp[chars.length] = 1;
        for (int idx = chars.length - 1; idx >= 0; idx--) {
            // i没到最后，说明有字符
            if (chars[idx] != '0') { // 之前的决定有问题
                // str[i] != '0'
                // 可能性一，i单转
                dp[idx] = dp[idx + 1];
                if (idx + 1 < chars.length && (chars[idx] - '0') * 10 + chars[idx + 1] - '0' <= 26) {
                    // 可能性二：i和i+1一起转
                    dp[idx] += dp[idx + 2];
                }
            }
        }
        for (int idx = 0; idx < dp.length; idx++) {
            System.out.print(dp[idx] + " ");
        }
        System.out.println();
        return dp[0];
    }

    public static void main(String[] args) {
        int N = 30;
        int testTime = 1;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * N);
//            String s = randomString(len);
            String s = "874862051561221";
            int ans1 = number1(s);
            int ans2 = number2(s);
            System.out.println(s);
            System.out.println(ans1);
            System.out.println(ans2);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }

    public static String randomString(int len) {
        char[] str = new char[len];
        for (int i = 0; i < len; i++) {
            str[i] = (char) ((int) (Math.random() * 10) + '0');
        }
        return String.valueOf(str);
    }
}
