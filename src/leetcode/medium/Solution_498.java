package leetcode.medium;

/**
 * 给你一个大小为 m x n 的矩阵 mat ，请以对角线遍历的顺序，用一个数组返回这个矩阵中的所有元素。
 * 示例 1：
 * 输入：mat = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,4,7,5,3,6,8,9]
 * 示例 2：
 * 输入：mat = [[1,2],[3,4]]
 * 输出：[1,2,3,4]
 * 提示：
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 104
 * 1 <= m * n <= 104
 * -105 <= mat[i][j] <= 105
 */
public class Solution_498 {
    public int[] findDiagonalOrder(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[] ans = new int[m * n];
        boolean flag = true;
        int i = 0;
        int x = 0, y = 0;
        while (i < m * n) {
            ans[i++] = mat[x][y];
            if (flag) {
                while (x - 1 >= 0 && y + 1 < n) {
                    x -= 1;
                    y += 1;
                    ans[i++] = mat[x][y];
                }
                if (y != n - 1) {
                    y += 1;
                } else {
                    x += 1;
                }
            } else {
                while (x + 1 < m && y - 1 >= 0) {
                    x += 1;
                    y -= 1;
                    ans[i++] = mat[x][y];
                }
                if (x != m - 1) {
                    x += 1;
                } else {
                    y += 1;
                }
            }
            flag = !flag;
        }
        return ans;
    }
}
