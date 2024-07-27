package leetcode.simple;


/**
 * 给定一个非负整数 num，反复将各个位上的数字相加，直到结果为一位数。返回这个结果。
 * 示例 1:
 * 输入: num = 38
 * 输出: 2
 * 解释: 各位相加的过程为：
 * 38 --> 3 + 8 --> 11
 * 11 --> 1 + 1 --> 2
 * 由于 2 是一位数，所以返回 2。
 */
public class Solution_258 {
    public int addDigits(int num) {
        if (String.valueOf(num).length() == 1) {
            return num;
        }
        while (String.valueOf(num).length() != 1) {
            int a = num;
            int sum = 0;
            while (a > 0) {
                sum += a % 10;
                a /= 10;
            }
            num = sum;
        }
        return num;
    }
}

/*
class Solution {
    public int addDigits(int num) {
        return (num - 1) % 9 + 1;
    }
}
*/
