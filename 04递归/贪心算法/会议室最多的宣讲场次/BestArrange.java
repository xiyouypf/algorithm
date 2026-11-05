package 贪心算法.会议室最多的宣讲场次;

import java.util.Arrays;
import java.util.Comparator;

public class BestArrange {
    public static class Program {
        public int start;
        public int end;

        public Program(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    // 暴力！所有情况都尝试！
    public static int bestArrange1(Program[] programs) {
        return process(programs, 0);
    }

    private static int process(Program[] programs, int line) {
        if (programs.length == 0) {
            return 0;
        }
        int ans = 0;
        for (int i = 0; i < programs.length; i++) {
            if (programs[i].start >= line) {
                ans = Math.max(ans, 1 + process(copyButExcept(programs, i), programs[i].end));
            }
        }
        return ans;
    }

    private static Program[] copyButExcept(Program[] programs, int idx) {
        Program[] ans = new Program[programs.length - 1];
        int k = 0;
        for (int i = 0; i < programs.length; i++) {
            if (i == idx) {
                continue;
            }
            ans[k++] = programs[i];
        }
        return ans;
    }

    // 会议的开始时间和结束时间，都是数值，不会 < 0
    public static int bestArrange2(Program[] programs) {
        Arrays.sort(programs, new Comparator<Program>() {
            @Override
            public int compare(Program o1, Program o2) {
                return o1.end - o2.end;
            }
        });
        int ans = 0;
        int line = 0;
        for (Program program : programs) {
            if (program.start >= line) {
                ans++;
                line = program.end;
            }
        }
        return ans;
    }

    // for test
    public static Program[] generatePrograms(int programSize, int timeMax) {
        Program[] ans = new Program[(int) (Math.random() * (programSize + 1))];
        for (int i = 0; i < ans.length; i++) {
            int r1 = (int) (Math.random() * (timeMax + 1));
            int r2 = (int) (Math.random() * (timeMax + 1));
            if (r1 == r2) {
                ans[i] = new Program(r1, r1 + 1);
            } else {
                ans[i] = new Program(Math.min(r1, r2), Math.max(r1, r2));
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int programSize = 12;
        int timeMax = 20;
        int timeTimes = 1000000;
        for (int i = 0; i < timeTimes; i++) {
            Program[] programs = generatePrograms(programSize, timeMax);
            if (bestArrange1(programs) != bestArrange2(programs)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
