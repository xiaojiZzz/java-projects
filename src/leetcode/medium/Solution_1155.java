package leetcode.medium;

import java.util.Arrays;


/**
 * 这里有 n 个一样的骰子，每个骰子上都有 k 个面，分别标号为 1 到 k 。
 * 给定三个整数 n ,  k 和 target ，返回可能的方式(从总共 kn 种方式中)滚动骰子的数量，使正面朝上的数字之和等于 target 。
 * 答案可能很大，你需要对 109 + 7 取模 。
 * 示例 1：
 * 输入：n = 1, k = 6, target = 3
 * 输出：1
 * 解释：你扔一个有 6 个面的骰子。
 * 得到 3 的和只有一种方法。
 * 示例 2：
 * 输入：n = 2, k = 6, target = 7
 * 输出：6
 * 解释：你扔两个骰子，每个骰子有 6 个面。
 * 得到 7 的和有 6 种方法：1+6 2+5 3+4 4+3 5+2 6+1。
 * 示例 3：
 * 输入：n = 30, k = 30, target = 500
 * 输出：222616187
 * 解释：返回的结果必须是对 109 + 7 取模。
 */
public class Solution_1155 {
    private static final int MOD = 1_000_000_007;

    public int numRollsToTarget(int n, int k, int target) {
        if (target < n || target > n * k) {
            return 0; // 无法组成 target
        }
        int[][] memo = new int[n + 1][target - n + 1];
        for (int[] m : memo) {
            Arrays.fill(m, -1); // -1 表示没有计算过
        }
        return dfs(n, target - n, memo, k);
    }

    private int dfs(int i, int j, int[][] memo, int k) {
        if (i == 0) {
            return j == 0 ? 1 : 0;
        }
        if (memo[i][j] != -1) { // 之前计算过
            return memo[i][j];
        }
        int res = 0;
        for (int x = 0; x < k && x <= j; x++) { // 掷出了 x
            res = (res + dfs(i - 1, j - x, memo, k)) % MOD;
        }
        return memo[i][j] = res; // 记忆化
    }
}

/*
class Solution {
    public int numRollsToTarget(int n, int k, int target) {
        if (target < n || target > n * k) {
            return 0; // 无法组成 target
        }
        final int MOD = 1_000_000_007;
        int[][] f = new int[n + 1][target - n + 1];
        f[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= target - n; j++) {
                for (int x = 0; x < k && x <= j; x++) { // 掷出了 x
                    f[i][j] = (f[i][j] + f[i - 1][j - x]) % MOD;
                }
            }
        }
        return f[n][target - n];
    }
}
*/

/*
class Solution {
    //终极优化
    public int numRollsToTarget(int n, int k, int target) {
        if (target < n || target > n * k) {
            return 0; // 无法组成 target
        }
        final int MOD = 1_000_000_007;
        long[] f = new long[target - n + 1];
        f[0] = 1;
        for (int i = 1; i <= n; i++) {
            int maxj = Math.min(i * (k - 1), target - n); // i 个骰子至多掷出 i*(k-1)
            for (int j = 1; j <= maxj; j++) {
                f[j] += f[j - 1]; // 原地计算 f 的前缀和
            }
            for (int j = maxj; j >= k; j--) {
                f[j] = (f[j] - f[j - k]) % MOD; // f[j] 是两个前缀和的差
            }
        }
        return (int) f[target - n];
    }
}
*/
