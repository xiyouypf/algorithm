package 贴纸拼词;

import java.util.HashMap;
import java.util.Map;

/**
 * # 题目描述
 * leetcode 691. 贴纸拼词
 * https://leetcode.cn/problems/stickers-to-spell-word/?utm_source=LCUS&utm_medium=ip_redirect&utm_campaign=transfer2china
 * 给定一个字符串str，给定一个字符串类型的数组arr，出现的字符都是小写英文
 * arr每一个字符串，代表一张贴纸，你可以把单个字符剪开使用，目的是拼出str来
 * 返回需要至少多少张贴纸可以完成这个任务。
 * 例子：target= "babac"，stickers = {"ba","c","abcd"}
 * ba + ba + c  3  abcd + abcd 2  abcd+ba 2
 * 所以返回2
 */
public class StickersToSpellWord {
    public static int minStickers1(String[] stickers, String target) {
        int ans = process1(stickers, target);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private static int process1(String[] stickers, String target) {
        if (target.length() == 0) {
            return 0;
        }
        int next = Integer.MAX_VALUE;
        for (String sticker : stickers) {
            String minus = minus1(target, sticker);
            if (target.length() != minus.length()) {
                next = Math.min(next, process1(stickers, minus));
            }
        }
        return next != Integer.MAX_VALUE ? next + 1 : Integer.MAX_VALUE;
    }

    private static String minus1(String target, String sticker) {
        char[] str1 = target.toCharArray();
        char[] str2 = sticker.toCharArray();

        int[] count = new int[26];
        for (char cha : str1) {
            count[cha - 'a']++;
        }
        for (char cha : str2) {
            count[cha - 'a']--;
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if (count[i] > 0) {
                for (int j = 0; j < count[i]; j++) {
                    builder.append((char) (i + 'a'));
                }
            }
        }
        return builder.toString();
    }

    public static int minStickers2(String[] stickers, String target) {
        Map<String, Integer> map = new HashMap<>();
        int ans = process2(map, stickers, target);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private static int process2(Map<String, Integer> map, String[] stickers, String target) {
        if (map.containsKey(target)) {
            return map.get(target);
        }
        if (target.length() == 0) {
            return 0;
        }
        int next = Integer.MAX_VALUE;
        for (String sticker : stickers) {
            String minus = minus1(target, sticker);
            if (target.length() != minus.length()) {
                next = Math.min(next, process2(map, stickers, minus));
            }
        }
        int ans = next != Integer.MAX_VALUE ? next + 1 : Integer.MAX_VALUE;
        map.put(target, ans);
        return ans;
    }

    public static int minStickers3(String[] stickers, String target) {
        int[][] counts = new int[stickers.length][26];
        for (int i = 0; i < stickers.length; i++) {
            char[] chars = stickers[i].toCharArray();
            for (char cha : chars) {
                counts[i][cha - 'a']++;
            }
        }
        int ans = process3(counts, target);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private static int process3(int[][] stickers, String t) {
        if (t.length() == 0) {
            return 0;
        }
        char[] target = t.toCharArray();
        int[] tCount = new int[26];
        for (char cha : target) {
            tCount[cha - 'a']++;
        }
        int min = Integer.MAX_VALUE;
        for (int[] sticker : stickers) {
            if (sticker[target[0] - 'a'] > 0) {
                String minus = minus3(tCount, sticker);
                if (t.length() != minus.length()) {
                    min = Math.min(min, process3(stickers, minus));
                }
            }
        }
        return min != Integer.MAX_VALUE ? min + 1 : Integer.MAX_VALUE;
    }

    private static String minus3(int[] tCount, int[] sticker) {
        StringBuilder builder = new StringBuilder();
        for (int j = 0; j < 26; j++) {
            if (tCount[j] > 0) {
                int nums = tCount[j] - sticker[j];
                if (nums > 0) {
                    for (int k = 0; k < nums; k++) {
                        builder.append((char) (j + 'a'));
                    }
                }
            }
        }
        return builder.toString();
    }

    public static int minStickers4(String[] stickers, String target) {
        int[][] counts = new int[stickers.length][26];
        for (int i = 0; i < stickers.length; i++) {
            char[] chars = stickers[i].toCharArray();
            for (char cha : chars) {
                counts[i][cha - 'a']++;
            }
        }
        Map<String, Integer> map = new HashMap<>();
        int ans = process4(map, counts, target);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private static int process4(Map<String, Integer> map, int[][] stickers, String t) {
        if (t.length() == 0) {
            return 0;
        }
        if (map.containsKey(t)) {
            return map.get(t);
        }
        char[] target = t.toCharArray();
        int[] tCount = new int[26];
        for (char cha : target) {
            tCount[cha - 'a']++;
        }
        int min = Integer.MAX_VALUE;
        for (int[] sticker : stickers) {
            if (sticker[target[0] - 'a'] > 0) {
                String minus = minus3(tCount, sticker);
                if (t.length() != minus.length()) {
                    min = Math.min(min, process4(map, stickers, minus));
                }
            }
        }
        int ans = min != Integer.MAX_VALUE ? min + 1 : Integer.MAX_VALUE;
        map.put(t, ans);
        return ans;
    }

    public static void main(String[] args) {
        String[] stickers = {"with","example","science"};
//        String[] stickers = {"these", "guess", "about", "garden", "him"};
        String target = "thehat";
//        String target = "atomher";
        System.out.println(minStickers1(stickers, target));
        System.out.println(minStickers2(stickers, target));
        System.out.println(minStickers3(stickers, target));
        System.out.println(minStickers4(stickers, target));
    }
}
