package leetcode.medium;


/**
 * 给你一个整数 n ，以二进制字符串的形式返回该整数的 负二进制（base -2）表示。
 * 注意，除非字符串就是 "0"，否则返回的字符串中不能含有前导零。
 * 示例 1：
 * 输入：n = 2
 * 输出："110"
 * 解释：(-2)2 + (-2)1 = 2
 * 示例 2：
 * 输入：n = 3
 * 输出："111"
 * 解释：(-2)2 + (-2)1 + (-2)0 = 3
 * 示例 3：
 * 输入：n = 4
 * 输出："100"
 * 解释：(-2)2 = 4
 */
public class Solution_1017 {
    public String baseNeg2(int n) {
        if (n == 0 || n == 1) {
            return String.valueOf(n);
        }
        StringBuilder res = new StringBuilder();
        while (n != 0) {
            int remainder = n & 1;
            res.append(remainder);
            n -= remainder;
            n /= -2;
        }
        return res.reverse().toString();
    }
}

/*
class Solution {
    public String baseNeg2(int n) {
        StringBuilder ans = new StringBuilder();
        if (n == 0) {
            return ans.append(0).toString();
        }
        int index = 0;
        while (n > 0) {
            if ((index & 1) == 1 && (n & 1) == 1) {
                n += 2;
            }
            ans.append(n & 1);
            index++;
            n >>= 1;
        }
        return ans.reverse().toString();
    }
}
*/

/*
class Solution {
    public String baseNeg2(int n) {
        if (n == 0) return "0";   // 0直接返回
        StringBuilder res = new StringBuilder();
        // 类似十进制转二进制的方法，只是余数如果为负数需要修正
        while (n != 0) {
            int mod = n % (-2);
            n = n / (-2);
            if (mod == -1) {
                // 修正余数为-1->1，对应商也要加1
                n++;
                mod = 1;
            }
            res.append(mod);
        }
        return res.reverse().toString();     // 生成的二进制是逆序的，需要反转
    }
}
*/
