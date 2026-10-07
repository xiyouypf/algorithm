package 汉诺塔问题;

import java.util.ArrayList;
import java.util.List;

/**
 * 面试题 08.06. 汉诺塔问题
 * https://leetcode.cn/problems/hanota-lcci/?utm_source=LCUS&utm_medium=ip_redirect&utm_campaign=transfer2china
 */
public class Hanoi {
    public static void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
        int n = A.size();
        process(n, A, C, B);
    }

    private static void process(int n, List<Integer> from, List<Integer> to, List<Integer> other) {
        if (n == 1) {
            System.out.println("Move 1 from " + from + " to " + to);
            to.add(from.remove(from.size() - 1));
        } else {
            process(n - 1, from, other, to);
            System.out.println("Move " + n + " from " + from + " to " + to);
            to.add(from.remove(from.size() - 1));
            process(n - 1, other, to, from);
        }
    }

    public static void main(String[] args) {
        List<Integer> A = new ArrayList<>();
        List<Integer> B = new ArrayList<>();
        List<Integer> C = new ArrayList<>();
        A.add(2);
        A.add(1);
        A.add(0);
        hanota(A, B, C);
    }
}
