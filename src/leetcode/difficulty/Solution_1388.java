package leetcode.difficulty;

import java.util.Arrays;


/**
 * 给你一个披萨，它由 3n 块不同大小的部分组成，现在你和你的朋友们需要按照如下规则来分披萨：
 * 你挑选 任意 一块披萨。
 * Alice 将会挑选你所选择的披萨逆时针方向的下一块披萨。
 * Bob 将会挑选你所选择的披萨顺时针方向的下一块披萨。
 * 重复上述过程直到没有披萨剩下。
 * 每一块披萨的大小按顺时针方向由循环数组 slices 表示。
 * 请你返回你可以获得的披萨大小总和的最大值。
 * 示例 1：
 * 输入：slices = [1,2,3,4,5,6]
 * 输出：10
 * 解释：选择大小为 4 的披萨，Alice 和 Bob 分别挑选大小为 3 和 5 的披萨。然后你选择大小为 6 的披萨，Alice 和 Bob 分别挑选
 * 大小为 2 和 1 的披萨。你获得的披萨总大小为 4 + 6 = 10 。
 */
public class Solution_1388 {
    public int maxSizeSlices(int[] slices) {
        int[] slices1 = Arrays.copyOfRange(slices, 0, slices.length - 1);
        int[] slices2 = Arrays.copyOfRange(slices, 1, slices.length);
        return Math.max(maxSum(slices1), maxSum(slices2));
    }

    private int maxSum(int[] slices) {
        int len = slices.length;
        int n = (len + 1) / 3;
        int[][] dp = new int[len][n + 1];
        dp[1][0] = 0;
        dp[0][0] = 0;
        dp[0][1] = slices[0];
        dp[1][1] = Math.max(slices[0], slices[1]);
        for (int i = 2; i < len; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = Math.max((dp[i - 2][j - 1] + slices[i]), dp[i - 1][j]);
            }
        }
        return dp[len - 1][n];
    }
}
