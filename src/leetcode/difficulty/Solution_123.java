package leetcode.difficulty;


/**
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 示例 1:
 * 输入：prices = [3,3,5,0,0,3,1,4]
 * 输出：6
 * 解释：在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
 *      随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
 */
public class Solution_123 {
    // 动态规划
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) return 0;
        // [][][] = [天数][是否持股][卖出的次数]
        int[][][] dp = new int[prices.length][2][3];
        int MIN_VALUE = Integer.MIN_VALUE / 2; // 防止溢出
        // 初始化
        dp[0][0][0] = 0; // 第一天休息
        dp[0][0][1] = dp[0][1][1] = MIN_VALUE; // 不可能
        dp[0][0][2] = dp[0][1][2] = MIN_VALUE; // 不可能
        dp[0][1][0] = -prices[0]; // 买股票
        for (int i = 1; i < prices.length; i++) {
            dp[i][0][0] = 0;
            dp[i][0][1] = Math.max(dp[i - 1][1][0] + prices[i], dp[i - 1][0][1]);
            dp[i][0][2] = Math.max(dp[i - 1][1][1] + prices[i], dp[i - 1][0][2]);
            dp[i][1][0] = Math.max(dp[i - 1][0][0] - prices[i], dp[i - 1][1][0]);
            dp[i][1][1] = Math.max(dp[i - 1][0][1] - prices[i], dp[i - 1][1][1]);
            dp[i][1][2] = MIN_VALUE;
        }
        return Math.max(0, Math.max(dp[prices.length - 1][0][1], dp[prices.length - 1][0][2]));
    }
}

/*
class Solution {
    // 同样的解法，第一天买再卖算 0，比较 return 即可
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) return 0;
        // [][][] = [天数][是否持股][卖出的次数]
        int[][][] dp = new int[prices.length][2][3];
        int MIN_VALUE = Integer.MIN_VALUE / 2; // 防止溢出
        //初始化
        dp[0][0][0] = 0;//第一天休息
        dp[0][0][1] = 0;
        dp[0][1][1] = -prices[0];
        dp[0][0][2] = 0;
        dp[0][1][2] = -prices[0];
        dp[0][1][0] = -prices[0];//买股票

        for (int i = 1; i < prices.length; i++) {
            dp[i][0][0] = 0;
            dp[i][0][1] = Math.max(dp[i - 1][1][0] + prices[i], dp[i - 1][0][1]);
            dp[i][0][2] = Math.max(dp[i - 1][1][1] + prices[i], dp[i - 1][0][2]);
            dp[i][1][0] = Math.max(dp[i - 1][0][0] - prices[i], dp[i - 1][1][0]);
            dp[i][1][1] = Math.max(dp[i - 1][0][1] - prices[i], dp[i - 1][1][1]);
            dp[i][1][2] = MIN_VALUE;
        }
        return Math.max(0, dp[prices.length - 1][0][2]);
    }
}
*/

/*
class Solution {
    // 优化时间复杂度
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int buy1 = -prices[0], sell1 = 0;
        int buy2 = -prices[0], sell2 = 0;
        for (int i = 1; i < n; ++i) {
            buy1 = Math.max(buy1, -prices[i]);
            sell1 = Math.max(sell1, buy1 + prices[i]);
            buy2 = Math.max(buy2, sell1 - prices[i]);
            sell2 = Math.max(sell2, buy2 + prices[i]);
        }
        return sell2;
    }
}
*/
