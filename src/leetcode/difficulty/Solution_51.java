package leetcode.difficulty;

import java.util.*;

/**
 * N 皇后
 * 按照国际象棋的规则，皇后可以攻击与之处在同一行或同一列或同一斜线上的棋子。
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
 * 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 * 示例 1：
 * 输入：n = 4
 * 输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 * 示例 2：
 * 输入：n = 1
 * 输出：[["Q"]]
 * 提示：
 * 1 <= n <= 9
 */
public class Solution_51 {
    public List<List<String>> solveNQueens(int n) {
        char[][] queens = new char[n][n];
        List<List<String>> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Arrays.fill(queens[i], '.');
        }
        backtrack(ans, queens, 0, n);
        return ans;
    }

    public void backtrack(List<List<String>> ans, char[][] queens, int idx, int n) {
        if (idx == n) {
            List<String> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                list.add(new String(queens[i]));
            }
            ans.add(list);
            return;
        }
        for (int i = 0; i < n; i++) {
            queens[idx][i] = 'Q';
            if (isOk(queens, idx, i, n)) {
                backtrack(ans, queens, idx + 1, n);
            }
            queens[idx][i] = '.';
        }
    }

    public boolean isOk(char[][] queens, int i, int j, int n) {
        for (int k = 0; k < i; k++) {
            if (queens[k][j] == queens[i][j]) {
                return false;
            }
        }
        int x = i - 1, y = j - 1;
        while (x >= 0 && y >= 0) {
            if (queens[x][y] == queens[i][j]) {
                return false;
            }
            x--;
            y--;
        }
        x = i - 1;
        y = j + 1;
        while (x >= 0 && y < n) {
            if (queens[x][y] == queens[i][j]) {
                return false;
            }
            x--;
            y++;
        }
        return true;
    }
}

/*
class Solution {
    private List<List<String>> lists = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        int[] x = new int[n];
        backtrack(0, n, x);
        return lists;
    }

    public void backtrack(int i, int n, int[] x) {
        if (i == n) {
            List<String> list = new ArrayList<>();
            for (int k = 0; k < n; k++) {
                StringBuffer s = new StringBuffer();
                for (int t = 0; t < n; t++) {
                    if (t == x[k]) {
                        s.append("Q");
                    } else {
                        s.append(".");
                    }
                }
                String str = s.toString();
                list.add(str);
            }
            lists.add(list);
        } else {
            for (int j = 0; j < n; j++) {
                x[i] = j;
                if (place(i, x)) {
                    backtrack(i + 1, n, x);
                }
            }
        }
    }

    public boolean place(int i, int[] x) {
        for (int j = 0; j < i; j++) {
            if ((Math.abs(j - i) == Math.abs(x[i] - x[j])) || x[i] == x[j]) {
                return false;
            }
        }
        return true;
    }
}
*/
