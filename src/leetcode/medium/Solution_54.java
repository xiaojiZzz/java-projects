package leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 螺旋矩阵
 * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 * 示例 1：
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 * 提示：
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 10
 * -100 <= matrix[i][j] <= 100
 */
public class Solution_54 {
    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean[][] isVisited = new boolean[m][n];
        int[][] pos = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        List<Integer> ans = new ArrayList<>();
        int x = 0, y = 0, idx = 0;
        for (int i = 0; i < m * n; i++) {
            ans.add(matrix[x][y]);
            isVisited[x][y] = true;
            int x1 = pos[idx][0], y1 = pos[idx][1];
            if (x + x1 >= 0 && x + x1 < m && y + y1 >= 0 && y + y1 < n && !isVisited[x + x1][y + y1]) {
                x += x1;
                y += y1;
            } else {
                idx = (idx + 1) % 4;
                x += pos[idx][0];
                y += pos[idx][1];
            }
        }
        return ans;
    }
}

/*
class Solution {
    // 削头旋转
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        while (matrix.length >= 1) {
            for (int num : matrix[0]) {
                res.add(num);
            }
            matrix = reversalArr(matrix);
        }
        return res;
    }

    private int[][] reversalArr(int[][] matrix) {
        int m = matrix[0].length;
        int n = matrix.length - 1;
        int[][] reArr = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                reArr[i][j] = matrix[j + 1][m - i - 1];
            }
        }
        return reArr;
    }
}
*/

/*
class Solution {
    int INF = 101;
    public List<Integer> spiralOrder(int[][] mat) {
        List<Integer> ans = new ArrayList<>();
        int m = mat.length, n = mat[0].length;
        // 定义四个方向
        int[][] dirs = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};
        for (int x = 0, y = 0, d = 0, i = 0; i < m * n; i++) {
            ans.add(mat[x][y]);
            mat[x][y] = INF;
            // 下一步要到达的位置
            int nx = x + dirs[d][0], ny = y + dirs[d][1];
            // 如果下一步发生「溢出」或者已经访问过（说明四个方向已经走过一次）
            if (nx < 0 || nx >= m || ny < 0 || ny >= n || mat[nx][ny] == INF) {
                d = (d + 1) % 4;
                nx = x + dirs[d][0]; ny = y + dirs[d][1];
            }
            x = nx; y = ny;
        }
        return ans;
    }
}
*/

/*
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<Integer>();
        if (matrix == null || matrix.length == 0)
            return list;
        int m = matrix.length;
        int n = matrix[0].length;
        int i = 0;

        //统计矩阵从外向内的层数，如果矩阵非空，那么它的层数至少为1层
        int count = (Math.min(m, n) + 1) / 2;
        //从外部向内部遍历，逐层打印数据
        while (i < count) {
            for (int j = i; j < n - i; j++) {
                list.add(matrix[i][j]);
            }
            for (int j = i + 1; j < m - i; j++) {
                list.add(matrix[j][(n - 1) - i]);
            }

            for (int j = (n - 1) - (i + 1); j >= i && (m - 1 - i != i); j--) {
                list.add(matrix[(m - 1) - i][j]);
            }
            for (int j = (m - 1) - (i + 1); j >= i + 1 && (n - 1 - i) != i; j--) {
                list.add(matrix[j][i]);
            }
            i++;
        }
        return list;
    }
}
*/
