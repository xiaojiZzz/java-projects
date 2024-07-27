package leetcode.medium;

import java.util.HashSet;
import java.util.Set;


/**
 * 给你一个下标从 0 开始、大小为 m x n 的矩阵 grid ，矩阵由若干 正 整数组成。
 * 你可以从矩阵第一列中的 任一 单元格出发，按以下方式遍历 grid ：
 * 从单元格 (row, col) 可以移动到 (row - 1, col + 1)、(row, col + 1)
 * 和 (row + 1, col + 1) 三个单元格中任一满足值 严格 大于当前单元格的单元格。
 * 返回你在矩阵中能够 移动 的 最大 次数。
 * 示例 1：
 * 输入：grid = [[2,4,3,5],[5,4,9,3],[3,4,2,11],[10,9,13,15]]
 * 输出：3
 * 解释：可以从单元格 (0, 0) 开始并且按下面的路径移动：
 * - (0, 0) -> (0, 1).
 * - (0, 1) -> (1, 2).
 * - (1, 2) -> (2, 3).
 * 可以证明这是能够移动的最大次数。
 * 示例 2：
 * 输入：grid = [[3,2,4],[2,1,9],[1,1,7]]
 * 输出：0
 * 解释：从第一列的任一单元格开始都无法移动。
 */
public class Solution_2684 {
    public int maxMoves(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        Set<Integer> q = new HashSet<>();
        for (int i = 0; i < m; i++) {
            q.add(i);
        }
        for (int j = 1; j < n; j++) {
            Set<Integer> q2 = new HashSet<>();
            for (int i : q) {
                for (int i2 = i - 1; i2 <= i + 1; i2++) {
                    if (0 <= i2 && i2 < m && grid[i][j - 1] < grid[i2][j]) {
                        q2.add(i2);
                    }
                }
            }
            q = q2;
            if (q.isEmpty()) {
                return j - 1;
            }
        }
        return n - 1;
    }
}

/*
class Solution {
    public int maxMoves(int[][] grid) {
        int maximumMoves = 0;
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        for (int j = 1; j < n; j++) {
            for (int i = 0; i < m; i++) {
                int currMaxMoves = Integer.MIN_VALUE;
                int minRow = Math.max(i - 1, 0), maxRow = Math.min(i + 1, m - 1);
                for (int k = minRow; k <= maxRow; k++) {
                    if (grid[i][j] > grid[k][j - 1]) {
                        currMaxMoves = Math.max(currMaxMoves, dp[k][j - 1] + 1);
                    }
                }
                dp[i][j] = currMaxMoves;
                maximumMoves = Math.max(maximumMoves, currMaxMoves);
            }
        }
        return maximumMoves;
    }
}
*/

/*
class Solution {
    private int ans;

    public int maxMoves(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            dfs(i, 0, grid); // 从第一列的任一单元格出发
        }
        return ans;
    }

    private void dfs(int i, int j, int[][] grid) {
        ans = Math.max(ans, j);
        if (ans == grid[0].length - 1) { // ans 已达到最大值
            return;
        }
        // 向右上/右/右下走一步
        for (int k = Math.max(i - 1, 0); k < Math.min(i + 2, grid.length); k++) {
            if (grid[k][j + 1] > grid[i][j]) {
                dfs(k, j + 1, grid);
            }
        }
        grid[i][j] = 0;
    }
}
*/