package leetcode.simple;


/**
 * 给你一个字符串 s，由若干单词组成，单词前后用一些空格字符隔开。返回字符串中 最后一个 单词的长度。
 * 单词 是指仅由字母组成、不包含任何空格字符的最大子字符串。
 * 示例 1：
 * 输入：s = "Hello World"
 * 输出：5
 * 解释：最后一个单词是“World”，长度为5。
 * 示例 2：
 * 输入：s = "   fly me   to   the moon  "
 * 输出：4
 * 解释：最后一个单词是“moon”，长度为4。
 */
public class Solution_58 {
    public int lengthOfLastWord(String s) {
        int length = s.length();
        char[] chars = s.toCharArray();
        int count = 0;
        int flag = 0;
        for (int i = length - 1; i >= 0; i--) {
            if (chars[i] == ' ') {
                if (flag == 0) {
                    continue;
                } else {
                    break;
                }
            } else {
                count++;
                flag = 1;
            }
        }
        return count;
    }
}
