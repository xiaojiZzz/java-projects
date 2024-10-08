package leetcode.difficulty;

import java.util.Arrays;
import java.util.PriorityQueue;


/**
 * 给你一个下标从 0 开始的 m x n 整数矩阵 grid 。你一开始的位置在 左上角 格子 (0, 0) 。
 * 当你在格子 (i, j) 的时候，你可以移动到以下格子之一：
 * 满足 j < k <= grid[i][j] + j 的格子 (i, k) （向右移动），或者
 * 满足 i < k <= grid[i][j] + i 的格子 (k, j) （向下移动）。
 * 请你返回到达 右下角 格子 (m - 1, n - 1) 需要经过的最少移动格子数，如果无法到达右下角格子，请你返回 -1 。
 * 示例 1：
 * 输入：grid = [[3,4,2,1],[4,2,3,1],[2,1,0,0],[2,4,0,0]]
 * 输出：4
 * 解释：上图展示了到达右下角格子经过的 4 个格子。
 * 示例 2：
 * 输入：grid = [[3,4,2,1],[4,2,1,1],[2,1,1,0],[3,4,1,0]]
 * 输出：3
 * 解释：上图展示了到达右下角格子经过的 3 个格子。
 * 示例 3：
 * 输入：grid = [[2,1,0],[1,0,0]]
 * 输出：-1
 * 解释：无法到达右下角格子。
 */
public class Solution_2617 {
    public int minimumVisitedCells(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dist = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE / 2);
        }
        PriorityQueue<int[]>[] row = new PriorityQueue[m];
        PriorityQueue<int[]>[] col = new PriorityQueue[n];
        Arrays.setAll(row, v -> new PriorityQueue<int[]>((a, b) -> a[0] - b[0]));
        Arrays.setAll(col, v -> new PriorityQueue<int[]>((a, b) -> a[0] - b[0]));
        dist[0][0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                while (!row[i].isEmpty() && row[i].peek()[1] + grid[i][row[i].peek()[1]] < j) {
                    row[i].poll();
                }
                if (!row[i].isEmpty()) {
                    dist[i][j] = Math.min(dist[i][j], row[i].peek()[0] + 1);
                }
                while (!col[j].isEmpty() && col[j].peek()[1] + grid[col[j].peek()[1]][j] < i) {
                    col[j].poll();
                }
                if (!col[j].isEmpty()) {
                    dist[i][j] = Math.min(dist[i][j], col[j].peek()[0] + 1);
                }
                row[i].offer(new int[]{dist[i][j], j});
                col[j].offer(new int[]{dist[i][j], i});
            }
        }
        return dist[m - 1][n - 1] == Integer.MAX_VALUE / 2 ? -1 : dist[m - 1][n - 1];
    }
}
