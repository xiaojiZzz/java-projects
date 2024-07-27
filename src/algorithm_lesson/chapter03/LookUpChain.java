package algorithm_lesson.chapter03;

@SuppressWarnings({"all"})
//动态规划求解矩阵连乘次数最少问题(备忘录方法)
public class LookUpChain {
    public static int[] p = {30, 35, 15, 5, 10, 20, 25};
    public static int n = 6;
    public static int[][] m = new int[n + 1][n + 1];
    public static int[][] s = new int[n + 1][n + 1];

    public static int lookUpChain(int i, int j) {
        if (m[i][j] > 0) {
            return m[i][j];
        }
        if (i == j) {
            return 0;
        }
        m[i][j] = lookUpChain(i, i) + lookUpChain(i + 1, j) + p[i - 1] * p[i] * p[j];
        s[i][j] = i;
        for (int k = i + 1; k < j; k++) {
            int t = lookUpChain(i, k) + lookUpChain(k + 1, j) + p[i - 1] * p[k] * p[j];
            if (t < m[i][j]) {
                m[i][j] = t;
                s[i][j] = k;
            }
        }
        return m[i][j];
    }

    public static void main(String[] args) {
        lookUpChain(1, 6);
        System.out.println(m[3][6]);
    }
}
