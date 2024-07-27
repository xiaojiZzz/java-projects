package algorithm_lesson.chapter03;

@SuppressWarnings({"all"})
//0-1背包问题
public class KnapsackProblem {
    public static int[][] m = new int[5][9];
    public static int[] w = {0, 2, 3, 4, 5}; //物品重量
    public static int[] v = {0, 3, 4, 5, 8}; //物品价值

    public static void knapsackProblem(int[][] m, int[] w, int[] v) {
        for (int i = 1; i < 5; i++) {
            for (int j = 0; j <= 8; j++) {
                if (w[i] > j) {
                    m[i][j] = m[i - 1][j];
                } else {
                    m[i][j] = Math.max(m[i - 1][j], m[i - 1][j - w[i]] + v[i]);
                }
            }
        }
        System.out.println("最大价值为：" + m[4][8]);
    }

    public static void main(String[] args) {
        knapsackProblem(m, w, v);
    }
}
