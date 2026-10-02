package 范围上.最长回文子序列;

// 测试链接：https://leetcode.com/problems/longest-palindromic-subsequence/
public class PalindromeSubsequence {
    public static int longestPalindromeSubseq1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] chars = s.toCharArray();
        return process1(chars, 0, chars.length - 1);
    }

    private static int process1(char[] chars, int L, int R) {
        if (L == R) {
            return 1;
        }
        if (L + 1 == R) {
            return chars[L] == chars[R] ? 2 : 1;
        }
        int ans1 = process1(chars, L + 1, R);
        int ans2 = process1(chars, L, R - 1);
        int ans3 = chars[L] == chars[R] ? 2 + process1(chars, L + 1, R - 1) : 0;
        return Math.max(ans1, Math.max(ans2, ans3));
    }

    public static int longestPalindromeSubseq2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int n = chars.length;
        int[][] dp = new int[n][n];
        dp[n - 1][n - 1] = 1;
        for (int L = 0; L <= n - 2; L++) {
            dp[L][L] = 1;
            dp[L][L + 1] = chars[L] == chars[L + 1] ? 2 : 1;
        }
        for (int L = n - 3; L >= 0; L--) {
            for (int R = L + 2; R < n; R++) {
                int ans = Math.max(dp[L + 1][R], dp[L][R - 1]);
                if (chars[L] == chars[R]) {
                    ans = Math.max(ans, 2 + dp[L + 1][R - 1]);
                }
                dp[L][R] = ans;
            }
        }
        for (int L = 0; L < dp.length; L++) {
            for (int R = 0; R < dp[0].length; R++) {
                System.out.print(dp[L][R] + " ");
            }
            System.out.println();
        }
        return dp[0][n - 1];
    }

    public static void main(String[] args) {
        String s = "bbbab";
        int ans1 = longestPalindromeSubseq1(s);
        int ans2 = longestPalindromeSubseq2(s);
        System.out.println(ans1);
        System.out.println(ans2);
    }
}