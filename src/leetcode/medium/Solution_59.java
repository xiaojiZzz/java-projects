package leetcode.medium;


/**
 * 给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
 * 示例 1：
 * 输入：n = 3
 * 输出：[[1,2,3],[8,9,4],[7,6,5]]
 */
public class Solution_59 {
    public int[][] generateMatrix(int n) {
        int[][] tmp = new int[n][n];
        // 定义四个方向
        int[][] dirs = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        for (int x = 0, y = 0, d = 0, i = 1; i <= n * n; i++) {
            tmp[x][y] = i;
            // 下一步要到达的位置
            int nx = x + dirs[d][0], ny = y + dirs[d][1];
            // 如果下一步发生「溢出」或者已经访问过（说明四个方向已经走过一次）
            if (nx < 0 || nx >= n || ny < 0 || ny >= n || tmp[nx][ny] != 0) {
                d = (d + 1) % 4;
                nx = x + dirs[d][0];
                ny = y + dirs[d][1];
            }
            x = nx;
            y = ny;
        }
        return tmp;
    }
}

/*
class Solution {
    public int[][] generateMatrix(int n) {
        int l = 0, r = n - 1, t = 0, b = n - 1;
        int[][] mat = new int[n][n];
        int num = 1, tar = n * n;
        while (num <= tar) {
            for (int i = l; i <= r; i++) {
                mat[t][i] = num++; // left to right.
            }
            t++;
            for (int i = t; i <= b; i++) {
                mat[i][r] = num++; // top to bottom.
            }
            r--;
            for (int i = r; i >= l; i--) {
                mat[b][i] = num++; // right to left.
            }
            b--;
            for (int i = b; i >= t; i--) {
                mat[i][l] = num++; // bottom to top.
            }
            l++;
        }
        return mat;
    }
}
*/
