package leetcode.contest.biweekly_135;

import java.util.Arrays;

/**
 * 网格图操作后的最大分数
 * 给你一个大小为 n x n 的二维矩阵 grid ，一开始所有格子都是白色的。
 * 一次操作中，你可以选择任意下标为 (i, j) 的格子，并将第 j 列中从最上面到第 i 行所有格子改成黑色。
 * 如果格子 (i, j) 为白色，且左边或者右边的格子至少一个格子为黑色，那么我们将 grid[i][j] 加到最后网格图的总分中去。
 * 请你返回执行任意次操作以后，最终网格图的 最大 总分数。
 * 示例 1：
 * 输入：grid = [[0,0,0,0,0],[0,0,3,0,0],[0,1,0,0,0],[5,0,0,3,0],[0,0,0,0,2]]
 * 输出：11
 * 解释：
 * 第一次操作中，我们将第 1 列中，最上面的格子到第 3 行的格子染成黑色。第二次操作中，我们将第 4 列中，最上面的格子到最后一行的格子染成黑色。
 * 最后网格图总分为 grid[3][0] + grid[1][2] + grid[3][3] 等于 11 。
 * 示例 2：
 * 输入：grid = [[10,9,0,0,15],[7,1,0,8,0],[5,20,0,11,0],[0,0,0,1,2],[8,12,1,10,3]]
 * 输出：94
 * 解释：
 * 我们对第 1 ，2 ，3 列分别从上往下染黑色到第 1 ，4， 0 行。最后网格图总分为 grid[0][0] + grid[1][0] + grid[2][1] +
 * grid[4][1] + grid[1][3] + grid[2][3] + grid[3][3] + grid[4][3] + grid[0][4] 等于 94 。
 * 提示：
 * 1 <= n == grid.length <= 100
 * n == grid[i].length
 * 0 <= grid[i][j] <= 109
 */
public class Solution_3225 {
    class Solution {
        public long maximumScore(int[][] grid) {
            int n = grid.length;
            // 每列的前缀和（从上到下）
            long[][] colSum = new long[n][n + 1];
            for (int j = 0; j < n; j++) {
                for (int i = 0; i < n; i++) {
                    colSum[j][i + 1] = colSum[j][i] + grid[i][j];
                }
            }

            long[][][] memo = new long[n - 1][n + 1][2];
            for (long[][] mat : memo) {
                for (long[] row : mat) {
                    Arrays.fill(row, -1); // -1 表示没有计算过
                }
            }

            // 枚举第 n-1 列有 i 个黑格
            long ans = 0;
            for (int i = 0; i <= n; i++) {
                ans = Math.max(ans, dfs(n - 2, i, 0, colSum, memo));
            }
            return ans;
        }

        // pre 表示第 j+1 列的黑格个数
        // dec=1 意味着第 j+1 列的黑格个数 (pre) < 第 j+2 列的黑格个数
        private long dfs(int j, int pre, int dec, long[][] colSum, long[][][] memo) {
            if (j < 0) {
                return 0;
            }
            if (memo[j][pre][dec] != -1) { // 之前计算过
                return memo[j][pre][dec];
            }
            long res = 0;
            // 枚举第 j 列有 cur 个黑格
            for (int cur = 0; cur <= colSum.length; cur++) {
                if (cur == pre) { // 情况一：相等
                    // 没有可以计入总分的格子
                    res = Math.max(res, dfs(j - 1, cur, 0, colSum, memo));
                } else if (cur < pre) { // 情况二：右边黑格多
                    // 第 j 列的第 [cur, pre) 行的格子可以计入总分
                    res = Math.max(res, dfs(j - 1, cur, 1, colSum, memo) + colSum[j][pre] - colSum[j][cur]);
                } else if (dec == 0) { // 情况三：cur > pre >= 第 j+2 列的黑格个数
                    // 第 j+1 列的第 [pre, cur) 行的格子可以计入总分
                    res = Math.max(res, dfs(j - 1, cur, 0, colSum, memo) + colSum[j + 1][cur] - colSum[j + 1][pre]);
                } else if (pre == 0) { // 情况四（凹形）：cur > pre < 第 j+2 列的黑格个数
                    // 此时第 j+2 列全黑最优（递归过程中一定可以枚举到这种情况）
                    // 第 j+1 列全白是最优的，所以只需考虑 pre=0 的情况
                    // 由于第 j+1 列在 dfs(j+1) 的情况二中已经统计过，这里不重复统计
                    res = Math.max(res, dfs(j - 1, cur, 0, colSum, memo));
                }
            }
            return memo[j][pre][dec] = res; // 记忆化
        }
    }
}

/*
class Solution {
    public long maximumScore(int[][] grid) {
        int n = grid.length;
        // 每列的前缀和（从上到下）
        long[][] colSum = new long[n][n + 1];
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n; i++) {
                colSum[j][i + 1] = colSum[j][i] + grid[i][j];
            }
        }

        long[][][] f = new long[n][n + 1][2];
        for (int j = 0; j < n - 1; j++) {
            // pre 表示第 j+1 列的黑格个数
            for (int pre = 0; pre <= n; pre++) {
                // dec=1 意味着第 j+1 列的黑格个数 (pre) < 第 j+2 列的黑格个数
                for (int dec = 0; dec < 2; dec++) {
                    long res = 0;
                    // 枚举第 j 列有 cur 个黑格
                    for (int cur = 0; cur <= n; cur++) {
                        if (cur == pre) {  // 情况一：相等
                            // 没有可以计入总分的格子
                            res = Math.max(res, f[j][cur][0]);
                        } else if (cur < pre) {  // 情况二：右边黑格多
                            // 第 j 列的第 [cur, pre) 行的格子可以计入总分
                            res = Math.max(res, f[j][cur][1] + colSum[j][pre] - colSum[j][cur]);
                        } else if (dec == 0) {  // 情况三：cur > pre >= 第 j+2 列的黑格个数
                            // 第 j+1 列的第 [pre, cur) 行的格子可以计入总分
                            res = Math.max(res, f[j][cur][0] + colSum[j + 1][cur] - colSum[j + 1][pre]);
                        } else if (pre == 0) {  // 情况四（凹形）：cur > pre < 第 j+2 列的黑格个数
                            // 此时第 j+2 列全黑最优（递归过程中一定可以枚举到这种情况）
                            // 第 j+1 列全白是最优的，所以只需考虑 pre=0 的情况
                            // 由于第 j+1 列在 dfs(j+1) 的情况二中已经统计过，这里不重复统计
                            res = Math.max(res, f[j][cur][0]);
                        }
                    }
                    f[j + 1][pre][dec] = res;
                }
            }
        }

        long ans = 0;
        for (long[] row : f[n - 1]) {
            ans = Math.max(ans, row[0]);
        }
        return ans;
    }
}
*/

/*
class Solution {
    public long maximumScore(int[][] grid) {
        int n = grid.length;
        long[][] colSum = new long[n][n + 1];
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n; i++) {
                colSum[j][i + 1] = colSum[j][i] + grid[i][j];
            }
        }

        long[][][] f = new long[n][n + 1][2];
        for (int j = 0; j < n - 1; j++) {
            // 用前缀最大值优化
            long preMax = f[j][0][1] - colSum[j][0];
            for (int pre = 1; pre <= n; pre++) {
                f[j + 1][pre][0] = f[j + 1][pre][1] = Math.max(f[j][pre][0], preMax + colSum[j][pre]);
                preMax = Math.max(preMax, f[j][pre][1] - colSum[j][pre]);
            }

            // 用后缀最大值优化
            long sufMax = f[j][n][0] + colSum[j + 1][n];
            for (int pre = n - 1; pre > 0; pre--) {
                f[j + 1][pre][0] = Math.max(f[j + 1][pre][0], sufMax - colSum[j + 1][pre]);
                sufMax = Math.max(sufMax, f[j][pre][0] + colSum[j + 1][pre]);
            }

            // 单独计算 pre=0 的状态
            f[j + 1][0][0] = sufMax; // 无需考虑 f[j][0][0]，因为不能连续三列全白
            f[j + 1][0][1] = Math.max(f[j][0][0], f[j][n][0]); // 第 j 列要么全白，要么全黑
        }

        long ans = 0;
        for (long[] row : f[n - 1]) {
            ans = Math.max(ans, row[0]);
        }
        return ans;
    }
}
*/
