package leetcode.simple;


/**
 * 给定一个非空的字符串 s ，检查是否可以通过由它的一个子串重复多次构成。
 * 示例 1:
 * 输入: s = "abab"
 * 输出: true
 * 解释: 可由子串 "ab" 重复两次构成。
 * 示例 2:
 * 输入: s = "aba"
 * 输出: false
 * 示例 3:
 * 输入: s = "abcabcabcabc"
 * 输出: true
 * 解释: 可由子串 "abc" 重复四次构成。 (或子串 "abcabc" 重复两次构成。)
 */
public class Solution_459 {
    public boolean repeatedSubstringPattern(String s) {
        int n = s.length();
        for (int i = 1; i <= n / 2; i++) {
            if (s.charAt(i) == s.charAt(0)) {
                String substring = s.substring(0, i);
                for (int j = i; j + i <= n; j += i) {
                    if (!substring.equals(s.substring(j, j + i))) {
                        break;
                    }
                    if (j + i == n) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}

/*
class Solution {
    public boolean repeatedSubstringPattern(String s) {
        String str = s + s;
        return str.substring(1, str.length() - 1).contains(s);
    }
}
*/

/*
class Solution {
    public boolean repeatedSubstringPattern(String s) {
        int lens = s.length(), i = 0;
        while (++i <= lens / 2) {
            if (lens % i != 0) continue;
            // 判断x是不是基串
            if (s.substring(lens - i, lens).equals(s.substring(0, i))) {
                // 判断拿去x后是否相等
                if (s.substring(i, lens).equals(s.substring(0, lens - i))) {
                    return true;
                }
            }
        }
        return false;
    }
}
*/
