package leetcode.medium;


/**
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 * 示例：
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案。
 */
public class Solution_5 {
    public String longestPalindrome(String s) {
        // 中心扩散
        if (s == null || s.length() == 1) {
            return s;
        }
        int length = s.length();
        char[] chars = s.toCharArray();
        int maxLen = 1;
        int begin = 0;
        for (int i = 1; i < length; i++) {
            int max1 = maxLen(chars, i, i);
            int max2 = maxLen(chars, i, i + 1);
            if (maxLen < Math.max(max1, max2)) {
                maxLen = Math.max(max1, max2);
                begin = i - (maxLen - 1) / 2;
            }
        }
        return s.substring(begin, begin + maxLen);
    }

    public int maxLen(char[] chars, int i, int j) {
        while (i >= 0 && j < chars.length) {
            if (chars[i] == chars[j]) {
                i--;
                j++;
            } else {
                break;
            }
        }
        return j - i - 1;
    }
}

/*
class Solution {
    public String longestPalindrome(String s) {
        if (s.length() == 1 || s.length() == 0) {
            return s;
        }
        int length = s.length();
        char[] chars = s.toCharArray();
        int maxLen = 1;
        int begin = 0;
        for (int i = 0; i < length - 1; i++) {
            for (int j = i + 1; j < length; j++) {
                if (chars[i] == chars[j] && isPal(chars, i, j)) {
                    if ((j - i + 1) > maxLen) {
                        maxLen = j - i + 1;
                        begin = i;
                    }
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }

    public boolean isPal(char[] chars, int i, int j) {
        while (i <= j) {
            if (chars[i] != chars[j]) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}
*/

/*
class Solution {
    public String longestPalindrome(String s) {
        // 动态规划
        int length = s.length();
        int begin = 0;
        int maxLen = 1;
        boolean[][] dp = new boolean[length][length];
        char[] chars = s.toCharArray();
        for (int i = 0; i < length; i++) {
            dp[i][i] = true;
        }
        for (int i = 1; i < length; i++) {
            for (int j = 0; j < i; j++) {
                if (i - j < 3) {
                    if (chars[i] == chars[j]) {
                        dp[j][i] = true;
                    } else {
                        dp[j][i] = false;
                    }
                } else if (chars[i] == chars[j]) {
                    dp[j][i] = dp[j + 1][i - 1];
                }
            }
        }
        for (int i = 0; i < length; i++) {
            for (int j = i; j < length; j++) {
                if (dp[i][j] == true) {
                    if (maxLen < j - i + 1) {
                        begin = i;
                        maxLen = j - i + 1;
                    }
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }
}
*/
