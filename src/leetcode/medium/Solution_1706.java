package leetcode.medium;

/**
 * 球会落何处
 * 用一个大小为 m x n 的二维网格 grid 表示一个箱子。你有 n 颗球。箱子的顶部和底部都是开着的。
 * 箱子中的每个单元格都有一个对角线挡板，跨过单元格的两个角，可以将球导向左侧或者右侧。
 * 将球导向右侧的挡板跨过左上角和右下角，在网格中用 1 表示。
 * 将球导向左侧的挡板跨过右上角和左下角，在网格中用 -1 表示。
 * 在箱子每一列的顶端各放一颗球。每颗球都可能卡在箱子里或从底部掉出来。
 * 如果球恰好卡在两块挡板之间的 "V" 形图案，或者被一块挡导向到箱子的任意一侧边上，就会卡住。
 * 返回一个大小为 n 的数组 answer ，其中 answer[i] 是球放在顶部的第 i 列后从底部掉出来的那一列对应的下标，如果球卡在盒子里，则返回 -1 。
 * 示例 1：
 * 输入：grid = [[1,1,1,-1,-1],[1,1,1,-1,-1],[-1,-1,-1,1,1],[1,1,1,1,-1],[-1,-1,-1,-1,-1]]
 * 输出：[1,-1,-1,-1,-1]
 * 解释：示例如图：
 * b0 球开始放在第 0 列上，最终从箱子底部第 1 列掉出。
 * b1 球开始放在第 1 列上，会卡在第 2、3 列和第 1 行之间的 "V" 形里。
 * b2 球开始放在第 2 列上，会卡在第 2、3 列和第 0 行之间的 "V" 形里。
 * b3 球开始放在第 3 列上，会卡在第 2、3 列和第 0 行之间的 "V" 形里。
 * b4 球开始放在第 4 列上，会卡在第 2、3 列和第 1 行之间的 "V" 形里。
 * 示例 2：
 * 输入：grid = [[-1]]
 * 输出：[-1]
 * 解释：球被卡在箱子左侧边上。
 * 示例 3：
 * 输入：grid = [[1,1,1,1,1,1],[-1,-1,-1,-1,-1,-1],[1,1,1,1,1,1],[-1,-1,-1,-1,-1,-1]]
 * 输出：[0,1,2,3,4,-1]
 * 提示：
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 100
 * grid[i][j] 为 1 或 -1
 */
public class Solution_1706 {
    public int[] findBall(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int col = i;
            for (int j = 0; j < m; j++) {
                int loc = grid[j][col];
                if (col == 0 && loc == -1 || col == n - 1 && loc == 1) {
                    col = -1;
                    break;
                }
                if (loc == -1 && grid[j][col - 1] == 1 || loc == 1 && grid[j][col + 1] == -1) {
                    col = -1;
                    break;
                }
                if (loc == -1) {
                    col--;
                } else {
                    col++;
                }
            }
            ans[i] = col;
        }
        return ans;
    }
}

/*
class Solution {
    public int[] findBall(int[][] grid) {
        int n = grid[0].length;
        int[] ans = new int[n];
        for (int j = 0; j < n; j++) {
            // 模拟第 j 列球的移动
            int curCol = j; // 当前列号
            for (int[] row : grid) {
                int d = row[curCol]; // -1 或 1，表示左/右
                curCol += d; // 左/右走一步
                // 如果球出界或者卡在 V 形，退出循环，否则继续循环（垂直落入下一排）
                // V 形就是 -1 的左边是 1，1 的右边是 -1，即 row[curCol] != d
                if (curCol < 0 || curCol == n || row[curCol] != d) {
                    curCol = -1;
                    break;
                }
            }
            ans[j] = curCol;
        }
        return ans;
    }
}
*/