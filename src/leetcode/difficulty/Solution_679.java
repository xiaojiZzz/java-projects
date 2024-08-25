package leetcode.difficulty;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个长度为4的整数数组 cards 。你有 4 张卡片，每张卡片上都包含一个范围在 [1,9] 的数字。
 * 您应该使用运算符 ['+', '-', '*', '/'] 和括号 '(' 和 ')' 将这些卡片上的数字排列成数学表达式，以获得值24。
 * 你须遵守以下规则:
 * 除法运算符 '/' 表示实数除法，而不是整数除法。
 * 例如， 4 /(1 - 2 / 3)= 4 /(1 / 3)= 12 。
 * 每个运算都在两个数字之间。特别是，不能使用 “-” 作为一元运算符。
 * 例如，如果 cards =[1,1,1,1] ，则表达式 “-1 -1 -1 -1” 是 不允许 的。
 * 你不能把数字串在一起
 * 例如，如果 cards =[1,2,1,2] ，则表达式 “12 + 12” 无效。
 * 如果可以得到这样的表达式，其计算结果为 24 ，则返回 true ，否则返回 false 。
 * 示例 1:
 * 输入: cards = [4, 1, 8, 7]
 * 输出: true
 * 解释: (8-4) * (7-1) = 24
 * 示例 2:
 * 输入: cards = [1, 2, 1, 2]
 * 输出: false
 * 提示:
 * cards.length == 4
 * 1 <= cards[i] <= 9
 */
public class Solution_679 {

    static final int TARGET = 24;
    static final double EPSILON = 1e-6;
    static final int ADD = 0, MULTIPLY = 1, SUBTRACT = 2;

    public boolean judgePoint24(int[] nums) {
        List<Double> list = new ArrayList<>();
        for (int num : nums) {
            list.add((double) num);
        }
        return solve(list);
    }

    public boolean solve(List<Double> list) {
        if (list.size() == 0) {
            return false;
        }
        if (list.size() == 1) {
            return Math.abs(list.get(0) - TARGET) < EPSILON;
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i != j) {
                    List<Double> list2 = new ArrayList<>();
                    for (int k = 0; k < size; k++) {
                        if (k != i && k != j) {
                            list2.add(list.get(k));
                        }
                    }
                    for (int k = 0; k < 4; k++) {
                        if (k < 2 && i > j) {
                            continue;
                        }
                        if (k == ADD) {
                            list2.add(list.get(i) + list.get(j));
                        } else if (k == MULTIPLY) {
                            list2.add(list.get(i) * list.get(j));
                        } else if (k == SUBTRACT) {
                            list2.add(list.get(i) - list.get(j));
                        } else {
                            if (Math.abs(list.get(j)) < EPSILON) {
                                continue;
                            } else {
                                list2.add(list.get(i) / list.get(j));
                            }
                        }
                        if (solve(list2)) {
                            return true;
                        }
                        list2.remove(list2.size() - 1);
                    }
                }
            }
        }
        return false;
    }
}

/*
class Solution {
    private static final double TARGET = 24;
    private static final double EPSILON = 1e-6;

    public boolean judgePoint24(int[] cards) {
        return helper(new double[]{cards[0], cards[1], cards[2], cards[3]});
    }

    private boolean helper(double[] nums) {
        if (nums.length == 1) return Math.abs(nums[0] - TARGET) < EPSILON;
        // 每次选择两个不同的数进行回溯
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                // 将选择出来的两个数的计算结果和原数组剩下的数加入 next 数组
                double[] next = new double[nums.length - 1];
                for (int k = 0, pos = 0; k < nums.length; k++) if (k != i && k != j) next[pos++] = nums[k];
                for (double num : calculate(nums[i], nums[j])) {
                    next[next.length - 1] = num;
                    if (helper(next)) return true;
                }
            }
        }
        return false;
    }

    private List<Double> calculate(double a, double b) {
        List<Double> list = new ArrayList<>();
        list.add(a + b);
        list.add(a - b);
        list.add(b - a);
        list.add(a * b);
        if (!(Math.abs(b) < EPSILON)) list.add(a / b);
        if (!(Math.abs(a) < EPSILON)) list.add(b / a);
        return list;
    }
}
*/
