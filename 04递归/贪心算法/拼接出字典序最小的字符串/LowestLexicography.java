package 贪心算法.拼接出字典序最小的字符串;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

public class LowestLexicography {
    public static String lowestString1(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        TreeSet<String> ans = new TreeSet<>();
        process(strs, ans, "");
        return ans.isEmpty() ? "" : ans.first();
    }

    private static void process(String[] strs, TreeSet<String> ans, String path) {
        if (strs.length == 0) {
            ans.add(path);
            return;
        }
        for (int i = 0; i < strs.length; i++) {
            process(removeIndexString(strs, i), ans, path + strs[i]);
        }
    }

    private static String[] removeIndexString(String[] arr, int idx) {
        String[] ans = new String[arr.length - 1];
        int k = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i == idx) {
                continue;
            }
            ans[k++] = arr[i];
        }
        return ans;
    }

    public static String lowestString2(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        Arrays.sort(strs, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o1 + o2).compareTo(o2 + o1);
            }
        });
        String ans = "";
        for (String str : strs) {
            ans += str;
        }
        return ans;
    }

    // for test
    public static String generateRandomString(int strLen) {
        char[] ans = new char[(int) (Math.random() * strLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            int value = (int) (Math.random() * 5);
            ans[i] = (Math.random() <= 0.5) ? (char) (65 + value) : (char) (97 + value);
        }
        return String.valueOf(ans);
    }

    // for test
    public static String[] generateRandomStringArray(int arrLen, int strLen) {
        String[] ans = new String[(int) (Math.random() * arrLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = generateRandomString(strLen);
        }
        return ans;
    }

    // for test
    public static String[] copyStringArray(String[] arr) {
        String[] ans = new String[arr.length];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = String.valueOf(arr[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        int arrLen = 6;
        int strLen = 5;
        int testTimes = 10000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            String[] arr1 = generateRandomStringArray(arrLen, strLen);
            String[] arr2 = copyStringArray(arr1);
            if (!lowestString1(arr1).equals(lowestString2(arr2))) {
                for (String str : arr1) {
                    System.out.print(str + ",");
                }
                System.out.println();
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
