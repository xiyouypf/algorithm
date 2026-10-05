package N皇后问题;

public class NQueens {
    public static int num1(int n) {
        if (n <= 1) {
            return n;
        }
        int[] record = new int[n];
        return process1(n, record, 0);
    }

    private static int process1(int n, int[] record, int row) {
        if (row == n) {
            return 1;
        }
        int ans = 0;
        for (int col = 0; col < n; col++) {
            if (isValid(record, row, col)) {
                record[row] = col;
                ans += process1(n, record, row + 1);
            }
        }
        return ans;
    }

    public static boolean isValid(int[] record, int i, int j) {
        // 0..i-1
        for (int k = 0; k < i; k++) {
            if (j == record[k] || Math.abs(record[k] - j) == Math.abs(i - k)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int n = 15;
        System.out.println(num1(n));
    }
}
