package leetcode.difficulty;

import java.util.Arrays;


/**
 * 给你两个长度为 n 下标从 0 开始的整数数组 cost 和 time ，分别表示给 n 堵不同的墙刷油漆需要的开销和时间。你有两名油漆匠：
 * 一位需要 付费 的油漆匠，刷第 i 堵墙需要花费 time[i] 单位的时间，开销为 cost[i] 单位的钱。
 * 一位 免费 的油漆匠，刷 任意 一堵墙的时间为 1 单位，开销为 0 。但是必须在付费油漆匠 工作 时，免费油漆匠才会工作。
 * 请你返回刷完 n 堵墙最少开销为多少。
 * 示例 1：
 * 输入：cost = [1,2,3,2], time = [1,2,3,2]
 * 输出：3
 * 解释：下标为 0 和 1 的墙由付费油漆匠来刷，需要 3 单位时间。
 * 同时，免费油漆匠刷下标为 2 和 3 的墙，需要 2 单位时间，开销为 0 。总开销为 1 + 2 = 3 。
 * 示例 2：
 * 输入：cost = [2,3,4,2], time = [1,1,1,1]
 * 输出：4
 * 解释：下标为 0 和 3 的墙由付费油漆匠来刷，需要 2 单位时间。
 * 同时，免费油漆匠刷下标为 1 和 2 的墙，需要 2 单位时间，开销为 0 。总开销为 2 + 2 = 4 。
 */
public class Solution_2742 {
    public int paintWalls(int[] cost, int[] time) {
        int n = cost.length;
        int[][] memo = new int[n][n * 2 + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1); // -1 表示没有计算过
        }
        return dfs(n - 1, 0, cost, time, memo);
    }

    private int dfs(int i, int j, int[] cost, int[] time, int[][] memo) {
        if (j > i) { // 剩余的墙都可以免费刷
            return 0;
        }
        if (i < 0) { // 上面 if 不成立，意味着 j < 0，不符合题目要求
            return Integer.MAX_VALUE / 2; // 防止加法溢出
        }
        int k = j + memo.length; // 加上偏移量，防止出现负数
        if (memo[i][k] != -1) { // 之前计算过
            return memo[i][k];
        }
        int res1 = dfs(i - 1, j + time[i], cost, time, memo) + cost[i];
        int res2 = dfs(i - 1, j - 1, cost, time, memo);
        return memo[i][k] = Math.min(res1, res2);
    }
}

/*
class Solution {
    public int paintWalls(int[] cost, int[] time) {
        int n = cost.length;
        int[][] memo = new int[n][n + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1); // -1 表示没有计算过
        }
        return dfs(n - 1, n, cost, time, memo);
    }

    // j 表示剩余需要的体积
    private int dfs(int i, int j, int[] cost, int[] time, int[][] memo) {
        if (j <= 0) { // 没有约束，后面什么也不用选了
            return 0;
        }
        if (i < 0) { // 此时 j>0，但没有物品可选，不合法
            return Integer.MAX_VALUE / 2; // 防止加法溢出
        }
        if (memo[i][j] != -1) { // 之前计算过
            return memo[i][j];
        }
        int res1 = dfs(i - 1, j - time[i] - 1, cost, time, memo) + cost[i];
        int res2 = dfs(i - 1, j, cost, time, memo);
        return memo[i][j] = Math.min(res1, res2);
    }
}
*/

/*
class Solution {
    public int paintWalls(int[] cost, int[] time) {
        int n = cost.length;
        int[] f = new int[n + 1];
        Arrays.fill(f, Integer.MAX_VALUE / 2); // 防止加法溢出
        f[0] = 0;
        for (int i = 0; i < n; i++) {
            int c = cost[i];
            int t = time[i] + 1; // 注意这里加一了
            for (int j = n; j > 0; j--) {
                f[j] = Math.min(f[j], f[Math.max(j - t, 0)] + c);
            }
        }
        return f[n];
    }
}
*/
