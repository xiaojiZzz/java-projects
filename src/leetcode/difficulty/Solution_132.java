package leetcode.difficulty;

import java.util.Arrays;

/**
 * 分割回文串 II
 * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是回文串。
 * 返回符合要求的 最少分割次数 。
 * 示例 1：
 * 输入：s = "aab"
 * 输出：1
 * 解释：只需一次分割就可将 s 分割成 ["aa","b"] 这样两个回文子串。
 * 示例 2：
 * 输入：s = "a"
 * 输出：0
 * 示例 3：
 * 输入：s = "ab"
 * 输出：1
 * 提示：
 * 1 <= s.length <= 2000
 * s 仅由小写英文字母组成
 */
public class Solution_132 {
    public int minCut(String S) {
        char[] chars = S.toCharArray();
        int n = chars.length;
        int[][] palMemo = new int[n][n];
        int[] memo = new int[n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(palMemo[i], -1);
        }
        Arrays.fill(memo, -1);
        return dfs(chars, palMemo, memo, n - 1);
    }

    private int dfs(char[] chars, int[][] palMemo, int[] memo, int r) {
        if (isPal(0, r, chars, palMemo)) {
            return 0;
        }
        if (memo[r] != -1) {
            return memo[r];
        }
        int res = Integer.MAX_VALUE;
        for (int l = 1; l <= r; l++) {
            if (isPal(l, r, chars, palMemo)) {
                res = Math.min(res, dfs(chars, palMemo, memo, l - 1) + 1);
            }
        }
        return memo[r] = res;
    }

    private boolean isPal(int l, int r, char[] chars, int[][] palMemo) {
        if (l >= r) {
            return true;
        }
        if (palMemo[l][r] != -1) {
            return palMemo[l][r] == 1;
        }
        boolean res = chars[l] == chars[r] && isPal(l + 1, r - 1, chars, palMemo);
        palMemo[l][r] = res ? 1 : 0;
        return res;
    }
}

class Solution {
    public int minCut(String S) {
        char[] chars = S.toCharArray();
        int n = chars.length;
        boolean[][] isPal = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(isPal[i], true);
        }
        for (int i = n - 2; i > 0; i--) {
            for (int j = i + 1; j < n; j++) {
                isPal[i][j] = chars[i] == chars[j] && isPal[i + 1][j - 1];
            }
        }
        int[] dp = new int[n];
        for (int r = 0; r < n; r++) {
            // 关键一步
            if (isPal[0][r]) {
                continue;
            }
            int res = Integer.MAX_VALUE;
            for (int l = 1; l <= r; l++) {
                if (isPal[l][r]) {
                    res = Math.min(res, dp[l - 1] + 1);
                }
            }
            dp[r] = res;
        }
        return dp[n - 1];
    }
}