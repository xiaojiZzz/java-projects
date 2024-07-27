package algorithm_lesson.chapter03;

@SuppressWarnings({"all"})
//算法实现题3-3 石子合并问题
public class Realization3_3 {
    public static int n = 4; //石子的堆数
    public static int[] a = {0, 4, 4, 5, 9, 4, 4, 5, 9}; //每堆石子的数量 * 2
    public static int[] sum = new int[2 * n + 1];
    public static int[][] max = new int[2 * n + 1][2 * n + 1];
    public static int[][] min = new int[2 * n + 1][2 * n + 1];

    public static void stoneMerge(int n, int[] a, int[] sum, int[][] max, int[][] min) {
        for (int i = 0; i < 2 * n + 1; i++) {
            for (int j = 0; j < 2 * n + 1; j++) {
                min[i][j] = 2000000000;
            }
        }
        for (int i = 1; i <= 2 * n; i++) {
            min[i][i] = 0;
            max[i][i] = 0;
            sum[i] = sum[i - 1] + a[i];
        }
        for (int i = 2; i <= n; i++) {
            for (int j = 1; i + j - 1 <= 2 * n; j++) {
                int r = i + j - 1;
                for (int k = j; k < r; k++) {
                    min[j][r] = Math.min(min[j][r], min[j][k] + min[k + 1][r] + sum[r] - sum[j - 1]);
                    max[j][r] = Math.max(max[j][r], max[j][k] + max[k + 1][r] + sum[r] - sum[j - 1]);
                }
            }
        }
        int minRes = 2000000000;
        int maxRes = 0;
        for (int i = 1; i <= n; i++) {
            minRes = Math.min(minRes, min[i][i + n - 1]);
            maxRes = Math.max(maxRes, max[i][i + n - 1]);
        }
        System.out.println("最小得分：" + minRes);
        System.out.println("最大得分：" + maxRes);
    }

    public static void main(String[] args) {
        stoneMerge(n, a, sum, max, min);
    }
}
