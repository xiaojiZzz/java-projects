package leetcode.medium;


/**
 * 石子游戏中，爱丽丝和鲍勃轮流进行自己的回合，爱丽丝先开始 。
 * 有 n 块石子排成一排。每个玩家的回合中，可以从行中 移除 最左边的石头或最右边的石头，并获得与该行中剩余石头值之 和 相等的得分。
 * 当没有石头可移除时，得分较高者获胜。
 * 鲍勃发现他总是输掉游戏（可怜的鲍勃，他总是输），所以他决定尽力 减小得分的差值 。爱丽丝的目标是最大限度地 扩大得分的差值 。
 * 给你一个整数数组 stones ，其中 stones[i] 表示 从左边开始 的第 i 个石头的值，如果爱丽丝和鲍勃都 发挥出最佳水平 ，请返回他们 得分的差值。
 * 示例 1：
 * 输入：stones = [5,3,1,4,2]
 * 输出：6
 * 解释：
 * - 爱丽丝移除 2 ，得分 5 + 3 + 1 + 4 = 13 。游戏情况：爱丽丝 = 13 ，鲍勃 = 0 ，石子 = [5,3,1,4] 。
 * - 鲍勃移除 5 ，得分 3 + 1 + 4 = 8 。游戏情况：爱丽丝 = 13 ，鲍勃 = 8 ，石子 = [3,1,4] 。
 * - 爱丽丝移除 3 ，得分 1 + 4 = 5 。游戏情况：爱丽丝 = 18 ，鲍勃 = 8 ，石子 = [1,4] 。
 * - 鲍勃移除 1 ，得分 4 。游戏情况：爱丽丝 = 18 ，鲍勃 = 12 ，石子 = [4] 。
 * - 爱丽丝移除 4 ，得分 0 。游戏情况：爱丽丝 = 18 ，鲍勃 = 12 ，石子 = [] 。
 * 得分的差值 18 - 12 = 6 。
 * 示例 2：
 * 输入：stones = [7,90,5,1,100,10,10,2]
 * 输出：122
 */
public class Solution_1690 {
    public int stoneGameVII(int[] stones) {
        int n = stones.length;
        int[] sum = new int[n + 1];
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + stones[i];
        }
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                dp[i][j] = Math.max(sum[j + 1] - sum[i + 1] - dp[i + 1][j], sum[j] - sum[i] - dp[i][j - 1]);
            }
        }
        return dp[0][n - 1];
    }
}

/*
class Solution {
    public int stoneGameVII(int[] stones) {
        int n = stones.length;
        int[] sum = new int[n + 1];
        int[][] memo = new int[n][n];
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + stones[i];
        }
        return dfs(0, n - 1, sum, memo);
    }

    public int dfs(int i, int j, int[] sum, int[][] memo) {
        if (i >= j) {
            return 0;
        }
        if (memo[i][j] != 0) {
            return memo[i][j];
        }
        int res = Math.max(sum[j + 1] - sum[i + 1] - dfs(i + 1, j, sum, memo), sum[j] - sum[i] - dfs(i, j - 1, sum, memo));
        memo[i][j] = res;
        return res;
    }
}
*/
