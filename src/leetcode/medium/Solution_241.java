package leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 为运算表达式设计优先级
 * 给你一个由数字和运算符组成的字符串 expression ，按不同优先级组合数字和运算符，计算并返回所有可能组合的结果。你可以 按任意顺序 返回答案。
 * 生成的测试用例满足其对应输出值符合 32 位整数范围，不同结果的数量不超过 104 。
 * 示例 1：
 * 输入：expression = "2-1-1"
 * 输出：[0,2]
 * 解释：
 * ((2-1)-1) = 0
 * (2-(1-1)) = 2
 * 示例 2：
 * 输入：expression = "2*3-4*5"
 * 输出：[-34,-14,-10,-10,10]
 * 解释：
 * (2*(3-(4*5))) = -34
 * ((2*3)-(4*5)) = -14
 * ((2*(3-4))*5) = -10
 * (2*((3-4)*5)) = -10
 * (((2*3)-4)*5) = 10
 * 提示：
 * 1 <= expression.length <= 20
 * expression 由数字和算符 '+'、'-' 和 '*' 组成。
 * 输入表达式中的所有整数值在范围 [0, 99]
 * 输入表达式中的所有整数都没有前导 '-' 或 '+' 表示符号。
 */
public class Solution_241 {
    public List<Integer> diffWaysToCompute(String expression) {
        List<Integer> list = new ArrayList<>();
        int len = expression.length();
        int start;
        for (start = 0; start < len; start++) {
            //找到一个运算符
            if (!Character.isDigit(expression.charAt(start))) {
                break;
            }
        }
        if (start == len) {
            list.add(Integer.parseInt(expression));
        }
        for (int i = start; i < len; i++) {
            if (!Character.isDigit(expression.charAt(i))) {
                char op = expression.charAt(i);
                List<Integer> left = diffWaysToCompute(expression.substring(0, i));
                List<Integer> right = diffWaysToCompute(expression.substring(i + 1, len));
                for (int j : left) {
                    for (int k : right) {
                        if (op == '+') {
                            list.add(j + k);
                        } else if (op == '-') {
                            list.add(j - k);
                        } else if (op == '*') {
                            list.add(j * k);
                        }
                    }
                }
            }
        }
        return list;
    }
}
