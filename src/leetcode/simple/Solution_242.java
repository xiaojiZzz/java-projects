package leetcode.simple;


/**
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 * 注意：若 s 和 t 中每个字符出现的次数都相同，则称 s 和 t 互为字母异位词。
 * 示例 1:
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * 示例 2:
 * 输入: s = "rat", t = "car"
 * 输出: false
 */
public class Solution_242 {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] charNum = new int[26];
        for (int i = 0; i < s.length(); i++) {
            charNum[s.charAt(i) - 'a']++;
            charNum[t.charAt(i) - 'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if (charNum[i] != 0) {
                return false;
            }
        }
        return true;
    }
}
