package leetcode.difficulty;


/**
 * 给定一个整数数组 prices ，它的第 i 个元素 prices[i] 是一支给定的股票在第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 示例 1：
 * 输入：k = 2, prices = [2,4,1]
 * 输出：2
 * 解释：在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
 */
public class Solution_188 {
    public int maxProfit(int k, int[] prices) {
        int len = prices.length;
        if (len < 2) {
            return 0;
        }
        k = Math.min(k, len / 2);
        int[][][] dp = new int[len][2][k + 1];
        for (int i = 0; i <= k; i++) {
            dp[0][0][i] = 0;
            dp[0][1][i] = -prices[0];
        }
        for (int i = 1; i < len; i++) {
            for (int j = 1; j <= k; j++) {
                dp[i][0][j] = Math.max(dp[i - 1][0][j], dp[i - 1][1][j - 1] + prices[i]);
                dp[i][1][j - 1] = Math.max(dp[i - 1][1][j - 1], dp[i - 1][0][j - 1] - prices[i]);
            }
        }
        return dp[len - 1][0][k];
    }
}

//注意上下区别，一共只能进行k次交易
/*
class Solution {
    public int maxProfit(int k, int[] prices) {
        int len = prices.length;
        if (len < 2) {
            return 0;
        }
        k = Math.min(k, len / 2);
        int[][][] dp = new int[len][2][k + 1];
        for (int i = 0; i <= k; i++) {
            dp[0][0][i] = 0;
            dp[0][1][i] = -prices[0];
        }
        for (int i = 1; i < len; i++) {
            for (int j = 1; j <= k; j++) {
                dp[i][0][j] = Math.max(dp[i - 1][0][j], dp[i - 1][1][j] + prices[i]);
                dp[i][1][j] = Math.max(dp[i - 1][1][j], dp[i - 1][0][j - 1] - prices[i]);
            }
        }
        return dp[len - 1][0][k];
    }
}
*/

/*
class Solution {
    //优化为二维数组
    public int maxProfit(int k, int[] prices) {
        int len = prices.length;
        if (len < 2) {
            return 0;
        }
        k = Math.min(k, len / 2);
        int[][] dp = new int[2][k + 1];
        for (int i = 0; i <= k; i++) {
            dp[0][i] = 0;
            dp[1][i] = -prices[0];
        }
        for (int i = 1; i < len; i++) {
            for (int j = 1; j <= k; j++) {
                dp[0][j] = Math.max(dp[0][j], dp[1][j - 1] + prices[i]);
                dp[1][j - 1] = Math.max(dp[1][j - 1], dp[0][j - 1] - prices[i]);
            }
        }
        return dp[0][k];
    }
}
*/

/*
class Solution {
    //优化为一维数组
    public int maxProfit(int k, int[] prices) {
        int len = prices.length;
        if (len < 2) {
            return 0;
        }
        k = Math.min(k, len / 2);
        int[] dp = new int[2 * k + 1];
        for (int i = 1; i < 2 * k; i += 2) {
            dp[i] = -prices[0];
        }
        for (int i = 0; i < len; i++) {
            for (int j = 1; j < 2 * k; j += 2) {
                dp[j] = Math.max(dp[j], dp[j - 1] - prices[i]);
                dp[j + 1] = Math.max(dp[j + 1], dp[j] + prices[i]);
            }
        }
        return dp[2 * k];
    }
}
*/
