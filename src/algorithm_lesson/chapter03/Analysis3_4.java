package algorithm_lesson.chapter03;

@SuppressWarnings({"all"})
//算法分析题3-4 二维0-1背包问题
public class Analysis3_4 {
    public static int[][][] m = new int[6][11][11]; //5个物品，10体积，10重量
    public static int[] w = {0, 2, 2, 6, 5, 4}; //物品重量
    public static int[] b = {0, 2, 2, 6, 5, 4}; //物品体积
    public static int[] v = {0, 6, 3, 5, 4, 6}; //物品价值

    public static void knapsackProblem(int[][][] m, int[] w, int[] b, int[] v) {
        for (int i = 1; i < 6; i++) {
            for (int j = 0; j <=10; j++) {
                for (int k = 0; k <= 10; k++) {
                    if (j >= w[i] && k >= b[i]) {
                        m[i][j][k] = Math.max(m[i - 1][j][k], m[i - 1][j - w[i]][k - b[i]] + v[i]);
                    } else {
                        m[i][j][k] = m[i - 1][j][k];
                    }
                }
            }
        }
        System.out.println("最大价值为：" + m[5][10][10]);
    }

    public static void main(String[] args) {
        knapsackProblem(m, w, b, v);
    }
}
