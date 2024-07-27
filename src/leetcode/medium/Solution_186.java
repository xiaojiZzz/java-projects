package leetcode.medium;


/**
 * 给你一个字符数组 s ，反转其中 单词 的顺序。
 * 单词 的定义为：单词是一个由非空格字符组成的序列。s 中的单词将会由单个空格分隔。
 * 必须设计并实现 原地 解法来解决此问题，即不分配额外的空间。
 * 示例 1：
 * 输入：s = ["t","h","e"," ","s","k","y"," ","i","s"," ","b","l","u","e"]
 * 输出：["b","l","u","e"," ","i","s"," ","s","k","y"," ","t","h","e"]
 * 示例 2：
 * 输入：s = ["a"]
 * 输出：["a"]
 */
public class Solution_186 {
    public void reverseWords(char[] s) {
        int l = 0, r = s.length - 1;
        while (l < r) {
            char tmp = s[l];
            s[l] = s[r];
            s[r] = tmp;
            l++;
            r--;
        }
        l = r = 0;
        int idx = 0;
        while (idx < s.length) {
            if (s[idx] == ' ') {
                r = idx - 1;
                while (l < r) {
                    char tmp = s[l];
                    s[l] = s[r];
                    s[r] = tmp;
                    l++;
                    r--;
                }
                l = idx + 1;
            }
            idx++;
        }
        r = s.length - 1;
        while (l < r) {
            char tmp = s[l];
            s[l] = s[r];
            s[r] = tmp;
            l++;
            r--;
        }
    }
}
