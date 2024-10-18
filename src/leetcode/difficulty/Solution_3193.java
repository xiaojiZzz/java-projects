package leetcode.difficulty;

import java.util.Arrays;

/**
 * 统计逆序对的数目
 * 给你一个整数 n 和一个二维数组 requirements ，其中 requirements[i] = [endi, cnti] 表示这个要求中的末尾下标和 逆序对 的数目。
 * 整数数组 nums 中一个下标对 (i, j) 如果满足以下条件，那么它们被称为一个 逆序对 ：
 * i < j 且 nums[i] > nums[j]
 * 请你返回 [0, 1, 2, ..., n - 1] 的排列 perm 的数目，满足对 所有 的 requirements[i] 都有 perm[0..endi] 恰好有 cnti 个逆序对。
 * 由于答案可能会很大，将它对 109 + 7 取余 后返回。
 * 示例 1：
 * 输入：n = 3, requirements = [[2,2],[0,0]]
 * 输出：2
 * 解释：
 * 两个排列为：
 * [2, 0, 1]
 * 前缀 [2, 0, 1] 的逆序对为 (0, 1) 和 (0, 2) 。
 * 前缀 [2] 的逆序对数目为 0 个。
 * [1, 2, 0]
 * 前缀 [1, 2, 0] 的逆序对为 (0, 2) 和 (1, 2) 。
 * 前缀 [1] 的逆序对数目为 0 个。
 * 示例 2：
 * 输入：n = 3, requirements = [[2,2],[1,1],[0,0]]
 * 输出：1
 * 解释：
 * 唯一满足要求的排列是 [2, 0, 1] ：
 * 前缀 [2, 0, 1] 的逆序对为 (0, 1) 和 (0, 2) 。
 * 前缀 [2, 0] 的逆序对为 (0, 1) 。
 * 前缀 [2] 的逆序对数目为 0 。
 * 示例 3：
 * 输入：n = 2, requirements = [[0,0],[1,0]]
 * 输出：1
 * 解释：
 * 唯一满足要求的排列为 [0, 1] ：
 * 前缀 [0] 的逆序对数目为 0 。
 * 前缀 [0, 1] 的逆序对为 (0, 1) 。
 * 提示：
 * 2 <= n <= 300
 * 1 <= requirements.length <= n
 * requirements[i] = [endi, cnti]
 * 0 <= endi <= n - 1
 * 0 <= cnti <= 400
 * 输入保证至少有一个 i 满足 endi == n - 1 。
 * 输入保证所有的 endi 互不相同。
 */
public class Solution_3193 {
    public int numberOfPermutations(int n, int[][] requirements) {
        final int MOD = 1_000_000_007;
        int[] req = new int[n];
        Arrays.fill(req, -1);
        req[0] = 0;
        int m = 0;
        for (int[] p : requirements) {
            req[p[0]] = p[1];
            m = Math.max(m, p[1]);
        }
        if (req[0] > 0) {
            return 0;
        }

        int[][] f = new int[n][m + 1];
        f[0][0] = 1;
        for (int i = 1; i < n; i++) {
            int mx = req[i] < 0 ? m : req[i];
            int r = req[i - 1];
            if (r >= 0) {
                for (int j = r; j <= Math.min(i + r, mx); j++) {
                    f[i][j] = f[i - 1][r];
                }
            } else {
                for (int j = 0; j <= mx; j++) {
                    for (int k = 0; k <= Math.min(i, j); k++) {
                        f[i][j] = (f[i][j] + f[i - 1][j - k]) % MOD;
                    }
                }
            }
        }
        return f[n - 1][req[n - 1]];
    }
}

/*
// 对上面的优化（时间+空间复杂度）
class Solution {
    public int numberOfPermutations(int n, int[][] requirements) {
        final int MOD = 1_000_000_007;
        int[] req = new int[n];
        Arrays.fill(req, -1);
        req[0] = 0;
        int m = 0;
        for (int[] p : requirements) {
            req[p[0]] = p[1];
            m = Math.max(m, p[1]);
        }
        if (req[0] > 0) {
            return 0;
        }

        int[] f = new int[m + 1];
        f[0] = 1;
        for (int i = 1; i < n; i++) {
            int mx = req[i] < 0 ? m : req[i];
            int r = req[i - 1];
            if (r >= 0) {
                Arrays.fill(f, 0, r, 0);
                Arrays.fill(f, r + 1, Math.min(i + r, mx) + 1, f[r]);
                Arrays.fill(f, Math.min(i + r, mx) + 1, m + 1, 0);
            } else {
                for (int j = 1; j <= mx; j++) { // 计算前缀和
                    f[j] = (f[j] + f[j - 1]) % MOD;
                }
                for (int j = mx; j > i; j--) { // 计算子数组和
                    f[j] = (f[j] - f[j - i - 1] + MOD) % MOD;
                }
            }
        }
        return f[req[n - 1]];
    }
}
*/

/*
class Solution {
    public int numberOfPermutations(int n, int[][] requirements) {
        int[] req = new int[n];
        Arrays.fill(req, -1);
        req[0] = 0;
        int m = 0;
        for (int[] p : requirements) {
            req[p[0]] = p[1];
            m = Math.max(m, p[1]);
        }
        if (req[0] > 0) {
            return 0;
        }

        int[][] memo = new int[n][m + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1); // -1 表示没有计算过
        }
        return dfs(n - 1, req[n - 1], req, memo);
    }

    private int dfs(int i, int j, int[] req, int[][] memo) {
        if (i == 0) {
            return 1;
        }
        if (memo[i][j] != -1) { // 之前计算过
            return memo[i][j];
        }
        int res = 0;
        int r = req[i - 1];
        if (r >= 0) {
            if (j >= r && j - i <= r) {
                res = dfs(i - 1, r, req, memo);
            }
        } else {
            for (int k = 0; k <= Math.min(i, j); k++) {
                res = (res + dfs(i - 1, j - k, req, memo)) % 1_000_000_007;
            }
        }
        return memo[i][j] = res; // 记忆化
    }
}
*/