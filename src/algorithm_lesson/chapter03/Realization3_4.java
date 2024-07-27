package algorithm_lesson.chapter03;

@SuppressWarnings({"all"})
//算法实现题3-4 数字三角形问题
public class Realization3_4 {
    public static int row = 5;
    public static int[][] triangle = {{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}};

    public static void maxTrace(int row, int[][] triangle) {
        int max = 0;
        int[][] dp = new int[row][row];
        dp[0][0] = triangle[0][0];
        for (int i = 1; i < row; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) {
                    dp[i][j] = dp[i - 1][j] + triangle[i][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - 1]) + triangle[i][j];
                }
                max = Math.max(dp[i][j], max);
            }
        }
        System.out.println(max);
    }

    public static void main(String[] args) {
        maxTrace(row, triangle);
    }
}
