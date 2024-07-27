package leetcode.medium;


/**
 * 给你一个由若干 0 和 1 组成的二维网格 grid，请你找出边界全部由 1 组成的最大正方形子网格，并返回该子网格中的元素数量。如果不存在，则返回 0。
 * 示例 1：
 * 输入：grid = [[1,1,1],[1,0,1],[1,1,1]]
 * 输出：9
 * 示例 2：
 * 输入：grid = [[1,1,0,0]]
 * 输出：1
 */
public class Solution_1139 {
    public int largest1BorderedSquare(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int r = 0, c = 0, w = 0;
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int len = 0;
                if (i > j) {
                    len = getLen(i, j, j + 1, grid);
                } else {
                    len = getLen(i, j, i + 1, grid);
                }
                if (len > 0 && len >= w) {
                    r = i;
                    c = j;
                    w = len;
                }
            }
        }
        return w * w;
    }

    public int getLen(int i, int j, int len, int[][] grid) {
        if (grid[i][j] != 1) {
            return 0;
        }
        int ans = 0;
        for (int k = 1; k <= len; k++) {
            if (isSquare(i, j, k, grid)) {
                ans = k;
            }
        }
        return ans;
    }

    public boolean isSquare(int i, int j, int l, int[][] grid) {
        for (int x = i; x > i - l; x--) {
            if (grid[x][j] != 1) {
                return false;
            }
        }
        for (int x = i; x > i - l; x--) {
            if (grid[x][j - l + 1] != 1) {
                return false;
            }
        }
        for (int x = j; x > j - l; x--) {
            if (grid[i][x] != 1) {
                return false;
            }
        }
        for (int x = j; x > j - l; x--) {
            if (grid[i - l + 1][x] != 1) {
                return false;
            }
        }
        return true;
    }
}

/*
class Solution {
    // 前缀和优化
    public int largest1BorderedSquare(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        int[][] row = new int[n + 1][m + 1], col = new int[n + 1][m + 1];
        for(int i = 1; i <= n; ++i) {
            for(int j = 1; j <= m; ++j) {
                row[i][j] = row[i][j - 1] + grid[i - 1][j - 1];
                col[i][j] = col[i - 1][j] + grid[i - 1][j - 1];
            }
        }

        int res = 0;
        for(int i = 1; i <= n; ++i) {
            for(int j = 1; j <= m; ++j) {
                for(int k = 1; i + k - 1 <= n && j + k - 1 <= m; ++k) {
                    int x = i + k - 1, y = j + k - 1;
                    if(row[i][y] - row[i][j - 1] == k && row[x][y] - row[x][j - 1] == k
                            && col[x][j] - col[i - 1][j] == k && col[x][y] - col[i - 1][y] == k)
                        res = Math.max(res, k);
                }
            }
        }
        return res * res;
    }
}
*/
