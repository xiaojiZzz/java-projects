package leetcode.difficulty;

import java.util.ArrayList;
import java.util.List;

/**
 * 棋盘上有效移动组合的数目
 * 有一个 8 x 8 的棋盘，它包含 n 个棋子（棋子包括车，后和象三种）。
 * 给你一个长度为 n 的字符串数组 pieces ，其中 pieces[i] 表示第 i 个棋子的类型（车，后或象）。
 * 除此以外，还给你一个长度为 n 的二维整数数组 positions ，
 * 其中 positions[i] = [ri, ci] 表示第 i 个棋子现在在棋盘上的位置为 (ri, ci) ，棋盘下标从 1 开始。
 * 棋盘上每个棋子都可以移动 至多一次 。每个棋子的移动中，首先选择移动的 方向 ，然后选择 移动的步数 ，
 * 同时你要确保移动过程中棋子不能移到棋盘以外的地方。棋子需按照以下规则移动：
 * 车可以 水平或者竖直 从 (r, c) 沿着方向 (r+1, c)，(r-1, c)，(r, c+1) 或者 (r, c-1) 移动。
 * 后可以 水平竖直或者斜对角 从 (r, c) 沿着方向 (r+1, c)，(r-1, c)，(r, c+1)，(r, c-1)，
 * (r+1, c+1)，(r+1, c-1)，(r-1, c+1)，(r-1, c-1) 移动。
 * 象可以 斜对角 从 (r, c) 沿着方向 (r+1, c+1)，(r+1, c-1)，(r-1, c+1)，(r-1, c-1) 移动。
 * 移动组合 包含所有棋子的 移动 。每一秒，每个棋子都沿着它们选择的方向往前移动 一步 ，直到它们到达目标位置。
 * 所有棋子从时刻 0 开始移动。如果在某个时刻，两个或者更多棋子占据了同一个格子，那么这个移动组合 不有效 。
 * 请你返回 有效 移动组合的数目。
 * 注意：
 * 初始时，不会有两个棋子 在 同一个位置 。
 * 有可能在一个移动组合中，有棋子不移动。
 * 如果两个棋子 直接相邻 且两个棋子下一秒要互相占据对方的位置，可以将它们在同一秒内 交换位置 。
 * 示例 1:
 * 输入：pieces = ["rook"], positions = [[1,1]]
 * 输出：15
 * 解释：上图展示了棋子所有可能的移动。
 * 示例 2：
 * 输入：pieces = ["queen"], positions = [[1,1]]
 * 输出：22
 * 解释：上图展示了棋子所有可能的移动。
 * 示例 3:
 * 输入：pieces = ["bishop"], positions = [[4,3]]
 * 输出：12
 * 解释：上图展示了棋子所有可能的移动。
 * 示例 4:
 * 输入：pieces = ["rook","rook"], positions = [[1,1],[8,8]]
 * 输出：223
 * 解释：每个车有 15 种移动，所以总共有 15 * 15 = 225 种移动组合。
 * 但是，有两个是不有效的移动组合：
 * - 将两个车都移动到 (8, 1) ，会导致它们在同一个格子相遇。
 * - 将两个车都移动到 (1, 8) ，会导致它们在同一个格子相遇。
 * 所以，总共有 225 - 2 = 223 种有效移动组合。
 * 注意，有两种有效的移动组合，分别是一个车在 (1, 8) ，另一个车在 (8, 1) 。
 * 即使棋盘状态是相同的，这两个移动组合被视为不同的，因为每个棋子移动操作是不相同的。
 * 示例 5：
 * 输入：pieces = ["queen","bishop"], positions = [[5,7],[3,4]]
 * 输出：281
 * 解释：总共有 12 * 24 = 288 种移动组合。
 * 但是，有一些不有效的移动组合：
 * - 如果后停在 (6, 7) ，它会阻挡象到达 (6, 7) 或者 (7, 8) 。
 * - 如果后停在 (5, 6) ，它会阻挡象到达 (5, 6) ，(6, 7) 或者 (7, 8) 。
 * - 如果象停在 (5, 2) ，它会阻挡后到达 (5, 2) 或者 (5, 1) 。
 * 在 288 个移动组合当中，281 个是有效的。
 * 提示：
 * n == pieces.length
 * n == positions.length
 * 1 <= n <= 4
 * pieces 只包含字符串 "rook" ，"queen" 和 "bishop" 。
 * 棋盘上最多只有一个后。
 * 1 <= ri, ci <= 8
 * 每一个 positions[i] 互不相同。
 */
public class Solution_2056 {

    int[][] dirR = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    int[][] dirQ = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
    int[][] dirB = {{1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
    int n;
    List<List<int[]>> allMoves;
    List<int[]> path;

    public int countCombinations(String[] pieces, int[][] positions) {
        n = pieces.length;
        allMoves = new ArrayList<>(n);
        path = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            char c = pieces[i].charAt(0);
            int x = positions[i][0];
            int y = positions[i][1];
            generateAllMoves(x, y, c);
        }
        return dfs(0);
    }

    private void generateAllMoves(int r, int c, char piece) {
        List<int[]> move = new ArrayList<>();
        move.add(new int[]{r, c, 0, 0, 0});
        int[][] dirs;
        if (piece == 'r') {
            dirs = dirR;
        } else if (piece == 'q') {
            dirs = dirQ;
        } else {
            dirs = dirB;
        }
        for (int[] d : dirs) {
            int nr = r + d[0];
            int nc = c + d[1];
            int step = 1;
            while (nr >= 1 && nr <= 8 && nc >= 1 && nc <= 8) {
                move.add(new int[]{r, c, d[0], d[1], step});
                nr += d[0];
                nc += d[1];
                step++;
            }
        }
        allMoves.add(move);
    }

    private boolean isValid(int[] move1, int[] move2) {
        int x1 = move1[0], y1 = move1[1], dx1 = move1[2], dy1 = move1[3], step1 = move1[4];
        int x2 = move2[0], y2 = move2[1], dx2 = move2[2], dy2 = move2[3], step2 = move2[4];
        for (int i = 0; i < Math.max(step1, step2); i++) {
            if (i < step1) {
                x1 += dx1;
                y1 += dy1;
            }
            if (i < step2) {
                x2 += dx2;
                y2 += dy2;
            }
            if (x1 == x2 && y1 == y2)
                return false;
        }
        return true;
    }

    private int dfs(int i) {
        if (i == n) {
            return 1;
        }
        int res = 0;
        for (int[] move1 : allMoves.get(i)) {
            boolean flag = true;
            for (int[] move2 : path) {
                if (!isValid(move1, move2)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                path.add(move1);
                res += dfs(i + 1);
                path.remove(path.size() - 1);
            }
        }
        return res;
    }
}
