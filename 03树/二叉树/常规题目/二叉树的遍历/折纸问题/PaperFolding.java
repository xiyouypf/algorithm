package 二叉树.常规题目.二叉树的遍历.折纸问题;

public class PaperFolding {
    public static void printAllFolds(int N) {
        process(N, 0, true);
    }

    private static void process(int n, int i, boolean down) {
        if (i == n) {
            return;
        }
        process(n, i + 1, true);
        System.out.print(down ? "凹 " : "凸 ");
        process(n, i + 1, false);
    }

    public static void main(String[] args) {
        printAllFolds(4);
    }
}
