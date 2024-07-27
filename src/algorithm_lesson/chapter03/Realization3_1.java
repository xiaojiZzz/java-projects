package algorithm_lesson.chapter03;

import java.util.Arrays;

@SuppressWarnings({"all"})
//算法实现题3-1 独立任务最优调度问题
public class Realization3_1 {
    //布尔量p[i][j][k]表示前k个作业是否可以在处理机A用时不超过i且处理机B用时不超过j时间内完成
    public static int getMinTime() {
        int[] at = {2, 5, 7, 10, 5, 2};
        int[] bt = {3, 8, 4, 11, 3, 4};
        int a = 31; //a是都在A上处理的全部时间
        int b = 33; //b是都在B上处理的全部时间
        int n = 6; //n是总的作业数
        boolean[][][] dp = new boolean[a + 1][b + 1][n + 1];
        for (int i = 0; i <= a; i++) {
            for (int j = 0; j <= b; j++) {
                dp[i][j][0] = true;
                for (int k = 1; k <= n ; k++) {
                    dp[i][j][k] = false;
                }
            }
        }
        for (int k = 1; k <= n; k++) {
            for (int i = 0; i <= a; i++) {
                for (int j = 0; j <= b; j++) {
                    if (i - at[k - 1] >= 0) {
                        dp[i][j][k] = dp[i - at[k - 1]][j][k - 1];
                    }
                    if (j - bt[k - 1] >= 0) {
                        dp[i][j][k] = dp[i][j][k] || dp[i][j - bt[k - 1]][k - 1];
                    }
                }
            }
        }
        int[] max = new int[(a + 1) * (b + 1)];
        int p = 0;
        for (int i = 0; i <= a; i++) {
            for (int j = 0; j <= b; j++) {
                    if (dp[i][j][n]) {
                        if (i > j) {
                            max[p] = i;
                            p++;
                        } else {
                            max[p] = j;
                            p++;
                        }
                    }
            }
        }
        Arrays.sort(max);
        int res = 0;
        for (int i = 0; i < max.length; i++) {
            if (max[i] != 0) {
                res = max[i];
                break;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int minTime = getMinTime();
        System.out.println(minTime);
    }
}
