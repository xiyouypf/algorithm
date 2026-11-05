package 贪心算法.点亮所有居民点的最少灯数;

import java.util.HashSet;
import java.util.Set;

public class Light {
    public static int minLight1(String road) {
        if (road == null || road.length() == 0) {
            return 0;
        }
        return process(road.toCharArray(), 0, new HashSet<>());
    }

    private static int process(char[] chars, int idx, Set<Integer> set) {
        if (idx == chars.length) {
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] == '.' && !set.contains(i - 1) && !set.contains(i) && !set.contains(i + 1)) {
                    return Integer.MAX_VALUE;
                }
            }
            return set.size();
        } else {
            int no = process(chars, idx + 1, set);
            int yes = Integer.MAX_VALUE;
            if (chars[idx] == '.') {
                set.add(idx);
                yes = process(chars, idx + 1, set);
                set.remove(idx);
            }
            return Math.min(no, yes);
        }
    }

    public static int minLight2(String road) {
        char[] chars = road.toCharArray();
        int i = 0;
        int ans = 0;
        while (i < chars.length) {
            // i位置是X，不需要放灯
            if (chars[i] == 'X') {
                i++;
            } else {
                // i位置是.，i+1位置为X，放一盏灯，i来到i+2位置
                if (i + 1 < chars.length && chars[i + 1] == 'X') {
                    ans++;
                    i += 2;
                // i位置是.，i+1位置为.，i+2位置为X，放一盏灯，i来到i+3位置
                // i位置是.，i+1位置为.，i+2位置为.，放一盏灯，i来到i+3位置
                } else {
                    i += 3;
                    ans++;
                }
            }
        }
        return ans;
    }

    // 更简洁的解法
    // 两个X之间，数一下.的数量，然后除以3，向上取整
    // 把灯数累加
    public static int minLight3(String road) {
        char[] str = road.toCharArray();
        int cur = 0;
        int light = 0;
        for (char c : str) {
            if (c == 'X') {
                light += (cur + 2) / 3;
                cur = 0;
            } else {
                cur++;
            }
        }
        light += (cur + 2) / 3;
        return light;
    }

    // for test
    public static String randomString(int len) {
        char[] res = new char[(int) (Math.random() * len) + 1];
        for (int i = 0; i < res.length; i++) {
            res[i] = Math.random() < 0.5 ? 'X' : '.';
        }
        return String.valueOf(res);
    }

    public static void main(String[] args) {
        int len = 20;
        int testTime = 100000;
        for (int i = 0; i < testTime; i++) {
            String test = randomString(len);
            int ans1 = minLight1(test);
            int ans2 = minLight2(test);
            int ans3 = minLight3(test);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("oops!");
            }
        }
        System.out.println("finish!");
    }
}
