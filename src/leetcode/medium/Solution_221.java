package leetcode.medium;


/**
 * 在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
 * 示例 1：
 * 输入：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 * 输出：4
 * 示例 2：
 * 输入：matrix = [["0","1"],["1","0"]]
 * 输出：1
 * 示例 3：
 * 输入：matrix = [["0"]]
 * 输出：0
 */
public class Solution_221 {
    public int maximalSquare(char[][] matrix) {
        int maxSide = 0;
        int rows = matrix.length, columns = matrix[0].length;
        int[][] dp = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    }
                    maxSide = Math.max(maxSide, dp[i][j]);
                }
            }
        }
        return maxSide * maxSide;
    }
}

/*
class Solution {
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] preSum = new int[m][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                preSum[i][j + 1] = preSum[i][j] + matrix[i][j] - '0';
            }
        }
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    int t = Math.min(m - i, n - j);
                    while (t > 0) {
                        int sum = 0;
                        for (int k = 0; k < t; k++) {
                            sum += preSum[i + k][j + t] - preSum[i + k][j];
                        }
                        if (sum == t * t) {
                            break;
                        } else {
                            t--;
                        }
                    }
                    ans = Math.max(ans, t * t);
                }
            }
        }
        return ans;
    }
}
*/
