package leetcode.medium;

import java.util.Arrays;


/**
 * 有 n 个城市，按从 0 到 n-1 编号。给你一个边数组 edges，其中 edges[i] = [fromi, toi, weighti]
 * 代表 fromi 和 toi 两个城市之间的双向加权边，距离阈值是一个整数 distanceThreshold。
 * 返回能通过某些路径到达其他城市数目最少、且路径距离 最大 为 distanceThreshold 的城市。如果有多个这样的城市，则返回编号最大的城市。
 * 注意，连接城市 i 和 j 的路径的距离等于沿该路径的所有边的权重之和。
 * 示例 1：
 * 输入：n = 4, edges = [[0,1,3],[1,2,1],[1,3,4],[2,3,1]], distanceThreshold = 4
 * 输出：3
 * 解释：城市分布图如上。
 * 每个城市阈值距离 distanceThreshold = 4 内的邻居城市分别是：
 * 城市 0 -> [城市 1, 城市 2]
 * 城市 1 -> [城市 0, 城市 2, 城市 3]
 * 城市 2 -> [城市 0, 城市 1, 城市 3]
 * 城市 3 -> [城市 1, 城市 2]
 * 城市 0 和 3 在阈值距离 4 以内都有 2 个邻居城市，但是我们必须返回城市 3，因为它的编号最大。
 * 示例 2：
 * 输入：n = 5, edges = [[0,1,2],[0,4,8],[1,2,3],[1,4,2],[2,3,1],[3,4,1]], distanceThreshold = 2
 * 输出：0
 * 解释：城市分布图如上。
 * 每个城市阈值距离 distanceThreshold = 2 内的邻居城市分别是：
 * 城市 0 -> [城市 1]
 * 城市 1 -> [城市 0, 城市 4]
 * 城市 2 -> [城市 3, 城市 4]
 * 城市 3 -> [城市 2, 城市 4]
 * 城市 4 -> [城市 1, 城市 2, 城市 3]
 * 城市 0 在阈值距离 2 以内只有 1 个邻居城市。
 */
public class Solution_1334 {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int[][] w = new int[n][n];
        for (int[] row : w) {
            Arrays.fill(row, Integer.MAX_VALUE / 2); // 防止加法溢出
        }
        for (int[] e : edges) {
            int x = e[0], y = e[1], wt = e[2];
            w[x][y] = w[y][x] = wt;
        }
        int[][][] memo = new int[n][n][n];

        int ans = 0;
        int minCnt = n;
        for (int i = 0; i < n; i++) {
            int cnt = 0;
            for (int j = 0; j < n; j++) {
                if (j != i && dfs(n - 1, i, j, memo, w) <= distanceThreshold) {
                    cnt++;
                }
            }
            if (cnt <= minCnt) { // 相等时取最大的 i
                minCnt = cnt;
                ans = i;
            }
        }
        return ans;
    }

    private int dfs(int k, int i, int j, int[][][] memo, int[][] w) {
        if (k < 0) { // 递归边界
            return w[i][j];
        }
        if (memo[k][i][j] != 0) { // 之前计算过
            return memo[k][i][j];
        }
        return memo[k][i][j] = Math.min(dfs(k - 1, i, j, memo, w),
                dfs(k - 1, i, k, memo, w) + dfs(k - 1, k, j, memo, w));
    }
}

/*
public class Solution {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int[][] w = new int[n][n];
        for (int[] row : w) {
            Arrays.fill(row, Integer.MAX_VALUE / 2); // 防止加法溢出
        }
        for (int[] e : edges) {
            int x = e[0], y = e[1], wt = e[2];
            w[x][y] = w[y][x] = wt;
        }

        int[][][] f = new int[n + 1][n][n];
        f[0] = w;
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    f[k + 1][i][j] = Math.min(f[k][i][j], f[k][i][k] + f[k][k][j]);
                }
            }
        }

        int ans = 0;
        int minCnt = n;
        for (int i = 0; i < n; i++) {
            int cnt = 0;
            for (int j = 0; j < n; j++) {
                if (j != i && f[n][i][j] <= distanceThreshold) {
                    cnt++;
                }
            }
            if (cnt <= minCnt) { // 相等时取最大的 i
                minCnt = cnt;
                ans = i;
            }
        }
        return ans;
    }
}
*/

/*
public class Solution {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int[][] w = new int[n][n];
        for (int[] row : w) {
            Arrays.fill(row, Integer.MAX_VALUE / 2); // 防止加法溢出
        }
        for (int[] e : edges) {
            int x = e[0], y = e[1], wt = e[2];
            w[x][y] = w[y][x] = wt;
        }

        int[][] f = w;
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    f[i][j] = Math.min(f[i][j], f[i][k] + f[k][j]);
                }
            }
        }

        int ans = 0;
        int minCnt = n;
        for (int i = 0; i < n; i++) {
            int cnt = 0;
            for (int j = 0; j < n; j++) {
                if (j != i && f[i][j] <= distanceThreshold) {
                    cnt++;
                }
            }
            if (cnt <= minCnt) { // 相等时取最大的 i
                minCnt = cnt;
                ans = i;
            }
        }
        return ans;
    }
}
*/
