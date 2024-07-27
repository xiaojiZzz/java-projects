package leetcode.medium;

import java.util.ArrayDeque;
import java.util.Queue;


/**
 * 给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 * 示例 1：
 * 输入：board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
 * 输出：[["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
 * 解释：被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。
 * 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
 * 示例 2：
 * 输入：board = [["X"]]
 * 输出：[["X"]]
 */
public class Solution_130 {
    public void solve(char[][] board) {
        int m = board.length, n = board[0].length;
        boolean[][] isVisited = new boolean[m][n];
        for (int i = 0; i < n; i++) {
            if (board[0][i] == 'O') {
                check(board, isVisited, 0, i);
            }
            if (board[m - 1][i] == 'O') {
                check(board, isVisited, m - 1, i);
            }
        }
        for (int i = 0; i < m; i++) {
            if (board[i][n - 1] == 'O') {
                check(board, isVisited, i, n - 1);
            }
            if (board[i][0] == 'O') {
                check(board, isVisited, i, 0);
            }
        }
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (!isVisited[i][j]) {
                    board[i][j] = 'X';
                }
            }
        }
    }

    public void check(char[][] board, boolean[][] isVisited, int i, int j) {
        int m = board.length, n = board[0].length;
        int[][] pos = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{i, j});
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int x = poll[0], y = poll[1];
            if (isVisited[x][y]) {
                continue;
            }
            isVisited[x][y] = true;
            for (int k = 0; k < 4; k++) {
                if (x + pos[k][0] > 0 && x + pos[k][0] < m - 1 && y + pos[k][1] > 0 && y + pos[k][1] < n - 1 &&
                        !isVisited[x + pos[k][0]][y + pos[k][1]] && board[x + pos[k][0]][y + pos[k][1]] == 'O') {
                    queue.offer(new int[]{x + pos[k][0], y + pos[k][1]});
                }
            }
        }
    }
}

/*
class Solution {
    int n, m;

    public void solve(char[][] board) {
        n = board.length;
        if (n == 0) {
            return;
        }
        m = board[0].length;
        for (int i = 0; i < n; i++) {
            dfs(board, i, 0);
            dfs(board, i, m - 1);
        }
        for (int i = 1; i < m - 1; i++) {
            dfs(board, 0, i);
            dfs(board, n - 1, i);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'A') {
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }

    public void dfs(char[][] board, int x, int y) {
        if (x < 0 || x >= n || y < 0 || y >= m || board[x][y] != 'O') {
            return;
        }
        board[x][y] = 'A';
        dfs(board, x + 1, y);
        dfs(board, x - 1, y);
        dfs(board, x, y + 1);
        dfs(board, x, y - 1);
    }
}
*/
