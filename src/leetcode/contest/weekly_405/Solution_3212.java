package leetcode.contest.weekly_405;

/**
 * 给你一个二维字符矩阵 grid，其中 grid[i][j] 可能是 'X'、'Y' 或 '.'，返回满足以下条件的子矩阵数量：
 * 包含 grid[0][0]
 * 'X' 和 'Y' 的频数相等。
 * 至少包含一个 'X'。
 * 示例 1：
 * 输入： grid = [["X","Y","."],["Y",".","."]]
 * 输出： 3
 * 示例 2：
 * 输入： grid = [["X","X"],["X","Y"]]
 * 输出： 0
 * 解释：
 * 不存在满足 'X' 和 'Y' 频数相等的子矩阵。
 * 示例 3：
 * 输入： grid = [[".","."],[".","."]]
 * 输出： 0
 * 解释：
 * 不存在满足至少包含一个 'X' 的子矩阵。
 * 提示：
 * 1 <= grid.length, grid[i].length <= 1000
 * grid[i][j] 可能是 'X'、'Y' 或 '.'.
 */
public class Solution_3212 {
    // 优化空间复杂度
    public int numberOfSubmatrices(char[][] grid) {
        int ans = 0;
        int[][] colCnt = new int[grid[0].length][2];
        for (char[] row : grid) {
            int s0 = 0, s1 = 0;
            for (int j = 0; j < row.length; j++) {
                if (row[j] != '.') {
                    colCnt[j][row[j] & 1]++;
                }
                s0 += colCnt[j][0];
                s1 += colCnt[j][1];
                if (s0 > 0 && s0 == s1) {
                    ans++;
                }
            }
        }
        return ans;
    }
}

/*
class Solution {
    public int numberOfSubmatrices(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        Map<Character, Integer> map = new HashMap<>(3);
        map.put('X', 0);
        map.put('Y', 1);
        map.put('.', 2);
        int[][][] arr = new int[m][n][3];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    Integer integer = map.get(grid[i][j]);
                    arr[i][j][integer]++;
                } else if (i == 0) {
                    Integer integer = map.get(grid[i][j]);
                    arr[i][j][0] = arr[i][j - 1][0];
                    arr[i][j][1] = arr[i][j - 1][1];
                    arr[i][j][2] = arr[i][j - 1][2];
                    arr[i][j][integer]++;
                } else if (j == 0) {
                    Integer integer = map.get(grid[i][j]);
                    arr[i][j][0] = arr[i - 1][j][0];
                    arr[i][j][1] = arr[i - 1][j][1];
                    arr[i][j][2] = arr[i - 1][j][2];
                    arr[i][j][integer]++;
                } else {
                    Integer integer = map.get(grid[i][j]);
                    arr[i][j][0] = arr[i][j - 1][0] + arr[i - 1][j][0] - arr[i - 1][j - 1][0];
                    arr[i][j][1] = arr[i][j - 1][1] + arr[i - 1][j][1] - arr[i - 1][j - 1][1];
                    arr[i][j][2] = arr[i][j - 1][2] + arr[i - 1][j][2] - arr[i - 1][j - 1][2];
                    arr[i][j][integer]++;
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j][0] > 0 && arr[i][j][1] == arr[i][j][0]) {
                    ans++;
                }
            }
        }
        return ans;
    }
}
*/

/*
class Solution {
    // 上面的优化写法
    public int numberOfSubmatrices(char[][] grid) {
        int ans = 0;
        int m = grid.length;
        int n = grid[0].length;
        int[][][] sum = new int[m + 1][n + 1][2];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sum[i + 1][j + 1][0] = sum[i + 1][j][0] + sum[i][j + 1][0] - sum[i][j][0];
                sum[i + 1][j + 1][1] = sum[i + 1][j][1] + sum[i][j + 1][1] - sum[i][j][1];
                if (grid[i][j] != '.') {
                    sum[i + 1][j + 1][grid[i][j] & 1]++;
                }
                if (sum[i + 1][j + 1][0] > 0 && sum[i + 1][j + 1][0] == sum[i + 1][j + 1][1]) {
                    ans++;
                }
            }
        }
        return ans;
    }
}
*/
