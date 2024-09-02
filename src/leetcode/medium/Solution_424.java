package leetcode.medium;

/**
 * 替换后的最长重复字符
 * 给你一个字符串 s 和一个整数 k 。你可以选择字符串中的任一字符，并将其更改为任何其他大写英文字符。该操作最多可执行 k 次。
 * 在执行上述操作后，返回 包含相同字母的最长子字符串的长度。
 * 示例 1：
 * 输入：s = "ABAB", k = 2
 * 输出：4
 * 解释：用两个'A'替换为两个'B',反之亦然。
 * 示例 2：
 * 输入：s = "AABABBA", k = 1
 * 输出：4
 * 解释：
 * 将中间的一个'A'替换为'B',字符串变为 "AABBBBA"。
 * 子串 "BBBB" 有最长重复字母, 答案为 4。
 * 可能存在其他的方法来得到同样的结果。
 * 提示：
 * 1 <= s.length <= 105
 * s 仅由大写英文字母组成
 * 0 <= k <= s.length
 */
public class Solution_424 {
    public int characterReplacement(String s, int k) {
        int n = s.length();
        int ans = 0, max = 0;
        int[] cnt = new int[26];
        for (int left = 0, right = 0; right < n; right++) {
            int idx = s.charAt(right) - 'A';
            cnt[idx]++;
            max = Math.max(max, cnt[idx]);
            if (right - left + 1 - max > k) {
                cnt[s.charAt(left++) - 'A']--;
            }
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }
}

/*
class Solution {
    public int characterReplacement(String s, int k) {
        char[] cs = s.toCharArray();
        int[] cnt = new int[26];
        int ans = 0;
        for (int l = 0, r = 0; r < s.length(); r++) {
            cnt[cs[r] - 'A']++;
            while (!check(cnt, k)) {
                cnt[cs[l] - 'A']--;
                l++;
            }
            ans = Math.max(ans, r - l + 1);
        }
        return ans;
    }

    boolean check(int[] cnt, int k) {
        int max = 0, sum = 0;
        for (int i = 0; i < 26; i++) {
            max = Math.max(max, cnt[i]);
            sum += cnt[i];
        }
        return sum - max <= k;
    }
}
*/