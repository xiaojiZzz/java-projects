package leetcode.simple;


/**
 * 给你一个下标从 0 开始、大小为 m x n 的整数矩阵 matrix ，新建一个下标从 0 开始、名为 answer 的矩阵。
 * 使 answer 与 matrix 相等，接着将其中每个值为 -1 的元素替换为所在列的 最大 元素。
 * 返回矩阵 answer 。
 * 示例 1：
 * 输入：matrix = [[1,2,-1],[4,-1,6],[7,8,9]]
 * 输出：[[1,2,9],[4,8,6],[7,8,9]]
 * 解释：上图显示了发生替换的元素（蓝色区域）。
 * - 将单元格 [1][1] 中的值替换为列 1 中的最大值 8 。
 * - 将单元格 [0][2] 中的值替换为列 2 中的最大值 9 。
 * 示例 2：
 * 输入：matrix = [[3,-1],[5,2]]
 * 输出：[[3,2],[5,2]]
 * 解释：上图显示了发生替换的元素（蓝色区域）。
 */
public class Solution_3033 {
    public int[][] modifiedMatrix(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] answer = new int[m][n];
        int[] maxArr = new int[n];
        for (int i = 0; i < n; i++) {
            int max = 0;
            for (int j = 0; j < m; j++) {
                max = Math.max(max, matrix[j][i]);
            }
            maxArr[i] = max;
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] != -1) {
                    answer[i][j] = matrix[i][j];
                } else {
                    answer[i][j] = maxArr[j];
                }
            }
        }
        return answer;
    }
}
