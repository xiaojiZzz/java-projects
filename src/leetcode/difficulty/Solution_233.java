package leetcode.difficulty;

import java.util.Arrays;

/**
 * 数字 1 的个数
 * 给定一个整数 n，计算所有小于等于 n 的非负整数中数字 1 出现的个数。
 * 示例 1：
 * 输入：n = 13
 * 输出：6
 * 示例 2：
 * 输入：n = 0
 * 输出：0
 * 提示：
 * 0 <= n <= 109
 */
public class Solution_233 {
    // 数位 DP
    public int countDigitOne(int n) {
        char[] chars = String.valueOf(n).toCharArray();
        int len = chars.length;
        int[][] memo = new int[len][len];
        for (int i = 0; i < len; i++) {
            Arrays.fill(memo[i], -1);
        }
        return dfs(0, 0, true, chars, memo);
    }

    // dfs 表示在前 i 位有 cnt 个 1 的前提下，我们能构造出的数中的 1 的个数总和
    private int dfs(int i, int cnt, boolean isLimited, char[] chars, int[][] memo) {
        if (i == chars.length) {
            return cnt;
        }
        if (!isLimited && memo[i][cnt] != -1) {
            return memo[i][cnt];
        }
        int res = 0;
        int up = isLimited ? chars[i] - '0' : 9;
        for (int j = 0; j <= up; j++) {
            res += dfs(i + 1, j == 1 ? cnt + 1 : cnt, isLimited && j == up, chars, memo);
        }
        return memo[i][cnt] = res;
    }
}
