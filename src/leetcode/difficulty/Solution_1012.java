package leetcode.difficulty;

import java.util.Arrays;

/**
 * 至少有 1 位重复的数字
 * 给定正整数 n，返回在 [1, n] 范围内具有 至少 1 位 重复数字的正整数的个数。
 * 示例 1：
 * 输入：n = 20
 * 输出：1
 * 解释：具有至少 1 位重复数字的正数（<= 20）只有 11 。
 * 示例 2：
 * 输入：n = 100
 * 输出：10
 * 解释：具有至少 1 位重复数字的正数（<= 100）有 11，22，33，44，55，66，77，88，99 和 100 。
 * 示例 3：
 * 输入：n = 1000
 * 输出：262
 * 提示：
 * 1 <= n <= 109
 */
public class Solution_1012 {
    public int numDupDigitsAtMostN(int n) {
        return n - countSpecialNumbers(n);
    }

    // lc.2376
    public int countSpecialNumbers(int n) {
        char[] s = Integer.toString(n).toCharArray();
        int[][] memo = new int[s.length][1 << 10];
        for (int[] row : memo) {
            Arrays.fill(row, -1); // -1 表示没有计算过
        }
        return dfs(0, 0, true, false, s, memo);
    }

    private int dfs(int i, int mask, boolean isLimit, boolean isNum, char[] s, int[][] memo) {
        if (i == s.length) {
            return isNum ? 1 : 0; // isNum 为 true 表示得到了一个合法数字
        }
        if (!isLimit && isNum && memo[i][mask] != -1) {
            return memo[i][mask]; // 之前计算过
        }
        int res = 0;
        if (!isNum) { // 可以跳过当前数位
            res = dfs(i + 1, mask, false, false, s, memo);
        }
        // 如果前面填的数字都和 n 的一样，那么这一位至多填数字 s[i]（否则就超过 n 啦）
        int up = isLimit ? s[i] - '0' : 9;
        // 枚举要填入的数字 d
        // 如果前面没有填数字，则必须从 1 开始（因为不能有前导零）
        for (int d = isNum ? 0 : 1; d <= up; d++) {
            if ((mask >> d & 1) == 0) { // d 不在 mask 中，说明之前没有填过 d
                res += dfs(i + 1, mask | (1 << d), isLimit && d == up, true, s, memo);
            }
        }
        if (!isLimit && isNum) {
            memo[i][mask] = res; // 记忆化
        }
        return res;
    }
}
