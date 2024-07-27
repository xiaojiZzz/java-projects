package leetcode.simple;


/**
 * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
 * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。
 *（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
 * 进阶：
 * 如果有大量输入的 S，称作 S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？
 * 示例 1：
 * 输入：s = "abc", t = "ahbgdc"
 * 输出：true
 * 示例 2：
 * 输入：s = "axc", t = "ahbgdc"
 * 输出：false
 */
public class Solution_392 {
    public boolean isSubsequence(String s, String t) {
        int l = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            boolean flag = false;
            for (int j = l; j < t.length(); j++) {
                if (t.charAt(j) == c) {
                    l = j + 1;
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                return false;
            }
        }
        return true;
    }
}

/*
class Solution {
    public boolean isSubsequence(String s, String t) {
        int n = s.length(), m = t.length();
        int i = 0, j = 0;
        while (i < n && j < m) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == n;
    }
}
*/
