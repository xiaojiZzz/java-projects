package leetcode.difficulty;

/**
 * 变为棋盘
 * 一个 n x n 的二维网络 board 仅由 0 和 1 组成 。每次移动，你能交换任意两列或是两行的位置。
 * 返回 将这个矩阵变为  “棋盘”  所需的最小移动次数 。如果不存在可行的变换，输出 -1。
 * “棋盘” 是指任意一格的上下左右四个方向的值均与本身不同的矩阵。
 * 示例 1:
 * 输入: board = [[0,1,1,0],[0,1,1,0],[1,0,0,1],[1,0,0,1]]
 * 输出: 2
 * 解释:一种可行的变换方式如下，从左到右：
 * 第一次移动交换了第一列和第二列。
 * 第二次移动交换了第二行和第三行。
 * 示例 2:
 * 输入: board = [[0, 1], [1, 0]]
 * 输出: 0
 * 解释: 注意左上角的格值为0时也是合法的棋盘，也是合法的棋盘.
 * 示例 3:
 * 输入: board = [[1, 0], [1, 0]]
 * 输出: -1
 * 解释: 任意的变换都不能使这个输入变为合法的棋盘。
 * 提示：
 * n == board.length
 * n == board[i].length
 * 2 <= n <= 30
 * board[i][j] 将只包含 0或 1
 */
public class Solution_782 {
    public int movesToChessboard(int[][] board) {
        int n = board.length;
        int[] firstRow = board[0];
        int[] firstCol = new int[n];
        int[] rowCnt = new int[2];
        int[] colCnt = new int[2];
        for (int i = 0; i < n; i++) {
            rowCnt[firstRow[i]]++; // 统计 0 和 1 的个数
            firstCol[i] = board[i][0];
            colCnt[firstCol[i]]++;
        }

        // 第一行，0 和 1 的个数之差不能超过 1
        // 第一列，0 和 1 的个数之差不能超过 1
        if (Math.abs(rowCnt[0] - rowCnt[1]) > 1 || Math.abs(colCnt[0] - colCnt[1]) > 1) {
            return -1;
        }

        // 每一行和第一行比较，要么完全相同，要么完全不同
        for (int[] row : board) {
            boolean same = row[0] == firstRow[0];
            for (int i = 0; i < n; i++) {
                if ((row[i] == firstRow[i]) != same) {
                    return -1;
                }
            }
        }

        return minSwap(firstRow, rowCnt) + minSwap(firstCol, colCnt);
    }

    // 计算最小交换次数
    private int minSwap(int[] arr, int[] cnt) {
        int n = arr.length;
        int x0 = cnt[1] > cnt[0] ? 1 : 0; // 如果 n 是偶数，x0 是 0
        int diff = 0;
        for (int i = 0; i < n; i++) {
            diff += i % 2 ^ arr[i] ^ x0;
        }
        return n % 2 > 0 ? diff / 2 : Math.min(diff, n - diff) / 2;
    }
}

/*
class Solution {

    private int n;

    // 状态压缩，n 在 30 以内，可以用 int 表示状态
    public int movesToChessboard(int[][] board) {
        n = board.length;
        int mask = (1 << n) - 1;
        int rowMask = 0, colMask = 0;
        for (int i = 0; i < n; ++i) {
            rowMask |= board[0][i] << i;
            colMask |= board[i][0] << i;
        }
        int revRowMask = mask ^ rowMask;
        int revColMask = mask ^ colMask;
        int sameRow = 0, sameCol = 0;
        for (int i = 0; i < n; ++i) {
            int curRowMask = 0, curColMask = 0;
            for (int j = 0; j < n; ++j) {
                curRowMask |= board[i][j] << j;
                curColMask |= board[j][i] << j;
            }
            if (curRowMask != rowMask && curRowMask != revRowMask) {
                return -1;
            }
            if (curColMask != colMask && curColMask != revColMask) {
                return -1;
            }
            sameRow += curRowMask == rowMask ? 1 : 0;
            sameCol += curColMask == colMask ? 1 : 0;
        }
        int t1 = f(rowMask, sameRow);
        int t2 = f(colMask, sameCol);
        return t1 == -1 || t2 == -1 ? -1 : t1 + t2;
    }

    private int f(int mask, int cnt) {
        int ones = Integer.bitCount(mask);
        if (n % 2 == 1) {
            if (Math.abs(n - ones * 2) != 1 || Math.abs(n - cnt * 2) != 1) {
                return -1;
            }
            if (ones == n / 2) {
                return n / 2 - Integer.bitCount(mask & 0xAAAAAAAA);
            }
            return (n / 2 + 1) - Integer.bitCount(mask & 0x55555555);
        } else {
            if (ones != n / 2 || cnt != n / 2) {
                return -1;
            }
            int cnt0 = n / 2 - Integer.bitCount(mask & 0xAAAAAAAA);
            int cnt1 = n / 2 - Integer.bitCount(mask & 0x55555555);
            return Math.min(cnt0, cnt1);
        }
    }
}
*/
