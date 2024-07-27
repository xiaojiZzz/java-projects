package leetcode.simple;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;


/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 * 有效字符串需满足：
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 每个右括号都有一个对应的相同类型的左括号。
 */
public class Solution_20 {
    public boolean isValid(String s) {
        if (s == null) {
            return false;
        }
        int length = s.length();
        HashMap<Character, Character> mp = new HashMap<>();
        mp.put(')', '(');
        mp.put(']', '[');
        mp.put('}', '{');
        char[] chars = s.toCharArray();
        if (length == 0 || s.length() % 2 == 1) {
            return false;
        }
        Deque<Character> stack = new ArrayDeque<>(); //利用栈
        for (int i = 0; i < length; i++) {
            if (mp.containsKey(chars[i])) {
                if (stack.isEmpty() || stack.peek() != mp.get(chars[i])) {
                    return false;
                } else {
                    stack.pop(); //pop() 前要保证栈不空，不然会有异常
                }
            } else {
                stack.push(chars[i]);
            }
        }
        return stack.isEmpty();
    }
}

/*
class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        char[] charArray = s.toCharArray();

        for (char ch : charArray) {
            //如果是左括号则直接入栈
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            } else {
                //如果是右括号，并且此时栈不为空
                if (!stack.isEmpty()) {
                    if (ch == ')') {
                        if (stack.pop() != '(')
                            return false;
                    } else if (ch == '}') {
                        if (stack.pop() != '{')
                            return false;
                    } else {
                        if (stack.pop() != '[')
                            return false;
                    }
                } else { //此时栈为空，但却来了个右括号，也直接返回false
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
*/
