package leetcode.difficulty;

/**
 * 给你 3 个正整数 zero ，one 和 limit 。
 * 一个二进制数组 arr 如果满足以下条件，那么我们称它是 稳定的 ：
 * 0 在 arr 中出现次数 恰好 为 zero 。
 * 1 在 arr 中出现次数 恰好 为 one 。
 * arr 中每个长度超过 limit 的子数组都 同时 包含 0 和 1 。
 * 请你返回 稳定 二进制数组的 总 数目。
 * 由于答案可能很大，将它对 109 + 7 取余 后返回。
 * 示例 1：
 * 输入：zero = 1, one = 1, limit = 2
 * 输出：2
 * 解释：
 * 两个稳定的二进制数组为 [1,0] 和 [0,1] ，两个数组都有一个 0 和一个 1 ，且没有子数组长度大于 2 。
 * 示例 2：
 * 输入：zero = 1, one = 2, limit = 1
 * 输出：1
 * 解释：
 * 唯一稳定的二进制数组是 [1,0,1] 。
 * 二进制数组 [1,1,0] 和 [0,1,1] 都有长度为 2 且元素全都相同的子数组，所以它们不稳定。
 * 示例 3：
 * 输入：zero = 3, one = 3, limit = 2
 * 输出：14
 * 解释：
 * 所有稳定的二进制数组包括 [0,0,1,0,1,1] ，[0,0,1,1,0,1] ，[0,1,0,0,1,1] ，[0,1,0,1,0,1] ，[0,1,0,1,1,0] ，[0,1,1,0,0,1] ，
 * [0,1,1,0,1,0] ，[1,0,0,1,0,1] ，[1,0,0,1,1,0] ，[1,0,1,0,0,1] ，[1,0,1,0,1,0] ，
 * [1,0,1,1,0,0] ，[1,1,0,0,1,0] 和 [1,1,0,1,0,0] 。
 * 提示：
 * 1 <= zero, one, limit <= 1000
 */
public class Solution_3130 {
    public int numberOfStableArrays(int zero, int one, int limit) {
        int mod = 1000000007;
        // dp[i][j][k] 表示最后一个是 k 的情况下，zero 为 i 个，one 为 j 个 的合法个数
        int[][][] dp = new int[zero + 1][one + 1][2];
        for (int i = 1; i <= Math.min(zero, limit); i++) {
            dp[i][0][0] = 1;
        }
        for (int i = 1; i <= Math.min(one, limit); i++) {
            dp[0][i][1] = 1;
        }
        for (int i = 1; i <= zero; i++) {
            for (int j = 1; j <= one; j++) {
                // + mod 是因为 dp[i - 1][j][1] + dp[i - 1][j][0] - (i > limit ? dp[i - limit - 1][j][1] : 0) 可能小于 0
                // 转 long 是因为 dp[i - 1][j][1] + dp[i - 1][j][0] 可能溢出
                dp[i][j][0] = (int) (((long) dp[i - 1][j][1] + dp[i - 1][j][0] + (i > limit ? mod - dp[i - limit - 1][j][1] : 0)) % mod);
                dp[i][j][1] = (int) (((long) dp[i][j - 1][0] + dp[i][j - 1][1] + (j > limit ? mod - dp[i][j - limit - 1][0] : 0)) % mod);
            }
        }
        return (dp[zero][one][0] + dp[zero][one][1]) % mod;
    }
}

/*
class Solution {
    private static final int MOD = 1_000_000_007;

    public int numberOfStableArrays(int zero, int one, int limit) {
        int[][][] memo = new int[zero + 1][one + 1][2];
        for (int[][] m : memo) {
            for (int[] m2 : m) {
                Arrays.fill(m2, -1); // -1 表示没有计算过
            }
        }
        return (dfs(zero, one, 0, limit, memo) + dfs(zero, one, 1, limit, memo)) % MOD;
    }

    private int dfs(int i, int j, int k, int limit, int[][][] memo) {
        if (i == 0) { // 递归边界
            return k == 1 && j <= limit ? 1 : 0;
        }
        if (j == 0) { // 递归边界
            return k == 0 && i <= limit ? 1 : 0;
        }
        if (memo[i][j][k] != -1) { // 之前计算过
            return memo[i][j][k];
        }
        if (k == 0) {
            // + MOD 保证答案非负
            memo[i][j][k] = (int) (((long) dfs(i - 1, j, 0, limit, memo) + dfs(i - 1, j, 1, limit, memo) +
                    (i > limit ? MOD - dfs(i - limit - 1, j, 1, limit, memo) : 0)) % MOD);
        } else {
            memo[i][j][k] = (int) (((long) dfs(i, j - 1, 0, limit, memo) + dfs(i, j - 1, 1, limit, memo) +
                    (j > limit ? MOD - dfs(i, j - limit - 1, 0, limit, memo) : 0)) % MOD);
        }
        return memo[i][j][k];
    }
}
*/