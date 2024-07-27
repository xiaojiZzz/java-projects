package leetcode.difficulty;

import java.util.*;


/**
 * 给定一个 m x n 整数矩阵 matrix ，找出其中 最长递增路径 的长度。
 * 对于每个单元格，你可以往上，下，左，右四个方向移动。 你 不能 在 对角线 方向上移动或移动到 边界外（即不允许环绕）。
 * 示例 1：
 * 输入：matrix = [[9,9,4],[6,6,8],[2,1,1]]
 * 输出：4
 * 解释：最长递增路径为 [1, 2, 6, 9]。
 * 示例 2：
 * 输入：matrix = [[3,4,5],[3,2,6],[2,2,1]]
 * 输出：4
 * 解释：最长递增路径是 [3, 4, 5, 6]。注意不允许在对角线方向上移动。
 * 示例 3：
 * 输入：matrix = [[1]]
 * 输出：1
 */
public class Solution_329 {
    public int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public int rows, columns;

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        rows = matrix.length;
        columns = matrix[0].length;
        int[][] outdegrees = new int[rows][columns];
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < columns; ++j) {
                for (int[] dir : dirs) {
                    int newRow = i + dir[0], newColumn = j + dir[1];
                    if (newRow >= 0 && newRow < rows && newColumn >= 0 && newColumn < columns && matrix[newRow][newColumn] < matrix[i][j]) {
                        ++outdegrees[i][j];
                    }
                }
            }
        }
        Queue<int[]> queue = new LinkedList<int[]>();
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < columns; ++j) {
                if (outdegrees[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                }
            }
        }
        int ans = 0;
        while (!queue.isEmpty()) {
            ++ans;
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                int[] cell = queue.poll();
                int row = cell[0], column = cell[1];
                for (int[] dir : dirs) {
                    int newRow = row + dir[0], newColumn = column + dir[1];
                    if (newRow >= 0 && newRow < rows && newColumn >= 0 && newColumn < columns && matrix[newRow][newColumn] > matrix[row][column]) {
                        --outdegrees[newRow][newColumn];
                        if (outdegrees[newRow][newColumn] == 0) {
                            queue.offer(new int[]{newRow, newColumn});
                        }
                    }
                }
            }
        }
        return ans;
    }
}

/*
class Solution {

    // 上下左右四个方向
    int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int longestIncreasingPath(int[][] matrix) {
        // 从每一个点出发，往下深搜，看它最远能到哪
        int m = matrix.length;
        int n = matrix[0].length;

        // 记忆化
        int[][] memo = new int[m][n];

        // 每个点都要作为起始点遍历一下
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 已经遍历过的就不用遍历了
                if (memo[i][j] == 0) {
                    ans = Math.max(ans, dfs(matrix, m, n, i, j, memo));
                }
                // 这里为什么不用再比较一次 ans 和 memo[i][j]呢？
                // 因为遍历前面节点的时候已经把后面的节点遍历了
                // 说明后面的节点肯定比前面的节点的最长路径短
                // 所以，不用多判断一次了
            }
        }

        return ans;
    }

    private int dfs(int[][] matrix, int m, int n, int i, int j, int[][] memo) {
        // 已经遍历过，直接返回
        if (memo[i][j] != 0) {
            return memo[i][j];
        }

        // 否则，看四个方向是否有满足条件的节点去扩散
        // 每个节点的初始路径为1
        int ans = 1;
        for (int[] dir : dirs) {
            int nextI = i + dir[0];
            int nextJ = j + dir[1];
            if (nextI >= 0 && nextJ >= 0 && nextI < m && nextJ < n && matrix[nextI][nextJ] > matrix[i][j]) {
                ans = Math.max(ans, dfs(matrix, m, n, nextI, nextJ, memo) + 1);
            }
        }

        // 记录到缓存中
        memo[i][j] = ans;
        return ans;
    }
}
*/

/*
class Solution {

    // 上下左右四个方向
    int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        // dp需要先算大的数，再算小的数才能转移，所以，我们先排序一下
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 节点的值及节点的坐标
                list.add(new int[]{matrix[i][j], i, j});
            }
        }

        // 按节点的值排序
        list.sort((a, b) -> b[0] - a[0]);

        int ans = 0;
        int[][] dp = new int[m][n];
        // 初始状态全为1
        for (int i = 0; i < m; i++) {
            Arrays.fill(dp[i], 1);
        }

        for (int[] element : list) {
            int val = element[0];
            int i = element[1];
            int j = element[2];
            for (int[] dir : dirs) {
                int nextI = i + dir[0];
                int nextJ = j + dir[1];
                if (nextI >= 0 && nextJ >= 0 && nextI < m && nextJ < n && matrix[nextI][nextJ] > matrix[i][j]) {
                    // 没有越界且相邻的节点值比当前节点大，就可以从它转移而来
                    dp[i][j] = Math.max(dp[i][j], dp[nextI][nextJ] + 1);
                }
            }

            // 记录所有节点出发的最大值
            ans = Math.max(ans, dp[i][j]);
        }

        return ans;
    }
}
*/
