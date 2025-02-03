package leetcode.simple;

import java.util.Arrays;

/**
 * 验证回文串 II
 * 给你一个字符串 s，最多 可以从中删除一个字符。
 * 请你判断 s 是否能成为回文字符串：如果能，返回 true ；否则，返回 false 。
 * 示例 1：
 * 输入：s = "aba"
 * 输出：true
 * 示例 2：
 * 输入：s = "abca"
 * 输出：true
 * 解释：你可以删除字符 'c' 。
 * 示例 3：
 * 输入：s = "abc"
 * 输出：false
 * 提示：
 * 1 <= s.length <= 105
 * s 由小写英文字母组成
 */
public class Solution_680 {
    public boolean validPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        char[] chars = s.toCharArray();
        while (left < right) {
            if (chars[left] == chars[right]) {
                left++;
                right--;
            } else {
                return check(Arrays.copyOfRange(chars, left + 1, right + 1)) || check(Arrays.copyOfRange(chars, left, right));
            }
        }
        return true;
    }

    private boolean check(char[] chars) {
        int left = 0, right = chars.length - 1;
        while (left < right) {
            if (chars[left] != chars[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
