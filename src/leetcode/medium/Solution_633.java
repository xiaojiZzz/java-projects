package leetcode.medium;

import java.util.HashSet;
import java.util.Set;

/**
 * 平方数之和
 * 给定一个非负整数 c ，你要判断是否存在两个整数 a 和 b，使得 a2 + b2 = c 。
 * 示例 1：
 * 输入：c = 5
 * 输出：true
 * 解释：1 * 1 + 2 * 2 = 5
 * 示例 2：
 * 输入：c = 3
 * 输出：false
 * 提示：
 * 0 <= c <= 231 - 1
 */
public class Solution_633 {
    public boolean judgeSquareSum(int c) {
        Set<Long> set = new HashSet<>();
        for (long i = 0; i < 1 << 16; i++) {
            if (2 * i * i == c) {
                return true;
            }
            if (set.contains(c - i * i)) {
                return true;
            }
            set.add(i * i);
        }
        return false;
    }
}

/*
class Solution {
    public boolean judgeSquareSum(int c) {
        for (int a = 0; a * a <= c / 2; a++) {
            int b = (int) Math.sqrt(c - a * a);
            if (a * a + b * b == c) {
                return true;
            }
        }
        return false;
    }
}
*/

/*
class Solution {
    public boolean judgeSquareSum(int c) {
        int a = 0;
        int b = (int) Math.sqrt(c);
        while (a <= b) {
            if (a * a == c - b * b) { // 避免溢出
                return true;
            }
            if (a * a < c - b * b) {
                a++;
            } else {
                b--;
            }
        }
        return false;
    }
}
*/