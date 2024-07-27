package leetcode.medium;


/**
 * 一个 2D 网格中的 峰值 是指那些 严格大于 其相邻格子(上、下、左、右)的元素。
 * 给你一个 从 0 开始编号 的 m x n 矩阵 mat ，其中任意两个相邻格子的值都 不相同 。找出 任意一个 峰值 mat[i][j] 并 返回其位置 [i,j] 。
 * 你可以假设整个矩阵周边环绕着一圈值为 -1 的格子。
 * 要求必须写出时间复杂度为 O(m log(n)) 或 O(n log(m)) 的算法
 * 示例 1:
 * 输入: mat = [[1,4],[3,2]]
 * 输出: [0,1]
 * 解释: 3 和 4 都是峰值，所以[1,0]和[0,1]都是可接受的答案。
 * 示例 2:
 * 输入: mat = [[10,20,15],[21,30,14],[7,16,32]]
 * 输出: [1,1]
 * 解释: 30 和 32 都是峰值，所以[1,1]和[2,2]都是可接受的答案。
 */
public class Solution_1901 {
    public int[] findPeakGrid(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int low = 0, high = m - 1;
        while (low <= high) {
            int i = (low + high) / 2;
            int j = -1, maxElement = -1;
            for (int k = 0; k < n; k++) {
                if (mat[i][k] > maxElement) {
                    j = k;
                    maxElement = mat[i][k];
                }
            }
            if (i - 1 >= 0 && mat[i][j] < mat[i - 1][j]) {
                high = i - 1;
                continue;
            }
            if (i + 1 < m && mat[i][j] < mat[i + 1][j]) {
                low = i + 1;
                continue;
            }
            return new int[]{i, j};
        }
        return new int[0];
    }
}

/*
class Solution {
    public int[] findPeakGrid(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int low = 0, high = n - 1;
        while (low <= high) {
            int i = low + (high - low) / 2;
            int j = -1, max = -1;
            for (int k = 0; k < m; k++) {
                if (mat[k][i] > max) {
                    j = k;
                    max = mat[k][i];
                }
            }
            if (i - 1 >= 0 && mat[j][i] < mat[j][i - 1]) {
                high = i - 1;
                continue;
            }
            if (i + 1 < n && mat[j][i] < mat[j][i + 1]) {
                low = i + 1;
                continue;
            }
            return new int[]{j, i};
        }
        return new int[0];
    }
}
*/

/*
class Solution {
    public int[] findPeakGrid(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        for (int i = 0; i < m; i++) {
            int left = 0;
            for (int j = 0; j < n; j++) {
                if (mat[i][j] > mat[i][left]) {
                    left = j;
                }
            }
            if (i == 0) {
                if (m == 1) {
                    return new int[]{i, left};
                } else if (mat[i][left] > mat[i + 1][left]) {
                    return new int[]{i, left};
                }
            } else if (i == m - 1) {
                if (m == 1) {
                    return new int[]{i, left};
                } else if (mat[i][left] > mat[i - 1][left]) {
                    return new int[]{i, left};
                }
            } else {
                if (m == 1) {
                    return new int[]{i, left};
                } else {
                    if (mat[i][left] > mat[i - 1][left] && mat[i][left] > mat[i + 1][left]) {
                        return new int[]{i, left};
                    }
                }
            }
        }
        return new int[]{0, 0};
    }
}
*/
