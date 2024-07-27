package algorithm_lesson.chapter03;

@SuppressWarnings({"all"})
//金字塔问题的优化
public class MaxNumInRotateArray2 {
    public static int[][] a = {{3}, {1, 5}, {8, 4, 3}, {2, 6, 7, 9}, {6, 2, 3, 5, 1}};

    public static int maxNumInRotateArray(int[][] a) {
        int len = a.length;
        int dp[] = new int[len];
        dp[0] = a[0][0];
        for (int i = 1; i < len; i++) {
            for (int j = i; j >= 0; j--) {
                if (j == i) {
                    dp[j] = dp[j - 1] + a[i][j];
                } else if (j == 0) {
                    dp[0] += a[i][0];
                } else {
                    dp[j] = Math.max(dp[j - 1], dp[j]) + a[i][j];
                }
            }
        }
        int max = dp[0];
        for (int i = 1; i < len; i++) {
            if (dp[i] > max) {
                max = dp[i];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int max = maxNumInRotateArray(a);
        System.out.println(max);
    }
}
