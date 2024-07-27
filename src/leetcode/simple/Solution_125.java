package leetcode.simple;


/**
 * 如果在将所有大写字符转换为小写字符、并移除所有非字母数字字符之后，短语正着读和反着读都一样。则可以认为该短语是一个 回文串 。
 * 字母和数字都属于字母数字字符。
 * 给你一个字符串 s，如果它是 回文串 ，返回 true ；否则，返回 false 。
 * 示例 1：
 * 输入: s = "A man, a plan, a canal: Panama"
 * 输出：true
 * 解释："amanaplanacanalpanama" 是回文串。
 */
public class Solution_125 {
    public boolean isPalindrome(String s) {
        char[] chars = s.toCharArray();
        int length = chars.length;
        char[] newChars = new char[length];
        int j = 0;
        for (int i = 0; i < length; i++) {
            if ('A' <= chars[i] && chars[i] <= 'Z' || 'a' <= chars[i] && chars[i] <= 'z' || '0' <= chars[i] && chars[i] <= '9') {
                if ('A' <= chars[i] && chars[i] <= 'Z') {
                    newChars[j++] = (char) ((int) chars[i] + 32);
                } else {
                    newChars[j++] = chars[i];
                }
            }
        }
        String s1 = String.valueOf(newChars);
        String str = s1.substring(0, j);
        char[] chars1 = str.toCharArray();
        for (int i = 0; i < j / 2; i++) {
            if (chars1[i] != chars1[j - 1 - i]) {
                return false;
            }
        }
        return true;
    }
}
