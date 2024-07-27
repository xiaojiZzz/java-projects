package algorithm_lesson.chapter03;

@SuppressWarnings({"all"})
//动态规划求解矩阵连乘次数最少问题
public class MatrixChain {
    public static int[] p = {30, 35, 15, 5, 10, 20, 25};
    public static int n = 6;
    public static int[][] m = new int[n + 1][n + 1];
    public static int[][] s = new int[n + 1][n + 1];

    public static void matrixChain(int[] p, int n, int[][] m, int[][] s) {
        for (int i = 1; i <= n; i++) {
            m[i][i] = 0;
        }
        for (int r = 2; r <= n; r++) {
            for (int i = 1; i <= n - r + 1; i++) {
                int j = i + r - 1;
                m[i][j] = m[i][i] + m[i + 1][j] + p[i - 1] * p[i] * p[j];
                s[i][j] = i;
                for (int k = i + 1; k < j; k++) {
                    int t = m[i][k] + m[k + 1][j] + p[i - 1] * p[k] * p[j];
                    if (t < m[i][j]) {
                        m[i][j] = t;
                        s[i][j] = k;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        matrixChain(p, n, m, s);
        System.out.println(m[3][6]);
    }
}
