package algorithm_lesson.chapter03;

@SuppressWarnings({"all"})
//金字塔问题，求解从上到下最大路径之和，从上到下，只能是经过正下方或者是右边一位
public class MaxNumInRotateArray {
    public static int[][] a = {{3}, {1, 5}, {8, 4, 3}, {2, 6, 7, 9}, {6, 2, 3, 5, 1}};

    public static int maxNumInRotateArray(int[][] a) {
        int max = 0;
        int len = a.length;
        int[][] dp = new int[len][len];
        dp[0][0] = a[0][0];
        for (int i = 1; i < len; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) {
                    dp[i][j] = dp[i - 1][j] + a[i][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - 1]) + a[i][j];
                }
                max = Math.max(dp[i][j], max);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int max = maxNumInRotateArray(a);
        System.out.println(max);
    }
}
