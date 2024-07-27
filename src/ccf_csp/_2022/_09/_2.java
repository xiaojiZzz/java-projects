package ccf_csp._2022._09;

import java.util.Scanner;

public class _2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(), x = scanner.nextInt();
        int sum = 0;
        int[] prices = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            prices[i] = scanner.nextInt();
            sum += prices[i];
        }
        x = sum - x;
        int[][] dp = new int[n + 1][sum];
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= x; j++) {
                if (j - prices[i] >= 0) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - prices[i]] + prices[i]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        System.out.print(sum - dp[n][x]);
    }
}
