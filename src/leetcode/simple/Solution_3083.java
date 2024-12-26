package leetcode.simple;

import java.util.HashSet;
import java.util.Set;

/**
 * 字符串及其反转中是否存在同一子字符串
 * 给你一个字符串 s ，请你判断字符串 s 是否存在一个长度为 2 的子字符串，在其反转后的字符串中也出现。
 * 如果存在这样的子字符串，返回 true；如果不存在，返回 false 。
 * 示例 1：
 * 输入：s = "leetcode"
 * 输出：true
 * 解释：子字符串 "ee" 的长度为 2，它也出现在 reverse(s) == "edocteel" 中。
 * 示例 2：
 * 输入：s = "abcba"
 * 输出：true
 * 解释：所有长度为 2 的子字符串 "ab"、"bc"、"cb"、"ba" 也都出现在 reverse(s) == "abcba" 中。
 * 示例 3：
 * 输入：s = "abcd"
 * 输出：false
 * 解释：字符串 s 中不存在满足「在其反转后的字符串中也出现」且长度为 2 的子字符串。
 * 提示：
 * 1 <= s.length <= 100
 * 字符串 s 仅由小写英文字母组成。
 */
public class Solution_3083 {
    public boolean isSubstringPresent(String s) {
        Set<String> set = new HashSet<>();
        int n = s.length();
        for (int i = 0; i + 1 < n; i++) {
            char c1 = s.charAt(i), c2 = s.charAt(i + 1);
            if (c1 == c2 || set.contains("" + c2 + c1)) {
                return true;
            }
            set.add("" + c1 + c2);
        }
        return false;
    }
}

/*
class Solution {
    public boolean isSubstringPresent(String S) {
        char[] s = S.toCharArray();
        boolean[][] vis = new boolean[26][26];
        for (int i = 1; i < s.length; i++) {
            int x = s[i - 1] - 'a';
            int y = s[i] - 'a';
            vis[x][y] = true;
            if (vis[y][x]) {
                return true;
            }
        }
        return false;
    }
}
*/

/*
class Solution {
    public boolean isSubstringPresent(String S) {
        char[] s = S.toCharArray();
        int[] vis = new int[26];
        for (int i = 1; i < s.length; i++) {
            int x = s[i - 1] - 'a';
            int y = s[i] - 'a';
            vis[x] |= 1 << y;
            if ((vis[y] >> x & 1) > 0) {
                return true;
            }
        }
        return false;
    }
}
*/