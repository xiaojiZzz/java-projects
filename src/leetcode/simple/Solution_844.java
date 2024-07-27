package leetcode.simple;

import java.util.ArrayDeque;
import java.util.Deque;


/**
 * 给定 s 和 t 两个字符串，当它们分别被输入到空白的文本编辑器后，如果两者相等，返回 true 。# 代表退格字符。
 * 注意：如果对空文本输入退格字符，文本继续为空。
 * 示例 1：
 * 输入：s = "ab#c", t = "ad#c"
 * 输出：true
 * 解释：s 和 t 都会变成 "ac"。
 * 示例 2：
 * 输入：s = "ab##", t = "c#d#"
 * 输出：true
 * 解释：s 和 t 都会变成 ""。
 * 示例 3：
 * 输入：s = "a#c", t = "b"
 * 输出：false
 * 解释：s 会变成 "c"，但 t 仍然是 "b"。
 */
public class Solution_844 {
    public boolean backspaceCompare(String s, String t) {
        Deque<Character> stack1 = new ArrayDeque<>();
        Deque<Character> stack2 = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != '#') {
                stack1.push(s.charAt(i));
            } else {
                if (!stack1.isEmpty()) {
                    stack1.pop();
                }
            }
        }
        for (int i = 0; i < t.length(); i++) {
            if (t.charAt(i) != '#') {
                stack2.push(t.charAt(i));
            } else {
                if (!stack2.isEmpty()) {
                    stack2.pop();
                }
            }
        }
        if (stack1.size() != stack2.size()) {
            return false;
        }
        while (!stack1.isEmpty()) {
            if (stack1.pop() != stack2.pop()) {
                return false;
            }
        }
        return true;
    }
}
