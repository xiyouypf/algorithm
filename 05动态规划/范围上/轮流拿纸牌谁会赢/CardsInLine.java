package 范围上.轮流拿纸牌谁会赢;

/**
 * 给定一个整型数组arr，代表数值不同的纸牌排成一条线
 * 玩家A和玩家B依次拿走每张纸牌，规定玩家A先拿，玩家B后拿，但是每个玩家每次只能拿走最左或最右的纸牌
 * 玩家A和玩家B都绝顶聪明，请返回最后获胜者的分数。
 */
public class CardsInLine {

    /**
     * 返回获胜者的分数
     */
    public static int win1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int f = f1(arr, 0, arr.length - 1);
        int g = g1(arr, 0, arr.length - 1);
        return Math.max(f, g);
    }

    /**
     * arr[L..R]，先手获得的最好分数返回
     */
    private static int f1(int[] arr, int L, int R) {
        if (L == R) {
            return arr[L];
        }
        int p1 = arr[L] + g1(arr, L + 1, R);
        int p2 = arr[R] + g1(arr, L, R - 1);
        return Math.max(p1, p2);
    }

    /**
     * arr[L..R]，后手获得的最好分数返回
     */
    private static int g1(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }
        int p1 = f1(arr, L + 1, R); // 对手拿走了L位置的数
        int p2 = f1(arr, L, R - 1); // 对手拿走了R位置的数
        // 对手会留一个最小的
        return Math.min(p1, p2);
    }

    public static int win2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[][] f2 = new int[arr.length][arr.length];
        int[][] g2 = new int[arr.length][arr.length];

        for (int L = 0; L < arr.length; L++) {
            f2[L][L] = arr[L];
            g2[L][L] = 0;
        }

        for (int L = arr.length - 2; L >= 0; L--) {
            for (int R = L + 1; R <= arr.length - 1; R++) {
                f2[L][R] = Math.max(arr[L] + g2[L + 1][R], arr[R] + g2[L][R - 1]);
                g2[L][R] = Math.min(f2[L + 1][R], f2[L][R - 1]);
            }
        }

        for (int L = 0; L < arr.length; L++) {
            for (int R = 0; R < arr.length; R++) {
                System.out.print(f2[L][R] + " ");
            }
            System.out.println();
        }

        System.out.println("-----------------");

        for (int L = 0; L < arr.length; L++) {
            for (int R = 0; R < arr.length; R++) {
                System.out.print(g2[L][R] + " ");
            }
            System.out.println();
        }

        int f = f2[0][arr.length - 1];
        int g = g2[0][arr.length - 1];
        return Math.max(f, g);
    }

    public static void main(String[] args) {
        int[] arr = {5, 7, 4, 5, 8, 1, 6, 0, 3, 4, 6, 1, 7};
        System.out.println(win1(arr));
        System.out.println(win2(arr));
    }
}
