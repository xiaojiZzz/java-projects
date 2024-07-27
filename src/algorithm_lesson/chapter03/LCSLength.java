package algorithm_lesson.chapter03;

@SuppressWarnings({"all"})
//最长公共子序列
public class LCSLength {
    public static char[] x = {'a', 'b', 'c', 'd', 'e', 'f'};
    public static char[] y = {'b', 'c', 'f', 'g', 'd', 'e'};
    public static int[][] c = new int[6][6];
    public static int[][] b = new int[6][6];

    public static void lcsLength(int m, int n, char[] x, char[] y, int[][] c, int[][] b) {
        for (int i = 0; i < m; i++) {
            c[i][0] = 0;
        }
        for (int i = 0; i < n; i++) {
            c[0][i] = 0;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (x[i] == y[j]) {
                    c[i][j] = c[i - 1][j - 1] + 1;
                    b[i][j] = 1;
                } else if (c[i - 1][j] > c[i][j - 1]) {
                    c[i][j] = c[i - 1][j];
                    b[i][j] = 2;
                } else {
                    c[i][j] = c[i][j - 1];
                    b[i][j] = 3;
                }
            }
        }
    }

    public static void lcs(int i, int j, char[] x, int[][] b) {
        if (i == 0 || j == 0) {
            return;
        }
        if (b[i][j] == 1) {
            lcs(i - 1, j - 1, x, b);
            System.out.print(x[i]);
        }
        if (b[i][j] == 2) {
            lcs(i - 1, j, x, b);
        } else {
            lcs(i, j - 1, x, b);
        }
    }

    public static void main(String[] args) {
        lcsLength(6, 6, x, y, c, b);
        lcs(5, 5, x, b);
    }
}
