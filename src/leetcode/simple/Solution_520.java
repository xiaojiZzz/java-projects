package leetcode.simple;


/**
 * 我们定义，在以下情况时，单词的大写用法是正确的：
 * 全部字母都是大写，比如 "USA" 。
 * 单词中所有字母都不是大写，比如 "leetcode" 。
 * 如果单词不只含有一个字母，只有首字母大写， 比如 "Google" 。
 * 给你一个字符串 word 。如果大写用法正确，返回 true ；否则，返回 false 。
 * 示例 1：
 * 输入：word = "USA"
 * 输出：true
 * 示例 2：
 * 输入：word = "FlaG"
 * 输出：false
 */
public class Solution_520 {
    public boolean detectCapitalUse(String word) {
        if (word.length() == 1) {
            return true;
        }
        if (word.charAt(0) > 'Z') {
            return word.toLowerCase().equals(word);
        }
        if (word.charAt(1) > 'Z') {
            return word.substring(1).toLowerCase().equals(word.substring(1));
        } else {
            return word.toUpperCase().equals(word);
        }
    }
}

/*
class Solution {
    public boolean detectCapitalUse(String word) {
        int cnt = 0;
        for (char c : word.toCharArray()) {
            if (Character.isUpperCase(c)) {
                cnt++;
            }
        }
        return cnt == 0 || cnt == word.length() || cnt == 1 && Character.isUpperCase(word.charAt(0));
    }
}
*/
