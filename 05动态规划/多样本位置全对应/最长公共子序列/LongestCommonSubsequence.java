package 多样本位置全对应.最长公共子序列;

// 这个问题leetcode上可以直接测
// 链接：https://leetcode.com/problems/longest-common-subsequence/
public class LongestCommonSubsequence {
    public static int longestCommonSubsequence1(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) {
            return 0;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        return process1(str1, str2, str1.length - 1, str2.length - 1);
    }

    private static int process1(char[] str1, char[] str2, int idx1, int idx2) {
        if (idx1 == 0 && idx2 == 0) {
            // str1[0..0]和str2[0..0]，都只剩一个字符了
            // 那如果字符相等，公共子序列长度就是1，不相等就是0
            return str1[0] == str2[0] ? 1 : 0;
        } else if (idx1 == 0) {
            // 这里的情况为：
            // str1[0...0]和str2[0...j]，str1只剩1个字符了，但是str2不只一个字符
            // 因为str1只剩一个字符了，所以str1[0...0]和str2[0...j]公共子序列最多长度为1
            // 如果str1[0] == str2[j]，那么此时相等已经找到了！公共子序列长度就是1，也不可能更大了
            // 如果str1[0] != str2[j]，只是此时不相等而已，
            // 那么str2[0...j-1]上有没有字符等于str1[0]呢？不知道，所以递归继续找
            if (str1[0] == str2[idx2]) {
                return 1;
            } else {
                return process1(str1, str2, 0, idx2 - 1);
            }
        } else if (idx2 == 0) {
            // 和上面的else if同理
            // str1[0...i]和str2[0...0]，str2只剩1个字符了，但是str1不只一个字符
            // 因为str2只剩一个字符了，所以str1[0...i]和str2[0...0]公共子序列最多长度为1
            // 如果str1[i] == str2[0]，那么此时相等已经找到了！公共子序列长度就是1，也不可能更大了
            // 如果str1[i] != str2[0]，只是此时不相等而已，
            // 那么str1[0...i-1]上有没有字符等于str2[0]呢？不知道，所以递归继续找
            if (str1[idx1] == str2[0]) {
                return 1;
            } else {
                return process1(str1, str2, idx1 - 1, 0);
            }
        } else {
            // 这里的情况为：
            // str1[0...i]和str2[0...i]，str1和str2都不只一个字符
            // 看函数开始之前的注释部分
            // p1就是可能性c)
            int p1 = process1(str1, str2, idx1 - 1, idx2);
            // p2就是可能性b)
            int p2 = process1(str1, str2, idx1, idx2 - 1);
            // p3就是可能性d)，如果可能性d)存在，即str1[i] == str2[j]，那么p3就求出来，参与pk
            // 如果可能性d)不存在，即str1[i] != str2[j]，那么让p3等于0，然后去参与pk，反正不影响
            int p3 = str1[idx1] == str2[idx2] ? 1 + process1(str1, str2, idx1 - 1, idx2 - 1) : 0;
            return Math.max(p1, Math.max(p2, p3));
        }
    }

    public static int longestCommonSubsequence2(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) {
            return 0;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int[][] dp = new int[str1.length][str2.length];
        dp[0][0] = str1[0] == str2[0] ? 1 : 0;
        for (int idx2 = 1; idx2 < str2.length; idx2++) {
            if (str1[0] == str2[idx2]) {
                dp[0][idx2]= 1;
            } else {
                dp[0][idx2] = dp[0][idx2 - 1];
            }
        }
        for (int idx1 = 1; idx1 < str1.length; idx1++) {
            if (str1[idx1] == str2[0]) {
                dp[idx1][0] = 1;
            } else {
                dp[idx1][0] = dp[idx1 - 1][0];
            }
        }
        for (int idx1 = 1; idx1 < str1.length; idx1++) {
            for (int idx2 = 1; idx2 < str2.length; idx2++) {
                int p1 = dp[idx1 - 1][idx2];
                int p2 = dp[idx1][idx2 - 1];
                int p3 = str1[idx1] == str2[idx2] ? 1 + dp[idx1 - 1][idx2 - 1] : dp[idx1 - 1][idx2 - 1];
                dp[idx1][idx2] = Math.max(p1, Math.max(p2, p3));
            }
        }
        return dp[str1.length - 1][str2.length - 1];
    }

    public static void main(String[] args) {
        String s1 = "a12b3c456d";
        String s2 = "1ef23ghi4j56k";
        int ans1 = longestCommonSubsequence1(s1, s2);
        int ans2 = longestCommonSubsequence2(s1, s2);
        System.out.println(ans1);
        System.out.println(ans2);
    }
}
