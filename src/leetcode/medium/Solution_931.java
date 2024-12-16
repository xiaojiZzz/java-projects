package leetcode.medium;

import java.util.Arrays;

/**
 * 下降路径最小和
 * 给你一个 n x n 的 方形 整数数组 matrix ，请你找出并返回通过 matrix 的下降路径 的 最小和 。
 * 下降路径 可以从第一行中的任何元素开始，并从每一行中选择一个元素。
 * 在下一行选择的元素和当前行所选元素最多相隔一列（即位于正下方或者沿对角线向左或者向右的第一个元素）。
 * 具体来说，位置 (row, col) 的下一个元素应当是 (row + 1, col - 1)、(row + 1, col) 或者 (row + 1, col + 1) 。
 * 示例 1：
 * 输入：matrix = [[2,1,3],[6,5,4],[7,8,9]]
 * 输出：13
 * 解释：如图所示，为和最小的两条下降路径
 * 示例 2：
 * 输入：matrix = [[-19,57],[-40,-5]]
 * 输出：-59
 * 解释：如图所示，为和最小的下降路径
 * 提示：
 * n == matrix.length == matrix[i].length
 * 1 <= n <= 100
 * -100 <= matrix[i][j] <= 100
 */
public class Solution_931 {
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        int[][] dp = new int[n][n];
        System.arraycopy(matrix[n - 1], 0, dp[n - 1], 0, n);
        for (int row = n - 2; row >= 0; row--) {
            for (int col = 0; col < n; col++) {
                if (col == 0) {
                    dp[row][col] = Math.min(dp[row + 1][0], dp[row + 1][1]);
                } else if (col == n - 1) {
                    dp[row][col] = Math.min(dp[row + 1][col - 1], dp[row + 1][col]);
                } else {
                    dp[row][col] = Math.min(Math.min(dp[row + 1][col - 1], dp[row + 1][col]), dp[row + 1][col + 1]);
                }
                dp[row][col] += matrix[row][col];
            }
        }
        return Arrays.stream(dp[0]).min().getAsInt();
    }
}
