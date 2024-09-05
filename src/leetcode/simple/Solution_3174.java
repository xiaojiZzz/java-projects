package leetcode.simple;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 清除数字
 * 给你一个字符串 s 。
 * 你的任务是重复以下操作删除 所有 数字字符：
 * 删除 第一个数字字符 以及它左边 最近 的 非数字 字符。
 * 请你返回删除所有数字字符以后剩下的字符串。
 * 示例 1：
 * 输入：s = "abc"
 * 输出："abc"
 * 解释：
 * 字符串中没有数字。
 * 示例 2：
 * 输入：s = "cb34"
 * 输出：""
 * 解释：
 * 一开始，我们对 s[2] 执行操作，s 变为 "c4" 。
 * 然后对 s[1] 执行操作，s 变为 "" 。
 * 提示：
 * 1 <= s.length <= 100
 * s 只包含小写英文字母和数字字符。
 * 输入保证所有数字都可以按以上操作被删除。
 */
public class Solution_3174 {
    public String clearDigits(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= 'a' && s.charAt(i) <= 'z') {
                stack.push(s.charAt(i));
            } else {
                stack.pop();
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        while (!stack.isEmpty()) {
            stringBuilder.append(stack.pollLast());
        }
        return stringBuilder.toString();
    }
}

/*
class Solution {
    public String clearDigits(String s) {
        StringBuilder st = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                st.deleteCharAt(st.length() - 1);
            } else {
                st.append(c);
            }
        }
        return st.toString();
    }
}
*/