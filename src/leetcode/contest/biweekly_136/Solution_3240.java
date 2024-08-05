package leetcode.contest.biweekly_136;

/**
 * 最少翻转次数使二进制矩阵回文 II
 * 给你一个 m x n 的二进制矩阵 grid 。
 * 如果矩阵中一行或者一列从前往后与从后往前读是一样的，那么我们称这一行或者这一列是 回文 的。
 * 你可以将 grid 中任意格子的值 翻转 ，也就是将格子里的值从 0 变成 1 ，或者从 1 变成 0 。
 * 请你返回 最少 翻转次数，使得矩阵中 所有 行和列都是 回文的 ，且矩阵中 1 的数目可以被 4 整除 。
 * 示例 1：
 * 输入：grid = [[1,0,0],[0,1,0],[0,0,1]]
 * 输出：3
 * 解释：
 * 示例 2：
 * 输入：grid = [[0,1],[0,1],[0,0]]
 * 输出：2
 * 解释：
 * 示例 3：
 * 输入：grid = [[1],[1]]
 * 输出：2
 * 解释：
 * 提示：
 * m == grid.length
 * n == grid[i].length
 * 1 <= m * n <= 2 * 105
 * 0 <= grid[i][j] <= 1
 */
public class Solution_3240 {
    public int minFlips(int[][] a) {
        int ans = 0;
        int m = a.length;
        int n = a[0].length;
        for (int i = 0; i < m / 2; i++) {
            for (int j = 0; j < n / 2; j++) {
                int cnt1 = a[i][j] + a[i][n - 1 - j] + a[m - 1 - i][j] + a[m - 1 - i][n - 1 - j];
                ans += Math.min(cnt1, 4 - cnt1); // 全为 1 或全为 0
            }
        }

        if (m % 2 > 0 && n % 2 > 0) {
            // 正中间的数必须是 0
            ans += a[m / 2][n / 2];
        }

        int diff = 0, cnt1 = 0;
        if (m % 2 > 0) {
            // 统计正中间这一排
            for (int j = 0; j < n / 2; j++) {
                if (a[m / 2][j] != a[m / 2][n - 1 - j]) {
                    diff += 1;
                } else {
                    cnt1 += a[m / 2][j] * 2;
                }
            }
        }
        if (n % 2 > 0) {
            // 统计正中间这一列
            for (int i = 0; i < m / 2; i++) {
                if (a[i][n / 2] != a[m - 1 - i][n / 2]) {
                    diff += 1;
                } else {
                    cnt1 += a[i][n / 2] * 2;
                }
            }
        }

        return ans + (diff > 0 ? diff : cnt1 % 4);
    }
}
