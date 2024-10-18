package leetcode.contest.biweekly_133;

import java.util.Arrays;

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