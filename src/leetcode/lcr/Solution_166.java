package leetcode.lcr;

/**
 * 珠宝的最高价值
 * 现有一个记作二维矩阵 frame 的珠宝架，其中 frame[i][j] 为该位置珠宝的价值。拿取珠宝的规则为：
 * 只能从架子的左上角开始拿珠宝
 * 每次可以移动到右侧或下侧的相邻位置
 * 到达珠宝架子的右下角时，停止拿取
 * 注意：珠宝的价值都是大于 0 的。除非这个架子上没有任何珠宝，比如 frame = [[0]]。
 * 示例 1:
 * 输入: frame = [[1,3,1],[1,5,1],[4,2,1]]
 * 输出: 12
 * 解释: 路径 1→3→5→2→1 可以拿到最高价值的珠宝
 * 提示：
 * 0 < frame.length <= 200
 * 0 < frame[0].length <= 200
 */
public class Solution_166 {
    public int jewelleryValue(int[][] frame) {
        int m = frame.length, n = frame[0].length;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i + 1][j + 1] = Math.max(dp[i][j + 1], dp[i + 1][j]) + frame[i][j];
            }
        }
        return dp[m][n];
    }
}

/*
class Solution {
    public int jewelleryValue(int[][] frame) {
        int m = frame.length, n = frame[0].length;
        return dfs(m - 1, n - 1, frame, new int[m][n]);
    }

    private int dfs(int i, int j, int[][] frame, int[][] memo) {
        if (i < 0 || j < 0) {
            return 0;
        }
        if (memo[i][j] > 0) {
            return memo[i][j];
        }
        return memo[i][j] = Math.max(dfs(i - 1, j, frame, memo), dfs(i, j - 1, frame, memo)) + frame[i][j];
    }
}
*/

/*
class Solution {
    public int jewelleryValue(int[][] frame) {
        int m = frame.length, n = frame[0].length;
        int[][] dp = new int[2][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[(i + 1) % 2][j + 1] = Math.max(dp[i % 2][j + 1], dp[(i + 1) % 2][j]) + frame[i][j];
            }
        }
        return dp[m % 2][n];
    }
}
*/

/*
class Solution {
    public int jewelleryValue(int[][] frame) {
        int m = frame.length, n = frame[0].length;
        int[] dp = new int[n + 1];
        for (int[] row : frame) {
            for (int j = 0; j < n; j++) {
                dp[j + 1] = Math.max(dp[j + 1], dp[j]) + row[j];
            }
        }
        return dp[n];
    }
}
*/
