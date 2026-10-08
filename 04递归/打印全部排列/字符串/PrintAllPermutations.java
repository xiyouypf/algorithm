package 打印全部排列.字符串;

import java.util.ArrayList;
import java.util.List;

public class PrintAllPermutations {
    public static List<String> permutation1(String s) {
        List<String> ans = new ArrayList<>();
        char[] chars = s.toCharArray();
        process1(ans, chars, 0);
        return ans;
    }

    private static void process1(List<String> ans, char[] chars, int idx) {
        if (idx == chars.length) {
            ans.add(new String(chars));
        } else {
            for (int i = idx; i < chars.length; i++) {
                swap(chars, i, idx);
                process1(ans, chars, idx + 1);
                swap(chars, i, idx);
            }
        }
    }

    public static List<String> permutationNoRepeat(String s) {
        List<String> ans = new ArrayList<>();
        char[] chars = s.toCharArray();
        process2(ans, chars, 0);
        return ans;
    }

    private static void process2(List<String> ans, char[] chars, int idx) {
        if (idx == chars.length) {
            ans.add(new String(chars));
        } else {
            boolean[] visit = new boolean[126];
            for (int i = idx; i < chars.length; i++) {
                if (!visit[chars[i]]) {
                    visit[chars[i]] = true;
                    swap(chars, idx, i);
                    process2(ans, chars, idx + 1);
                    swap(chars, idx, i);
                }
            }
        }
    }

    private static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    public static void main(String[] args) {
        String s = "acc";
        List<String> ans1 = permutation1(s);
        for (String str : ans1) {
            System.out.println(str);
        }
        System.out.println("=======");
        List<String> ans2 = permutationNoRepeat(s);
        for (String str : ans2) {
            System.out.println(str);
        }
//        System.out.println("=======");
//        List<String> ans3 = permutation3(s);
//        for (String str : ans3) {
//            System.out.println(str);
//        }

    }
}
