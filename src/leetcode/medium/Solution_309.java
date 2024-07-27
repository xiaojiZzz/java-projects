package leetcode.medium;


/**
 * 给定一个整数数组prices，其中第  prices[i] 表示第 i 天的股票价格 。​
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 示例 1:
 * 输入: prices = [1,2,3,0,2]
 * 输出: 3
 * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
 */
public class Solution_309 {
    public int maxProfit(int[] prices) {
        int len = prices.length;
        // dp[i][0]: 手上不持有股票，并且不在冷冻期中的累计最大收益
        // dp[i][1]: 手上持有股票的最大收益
        // dp[i][2]: 手上不持有股票，并且处于冷冻期中的累计最大收益
        int[][] dp = new int[len][3];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        dp[0][2] = 0;
        for (int i = 1; i < len; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][2]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
            dp[i][2] = dp[i - 1][1] + prices[i];
        }
        return Math.max(dp[len - 1][0], dp[len - 1][2]);
    }
}

/*
class Solution {
    // 空间复杂度优化
    public int maxProfit(int[] prices) {
        int len = prices.length;
        int[] dp = new int[3];
        dp[0] = 0;
        dp[1] = -prices[0];
        dp[2] = 0;
        int pre0 = dp[0], pre1 = dp[1], pre2 = dp[2];
        for (int i = 1; i < len; i++) {
            dp[0] = Math.max(dp[0], pre2);
            dp[1] = Math.max(dp[1], pre0 - prices[i]);
            dp[2] = pre1 + prices[i];
            pre0 = dp[0];
            pre1 = dp[1];
            pre2 = dp[2];
        }
        return Math.max(dp[0], dp[2]);
    }
}
*/
