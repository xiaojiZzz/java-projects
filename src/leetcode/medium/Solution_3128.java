package leetcode.medium;

/**
 * 直角三角形
 * 给你一个二维 boolean 矩阵 grid 。
 * 请你返回使用 grid 中的 3 个元素可以构建的 直角三角形 数目，且满足 3 个元素值 都 为 1 。
 * 注意：
 * 如果 grid 中 3 个元素满足：一个元素与另一个元素在 同一行，同时与第三个元素在 同一列 ，那么这 3 个元素称为一个 直角三角形 。
 * 这 3 个元素互相之间不需要相邻。
 * 示例 1：
 * 输入：grid = [[0,1,0],[0,1,1],[0,1,0]]
 * 输出：2
 * 示例 2：
 * 输入：grid = [[1,0,0,0],[0,1,0,1],[1,0,0,0]]
 * 输出：0
 * 示例 3：
 * 输入：grid = [[1,0,1],[1,0,0],[1,0,0]]
 * 输出：2
 * 提示：
 * 1 <= grid.length <= 1000
 * 1 <= grid[i].length <= 1000
 * 0 <= grid[i][j] <= 1
 */
public class Solution_3128 {
    public long numberOfRightTriangles(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] preRowSum = new int[m + 1][n + 1];
        int[][] preColSum = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                preRowSum[i][j] = preRowSum[i][j - 1] + grid[i - 1][j - 1];
            }
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                preColSum[j][i] = preColSum[j - 1][i] + grid[j - 1][i - 1];
            }
        }
        long ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    long left = preRowSum[i + 1][j];
                    long right = preRowSum[i + 1][n] - preRowSum[i + 1][j + 1];
                    long top = preColSum[i][j + 1];
                    long button = preColSum[m][j + 1] - preColSum[i + 1][j + 1];
                    ans += (left + right) * (top + button);
                }
            }
        }
        return ans;
    }
}

/*
class Solution {
    public long numberOfRightTriangles(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        int[] col = new int[m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                col[j] += grid[i][j];
            }
        }
        long res = 0;
        for (int i = 0; i < n; i++) {
            int row = Arrays.stream(grid[i]).sum();
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    res += (row - 1) * (col[j] - 1);
                }
            }
        }
        return res;
    }
}
*/