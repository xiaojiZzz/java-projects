package leetcode.medium;

/**
 * 分割字符频率相等的最少子字符串
 * 给你一个字符串 s ，你需要将它分割成一个或者更多的 平衡 子字符串。
 * 比方说，s == "ababcc" 那么 ("abab", "c", "c") ，("ab", "abc", "c") 和 ("ababcc") 都是合法分割，
 * 但是 ("a", "bab", "cc") ，("aba", "bc", "c") 和 ("ab", "abcc") 不是，不平衡的子字符串用粗体表示。
 * 请你返回 s 最少 能分割成多少个平衡子字符串。
 * 注意：一个 平衡 字符串指的是字符串中所有字符出现的次数都相同。
 * 示例 1：
 * 输入：s = "fabccddg"
 * 输出：3
 * 解释：
 * 我们可以将 s 分割成 3 个子字符串：("fab, "ccdd", "g") 或者 ("fabc", "cd", "dg") 。
 * 示例 2：
 * 输入：s = "abababaccddb"
 * 输出：2
 * 解释：
 * 我们可以将 s 分割成 2 个子字符串：("abab", "abaccddb") 。
 * 提示：
 * 1 <= s.length <= 1000
 * s 只包含小写英文字母。
 */
public class Solution_3144 {
    public int minimumSubstringsInPartition(String s) {
        int n = s.length();
        int[] memo = new int[n];
        return dfs(s, memo, n - 1);
    }

    private int dfs(String s, int[] memo, int i) {
        if (i < 0) {
            return 0;
        }
        if (memo[i] > 0) {
            return memo[i];
        }
        int k = 0, maxCnt = 0;
        int[] cnt = new int[26];
        int res = Integer.MAX_VALUE;
        for (int j = i; j >= 0; j--) {
            k += (cnt[s.charAt(j) - 'a']++ == 0) ? 1 : 0;
            maxCnt = Math.max(maxCnt, cnt[s.charAt(j) - 'a']);
            if (k * maxCnt == i - j + 1) {
                res = Math.min(res, dfs(s, memo, j - 1) + 1);
            }
        }
        return memo[i] = res;
    }
}

/*
class Solution {
    public int minimumSubstringsInPartition(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int k = 0, maxCnt = 0;
            int[] cnt = new int[26];
            int res = Integer.MAX_VALUE;
            for (int j = i; j > 0; j--) {
                k += (cnt[s.charAt(j - 1) - 'a']++ == 0) ? 1 : 0;
                maxCnt = Math.max(maxCnt, cnt[s.charAt(j - 1) - 'a']);
                if (k * maxCnt == i - j + 1) {
                    res = Math.min(res, dp[j - 1] + 1);
                }
            }
            dp[i] = res;
        }
        return dp[n];
    }
}
*/
