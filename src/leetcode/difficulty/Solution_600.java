package leetcode.difficulty;

import java.util.Arrays;

/**
 * 不含连续1的非负整数
 * 给定一个正整数 n ，请你统计在 [0, n] 范围的非负整数中，有多少个整数的二进制表示中不存在 连续的 1 。
 * 示例 1:
 * 输入: n = 5
 * 输出: 5
 * 解释:
 * 下面列出范围在 [0, 5] 的非负整数与其对应的二进制表示：
 * 0 : 0
 * 1 : 1
 * 2 : 10
 * 3 : 11
 * 4 : 100
 * 5 : 101
 * 其中，只有整数 3 违反规则（有两个连续的 1 ），其他 5 个满足规则。
 * 示例 2:
 * 输入: n = 1
 * 输出: 2
 * 示例 3:
 * 输入: n = 2
 * 输出: 3
 * 提示:
 * 1 <= n <= 109
 */
public class Solution_600 {
    public int findIntegers(int n) {
        int m = Integer.SIZE - Integer.numberOfLeadingZeros(n);
        int[][] memo = new int[m][2];
        for (int[] row : memo) {
            Arrays.fill(row, -1); // -1 表示没有计算过
        }
        return dfs(m - 1, 0, true, n, memo); // 从高位到低位
    }

    // pre 表示前一个比特位填的数
    private int dfs(int i, int pre, boolean isLimit, int n, int[][] memo) {
        if (i < 0) {
            return 1;
        }
        if (!isLimit && memo[i][pre] >= 0) { // 之前计算过
            return memo[i][pre];
        }
        int up = isLimit ? n >> i & 1 : 1;
        int res = dfs(i - 1, 0, isLimit && up == 0, n, memo); // 填 0
        if (pre == 0 && up == 1) { // 可以填 1
            res += dfs(i - 1, 1, isLimit, n, memo); // 填 1
        }
        if (!isLimit) {
            memo[i][pre] = res; // 记忆化
        }
        return res;
    }
}
