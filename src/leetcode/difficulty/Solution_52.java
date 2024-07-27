package leetcode.difficulty;


/**
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n × n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 给你一个整数 n ，返回 n 皇后问题 不同的解决方案的数量。
 * 示例 1：
 * 输入：n = 4
 * 输出：2
 */
public class Solution_52 {
    private int sum = 0;

    public int totalNQueens(int n) {
        int[] x = new int[n];
        backtrack(0, n, x);
        return sum;
    }

    public void backtrack(int i, int n, int[] x) {
        if (i == n) {
            sum++;
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
