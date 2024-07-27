package algorithm_lesson.chapter03;

@SuppressWarnings({"all"})
//算法实现题3-2 最优批处理问题
public class Realization3_2 {
    public static int n = 5;
    public static int s = 1;
    public static int[] t = {0, 1, 3, 4, 2, 1};
    public static int[] f = {0, 3, 2, 3, 3, 4};

    public static void minFee(int n, int s, int[] t, int[] f) {
        int sumTime = 0;
        for (int i = 1; i <= n; i++) {
            sumTime += t[i];
        }
        sumTime += n * s;
        int[][] dp = new int[n + 1][sumTime + 1];
        for (int i = 1; i <= n; i++) {
            for (int begin = sumTime; begin >= 0; begin--) {
                dp[i][begin] = 99999;
            }
        }
        for (int i = n; i > 0; i--) {
            for (int begin = sumTime; begin >= 0; begin--) {
                int ti = begin + s;
                int fi = 0;
                for (int j = i; j <= n; j++) {
                    fi += f[j - 1];
                    ti += t[j - 1];
                    int price = ti * fi;
                    if (j != n) {
                        price += dp[j][ti];
                    }
                    if (price < dp[i][begin]) {
                        dp[i][begin] = price;
                    }
                }
            }
        }
        System.out.println("最小费用为：" + dp[1][0]);
    }

    public static void main(String[] args) {
        minFee(n, s, t, f);
    }
}
