package leetcode.medium;

import java.util.ArrayDeque;
import java.util.Deque;


/**
 * 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
 * 整数除法仅保留整数部分。
 * 你可以假设给定的表达式总是有效的。所有中间结果将在 [-231, 231 - 1] 的范围内。
 * 注意：不允许使用任何将字符串作为数学表达式计算的内置函数，比如 eval() 。
 * 示例 1：
 * 输入：s = "3+2*2"
 * 输出：7
 * 示例 2：
 * 输入：s = " 3/2 "
 * 输出：1
 * 示例 3：
 * 输入：s = " 3+5 / 2 "
 * 输出：5
 */
public class Solution_227 {
    public int calculate(String s) {
        Deque<Integer> digit = new ArrayDeque<>();
        Deque<Character> symbol = new ArrayDeque<>();
        int i = 0, n = s.length();
        while (i < n) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                int num = 0;
                while (i < n && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + s.charAt(i) - '0';
                    i++;
                }
                digit.push(num);
            } else if (c == '+' || c == '-') {
                symbol.push(c);
                i++;
            } else if (c == '*' || c == '/') {
                int num = 0;
                i++;
                while (s.charAt(i) == ' ') {
                    i++;
                }
                while (i < n && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + s.charAt(i) - '0';
                    i++;
                }
                if (c == '*') {
                    digit.push(digit.pop() * num);
                } else {
                    digit.push(digit.pop() / num);
                }
            } else {
                i++;
            }
        }
        while (!symbol.isEmpty()) {
            Character pop = symbol.pollLast();
            Integer num = digit.pollLast();
            if (pop == '+') {
                digit.offer(num + digit.pollLast());
            } else {
                digit.offer(num - digit.pollLast());
            }
        }
        return digit.peek();
    }
}

/*
class Solution {
    public int calculate(String s) {
        Deque<Integer> stack = new ArrayDeque<Integer>();
        char preSign = '+';
        int num = 0;
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            if (Character.isDigit(s.charAt(i))) {
                num = num * 10 + s.charAt(i) - '0';
            }
            if (!Character.isDigit(s.charAt(i)) && s.charAt(i) != ' ' || i == n - 1) {
                switch (preSign) {
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-num);
                        break;
                    case '*':
                        stack.push(stack.pop() * num);
                        break;
                    default:
                        stack.push(stack.pop() / num);
                }
                preSign = s.charAt(i);
                num = 0;
            }
        }
        int ans = 0;
        while (!stack.isEmpty()) {
            ans += stack.pop();
        }
        return ans;
    }
}
*/
