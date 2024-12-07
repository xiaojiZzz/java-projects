package leetcode.medium;

/**
 * 骑士在棋盘上的概率
 * 在一个 n x n 的国际象棋棋盘上，一个骑士从单元格 (row, column) 开始，并尝试进行 k 次移动。
 * 行和列是 从 0 开始 的，所以左上单元格是 (0,0) ，右下单元格是 (n - 1, n - 1) 。
 * 象棋骑士有8种可能的走法，如下图所示。每次移动在基本方向上是两个单元格，然后在正交方向上是一个单元格。
 * 每次骑士要移动时，它都会随机从8种可能的移动中选择一种(即使棋子会离开棋盘)，然后移动到那里。
 * 骑士继续移动，直到它走了 k 步或离开了棋盘。
 * 返回 骑士在棋盘停止移动后仍留在棋盘上的概率 。
 * 示例 1：
 * 输入: n = 3, k = 2, row = 0, column = 0
 * 输出: 0.0625
 * 解释: 有两步(到(1,2)，(2,1))可以让骑士留在棋盘上。
 * 在每一个位置上，也有两种移动可以让骑士留在棋盘上。
 * 骑士留在棋盘上的总概率是0.0625。
 * 示例 2：
 * 输入: n = 1, k = 0, row = 0, column = 0
 * 输出: 1.00000
 * 提示:
 * 1 <= n <= 25
 * 0 <= k <= 100
 * 0 <= row, column <= n - 1
 */
public class Solution_688 {

    private final int[][] directions = new int[][]{
            {-1, -2}, {1, -2}, {-2, -1}, {-2, 1},
            {-1, 2}, {1, 2}, {2, 1}, {2, -1}
    };

    public double knightProbability(int n, int k, int row, int column) {
        // dp数组：dp[step][i][j] 表示在第step步时，骑士在(i, j)的概率
        double[][][] dp = new double[k + 1][n][n];
        dp[0][row][column] = 1.0;

        for (int step = 1; step <= k; step++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dp[step - 1][i][j] > 0) {
                        for (int[] dir : directions) {
                            int ni = i + dir[0];
                            int nj = j + dir[1];
                            if (ni >= 0 && ni < n && nj >= 0 && nj < n) {
                                dp[step][ni][nj] += dp[step - 1][i][j] / 8.0;
                            }
                        }
                    }
                }
            }
        }

        // 计算k步之后的概率总和
        double result = 0.0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result += dp[k][i][j];
            }
        }
        return result;
    }
}

/*
class Solution {

    private static final int[][] DIRS = {{2, 1}, {1, 2}, {-1, 2}, {-2, 1}, {-2, -1}, {-1, -2}, {1, -2}, {2, -1}};

    public double knightProbability(int n, int k, int row, int column) {
        double[][][] f = new double[k + 1][n + 4][n + 4];
        for (int i = 2; i < n + 2; i++) {
            Arrays.fill(f[0][i], 2, n + 2, 1);
        }
        for (int step = 1; step <= k; step++) {
            for (int i = 2; i < n + 2; i++) {
                for (int j = 2; j < n + 2; j++) {
                    for (int[] d : DIRS) {
                        f[step][i][j] += f[step - 1][i + d[0]][j + d[1]];
                    }
                    f[step][i][j] /= DIRS.length;
                }
            }
        }
        return f[k][row + 2][column + 2];
    }
}
*/

/*
class Solution {

    private static final int[][] DIRS = {{2, 1}, {1, 2}, {-1, 2}, {-2, 1}, {-2, -1}, {-1, -2}, {1, -2}, {2, -1}};

    public double knightProbability(int n, int k, int row, int column) {
        double[][][] memo = new double[k + 1][n][n];
        return dfs(k, row, column, n, memo);
    }

    private double dfs(int k, int i, int j, int n, double[][][] memo) {
        if (i < 0 || j < 0 || i >= n || j >= n) {
            return 0;
        }
        if (k == 0) {
            return 1;
        }
        if (memo[k][i][j] > 0) { // 之前计算过
            return memo[k][i][j];
        }
        double res = 0;
        for (int[] d : DIRS) {
            res += dfs(k - 1, i + d[0], j + d[1], n, memo);
        }
        return memo[k][i][j] = res / DIRS.length; // 记忆化
    }
}
*/