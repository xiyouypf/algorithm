package 打印全部子序列.字符串;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PrintAllSubSequences {
    public static List<String> subs(String s) {
        char[] chars = s.toCharArray();
        List<String> ans = new ArrayList<>();
        process1(ans, chars, 0, "");
        return ans;
    }

    private static void process1(List<String> ans, char[] chars, int idx, String path) {
        if (idx == chars.length) {
            ans.add(path);
            return;
        }
        process1(ans, chars, idx + 1, path);
        process1(ans, chars, idx + 1, path + chars[idx]);
    }

    public static List<String> subsNoRepeat(String s) {
        char[] chars = s.toCharArray();
        Set<String> ans = new HashSet<>();
        process2(ans, chars, 0, "");
        return new ArrayList<>(ans);
    }

    private static void process2(Set<String> ans, char[] chars, int idx, String path) {
        if (idx == chars.length) {
            ans.add(path);
            return;
        }
        process2(ans, chars, idx + 1, path);
        process2(ans, chars, idx + 1, path + chars[idx]);
    }

    public static void main(String[] args) {
        String test = "acccc";
        List<String> ans1 = subs(test);
        List<String> ans2 = subsNoRepeat(test);

        for (String str : ans1) {
            System.out.println(str);
        }
        System.out.println("=================");
        for (String str : ans2) {
            System.out.println(str);
        }
        System.out.println("=================");

    }
}
