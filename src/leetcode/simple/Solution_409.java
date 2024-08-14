package leetcode.simple;

/**
 * 给定一个包含大写字母和小写字母的字符串 s ，返回 通过这些字母构造成的 最长的回文串的长度。
 * 在构造过程中，请注意 区分大小写 。比如 "Aa" 不能当做一个回文字符串。
 * 示例 1:
 * 输入:s = "abccccdd"
 * 输出:7
 * 解释:
 * 我们可以构造的最长的回文串是"dccaccd", 它的长度是 7。
 * 示例 2:
 * 输入:s = "a"
 * 输出:1
 * 解释：可以构造的最长回文串是"a"，它的长度是 1。
 * 提示:
 * 1 <= s.length <= 2000
 * s 只由小写 和/或 大写英文字母组成
 */
public class Solution_409 {
    public int longestPalindrome(String s) {
        int[] number = new int['z' - 'A' + 1];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            number[c - 'A']++;
        }
        int ans = 0;
        boolean flag = false;
        for (int j : number) {
            if (j % 2 == 0) {
                ans += j;
            } else {
                flag = true;
                ans += j - 1;
            }
        }
        return ans + (flag ? 1 : 0);
    }
}
